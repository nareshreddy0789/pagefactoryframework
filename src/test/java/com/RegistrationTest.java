package com.naresh.selenium.tests;

import com.naresh.selenium.framework.BaseTest;
import com.naresh.selenium.models.User;
import com.naresh.selenium.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.naresh.selenium.framework.BasePage.initPage;
import static com.naresh.selenium.framework.Utils.isElementPresent;
import static com.naresh.selenium.models.UserBuilder.randomUser;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: naresh
 * Date: 05/10/15
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationTest extends BaseTest {
    private LoginPage loginPage;
    private BasicPage basicPage;
    private RegisterPage registerPage;
    private HeaderPage headerPage;
    private AppsContentPage contentPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        registerPage = initPage(RegisterPage.class);
        headerPage = initPage(HeaderPage.class);
        contentPage = initPage(AppsContentPage.class);
        user = randomUser();
    }

   @Test
    public void registerAndVerifyUserTest() {
       //doesn't work for the Basic Authentication, it always returns admin user from Authentication
        loginPage.registerNewUser();
        registerPage.registerAs(user, RegisterPage.USER);
        assertHeader(user);
    }

   @Test
    public void registerLogoutAndVerifyLoginUserTest() {
        loginPage.registerNewUser();
        registerPage.registerAs(user, RegisterPage.USER);
        loginPage = basicPage.forceLogout();
        loginPage.loginAs(user);
        assertHeader(user);
    }

    @Test
    public void registerDevAndVerifyUploadPageTest() {
        loginPage.registerNewUser();
        headerPage = registerPage.registerAs(user, RegisterPage.DEVELOPER).getHeader();
        assertThat(headerPage.isAbleToUploadApplication()).isTrue();
    }

    @Test
    public void registerUserAndVerifySeeAplicationTest() {
        loginPage.registerNewUser();
        registerPage.registerAs(user, RegisterPage.USER);
        assertThat(contentPage.isAbleToSeeApplication()).isTrue();
        assertThat(headerPage.isAbleToUploadApplication()).isFalse();
    }

    private void assertHeader(User user){
        assertThat(headerPage.getWelcomeMessage()).isEqualTo("Welcome " + user.getFname() + " " + user.getLname());
    }
}

