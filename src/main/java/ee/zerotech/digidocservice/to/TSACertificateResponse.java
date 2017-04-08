package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class TSACertificateResponse {
    private String status;
    private String certificateData;
}
