package com.example.demo.controller;

import com.example.demo.model.Products;
import com.example.demo.service.CalculateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 計算コントローラ
 *
 * @author wataru okamura
 */
@Controller
@RequestMapping(path = "/calculate")
public class CalculateController {

	@Autowired
	private CalculateService calculateService;

	@GetMapping(path = "")
	public String display(Model model) {

		model.addAttribute("products", new Products());
		return "calculate";
	}

	@PostMapping(path = "")
	public String calculate(@ModelAttribute Products products, Model model) throws CloneNotSupportedException {

		calculateService.calculate(products.clone());
		return "calculate";
	}

}
