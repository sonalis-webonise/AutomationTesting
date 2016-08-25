import org.apache.xpath.operations.String;
import org.junit.Assert;
import org.junit.Test;
import io.ddavison.conductor.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;
//import java.lang.String;
/**
 * Created by webonise on 23/8/16.
 */

public class seleniumTest{

    @Test
    public void testFlightLinkExists () {

        System.setProperty("webdriver.chrome.driver","chromedriver.linux");
        WebDriver driver=new ChromeDriver();

        driver.get("https://www.tripadvisor.in");
        Assert.assertEquals("tripadvisor","tripadvisor");

        driver.findElement(By.xpath("//*[@id='rdoFlights']")).click();

        WebElement toAirport = driver.findElement(By.id("metaFlightTo"));
        toAirport.sendKeys("New Delhi");
        toAirport.sendKeys(Keys.ENTER);

        Random r=new Random();
        int n=r.nextInt(5);


        Calendar c=Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, r.nextInt(5));
        int leaveDate= c.get(Calendar.DAY_OF_MONTH);
        selectDate(driver,leaveDate);

        c.add(Calendar.DAY_OF_MONTH,r.nextInt(10));
        int arrivalDate=c.get(Calendar.DAY_OF_MONTH);
        selectDate(driver,arrivalDate);

        Select dropdown = new Select(driver.findElement(By.id("fadults")));
        dropdown.selectByIndex(n);


        WebElement findFlight=driver.findElement(By.xpath("//*[@id='SUBMIT_FLIGHTS']"));
        findFlight.click();

        WebDriverWait wait=new WebDriverWait(driver,10);
        try {
            WebElement popup = driver.findElement(By.className("ui_close_x"));
            popup.click();
        } catch (NoSuchElementException e) {
        }
        driver.findElement(By.className("price")).getText();
        System.out.print(driver.findElement(By.className("price")).getText());


    }

    private void selectDate(WebDriver driver,int date) {
            WebElement opencalender=driver.findElement(By.xpath("//*[@id='checkIn']"));
            opencalender.click();
            List<WebElement> dates=driver.findElements(By.xpath("//div[@class='month']/table/tbody//tr/td"));
            for(WebElement ele:dates)
            {
                if(ele.getText().equals(java.lang.String.valueOf(date)))
                {
                    ele.click();
                    break;
                }

            }

    }
}
