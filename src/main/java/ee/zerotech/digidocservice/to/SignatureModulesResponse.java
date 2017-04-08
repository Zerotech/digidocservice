package ee.zerotech.digidocservice.to;

import ee.zerotech.digidocservice.gen.SignatureModulesArray;
import lombok.Data;

@Data
public class SignatureModulesResponse {
    private String status;
    private SignatureModulesArray modules;
}
