package su.sergey.beans;

import java.util.Date;


public class Bean1 {
	private int a;
	private Date birthDate[];
	private Bean2 c;
	private String d;

	/**
	 * Gets the c
	 * @return Returns a Bean2
	 */
	public Bean2 getC() {
		return c;
	}
	/**
	 * Sets the c
	 * @param c The c to set
	 */
	public void setC(Bean2 c) {
		this.c = c;
	}

	/**
	 * Gets the a
	 * @return Returns a int
	 */
	public int getA() {
		return a;
	}
	/**
	 * Sets the a
	 * @param a The a to set
	 */
	public void setA(int a) {
		this.a = a;
	}

	/**
	 * Gets the birthDate
	 * @return Returns a Date[]
	 */
	public Date[] getBirthDate() {
		return birthDate;
	}
	/**
	 * Sets the birthDate
	 * @param birthDate The birthDate to set
	 */
	public void setBirthDate(Date[] birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the d
	 * @return Returns a String
	 */
	public String getD() {
		return d;
	}
	/**
	 * Sets the d
	 * @param d The d to set
	 */
	public void setD(String d) {
		this.d = d;
	}

}
