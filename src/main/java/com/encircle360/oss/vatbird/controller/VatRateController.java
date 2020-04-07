package com.encircle360.oss.vatbird.controller;

import com.encircle360.oss.vatbird.dto.EUVatRate;
import com.encircle360.oss.vatbird.service.EUVatRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/vat-rates")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VatRateController {

    private final EUVatRateService euVatRateService;

    @GetMapping("/{territoryCode}")
    public ResponseEntity<EUVatRate> getVatRate(@PathVariable("territoryCode") String territoryCode) {
        EUVatRate vatRate = euVatRateService.getVatRates().get(territoryCode);

        if(vatRate == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(vatRate);
    }
}
