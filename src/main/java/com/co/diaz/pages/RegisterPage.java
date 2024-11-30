package com.co.diaz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;

    // Localizadores de elementos
    private By myAccountDropdown = By.cssSelector("a[title='My Account']");
    private By registerOption = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a");
    private By firstNameField = By.id("input-firstname");
    private By lastNameField = By.id("input-lastname");
    private By emailField = By.id("input-email");
    private By telephoneField = By.id("input-telephone");
    private By passwordField = By.id("input-password");
    private By confirmPasswordField = By.id("input-confirm");
    private By privacyPolicyCheckbox = By.name("agree");
    private By continueButton = By.cssSelector("input[value='Continue']");
    private By finalContinueButton = By.cssSelector("#content > div > div > a");

    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Métodos de la página
    public void openRegisterPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(myAccountDropdown).click();
        wait.until(webDriver -> driver.findElement(registerOption).isDisplayed());
        driver.findElement(registerOption).click();
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String telephone, String password) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(telephoneField).sendKeys(telephone);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
        driver.findElement(privacyPolicyCheckbox).click();
        driver.findElement(continueButton).click();
    }

    public void clickFinalContinueButton() {
        driver.findElement(finalContinueButton).click();
    }
}
