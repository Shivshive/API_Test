package pojo;

import java.io.Serializable;

public class UserDetails implements Serializable {
	
	private String streetName;
	private String number;
	private String city;
	private String country;
	
	public UserDetails() {
		
	}
	
	public UserDetails(String streetName, String number, String city, String country) {
		this.streetName = streetName;
		this.number = number;
		this.city = city;
		this.country = country;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "StreetName-"+this.getStreetName()+" Number-"+this.getNumber()+" City-"+this.getCity()+" Country-"+this.getCountry();
	}

}
