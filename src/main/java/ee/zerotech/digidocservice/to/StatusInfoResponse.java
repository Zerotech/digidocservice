package ee.zerotech.digidocservice.to;

import ee.zerotech.digidocservice.gen.SignedDocInfo;
import lombok.Data;

@Data
public class StatusInfoResponse {
    private String status;
    private String statusCode;
    private SignedDocInfo signedDocInfo;
}
