package com.encircle360.oss.vatbird.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
class EUVatRateServiceTest {

    @Autowired
    private EUVatRateService vatRateService;

    @Test
    void getVatRates() {
        assertFalse(vatRateService.getVatRates().isEmpty());
    }
}
