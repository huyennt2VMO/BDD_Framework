package basepage;

import java.io.File;
import java.util.HashMap;

//import org.openqa.selenium.By;
import org.slf4j.Logger;

import com.jayway.jsonpath.JsonPath;

import utils.log.LogHelper;
import testobject.TestObject;
import utils.helpers.FileHelper;
//import utils.helpers.WebCommonHelper;
import utils.keywords.WebKeywords;

public class Page {
	protected static final Logger logger = LogHelper.getLogger();
	protected WebKeywords action;
	protected HashMap<String, String> repoFile;
//	private TestObject to = new TestObject();
//	private HashMap<String, HashMap<String, By>> locationMap;
//	protected static ConfigSettings config;

	protected Page() {
		action = new WebKeywords();
//		config = new ConfigSettings(System.getProperty("user.dir"));
	}

	protected Page(WebKeywords action) {
		this();
		this.action = action;
//		config = new ConfigSettings(System.getProperty("user.dir"));
	}

//	public void initLocationMap() {
//		if(this.locationMap == null) {
//			this.locationMap = new HashMap<>();
//		}
//		HashMap<String, By> map = new HashMap<>();
//		try {
//			String strClassName = null;
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

	/**
	 * @param strRepoFile
	 * @return
	 */
	public String setRepoFile(String strRepoFile) {
		if (this.repoFile == null) {
			this.repoFile = new HashMap<>();
		}
		// String strClassName = Reflection.getCallerClass(2).getSimpleName();
		String strClassName = getCallerClass(2).getSimpleName();
		logger.info("Set Repo File - Get caller class 2: " + strClassName); // Set Repo File - Get caller class 2:
																			// W3SchoolSignUp

//	    Json file
		strRepoFile = strRepoFile + File.separator + strClassName + ".json"; // Repo file relative path:
																				// object_repository\W3SchoolSignUp.json
		logger.info("Repo file relative path: " + strRepoFile);
		strRepoFile = FileHelper.getCorrectJsonFilePath(strRepoFile);
		return this.repoFile.put(strClassName, strRepoFile);
	}

	private static Class<?> getCallerClass(final int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		try {
			return getCallerClassFromStackTrace(index + 1);
		} catch (final ClassNotFoundException e) {
			logger.error("Could not find class in ReflectionUtil.getCallerClass({}), index<" + index + ">"
					+ ", exception< " + e + ">");
		}
		return null;
	}

	private static Class<?> getCallerClassFromStackTrace(final int index) throws ClassNotFoundException {

		final StackTraceElement[] elements = new Throwable().getStackTrace();
		int i = 0;
		for (final StackTraceElement element : elements) {
			if (isValidlMethod(element)) {
				if (i == index) {
					return Class.forName(element.getClassName());
				}
				++i;
			}
		}
		throw new IndexOutOfBoundsException(Integer.toString(index));
	}

	private static boolean isValidlMethod(final StackTraceElement element) {
		if (element.isNativeMethod()) {
			return false;
		}
		final String cn = element.getClassName();
		if (cn.startsWith("sun.reflect.")) {
			return false;
		}
		final String mn = element.getMethodName();
		if (cn.startsWith("java.lang.reflect.") && (mn.equals("invoke") || mn.equals("newInstance"))) {
			return false;
		}
		if (cn.equals("java.lang.Class") && mn.equals("newInstance")) {
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	public String getRepoFile() {
//		this.repoFile.entrySet();
		Class<?> clas = this.getClass();
		String strClassName = clas.getSimpleName();
//		String strClassName = getCallerClass(3).getSimpleName();
		return this.repoFile.get(strClassName);
	}

	/**
	 * @param nameOfElement
	 * @return
	 */
	public TestObject findTestObject(String nameOfElement) {
		
		String repoFile = this.getRepoFile();
		try {
			File jsonRepoFile = new File(repoFile);
			String locatorValue = JsonPath.read(jsonRepoFile, "$." + nameOfElement).toString();
			logger.debug(nameOfElement + ":" +locatorValue);
			return new TestObject(nameOfElement, locatorValue);	
		} catch (Exception e) {
			logger.error("Error message: " + e.getMessage());
		}
		return null;

		// for Excel file
//	    return this.getLocation(nameOfElement);
	}

//	public By findWebElements(String nameOfElement) {
//		this.to.setObjectName(nameOfElement);
//		String xpathOfElement = config.getConfigProperties().getProperty(this.to.getObjectName());
//		if(xpathOfElement.contains("//")) {
//			return By.xpath(xpathOfElement);
//		}
//		return By.id(xpathOfElement);
//	}
//	private By getLocation(String locationName) {
//		String rootPage = "Page";
//		Class<?> clas = this.getClass();
//		String strClassName = clas.getSimpleName();
//		HashMap<String, By> map = null;
//		By locat = null;
//		this.to.setObjectName(locationName);
//		do {
//			map = this.locationMap.get(strClassName);
//			if(map.containsKey(locationName)) {
//				locat = map.get(locationName);
//			}
//			clas = clas.getSuperclass();
//			strClassName = clas.getSimpleName();
//		}while(locat == null && !strClassName.equals(rootPage));
//		return locat;
//	}
//	
//	public By findWebElement(String location) {
//		return this.getLocation(location);
//	}
}

