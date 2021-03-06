package org.openqa.selendroid.nativetests;

import static org.openqa.selendroid.waiter.TestWaiter.waitFor;

import org.openqa.selendroid.SelendroidKeys;
import org.openqa.selendroid.tests.internal.BaseAndroidTest;
import org.openqa.selendroid.waiter.WaitingConditions;
import org.openqa.selenium.HasInputDevices;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SendKeyAndNativeKeyTests extends BaseAndroidTest {
  public static final String ACTIVITY_CLASS = "org.openqa.selendroid.testapp."
      + "HomeScreenActivity";

  protected void precondition() {
    driver.switchTo().window(NATIVE_APP);
    driver.get("and-activity://" + ACTIVITY_CLASS);
    waitFor(WaitingConditions.driverUrlToBe(driver, "and-activity://HomeScreenActivity"));
  }

  @Test
  public void nativeSearchCanBeTriggered() throws Exception {
    precondition();

    ((HasInputDevices) driver).getKeyboard().sendKeys(SelendroidKeys.SEARCH);
    

    ((HasInputDevices) driver).getKeyboard().sendKeys("cars");

    ((HasInputDevices) driver).getKeyboard().sendKeys(SelendroidKeys.ENTER);
    waitFor(WaitingConditions.driverUrlToBe(driver, "and-activity://SearchUsersActivity"));

    Assert.assertTrue(driver.getPageSource().contains("Mercedes Benz"));
  }
}
