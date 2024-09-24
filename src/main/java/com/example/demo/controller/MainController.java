package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class MainController {

    @GetMapping
    public String showCalculator() {
        return "main";
    }

    @PostMapping
    public String calculate(
            @RequestParam(name = "num1", required = true) double num1,
            @RequestParam(name = "num2", required = true) double num2,
            @RequestParam(name = "operation", required = true) String operation,
            Model model) {

        double result = 0;

        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    model.addAttribute("error", "Что-то пошло не так(");
                    return "error";
                }
                break;
        }

        model.addAttribute("result", result);
        return "result";
    }


    @GetMapping("/converter")
    public String converter(
            @RequestParam(name = "val1", required = false) String val1,
            @RequestParam(name = "val2", required = false) String val2,
            @RequestParam(name = "amount", required = false) Double amount,
            Model model) {

        if (amount != null && val1 != null && val2 != null) {
            double conversionRate = getConversionRate(val1, val2);
            double result = amount * conversionRate;

            String formattedResult = String.format("%.2f", result);

            model.addAttribute("amount", amount);
            model.addAttribute("val1", val1);
            model.addAttribute("val2", val2);
            model.addAttribute("result", formattedResult);
        } else {
            model.addAttribute("error", "Пожалуйста, введите сумму и выберите валюты.");
        }
        return "converter";
    }

    private double getConversionRate(String val1, String val2) {
        if (val1.equals("USD") && val2.equals("EUR")) {
            return 0.85;
        } else if (val1.equals("EUR") && val2.equals("USD")) {
            return 1.18;
        } else if (val1.equals("USD") && val2.equals("RUB")) {
            return 75;
        } else if (val1.equals("RUB") && val2.equals("USD")) {
            return 0.013;
        } else if (val1.equals("EUR") && val2.equals("RUB")) {
            return 88;
        } else if (val1.equals("RUB") && val2.equals("EUR")) {
            return 0.011;
        } else {
            return 1;
        }
    }
}
