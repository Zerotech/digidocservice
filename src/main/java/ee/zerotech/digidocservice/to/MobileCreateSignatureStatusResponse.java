package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class MobileCreateSignatureStatusResponse {
    private int sesscode;
    private String status;
    private String signature;
}
