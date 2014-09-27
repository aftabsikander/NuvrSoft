package com.nurvsoft.proteintracker.model;

public class User {
	
	private String Desc, num_val;

	public User(String para_Desc, String para_num_val){
		
		this.Desc = para_Desc;
		this.num_val = para_num_val;		
		
		
		
		
	}
	
	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public String getNum_val() {
		return num_val;
	}

	public void setNum_val(String num_val) {
		this.num_val = num_val;
	}

}
