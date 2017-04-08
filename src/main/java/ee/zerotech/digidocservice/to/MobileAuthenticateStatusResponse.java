package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class MobileAuthenticateStatusResponse {
	private String status;
	private String signature;
}
