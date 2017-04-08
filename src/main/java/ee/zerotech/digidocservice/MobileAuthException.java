package ee.zerotech.digidocservice;

import ee.zerotech.digidocservice.to.MobileAuthenticateResponse;

/**
 * Exception to push clients to handle exceptional situations.
 */
public class MobileAuthException extends Exception {

    private MobileAuthenticateResponse response;

    public MobileAuthException(MobileAuthenticateResponse response) {
        this.response = response;
    }

    public MobileAuthenticateResponse getResponse() {
        return this.response;
    }
}
