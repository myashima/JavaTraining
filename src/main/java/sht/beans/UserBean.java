package sht.beans;

import java.io.Serializable;

public class UserBean implements Serializable {
	private String name;
	private String id;
	private String password;
	private String spot;
	private String pref;

	//デフォルトコンストラクタ
	public UserBean() {
	}
	public UserBean(String id,String password) {
		this.id = id;
		this.password = password;
	}
	public UserBean(String name,String id,String password) {
		this.name = name;
		this.id = id;
		this.password = password;
	}
	public String getName() {
		return this.name;
	}
	public String getId() {
		return this.id;
	}
	public String getPassword() {
		return this.password;
	}
	public void setSpot(String spot) {
		this.spot = spot;
	}
	public void setPref(String pref) {
		this.pref = pref;
	}
	public String getSpot() {
		return this.spot;
	}
	public String getPref() {
		return this.pref;
	}
}
