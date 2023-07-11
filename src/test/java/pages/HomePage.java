package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class HomePage {

    private WebDriver webdriver;

    public HomePage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    private final By ACCORDION = By.xpath(".//div[@data-accordion-component='Accordion']");
    private final By ACCORDION_ANSWER = By.xpath(".//div[(@data-accordion-component='AccordionItemPanel' and not(@hidden))]");

    public void waitForVisible(By element) {
        new WebDriverWait(webdriver,
                Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void scrollToAccordion() {
        ((JavascriptExecutor)webdriver).executeScript("arguments[0].scrollIntoView();",
                webdriver.findElement(ACCORDION));
    }

    public void clickAccordionItemByText(String text) {
        By element = By.xpath(".//div[@data-accordion-component='AccordionItemButton' and contains(text(),'"+text+"')]");
        waitForVisible(element);
        webdriver.findElement(element).click();
    }

    public void assertAccordionAnswer(String text) {
        waitForVisible(ACCORDION_ANSWER);
        assertEquals(webdriver.findElement(ACCORDION_ANSWER).getText(), text);
    }
}
