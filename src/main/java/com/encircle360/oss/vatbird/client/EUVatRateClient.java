package com.encircle360.oss.vatbird.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "euVatRateClient", url = "${vat-rates.eu.database}")
public interface EUVatRateClient {

    @GetMapping
    byte[] downloadVatRateCSV();
}
