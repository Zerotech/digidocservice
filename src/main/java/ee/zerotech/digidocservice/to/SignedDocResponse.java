package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class SignedDocResponse {
    private String status;
    private String signedDocData;
}
