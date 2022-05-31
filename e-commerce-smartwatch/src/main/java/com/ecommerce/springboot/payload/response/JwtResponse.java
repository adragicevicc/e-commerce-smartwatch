package com.ecommerce.springboot.payload.response;

import java.util.List;

public class JwtResponse {
	
	private String token;
	private String type = "Bearer";
	private int id;
	private String email;
	private List<String> uloge;
	private int id_korpa;

	public JwtResponse(String accessToken, int id, String email, List<String> uloge, int id_korpa) {
		this.token = accessToken;
		this.id = id;
		this.email = email;
		this.uloge = uloge;
		this.id_korpa=id_korpa;
	}
	
	

	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public int getId_korpa() {
		return id_korpa;
	}



	public void setId_korpa(int id_korpa) {
		this.id_korpa = id_korpa;
	}



	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getUloge() {
		return uloge;
	}

	public void setUloge(List<String> uloge) {
		this.uloge = uloge;
	}
	
	


}
