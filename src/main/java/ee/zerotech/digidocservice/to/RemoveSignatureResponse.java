package ee.zerotech.digidocservice.to;

import ee.zerotech.digidocservice.gen.SignedDocInfo;
import lombok.Data;

@Data
public class RemoveSignatureResponse {
    private String status;
    private SignedDocInfo signedDocInfo;
}