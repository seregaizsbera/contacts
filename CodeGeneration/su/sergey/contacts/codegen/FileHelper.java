package su.sergey.contacts.codegen;

import java.io.File;
import java.io.IOException;

/**
 * FileHelper
 * 
 * @author Сергей Богданов
 */
public class FileHelper {
	private String rootDirectory;
	
	/**
	 * Constructor for FileHelper
	 */
	public FileHelper(String rootDirectory) {
		if (rootDirectory.endsWith("/")) {
			this.rootDirectory = rootDirectory;
		} else {
			this.rootDirectory = rootDirectory + "/";
		}
	}
	
	public String prepareFile(String packageName, String fileName) throws IOException {
		String packageFolder = (packageName == null) ? "" : packageName.replace('.', '/');
		packageFolder = rootDirectory + packageFolder;
		File pacakageDir = new File(packageFolder);
		pacakageDir.mkdirs();
		if (!pacakageDir.exists()) {
			throw new IOException("Can't make directory " + packageFolder);
		}
		if (packageFolder.endsWith("/")) {
			return packageFolder + fileName;
		}
		return packageFolder + "/" + fileName;
	}
}
