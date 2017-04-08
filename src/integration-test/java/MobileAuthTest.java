import ee.zerotech.digidocservice.MobileAuthException;
import ee.zerotech.digidocservice.MobileAuthService;
import ee.zerotech.digidocservice.to.MobileAuthenticateResponse;
import ee.zerotech.digidocservice.to.MobileAuthenticateStatusResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MobileAuthTest {

    @BeforeClass
    public static void setupTruststore() {
        System.setProperty("javax.net.ssl.trustStoreType","PKCS12");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");
        System.setProperty("javax.net.ssl.trustStore","src/integration-test/conf/sktruststore.p12");
    }

    /**
     * Test mobile numbers http://www.id.ee/?id=36373
     */
    @Test
    public void testMobileAuthentication() throws Exception {
        MobileAuthService mobileAuthService = new MobileAuthService("https://tsp.demo.sk.ee/");
        MobileAuthenticateResponse response = mobileAuthService.start("37200000766","EST");
        MobileAuthenticateStatusResponse status = mobileAuthService.waitWithPolling(response.getSesscode());
        Assert.assertEquals("USER_AUTHENTICATED", status.getStatus());
    }

    /**
     * Scenario from http://www.id.ee/?id=36373
     */
    @Test
    public void testMobileAuthenticationWhenMobileIDisNotActivated() {
        MobileAuthService mobileAuthService = new MobileAuthService("https://tsp.demo.sk.ee/");

        try {
            mobileAuthService.start("37200001", "EST");
            Assert.fail("shouldn't reach here!");
        } catch (MobileAuthException e) {
            Assert.assertEquals("303", e.getErrorCode());
        }
    }

}
