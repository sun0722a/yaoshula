package _01_register.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class MemberBean {
	private Integer id;
	private String name;
	private String password;
	private String gender;
	private Date birthday;
	private String email;
	private String cellphone;
	private String address;
	private String fileName;
	private Blob picture;
	private Timestamp createTime;
	private String status;
	private String permission;

	public MemberBean(Integer id, String name, String password, String gender, Date birthday, String email,
			String cellphone, String address, String fileName, Blob picture, Timestamp createTime, String status,
			String permission) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.cellphone = cellphone;
		this.address = address;
		this.fileName = fileName;
		this.picture = picture;
		this.createTime = createTime;
		this.status = status;
		this.permission = permission;
	}

	public MemberBean() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}
