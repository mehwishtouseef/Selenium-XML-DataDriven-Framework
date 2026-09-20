package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.XMLReader;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginXmlData")
    public Object[][] getLoginData() {
        String filePath = "src/test/resources/testdata.xml";
        return XMLReader.getTestData(filePath);
    }

    @Test(dataProvider = "loginXmlData")
    public void testLoginWithXMLData(String username, String password, String expected) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (expected.contains(".html")) {
            Assert.assertTrue(driver.getCurrentUrl().contains(expected), "URL did not match expected inventory page.");
        } else {
            String error = loginPage.getErrorMessage();
            Assert.assertTrue(error.contains(expected), "Error message did not match.");
        }
    }
}