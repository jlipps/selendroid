package org.openqa.selendroid.nativetests;

import static org.openqa.selendroid.waiter.TestWaiter.waitFor;

import org.openqa.selendroid.tests.internal.BaseAndroidTest;
import org.openqa.selendroid.waiter.WaitingConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NativeElementInteractionTests extends BaseAndroidTest {
  @Test
  public void testShouldBeAbleToGetTextOfElement() {
    openStartActivity();
    WebElement button = driver.findElement(By.id("waitingButtonTest"));
    Assert.assertEquals(button.getText(), "Show Progress Bar for a while");
  }

  @Test()
  public void testShouldBeAbleToClickOnElement() {
    openStartActivity();
    WebElement button = driver.findElement(By.id("startUserRegistration"));
    button.click();

    waitFor(WaitingConditions.driverUrlToBe(driver, "and-activity://RegisterUserActivity"));
  }

  @Test
  public void testShouldBeAbleToGetAttributeOfTextField() {
    openStartActivity();
    WebElement inputField = driver.findElement(By.id("my_text_field"));
    Assert.assertEquals(inputField.getAttribute("enabled"), "true");
  }

  @Test
  public void testShouldBeAbleToGetAttributeOfButton() {
    openStartActivity();
    WebElement button = driver.findElement(By.id("waitingButtonTest"));
    Assert.assertEquals(button.getAttribute("ContentDescription"), "waitingButtonTestCD");
    Assert.assertEquals(button.getAttribute("text"), "Show Progress Bar for a while");
  }

  @Test
  public void testShouldBeAbleToSendKeysAndClearAnElement() {
    openStartActivity();
    WebElement inputField = driver.findElement(By.id("my_text_field"));
    String text = "a.anyString@not.existent%.1.de";
    inputField.sendKeys(text);
    Assert.assertEquals(inputField.getText(), text);
    inputField.clear();
    Assert.assertEquals(inputField.getAttribute("text"), "");
  }

  @Test
  public void testShouldBeAbleToGetSelectedStateOfElement() {
    openStartActivity();
    WebElement checkBox = driver.findElement(By.id("input_adds_check_box"));
    Assert.assertEquals(checkBox.isSelected(), true);
    checkBox.click();
    Assert.assertEquals(checkBox.isSelected(), false);
  }

  /**
   * Based on the default test emulator used to verify build that has a resolution of 320x480
   * pixels.
   */
  @Test
  public void testShouldBeAbleToGetSizeOfElement() {
    openStartActivity();
    WebElement button = driver.findElement(By.id("waitingButtonTest"));
    Dimension dimension = button.getSize();
    Assert.assertEquals(dimension.height, 48);
    Assert.assertEquals(dimension.width, 210);
  }

  /**
   * Based on the default test emulator used to verify build that has a resolution of 320x480
   * pixels.
   */
  @Test
  public void testShouldBeAbleToGetLocationOfElement() {
    openStartActivity();
    WebElement button = driver.findElement(By.id("waitingButtonTest"));
    Point location = button.getLocation();
    Assert.assertEquals(location.x, 0);
    Assert.assertEquals(location.y, 247);
  }

  @Test
  public void testShouldNotBeAbleToExecuteSimpleJavaScript() {
    openStartActivity();
    try {
      ((JavascriptExecutor) driver).executeScript("return document.title");
      Assert.fail();
    } catch (WebDriverException e) {
      Assert
          .assertTrue(e.getMessage().contains("Executing script is only available in web views."));
    }
  }

  @Test
  public void testShouldBeAbleToGetDisplayedStateOfElement() {
    openStartActivity();
    WebElement button = driver.findElement(By.id("waitingButtonTest"));
    Assert.assertEquals(button.isDisplayed(), true);
  }

  @Test
  public void testShouldBeAbleToGetEnbledStateOfElement() {
    openStartActivity();
    WebElement button = driver.findElement(By.id("waitingButtonTest"));
    Assert.assertEquals(button.isEnabled(), true);
  }

  @Test
  public void testShouldNotBeAbleToSubmitAnElement() {
    openStartActivity();
    WebElement button = driver.findElement(By.id("waitingButtonTest"));
    try {
      button.submit();
      Assert.fail();
    } catch (WebDriverException e) {
      Assert.assertTrue(e.getMessage().contains("Submit is not supported for native elements."));
    }
  }
}
