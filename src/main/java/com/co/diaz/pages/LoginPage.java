package com.co.diaz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    // Selectores
    private By myAccountMenu = By.xpath("//a[@title='My Account']");
    private By loginOption = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a");
    private By emailInput = By.id("input-email");
    private By passwordInput = By.id("input-password");
    private By loginButton = By.xpath("//input[@value='Login']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(myAccountMenu).click();
        wait.until(webDriver -> driver.findElement(loginOption).isDisplayed());
        driver.findElement(loginOption).click();
    }

    public void fillLoginForm(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
