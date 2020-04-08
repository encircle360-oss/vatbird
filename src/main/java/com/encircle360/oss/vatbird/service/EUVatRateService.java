package com.encircle360.oss.vatbird.service;

import com.encircle360.oss.vatbird.client.EUVatRateClient;
import com.encircle360.oss.vatbird.dto.rates.EUVatRate;
import com.encircle360.oss.vatbird.dto.rates.EUVatRateCSVRow;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EUVatRateService {

    private final CacheManager cacheManager;
    private final EUVatRateClient vatRateClient;

    @Cacheable("euVatRates")
    public Map<String, EUVatRate> getVatRates() {
        byte[] vatRateCSV = vatRateClient.downloadVatRateCSV();
        CSVReader csvReader = new CSVReader(new InputStreamReader(new ByteArrayInputStream(vatRateCSV), StandardCharsets.UTF_8));
        List<EUVatRateCSVRow> euVatRatesFromCSV = new CsvToBeanBuilder(csvReader)
                .withType(EUVatRateCSVRow.class)
                .withSeparator(',')
                .build()
                .parse();

        return euVatRatesFromCSV
                .stream()
                .filter(euVatRateCSVRow -> euVatRateCSVRow.getStopDate() != null &&
                        euVatRateCSVRow.getStopDate().isEmpty())
                .flatMap(euVatRateCSVRow -> Arrays.stream(euVatRateCSVRow.getTerritoryCode().split("\n"))
                        .map(territoryCode -> EUVatRate.builder()
                                .territoryCode(territoryCode)
                                .description(euVatRateCSVRow.getDescription())
                                .currencyCode(euVatRateCSVRow.getCurrencyCode())
                                .rate(euVatRateCSVRow.getRate())
                                .build()))
                .collect(Collectors.toMap(EUVatRate::getTerritoryCode, Function.identity()));
    }

    @Scheduled(cron = "0 1 * * * *")
    public void evictAllCacheValues() {
        Cache cache = cacheManager.getCache("euVatRates");
        if (cache != null) {
            log.info("Clearing euVatRates cache...");
            cache.clear();
        }
    }
}
