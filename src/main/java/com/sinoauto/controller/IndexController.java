package com.sinoauto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class IndexController {

	@RequestMapping(value = "/")
	public String index() {
		return "redirect:/hqls/index.html";
	}
}
