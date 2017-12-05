public class Flight implements java.io.Serializable {
	private String airlineid = null;
	private String flightnum = null;
	private String numofseats = null;
	private String daysoperating = null;
	private String minlengthofstay = null;
	private String maxlengthofstay = null;
	
	public Flight() {
		
	}
	
	public String getAirlineID() {
		return airlineid;
	}
	public void setAirlineID(String airlineid) {
		this.airlineid = airlineid;
	}
	public String getFlightNum() {
		return flightnum;
	}
	public void setFlightNum(String flightnum) {
		this.flightnum = flightnum;
	}
	public String getNumOfSeats() {
		return numofseats;
	}
	public void setNumOfSeats(String numofseats) {
		this.numofseats = numofseats;
	}
	public String getDaysOperating() {
		return daysoperating;
	}
	public void setDaysOperating(String daysoperating) {
		this.daysoperating = daysoperating;
	}
	public String getMinLengthOfStay() {
		return minlengthofstay;
	}
	public void setMinLengthOfStay(String minlengthofstay) {
		this.minlengthofstay = minlengthofstay;
	}
	public String getMaxLengthOfStay() {
		return maxlengthofstay;
	}
	public void setMaxLengthOfStay(String maxlengthofstay) {
		this.maxlengthofstay = maxlengthofstay;
	}
	
	
}
