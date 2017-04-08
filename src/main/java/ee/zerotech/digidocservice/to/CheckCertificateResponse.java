package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class CheckCertificateResponse {
	private String status;
	private String userIDCode;
	private String userGivenname;
	private String userSurname;
	private String userCountry;
	private String userOrganisation;
	private String userCN;
	private String issuerCN;
	private String keyUsage;
	private String enhancedKeyUsage;
	private String revocationData;
}
