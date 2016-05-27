package com.naresh.selenium.tests;

import com.naresh.selenium.framework.BaseTest;
import com.naresh.selenium.models.User;
import com.naresh.selenium.pages.AjaxTestPage;
import com.naresh.selenium.pages.BasicPage;
import com.naresh.selenium.pages.JSTestPage;
import com.naresh.selenium.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.naresh.selenium.framework.BasePage.initPage;
import static com.naresh.selenium.models.UserBuilder.admin;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: naresh
 * Date: 05/10/15
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */
public class JSTest extends BaseTest {
    private LoginPage loginPage;
    private BasicPage basicPage;
    private AjaxTestPage ajaxTestPage;
    private JSTestPage jSTestPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
//      headerPage = initPage(HeaderPage.class);
        user = admin();
    }

    @Test
    public void findPositionTest() {
        jSTestPage = loginPage.loginAs(user).getHeader().clickJSTestPage();
        String s = jSTestPage.find();
        assertThat(s).isEqualTo("Whoo Hoooo! Correct!");
    }
}
