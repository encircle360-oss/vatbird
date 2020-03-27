package com.encircle360.oss.vatbird.controller;

import com.encircle360.oss.vatbird.dto.CountryCode;
import com.encircle360.oss.vatbird.service.VatService;
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
@RequestMapping("/vat")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VatController {

    private final VatService vatService;

    @GetMapping("/{countryCode}/{vatId}")
    public ResponseEntity<?> checkVatId(@PathVariable("countryCode") CountryCode countryCode, @PathVariable("vatId") String vatId) {
        if(vatService.checkVatId(countryCode, vatId)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
