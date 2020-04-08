package com.encircle360.oss.vatbird.service;

import com.encircle360.oss.vatbird.dto.validation.CountryCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
class EUVatValidationServiceTest {

    @Autowired
    private EUVatValidationService euVatValidationService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void checkVatId() {
        assertTrue(euVatValidationService.checkVatId(CountryCode.DE, "115235681"));
    }
}
