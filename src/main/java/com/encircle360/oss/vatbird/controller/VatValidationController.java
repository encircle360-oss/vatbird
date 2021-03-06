package com.encircle360.oss.vatbird.controller;

import com.encircle360.oss.vatbird.dto.validation.CheckVatIdResponse;
import com.encircle360.oss.vatbird.dto.validation.CountryCode;
import com.encircle360.oss.vatbird.service.EUVatValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/vat-ids")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VatValidationController {

    private final EUVatValidationService euVatValidationService;

    @GetMapping("/{countryCode}/{vatId}")
    public ResponseEntity<CheckVatIdResponse> checkVatId(@PathVariable("countryCode") CountryCode countryCode, @PathVariable("vatId") String vatId) {
        CheckVatIdResponse checkVatIdResponse = CheckVatIdResponse.builder()
                .countryCode(countryCode)
                .vatId(vatId)
                .isValid(euVatValidationService.checkVatId(countryCode, vatId))
                .build();

        if(checkVatIdResponse.isValid()) {
            return ResponseEntity.ok(checkVatIdResponse);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(checkVatIdResponse);
    }
}
