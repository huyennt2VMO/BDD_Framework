package testobject;

import org.openqa.selenium.By;

import testobject.TestObject;
import utils.helpers.WebCommonHelper;

public class TestObject {
	private String objectName;
	private String locatorValue;
	
	public TestObject() {
		
	}
	
	public TestObject(String nameOfElement, String locatorValue) {
		// TODO Auto-generated constructor stub
		this.objectName = nameOfElement;
		this.locatorValue = locatorValue;
	}

	public String getObjectName() {
		return objectName;
	}
	
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getLocatorValue() {
		return locatorValue;
	}

	public void setLocatorValue(String locatorValue) {
		this.locatorValue = locatorValue;
	}
	
	public By getBy() {
		String locatorValue = this.getLocatorValue();
		String[] locator = locatorValue.split(":");
		return WebCommonHelper.getByStrategy(locator[1], locator[0], "");
	}
	
}
