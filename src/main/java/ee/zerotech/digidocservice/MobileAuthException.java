package ee.zerotech.digidocservice;

import lombok.Getter;

/**
 * Exception to push clients to handle exceptional situations.
 */
@Getter
public class MobileAuthException extends Exception {

    private String errorCode;
    private String errorMessage;

    public MobileAuthException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
