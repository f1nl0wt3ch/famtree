package domain;

import java.sql.Timestamp;

public class DiedPeople {
	private int id;
	private String name;
	private Timestamp diedDate;
	private Timestamp bornDate;
	private String detail;
	private String imageLink;
	public DiedPeople() {
		super();
	}
	public DiedPeople(int id, String name, Timestamp diedDate, Timestamp bornDate, String detail, String imageLink) {
		super();
		this.id = id;
		this.name = name;
		this.diedDate = diedDate;
		this.bornDate = bornDate;
		this.detail = detail;
		this.imageLink = imageLink;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public Timestamp getDiedDate() {
		return diedDate;
	}
	public void setDiedDate(Timestamp diedDate) {
		this.diedDate = diedDate;
	}
	public Timestamp getBornDate() {
		return bornDate;
	}
	public void setBornDate(Timestamp bornDate) {
		this.bornDate = bornDate;
	}
	
}
