package com.example.currencyconverter.web.controllers;

import com.example.currencyconverter.domain.models.view.CurrencyAllViewModel;
import com.example.currencyconverter.service.CurrencyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final CurrencyService currencyService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(CurrencyService currencyService, ModelMapper modelMapper) {
        this.currencyService = currencyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) throws IOException {
        modelAndView.setViewName("index");
        modelAndView.addObject("currencies",
                this.currencyService.findAllCurrencies()
                .stream()
                .map(c -> this.modelMapper.map(c, CurrencyAllViewModel.class))
                .collect(Collectors.toList())
        );
        return modelAndView;
    }
}
