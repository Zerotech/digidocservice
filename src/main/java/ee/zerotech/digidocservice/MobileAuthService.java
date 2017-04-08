package ee.zerotech.digidocservice;

import ee.zerotech.digidocservice.to.MobileAuthenticateResponse;
import ee.zerotech.digidocservice.to.MobileAuthenticateStatusResponse;
import lombok.Setter;

import javax.xml.ws.soap.SOAPFaultException;
import java.util.Random;

@Setter
public class MobileAuthService {

    private final DigidocService digidocService;
    private String serviceName = "Testimine";
    private boolean returnCertData = false;
    private boolean returnRevocationData = false;
    private boolean waitSignature = true;
    private int errorsCountToHandle = 3;
    private boolean printStackTraces = false;
    private int pollingCount = 100;
    private int sleepBetweenPollingMs = 2000;

    public MobileAuthService(String endpoint) {
        this.digidocService = new DigidocServiceImpl(endpoint);
    }


    public MobileAuthenticateResponse start(String phoneNo, String language) throws MobileAuthException {
        return start(phoneNo, null, language);
    }


    public MobileAuthenticateResponse start(String phoneNo, String idcode, String language) throws MobileAuthException {
        try {
            MobileAuthenticateResponse mobileAuthenticateResponse = digidocService.mobileAuthenticate(
                    (idcode == null) ? "" : idcode,
                    (idcode == null) ? "" : "EE",
                    phoneNo,
                    language,
                    this.serviceName,
                    "",
                    generateSPChallenge(),
                    "asynchClientServer",
                    0,
                    this.returnCertData,
                    this.returnRevocationData
            );

            return mobileAuthenticateResponse;
        } catch (SOAPFaultException e) {
            if (this.printStackTraces) {
                e.printStackTrace();
            }

            String errorCode = e.getFault().getFaultString();
            String errorMessage = e.getFault().getDetail().getValue();
            throw new MobileAuthException(errorCode, errorMessage);
        }
    }


    /**
     * be aware that with waitSignature = true SK timeouts before user timeouts.
     */
    public MobileAuthenticateStatusResponse getStatus(int sessionId) {
        return digidocService.getMobileAuthenticateStatus(sessionId, waitSignature);
    }

    /**
     * Use this method to wait for signature and handle service soap errors, SK timeouts before user timeouts.
     */
    public MobileAuthenticateStatusResponse waitWithErrorHandling(int sessionId) {
        for (int i = 0; i < errorsCountToHandle; i++ ) {
            try {
                return getStatus(sessionId);
            } catch(SOAPFaultException e) {
                if (this.printStackTraces) {
                    e.printStackTrace();
                }
            }
        }

        throw new RuntimeException("Handled max " + errorsCountToHandle + "SOAPFaultExceptions without actual getMobileAuthenticateStatus() result from Digidocservice");
    }

    public MobileAuthenticateStatusResponse waitWithPolling(int sessionId) {
        for (int i = 0; i < this.pollingCount; i++) {
            MobileAuthenticateStatusResponse response = digidocService.getMobileAuthenticateStatus(sessionId, false);
            if ("OUTSTANDING_TRANSACTION".equals(response.getStatus())) {
                try {
                    Thread.sleep(this.sleepBetweenPollingMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return response;
            }
        }

        throw new RuntimeException("Handled max polls to mobileAuthenticateStatus(), increase polling count or sleep time!");
    }


    /**
     * http://sk-eid.github.io/dds-documentation/api/api_docs/#mobileautheticate
     */
    protected String generateSPChallenge() {
        byte[] ba = new byte[10];
        new Random().nextBytes(ba);
        StringBuilder sb = new StringBuilder();

        for (byte b : ba) {
            sb.append(String.format("%02X", b));
        }

        return sb.toString();
    }

}
