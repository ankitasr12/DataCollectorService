package com.assessment.datacollector.service;

import com.assessment.datacollector.model.PrometheusResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * <p>Title: DataCollectAndPublishService</p>
 * <p/>
 * <p>Description: Service that provides methods to
 * retrieve the  Prometheus metric logs from the endpoint every 5 minutes
 * and publish the same to a kafka topic
 *
 * @author Ankita Srivastava
 * @version 1.0
 */
@Component
public class DataCollectAndPublishService {

    private static final Logger LOGGER = LogManager.getLogger(DataCollectAndPublishService.class);

    @Autowired
    DataCollectorService dataCollectorService;

    @Autowired
    DataPublisherService dataPublisherService;

    /**
     * Method calls the prometheus endpoint  to retrieve metric data every 5 mins
     * If successfully retrieved, the response is published to a kafka topic.
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void retrieveAndPublishData() {

        String metricData = retrievePrometheusData();
        LOGGER.info("Prometheus metric data retrieved successfully");

        publishDataToKafka(metricData);
    }

    /**
     * Method publishes the retrieved Prometheus data to Kafka
     *
     * @param metricData retrieved Data
     */
    private void publishDataToKafka(String metricData) {
        dataPublisherService.publishToKafka(metricData);
    }

    /**
     * Method calls the endpoint to retrieve data and returns the same
     */
    private String retrievePrometheusData() {
        PrometheusResponse prometheusResponse = dataCollectorService.getPrometheusResponse();
        return prometheusResponse.getApiResponse();
    }
}
