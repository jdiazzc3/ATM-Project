package com.co.diaz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;

    // Selectores
    public By myAccountMenu = By.xpath("//a[@title='My Account']");
    public By searchBox = By.cssSelector("#search > input");
    private By searchButton = By.cssSelector("#search > span > button");
    public By addToCartMac = By.xpath("//*[@id='content']/div[3]/div[1]/div/div[2]/div[2]/button[1]");
    public By addToCartIphone = By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div[2]/button[1]");
    private By viewCartButton = By.cssSelector("#cart > button");
    public By checkoutButton = By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[2]/strong");
    private By continueButton1 = By.xpath("//*[@id=\"button-payment-address\"]");
    private By continueButton2 = By.xpath("//*[@id=\"button-shipping-address\"]");
    private By continueButton3 = By.xpath("//*[@id=\"button-shipping-method\"]");
    private By checkbox = By.xpath("//*[@id=\"collapse-payment-method\"]/div/div[3]/div/input[1]");
    private By paymentButton = By.xpath("//*[@id=\"button-payment-method\"]");
    private By confirmOrderButton = By.xpath("//*[@id=\"button-confirm\"]");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Métodos para acciones en el flujo
    public void searchProduct(String productName) {
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void addToCart(By addToCartButton, int quantity) {
        for (int i = 0; i < quantity; i++) {
            driver.findElement(addToCartButton).click();
        }
    }

    public void viewCart() {
        driver.findElement(viewCartButton).click();
    }

    public void checkout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

        // Desplazarse al elemento
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);

        // Intentar hacer clic usando JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
    }

    public void completeCheckout() {
       //desplazarse al elemento
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(continueButton1));
        driver.findElement(continueButton1).click();
       //desplazarse al elemento
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(continueButton2));
        driver.findElement(continueButton2).click();
       //desplazarse al elemento
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(continueButton3));
        driver.findElement(continueButton3).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkboxElement = wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkboxElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxElement);

        //desplazarse al elemento
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(paymentButton));
        driver.findElement(paymentButton).click();

        driver.findElement(confirmOrderButton).click();
    }

    public void clearSearchBox() {
        driver.findElement(searchBox).clear();
    }

    public boolean isProductInCart(String productName) {
        return driver.getPageSource().contains(productName);
    }
}
