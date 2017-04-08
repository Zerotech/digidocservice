package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class CertificateResponse {
    private String status;
    private String certificateData;
}
