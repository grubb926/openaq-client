package com.example.openaqclient.controller;

import com.example.openaqclient.controller.form.CityAirQualityRequest;
import com.example.openaqclient.exception.APIResponseException;
import com.example.openaqclient.service.LatestAirQualityService;
import com.example.openaqclient.service.result.Location;
import com.example.openaqclient.service.result.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cities")
public class MainController {

    @Value("${openaq.query.param.city}")
    private String cityKey;

    @Value("${openaq.query.param.sort}")
    private String sortKey;

    @Value("${openaq.query.param.limit}")
    private String limitKey;

    private LatestAirQualityService latestAirQualityService;

    @Autowired
    public MainController(LatestAirQualityService latestAirQualityService) {
        this.latestAirQualityService = latestAirQualityService;
    }

    @GetMapping
    public String latestAirQualityForCity(Model model) {
        final CityAirQualityRequest request =  new CityAirQualityRequest();
        request.setLimit(100);
        model.addAttribute("request", request);
        return "index";
    }

    @PostMapping("/result")
    public String latestAirQualityForCityResults(@ModelAttribute CityAirQualityRequest request, Model model) throws APIResponseException {
        final ResultWrapper<Location> resultWrapper = latestAirQualityService.getLatestAirQualityForCityWithParameters(extractQueryParamsFromRequest(request));
        model.addAttribute("results", resultWrapper.getResults());
        return "result";
    }

    private Map<String, String> extractQueryParamsFromRequest(CityAirQualityRequest request) {
        final Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put(cityKey, (StringUtils.isEmpty(request.getCityName()) ? "" : request.getCityName()));
        queryParameters.put(sortKey, request.getSort());
        queryParameters.put(limitKey, String.valueOf(request.getLimit()));
        return queryParameters;
    }
}
