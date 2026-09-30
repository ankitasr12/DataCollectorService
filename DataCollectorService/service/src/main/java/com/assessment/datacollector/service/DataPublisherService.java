package com.assessment.datacollector.service;

import com.assessment.datacollector.configuration.KafkaTopicConfig;
import com.assessment.datacollector.exception.TechnicalFailureException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>Title: DataPublisherService</p>
 * <p/>
 * <p>Description: Service that provides methods to
 * publish the Prometheus response data to a kafka topic
 *
 * @author Ankita Srivastava
 * @version 1.0
 */
@Service
public class DataPublisherService {

    private static final Logger LOGGER = LogManager.getLogger(DataPublisherService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTopicConfig kafkaTopicConfig;

    /**
     * Method to publish message to topic
     *
     * @param apiResponse Prometheus endpoint Response
     */
    public void publishToKafka(String apiResponse) {
        try {
            String[] lines = apiResponse.split("\n");

            for (String line : lines) {
                if (!line.startsWith("#") && line.contains("{")) {
                    String key = line.substring(0, line.indexOf("{"));
                    String value = line.substring(line.indexOf("{"), line.indexOf("}")+1);

                    ProducerRecord<String, String> pr = new ProducerRecord<>(kafkaTopicConfig.productTopic().name(), key, value);
                    kafkaTemplate.send(pr);
                }
            }
            LOGGER.info("Message was successfully published to the topic");

        } catch (Exception e) {
            LOGGER.error("Error occurred while publishing to the kafka topic.");
            handleKafkaException(e);
        }
    }

    /**
     * Method handles any exception occurred during publishing to kafka topic
     *
     * @param e exception
     */
    private void handleKafkaException(Exception e) {
        LOGGER.error("Publishing to Kafka failed ===>" +e);
        throw new TechnicalFailureException("Publishing to Kafka failed" +e.getMessage());
    }

}
