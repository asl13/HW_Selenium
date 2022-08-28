package Homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DemoQAPracticeFormCheck {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        String browser = System.getProperty("browser");
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("opera")) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void finish() {
        driver.quit();
    }

    @Test
    public void fillInPracticeForm() {

        WebElement inputFName = driver.findElement(By.cssSelector("#firstName"));
        inputFName.sendKeys("Ivan");

        WebElement inputLName = driver.findElement(By.cssSelector("#lastName"));
        inputLName.sendKeys("Ivanov");

        WebElement inputEmail = driver.findElement(By.cssSelector("#userEmail"));
        inputEmail.sendKeys("ivanov@ru.ru");

        WebElement inputGender = driver.findElement(By.cssSelector("label[for='gender-radio-1']"));
        inputGender.click();

        WebElement inputMobile = driver.findElement(By.cssSelector("#userNumber"));
        inputMobile.sendKeys("0123456789");

        WebElement inputDateOfBirth = driver.findElement(By.cssSelector("#dateOfBirthInput"));
        inputDateOfBirth.sendKeys(Keys.chord(Keys.CONTROL, "a"), "01 Nov 2000", Keys.ENTER);

        WebElement inputSubject = driver.findElement(By.cssSelector("#subjectsInput"));
        inputSubject.sendKeys("Arts");

        WebElement submitSubject = driver.findElement(By.cssSelector("#react-select-2-option-0"));
        submitSubject.click();

        WebElement inputHobbiesSport = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1'"));
        inputHobbiesSport.click();
        WebElement inputHobbiesMusic = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-3'"));
        inputHobbiesMusic.click();

        WebElement inputAddress = driver.findElement(By.cssSelector("#currentAddress"));
        inputAddress.sendKeys("Novosibirsk");

        WebElement uploadPicture = driver.findElement(By.cssSelector("#uploadPicture"));
        uploadPicture.sendKeys(System.getProperty("user.dir")+"\\src\\img\\test.png");

        WebElement inputState = driver.findElement(By.cssSelector("#react-select-3-input"));
        inputState.sendKeys("NCR");

        WebElement submitState = driver.findElement(By.cssSelector("#react-select-3-option-0"));
        submitState.click();

        WebElement inputCity = driver.findElement(By.cssSelector("#react-select-4-input"));
        inputCity.sendKeys("Delhi");

        WebElement sumbitCity = driver.findElement(By.cssSelector("#react-select-4-option-0"));
        sumbitCity.click();
        inputCity.submit();

        WebElement studentName = driver.findElement(By.xpath("//td[text()='Student Name']/../td[2]"));
        Assertions.assertEquals("Ivan Ivanov", studentName.getText());

        WebElement studentEmail = driver.findElement(By.xpath("//td[text()='Student Email']/../td[2]"));
        Assertions.assertEquals("ivanov@ru.ru", studentEmail.getText());

        WebElement gender = driver.findElement(By.xpath("//td[text()='Gender']/../td[2]"));
        Assertions.assertEquals("Male", gender.getText());

        WebElement mobile = driver.findElement(By.xpath("//td[text()='Mobile']/../td[2]"));
        Assertions.assertEquals("0123456789", mobile.getText());

        WebElement dateOfBirth = driver.findElement(By.xpath("//td[text()='Date of Birth']/../td[2]"));
        Assertions.assertEquals("01 November,2000", dateOfBirth.getText());

        WebElement subjects = driver.findElement(By.xpath("//td[text()='Subjects']/../td[2]"));
        Assertions.assertEquals("Arts", subjects.getText());

        WebElement hobbies = driver.findElement(By.xpath("//td[text()='Hobbies']/../td[2]"));
        Assertions.assertEquals("Sports, Music", hobbies.getText());

        WebElement picture = driver.findElement(By.xpath("//td[text()='Picture']/../td[2]"));
        Assertions.assertEquals("test.png", picture.getText());

        WebElement address = driver.findElement(By.xpath("//td[text()='Address']/../td[2]"));
        Assertions.assertEquals("Novosibirsk", address.getText());

        WebElement stateAndCity = driver.findElement(By.xpath("//td[text()='State and City']/../td[2]"));
        Assertions.assertEquals("NCR Delhi", stateAndCity.getText());

    }

}
