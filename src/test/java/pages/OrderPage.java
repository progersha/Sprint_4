package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class OrderPage {
    private WebDriver webdriver;

    public OrderPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    private final By NAME = By.xpath(".//input[@placeholder='* Имя']");
    private final By LAST_NAME = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By ADDRESS = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By METRO = By.xpath(".//div[@class='select-search']");
    private final By PHONE = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By DATE = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By DURATION = By.xpath(".//div[@class='Dropdown-root']");
    private final By COMMENT = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By NEXT_BUTTON = By.xpath(".//button[contains(text(),'Далее')]");
    private final By ORDER_BUTTON = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private final By COOKIES_BUTTON = By.id("rcc-confirm-button");
    private final By ORDER_CONFIRM_MODAL_HEADER = By.xpath("(.//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?'])");
    private final By ORDER_SUCCESS_MODAL_HEADER = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    public void acceptCookies() {
        webdriver.findElement(COOKIES_BUTTON).click();
    }

    public void clickCreateOrder() {
        int randNum = (int) (Math.random() * 1) + 1;
        webdriver.findElement(By.xpath("(.//button[text()='Заказать'])["+randNum+"]")).click();
    }

    public void setMetroStation(String nameStation) {
        webdriver.findElement(METRO).click();
        By station = By.xpath(".//*[(@class='Order_Text__2broi' and text()='" + nameStation + "')]");
        webdriver.findElement(station).click();
    }

    public void fillOrderClientData(String name, String lastname, String address, String metro, String phone){
        webdriver.findElement(NAME).sendKeys(name);
        webdriver.findElement(LAST_NAME).sendKeys(lastname);
        webdriver.findElement(ADDRESS).sendKeys(address);
        setMetroStation(metro);
        webdriver.findElement(PHONE).sendKeys(phone);
    }

    public void clickNextButton() {
        webdriver.findElement(NEXT_BUTTON).click();
    }

    public void setDuration(String days) {
        webdriver.findElement(DURATION).click();
        By duration = By.xpath(".//div[@class='Dropdown-menu']/div['" + days + "']");
        webdriver.findElement(duration).click();
    }

    public void setColor(String colorName) {
        By color = By.xpath(".//div[@class='Order_Checkboxes__3lWSI']/label['" + colorName + "']/input");
        webdriver.findElement(color).click();
    }

    public void fillOrderRentData(String date, String duration, String color, String comment) {
        webdriver.findElement(DATE).sendKeys(date, Keys.ENTER);
        setDuration(duration);
        setColor(color);
        webdriver.findElement(COMMENT).sendKeys(comment);
    }

    public void clickOrderButton() {
        webdriver.findElement(ORDER_BUTTON).click();
    }

    public void assertThatOrderCreatedSuccess() {
        new WebDriverWait(webdriver,
                Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(ORDER_CONFIRM_MODAL_HEADER));
        String actual = webdriver.findElement(ORDER_SUCCESS_MODAL_HEADER).getText();
        assertTrue(actual.contains("Заказ оформлен"));
    }
}