package com.encircle360.oss.vatbird.service;

import com.encircle360.oss.vatbird.dto.CountryCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class VatServiceTest {

    @Autowired
    private VatService vatService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void checkVatId() {
        vatService.checkVatId(CountryCode.DE, "313551330");
    }
}