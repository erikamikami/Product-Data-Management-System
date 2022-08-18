package com.example.controller;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.User;
import com.example.form.UserRegisterForm;
import com.example.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	private UserRegisterForm SetUpUserRegisterForm() {
		return new UserRegisterForm();
	}
	
	@RequestMapping("/register")
	public String toShowRegisterUserForm() {
		return "register";
	}
	
	@RequestMapping("/register/comp")
	public String register(@Validated UserRegisterForm registerForm, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute(registerForm); // (1)
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(registerForm), result); // (2)
			return "redirect:/user/register";
		}
		
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		BeanUtils.copyProperties(registerForm, user);
		
		userService.userRegister(user);
		
		return "login";
	}

}
