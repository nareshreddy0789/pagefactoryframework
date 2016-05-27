package com.naresh.selenium.pages;

/**
 * Created with IntelliJ IDEA.
 * User: naresh
 * Date: 05/10/15
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */
public class MyApplicationsPage extends BasicPage{
    public AppsContentPage getAppsContent() {
        return initPage(AppsContentPage.class);
    }
}
