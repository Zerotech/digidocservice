package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class MobileCreateSignatureResponse {
    private String status;
    private String challengeId;
    private int sesscode;
}
