package home_page;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.OrderPage;

@RunWith(Parameterized.class)
public class OrderTest {

    private final String NAME;
    private final String LASTNAME;
    private final String ADDRESS;
    private final String METRO;
    private final String PHONE;
    private final String DATE;
    private final String DURATION;
    private final String COLOR;
    private final String COMMENT;

    public OrderTest(String name,
                     String lastname,
                     String address,
                     String metro,
                     String phone,
                     String date,
                     String duration,
                     String color,
                     String comment) {
        this.NAME = name;
        this.LASTNAME = lastname;
        this.ADDRESS = address;
        this.METRO = metro;
        this.PHONE = phone;
        this.DATE = date;
        this.DURATION = duration;
        this.COLOR = color;
        this.COMMENT = comment;
    }

    @Parameterized.Parameters
    public static Object[][] orderParams() {
        return new Object[][]{
                {
                    "Иван",
                    "Иванов",
                    "Москва, ул. Ленина",
                    "Черкизовская",
                    "89111234567",
                    "12.07.2023",
                    "двое суток",
                    "серая безысходность",
                    "no comments"
                },
        };
    }

    WebDriver webdriver;

    @Before
    public void startUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webdriver = new ChromeDriver(options);
        webdriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void Order() {
        OrderPage orderPage = new OrderPage(webdriver);
        orderPage.acceptCookies();
        orderPage.clickCreateOrder();
        orderPage.fillOrderClientData(NAME, LASTNAME, ADDRESS, METRO, PHONE);
        orderPage.clickNextButton();
        orderPage.fillOrderRentData(DATE,DURATION, COLOR, COMMENT);
        orderPage.clickOrderButton();

        orderPage.assertThatOrderCreatedSuccess();
    }

    @After
    public void teardown() {
        webdriver.quit();
    }
}
