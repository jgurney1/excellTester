package com.qa.ExcelTester;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoLandingPage {

	@FindBy(xpath="/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")
	private WebElement usernameBox;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")
	private WebElement passwordBox;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")
	private WebElement submitButton;
	
	@FindBy(xpath="/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")
	private WebElement loginPage;
	
	public void inputUsername(String username) {
		usernameBox.sendKeys(username);
	}
	
	public void inputPassword(String password) {
		passwordBox.sendKeys(password);
	}
	
	public void submit() {
		submitButton.click();
	}
	
	public void goToLoginPage() {
		loginPage.click();
	}
	
}
