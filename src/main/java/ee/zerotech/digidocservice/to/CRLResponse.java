package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class CRLResponse {
    private String status;
    private String crlData;
}
