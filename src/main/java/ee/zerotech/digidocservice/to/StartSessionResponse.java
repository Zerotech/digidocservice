package ee.zerotech.digidocservice.to;

import ee.zerotech.digidocservice.gen.SignedDocInfo;
import lombok.Data;

@Data
public class StartSessionResponse {
    private String status;
    private int sesscode;
    private SignedDocInfo signedDocInfo;
}
