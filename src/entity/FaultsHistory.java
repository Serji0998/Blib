package entity;

import java.io.Serializable;
import java.util.Date;
/**
 * The entity class that stores faults.
 * {@code implements} {@link java.io.Serializable} since it need to be passed to the server.
 */
public class FaultsHistory implements Serializable{
	/**
	 * 
	 */
	private String userID;
	private String fault;
	private java.sql.Date faultDate;
	/**
	 * empty constructor
	 */
	public FaultsHistory() {}
	/**
	 * a 2-argument cosntructor
	 * @param userID
	 * @param fault
	 */
	public FaultsHistory(String userID,String fault) {
		
		this.userID=userID;
		this.fault=fault;
	}
	/**
	 * 
	 * @return
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * 
	 * @param userID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * 
	 * @return
	 */
	public String getFault() {
		return fault;
	}
	/**
	 * 
	 * @param faultDesc
	 */
	public void setFaultDesc(String faultDesc) {
		this.fault = faultDesc;
	}
	/**
	 * 
	 * @return
	 */
	public Date getFaultDate() {
		return faultDate;
	}
	/**
	 * 
	 * @param faultDate
	 */
	public void setFaultDate(java.sql.Date faultDate) {
		this.faultDate = faultDate;
	}
	

}
