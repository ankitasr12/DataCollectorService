package com.assessment.datacollector.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusResponse {

    private int statusCode;
    private String apiResponse;
}
