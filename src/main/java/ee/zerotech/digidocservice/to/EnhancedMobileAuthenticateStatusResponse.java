package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class EnhancedMobileAuthenticateStatusResponse {
	
	public enum Status {
		OUTSTANDING_TRANSACTION, 
		USER_AUTHENTICATED, 
		NOT_VALID, 
		EXPIRED_TRANSACTION, 
		USER_CANCEL, 
		MID_NOT_READY,
		PHONE_ABSENT,
		SENDING_ERROR,
		SIM_ERROR,
		INTERNAL_ERROR
	}
	
	private MobileAuthenticateStatusResponse mobileAuthenticateStatusResponse;
	private Status status;
}
