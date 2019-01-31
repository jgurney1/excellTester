package com.qa.ExcelTester;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoLoginPage {

	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")
	private WebElement usernameBox;
	
	@FindBy(xpath ="/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")
	private WebElement passwordBox;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")
	private WebElement submitButton;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
	private WebElement loginResult;
	
	public void inputUsername(String username) {
		usernameBox.sendKeys(username);
	}
	
	public void inputPassword(String password) {
		passwordBox.sendKeys(password);
	}
	
	public void submit() {
		submitButton.click();
	}
	
	public String getResult() {
		return loginResult.getText();
	}
	
}
