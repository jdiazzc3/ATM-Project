package com.co.diaz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    // Selector
    private By accountName = By.cssSelector("a[title='My Account']");
    private By loginOption = By.linkText("Login");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Método para validar que el usuario ha iniciado sesión
    public boolean isUserLoggedIn() {
        driver.findElement(accountName).click();
        if (driver.findElement(loginOption).isDisplayed()) {
            return false;
        } else {
            return true;
    }
    }

    public void diselectAccount() {
        driver.findElement(accountName).click();
    }
}
