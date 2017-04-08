package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class MobileCertificateResponse {
    private String authCertStatus;
    private String signCertStatus;
    private String authCertData;
    private String signCertData;
}
