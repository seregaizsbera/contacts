package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.person.valueobjects.Coworker;

public class DefaultCoworker implements Serializable, Coworker {
	private String job;
	private String department;
	private String administration;
	private String post;
	private String description;

	/**
	 * Gets the description
	 * @return Returns a String
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the job
	 * @return Returns a String
	 */
	public String getJob() {
		return job;
	}
	
	/**
	 * Sets the job
	 * @param job The job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}
	
	/**
	 * Gets the administration
	 * @return Returns a String
	 */
	public String getAdministration() {
		return administration;
	}
	
	/**
	 * Sets the administration
	 * @param administration The administration to set
	 */
	public void setAdministration(String administration) {
		this.administration = administration;
	}

	/**
	 * Gets the department
	 * @return Returns a String
	 */
	public String getDepartment() {
		return department;
	}
	
	/**
	 * Sets the department
	 * @param department The department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * Gets the post
	 * @return Returns a String
	 */
	public String getPost() {
		return post;
	}
	
	/**
	 * Sets the post
	 * @param post The post to set
	 */
	public void setPost(String post) {
		this.post = post;
	}
}
