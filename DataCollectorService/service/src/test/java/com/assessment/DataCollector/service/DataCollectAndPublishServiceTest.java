package com.assessment.DataCollector.service;

import com.assessment.datacollector.service.DataCollectAndPublishService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DataCollectAndPublishServiceTest {

    private static final Logger LOGGER = LogManager.getLogger(DataCollectAndPublishServiceTest.class);

    @Autowired
    DataCollectAndPublishService dataCollectAndPublishService;

    @BeforeEach
    public void setUp() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Test pre setup completed.");
        }
    }

    @AfterEach
    public void tearDown() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Test post setup completed.");
        }
    }

    @Test
    public void test() {

    }


}