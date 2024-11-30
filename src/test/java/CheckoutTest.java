import com.co.diaz.pages.HomePage;
import com.co.diaz.pages.LoginPage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.co.diaz.pages.CheckoutPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class CheckoutTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        // Configuración de espera implícita
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opencart.abstracta.us/index.php?route=common/home");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        try (FileInputStream fis = new FileInputStream("src/test/resources/testdata.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Leer datos del archivo Excel
            Row macRow = sheet.getRow(1); // Primera fila
            String macProduct = macRow.getCell(0).getStringCellValue();
            int macQuantity = getNumericCellValue(macRow.getCell(1));
            assert macProduct != null && !macProduct.isEmpty() : "El producto MAC está vacío o nulo.";
            assert macQuantity > 0 : "La cantidad de productos MAC es menor o igual a cero.";

            Row iphoneRow = sheet.getRow(2); // Segunda fila
            String iphoneProduct = iphoneRow.getCell(0).getStringCellValue();
            int iphoneQuantity = getNumericCellValue(iphoneRow.getCell(1));
            assert iphoneProduct != null && !iphoneProduct.isEmpty() : "El producto iPhone está vacío o nulo.";
            assert iphoneQuantity > 0 : "La cantidad de productos iPhone es menor o igual a cero.";

            // Verifica que haya un usuario logueado
            if (homePage.isUserLoggedIn()) {

                homePage.diselectAccount();
                System.out.println("El usuario está logueado.");
                assert !homePage.isUserLoggedIn() : "El usuario no se ha deslogueado correctamente.";

                // Flujo de búsqueda y compra
                checkoutPage.searchProduct(macProduct);
                checkoutPage.addToCart(checkoutPage.addToCartMac, macQuantity);

                // Limpiar el cuadro de búsqueda
                checkoutPage.clearSearchBox();

                // Espera explícita
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.attributeToBe(checkoutPage.searchBox, "value", ""));

                // Búsqueda y compra
                checkoutPage.searchProduct(iphoneProduct);
                checkoutPage.addToCart(checkoutPage.addToCartIphone, iphoneQuantity);

                checkoutPage.viewCart();

                // Espera explícita de 5 segundos
                Thread.sleep(5000);

                checkoutPage.checkout();

                Thread.sleep(5000);

                checkoutPage.completeCheckout();
            } else {
                System.out.println("El usuario no está logueado.");
                homePage.diselectAccount();
                // Flujo de login, búsqueda y compra
                loginPage.openLoginPage();

                String email = "john.doeefgjkgdh@test.com";
                String password = "password123";

                loginPage.fillLoginForm(email, password);
                loginPage.clickLoginButton();


                // Flujo de búsqueda y compra
                checkoutPage.searchProduct(macProduct);
                checkoutPage.addToCart(checkoutPage.addToCartMac, macQuantity);

                // Limpiar el cuadro de búsqueda
                checkoutPage.clearSearchBox();

                // Espera explícita
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.attributeToBe(checkoutPage.searchBox, "value", ""));

                checkoutPage.searchProduct(iphoneProduct);
                checkoutPage.addToCart(checkoutPage.addToCartIphone, iphoneQuantity);

                checkoutPage.viewCart();

                // Espera explícita de 5 segundos
                Thread.sleep(5000);

                checkoutPage.checkout();

                Thread.sleep(5000);

                checkoutPage.completeCheckout();

            }



            System.out.println("Flujo completado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }

    private static int getNumericCellValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            return Integer.parseInt(cell.getStringCellValue());
        } else {
            throw new IllegalStateException("Cannot get a NUMERIC value from a non-numeric cell");
        }
    }
}