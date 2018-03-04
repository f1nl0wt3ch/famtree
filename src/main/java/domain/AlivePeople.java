package domain;

import java.sql.Timestamp;
import java.util.List;

public class AlivePeople {
    private int id;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private String detail;
    private boolean receiveEmail = true;
    private Timestamp bornDate;
    private String imageLink;
    private List<Relation> relations;
    
	public AlivePeople() {
		super();
	}

	public AlivePeople(int id, String fullName, String email, String address, String phone, String detail,
			boolean receiveEmail, Timestamp bornDate, String imageLink, List<Relation> relations) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.detail = detail;
		this.receiveEmail = receiveEmail;
		this.bornDate = bornDate;
		this.imageLink = imageLink;
		this.relations = relations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(boolean receiveEmail) {
		this.receiveEmail = receiveEmail;
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

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public Timestamp getBornDate() {
		return bornDate;
	}

	public void setBornDate(Timestamp bornDate) {
		this.bornDate = bornDate;
	}
	
}
