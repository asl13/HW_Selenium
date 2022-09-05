package Homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
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

        SoftAssertions softAssert = new SoftAssertions();

        String fName = "Ivan";
        String lName = "Ivanov";
        String email = "ivanov@ru.ru";
        String gender = "Male";
        String mobileNum = "0123456789";
        String birthDate = "01 November,2000";
        String subject = "Arts";
        String hobby1 = "Sports";
        String hobby2 = "Music";
        String testPic = "test.png";
        String homeAddress = "Novosibirsk";
        String state = "NCR";
        String city = "Delhi";

        WebElement inputFName = driver.findElement(By.cssSelector("#firstName"));
        inputFName.sendKeys(fName);

        WebElement inputLName = driver.findElement(By.cssSelector("#lastName"));
        inputLName.sendKeys(lName);

        WebElement inputEmail = driver.findElement(By.cssSelector("#userEmail"));
        inputEmail.sendKeys(email);

        WebElement inputGender = driver.findElement(By.xpath("//label[text()='" + gender + "']"));
        inputGender.click();

        WebElement inputMobile = driver.findElement(By.cssSelector("#userNumber"));
        inputMobile.sendKeys(mobileNum);

        WebElement inputDateOfBirth = driver.findElement(By.cssSelector("#dateOfBirthInput"));
        inputDateOfBirth.sendKeys(Keys.chord(Keys.CONTROL, "a"), birthDate, Keys.ENTER);

        WebElement inputSubject = driver.findElement(By.cssSelector("#subjectsInput"));
        inputSubject.sendKeys(subject);

        WebElement submitSubject = driver.findElement(By.cssSelector("#react-select-2-option-0"));
        submitSubject.click();

        WebElement inputHobbiesSport = driver.findElement(By.xpath("//label[text()='" + hobby1 + "']"));
        inputHobbiesSport.click();
        WebElement inputHobbiesMusic = driver.findElement(By.xpath("//label[text()='" + hobby2 + "']"));
        inputHobbiesMusic.click();

        WebElement inputAddress = driver.findElement(By.cssSelector("#currentAddress"));
        inputAddress.sendKeys(homeAddress);

        WebElement uploadPicture = driver.findElement(By.cssSelector("#uploadPicture"));
        uploadPicture.sendKeys(System.getProperty("user.dir") + "\\src\\img\\" + testPic);

        WebElement inputState = driver.findElement(By.cssSelector("#react-select-3-input"));
        inputState.sendKeys(state);

        WebElement submitState = driver.findElement(By.cssSelector("#react-select-3-option-0"));
        submitState.click();

        WebElement inputCity = driver.findElement(By.cssSelector("#react-select-4-input"));
        inputCity.sendKeys(city);

        WebElement sumbitCity = driver.findElement(By.cssSelector("#react-select-4-option-0"));
        sumbitCity.click();
        inputCity.submit();

        softAssert.assertThat(driver.findElement(By.xpath("//td[text()='Student Name']/../td[2]")).getText()).isEqualTo(fName + " " + lName);
        softAssert.assertThat(driver.findElement(By.xpath("//td[text()='Student Email']/../td[2]")).getText()).isEqualTo(email);
        softAssert.assertThat(driver.findElement(By.xpath("//td[text()='Gender']/../td[2]")).getText()).isEqualTo(gender);
        softAssert.assertThat(driver.findElement(By.xpath("//td[text()='Mobile']/../td[2]")).getText()).isEqualTo(mobileNum);
        softAssert.assertThat(driver.findElement(By.xpath("//td[text()='Date of Birth']/../td[2]")).getText()).isEqualTo(birthDate);
        softAssert.assertThat(driver.findElement(By.xpath("//td[text()='Subjects']/../td[2]")).getText()).isEqualTo(subject);
        softAssert.assertThat(driver.findElement(By.xpath("//td[text()='Hobbies']/../td[2]")).getText()).isEqualTo(hobby1 + ", " + hobby2);
        softAssert.assertThat(driver.findElement(By.xpath("//td[text()='Picture']/../td[2]")).getText()).isEqualTo(testPic);
        softAssert.assertThat(driver.findElement(By.xpath("//td[text()='Address']/../td[2]")).getText()).isEqualTo(homeAddress);
        softAssert.assertThat(driver.findElement(By.xpath("//td[text()='State and City']/../td[2]")).getText()).isEqualTo(state + " " + city);

        softAssert.assertAll();
    }

}