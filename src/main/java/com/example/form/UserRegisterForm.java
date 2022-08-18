package com.example.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegisterForm {
	
	@NotBlank(message = "error:may not be empty")
	private String name;
	
	@NotBlank(message = "error:may not be empty")
	private String password;
	
	@NotBlank(message = "error:may not be empty")
	private String authority;

}
