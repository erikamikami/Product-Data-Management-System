package com.example.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.pagination.Pagination;

@Controller
public class ItemController {
	
	@ModelAttribute
	public Pagination setUpToPagination() {
		return new Pagination();
	}

}
