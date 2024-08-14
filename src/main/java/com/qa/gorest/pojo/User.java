package com.qa.gorest.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("status")
	private String status;
	
	public User(String name,String email,String gender,String status) {
		
		this.name = name;
		this.gender = gender;
		this.status = status;
		this.email = email;
	}
	

}
