package com.naresh.selenium.tests;

import com.naresh.selenium.framework.BaseTest;
import com.naresh.selenium.models.User;
import com.naresh.selenium.pages.BasicPage;
import com.naresh.selenium.pages.HeaderPage;
import com.naresh.selenium.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.naresh.selenium.framework.Utils.isElementPresent;
import static com.naresh.selenium.models.UserBuilder.admin;
import static org.fest.assertions.Assertions.assertThat;
import static com.naresh.selenium.framework.BasePage.initPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private BasicPage basicPage;
    private HeaderPage headerPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        headerPage = initPage(HeaderPage.class);
        user = admin();
    }

    @Test
    public void correctLoginTest() {
        loginPage.loginAs(user);
        assertHeader(user);
    }

    @Test
    public void incorrectLoginTest() {
        user.setPassword(user.getPassword() + user.getPassword());
        loginPage.loginAs(user);
        assertThat(isElementPresent(basicPage.flash)).isTrue();
        assertThat(basicPage.getFlashMessage()).isEqualTo("You have entered an invalid username or password!");
    }

    @Test
    public void incorrectThenCorrectTest() {
        user.setPassword(user.getPassword() + user.getPassword());
        loginPage.loginAs(user);
        user.setPassword(admin().getPassword());
        loginPage.loginAs(user);
        assertHeader(user);
    }

    private void assertHeader(User user){
        assertThat(headerPage.getWelcomeMessage()).isEqualTo("Welcome " + user.getFname() + " " + user.getLname());
    }
}
