package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.person.valueobjects.Coworker;

public class DefaultCoworker implements Serializable, Coworker {
	private String job;
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
}
