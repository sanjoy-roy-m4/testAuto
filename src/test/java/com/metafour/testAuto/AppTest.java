package com.metafour.testAuto;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://192.168.0.175/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void test01() throws Exception {
    driver.get("http://ncpg-dev1.metafour.lan/online/");
    assertTrue(isElementPresent(By.xpath("//form[@id='mainform']//h2[contains(text(),'Login')]")));
    assertTrue(isElementPresent(By.id("access_code")));
    assertTrue(isElementPresent(By.id("login")));
    driver.findElement(By.id("access_code")).clear();
    driver.findElement(By.id("access_code")).sendKeys("TESTCL01");
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("testus01");
    driver.findElement(By.name("j_password")).clear();
    driver.findElement(By.name("j_password")).sendKeys("testus01");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    assertEquals("Online booking", driver.getTitle());
    assertTrue(isElementPresent(By.xpath("//section[@id='booking']")));
    assertTrue(isElementPresent(By.xpath("//section[@id='addresses']")));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

//  private boolean isAlertPresent() {
//    try {
//      driver.switchTo().alert();
//      return true;
//    } catch (NoAlertPresentException e) {
//      return false;
//    }
//  }

//  private String closeAlertAndGetItsText() {
//    try {
//      Alert alert = driver.switchTo().alert();
//      String alertText = alert.getText();
//      if (acceptNextAlert) {
//        alert.accept();
//      } else {
//        alert.dismiss();
//      }
//      return alertText;
//    } finally {
//      acceptNextAlert = true;
//    }
//  }
}
