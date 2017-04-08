package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class MobileSignResponse {
    private String status;
    private String statusCode;
    private String challengeID;
}
