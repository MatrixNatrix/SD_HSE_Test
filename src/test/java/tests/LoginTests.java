package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.BaseTest;

public class LoginTests extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] provideLoginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", true, ""},
                {"", "secret_sauce", false, "Epic sadface: Username is required"},
                {"standard_user", "", false, "Epic sadface: Password is required"},
                {"locked_out_user", "secret_sauce", false, "Epic sadface: Sorry, this user has been locked out."},
                {"invalid_user", "wrong_pass", false, "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLoginScenarios(String username,
                                   String password,
                                   boolean shouldLoginSuccess,
                                   String expectedErrorMessage) {
        loginPage.open();
        loginPage.login(username, password);

        if (shouldLoginSuccess) {
            Assert.assertTrue(loginPage.isOnInventoryPage(), "Login failed for valid credentials");
        } else {
            Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "Error message mismatch");
        }
    }
}