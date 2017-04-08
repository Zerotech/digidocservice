package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class PrepareSignatureResponse {
    private String status; 
    private String signatureId;
    private String signedInfoDigest;
}
