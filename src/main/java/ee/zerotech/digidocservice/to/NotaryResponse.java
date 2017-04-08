package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class NotaryResponse {
    private String status;
    private String ocspData;
}
