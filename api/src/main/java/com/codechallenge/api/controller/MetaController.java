package com.codechallenge.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MetaController {

	@GetMapping("/")
	public String healthCheck() {
		return "OK";
	}

	@GetMapping("/version")
	public String version() {
		return "1.0.0";
	}

}
