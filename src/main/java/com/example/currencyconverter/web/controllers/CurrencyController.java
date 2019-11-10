package com.example.currencyconverter.web.controllers;

import com.example.currencyconverter.domain.models.binding.ConverterBindingModel;
import com.example.currencyconverter.domain.models.binding.CurrencyAddBindingModel;
import com.example.currencyconverter.domain.models.service.CurrencyServiceModel;
import com.example.currencyconverter.domain.models.view.CurrencyViewModel;
import com.example.currencyconverter.error.CurrencyNameAlreadyExistsException;
import com.example.currencyconverter.service.CurrencyService;
import com.example.currencyconverter.validation.CurrencyAddValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CurrencyAddValidator addValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public CurrencyController(CurrencyService currencyService, CurrencyAddValidator addValidator, ModelMapper modelMapper) {
        this.currencyService = currencyService;
        this.addValidator = addValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView addCurrency(ModelAndView modelAndView,
                                    @ModelAttribute(name = "model") CurrencyAddBindingModel model){
        modelAndView.addObject("model", model);
        modelAndView.setViewName("currencies/add-currency");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addCurrencyConfirm(ModelAndView modelAndView,
                                           @ModelAttribute(name = "model") CurrencyAddBindingModel model,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            modelAndView.addObject("model", model);
            modelAndView.setViewName("currencies/add-currency");
            return modelAndView;
        }

        CurrencyServiceModel currencyServiceModel = this.modelMapper.map(model, CurrencyServiceModel.class);

        this.currencyService.createCurrency(currencyServiceModel);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/convert")
    public ModelAndView convertCurrency(ModelAndView modelAndView,
                                        @ModelAttribute(name = "model") ConverterBindingModel model){
        modelAndView.addObject("model", model);
        modelAndView.setViewName("currencies/convert-currency");
        return modelAndView;
    }

    @PostMapping("/convert")
    public ModelAndView convertCurrencyConfirm(ModelAndView modelAndView,
                                                @ModelAttribute(name = "model") ConverterBindingModel model,
                                                BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            modelAndView.addObject("model", model);
            modelAndView.setViewName("currencies/convert-currency");
            return modelAndView;
        }

        model.setResult(this.currencyService.convertCurrency(model.getFromCurrency(),
                model.getToCurrency(), model.getAmount()));
        modelAndView.addObject("model", model);
        modelAndView.setViewName("currencies/convert-currency-result");
        return modelAndView;
    }

    @GetMapping("/fetch")
    @ResponseBody
    public List<CurrencyViewModel> fetchCurrencies() {
        return this.currencyService.findAllCurrencies()
                .stream()
                .map(c -> this.modelMapper.map(c, CurrencyViewModel.class))
                .collect(Collectors.toList());
    }


    @ExceptionHandler({CurrencyNameAlreadyExistsException.class})
    public ModelAndView handleProductNameALreadyExist(CurrencyNameAlreadyExistsException e) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("statusCode", e.getStatusCode());
        return modelAndView;
    }

}
