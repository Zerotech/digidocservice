package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class MobileAuthenticateResponse {
    private Integer sesscode;
    private String status;
    private String userIDCode;
    private String userGivenname;
    private String userSurname;
    private String userCountry;
    private String userCN;
    private String certificateData;
    private String challengeID;
    private String challenge;
    private String revocationData;
    private String errorCode;
    private String errorMessage;
}
