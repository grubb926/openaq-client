package com.example.openaqclient.controller;

import com.example.openaqclient.controller.form.CityAirQualityRequest;
import com.example.openaqclient.exception.APIResponseException;
import com.example.openaqclient.service.LatestAirQualityService;
import com.example.openaqclient.service.result.ResultWrapper;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LatestAirQualityService latestAirQualityService;

    @Test
    public void shouldReturnIndexPageWhenGetIsCalled() throws Exception {
        mockMvc.perform(get("/cities")).andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("request", isA(CityAirQualityRequest.class)));
    }

    @Test
    public void shouldReturnResultsPageWhenPostIsCalled() throws Exception, APIResponseException {
        when(latestAirQualityService.getLatestAirQualityForCityWithParameters(anyMap())).thenReturn(new ResultWrapper<>(null, Lists.emptyList()));
        mockMvc.perform(post("/cities/result")).andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("results", isA(List.class)));
    }

}