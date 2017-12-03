
public class Reservation implements java.io.Serializable {
	private String number = null;
	private String date = null;
	private String departing = null;
	private String destination = null;
	private String flightnum = null;
	private String seat = null;
	private String meal = null;
	private String deptime = null;
	private String type = null;
	
	public Reservation() {
		
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDeparting() {
		return departing;
	}
	public void setDeparting(String departing) {
		this.departing = departing;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getFlightnum() {
		return flightnum;
	}
	public void setFlightnum(String flightnum) {
		this.flightnum = flightnum;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getMeal() {
		return meal;
	}
	public void setMeal(String meal) {
		this.meal = meal;
	}
	public String getDeptime() {
		return deptime;
	}
	public void setDeptime(String deptime) {
		this.deptime = deptime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
