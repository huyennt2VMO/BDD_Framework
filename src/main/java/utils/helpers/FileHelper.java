package utils.helpers;

import java.io.File;

public class FileHelper {
	private static final String USER_DIR = "user.dir";
	private static final String OBJECT_REPO_FLODER = "obj_repository";

	/**
	   * get correct type of json file;
	   *
	   * @param jsonFilePath
	   * @return
	   */
	  public static String getCorrectJsonFilePath(String jsonFilePath) {
	    String correctXlFilePath = System.getProperty(USER_DIR) + File.separator + jsonFilePath;
	    return correctXlFilePath;
	  }
}
