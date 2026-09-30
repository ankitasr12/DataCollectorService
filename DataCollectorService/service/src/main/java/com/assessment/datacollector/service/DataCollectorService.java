package com.assessment.datacollector.service;

import com.assessment.datacollector.exception.ResourceNotFoundException;
import com.assessment.datacollector.exception.ServiceNotAvailableException;
import com.assessment.datacollector.exception.TechnicalFailureException;
import com.assessment.datacollector.model.PrometheusResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Title: DataCollectorService</p>
 * <p/>
 * <p>Description: Service that provides methods to
 * retrieve the  Prometheus metric logs from the endpoint
 *
 * @author Ankita Srivastava
 * @version 1.0
 */
@Service
public class DataCollectorService {

    private static final Logger LOGGER = LogManager.getLogger(DataCollectorService.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${prometheus.endpoint.url}")
    private String prometheusEndpoint;

    /**
     * Method to call the Rest endpoint and return data.
     * Throws exception if any occurs during the API call.
     *
     * @return PrometheusResponse
     */
    public PrometheusResponse getPrometheusResponse() {
        PrometheusResponse prometheusResponse = new PrometheusResponse();
        try {
            ResponseEntity<String> response = restTemplate.exchange(prometheusEndpoint, HttpMethod.GET, null, String.class);
            LOGGER.info("Prometheus API call successful");
            prometheusResponse.setStatusCode(response.getStatusCode().value());
            prometheusResponse.setApiResponse(response.getBody());
            return prometheusResponse;

        } catch (Exception e) {
            LOGGER.error("Error occurred while calling the Prometheus API.");
            handleException(e);
        }
        return null;
    }


    /**
     * Method handles the exception occurred during the API call
     *
     * @param e exception
     */
    private void handleException(Exception e) {
        String errMsg = String.format("Retrieving prometheus response failed with error %s ", e.getMessage().split(":")[0]);
        LOGGER.error(errMsg);
        if (e instanceof HttpClientErrorException) {
            if (((HttpClientErrorException) e).getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceNotFoundException(errMsg);
            }
        } else if (e instanceof ResourceAccessException) {
            throw new ServiceNotAvailableException(errMsg);
        } else {
            throw new TechnicalFailureException(errMsg);
        }
    }
}
