package su.sergey.contacts.util;

import java.io.Serializable;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class ProductInfo implements Serializable {
	private String version;
	private String builtBy;
	private String buildDate;
	private String buildTime;
	
	/**
	 * Constructor for ProductInfo
	 */
	public ProductInfo(Manifest manifest) {
		Attributes attributes = manifest.getMainAttributes();
		version = attributes.getValue("version");
		builtBy = attributes.getValue("built-by");
		buildDate = attributes.getValue("build-date");
		buildTime = attributes.getValue("build-time");
	}
	
	/**
	 * Gets the buildDate
	 * @return Returns a String
	 */
	public String getBuildDate() {
		return buildDate;
	}
	
	/**
	 * Sets the buildDate
	 * @param buildDate The buildDate to set
	 */
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}

	/**
	 * Gets the buildTime
	 * @return Returns a String
	 */
	public String getBuildTime() {
		return buildTime;
	}
	
	/**
	 * Sets the buildTime
	 * @param buildTime The buildTime to set
	 */
	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}

	/**
	 * Gets the version
	 * @return Returns a String
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Sets the version
	 * @param version The version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Gets the builtBy
	 * @return Returns a String
	 */
	public String getBuiltBy() {
		return builtBy;
	}
	
	/**
	 * Sets the builtBy
	 * @param builtBy The builtBy to set
	 */
	public void setBuiltBy(String builtBy) {
		this.builtBy = builtBy;
	}
}
