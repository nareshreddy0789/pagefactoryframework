package com.sayem.selenium.pages;

import com.sayem.selenium.framework.BasePage;
import com.sayem.selenium.framework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA.
 * User: ssayem
 * Date: 05/10/13
 * Time: 18:43
 * To change this template use File | Settings | File Templates.
 */
public class AjaxTestPage extends BasePage {
    @FindBy(xpath = X_TEXT_FIELD_XPATH)
    WebElement xTextField;
    @FindBy(xpath = Y_TEXT_FIELD_XPATH)
    WebElement yTextField;
    @FindBy(xpath = CALC_BUTTON_XPATH)
    WebElement calcButton;

    public float calculate(float x, float y) {
        xTextField.sendKeys(String.valueOf(x));
        yTextField.sendKeys(String.valueOf(y));
        calcButton.click();
        Utils.waitForElementPresent(RESULT_TEXT_XPATH);
        return Float.valueOf(driver.findElement(By.xpath(RESULT_TEXT_XPATH)).getText().substring("Result is: ".length()));
    }

    public Boolean isIncorrextStringCalc(String x, String y) {
        xTextField.sendKeys(x);
        yTextField.sendKeys(y);
        calcButton.click();
        Utils.waitForElementPresent(RESULT_TEXT_XPATH);
        return driver.findElement(By.xpath(RESULT_TEXT_XPATH)).getText().contains("Incorrect data");
    }

    public static final String CALC_BUTTON_XPATH = "//button[@id='calc']";
    public static final String Y_TEXT_FIELD_XPATH = "//input[@id='y']";
    public static final String X_TEXT_FIELD_XPATH = "//input[@id='x']";
    public static final String RESULT_TEXT_XPATH = "//div[@id='result']";
}
