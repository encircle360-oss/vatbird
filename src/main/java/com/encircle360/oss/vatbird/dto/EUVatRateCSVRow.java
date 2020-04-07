package com.encircle360.oss.vatbird.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EUVatRateCSVRow {

    @CsvBindByName(column = "territory_codes")
    String territoryCode;

    @CsvBindByName(column = "currency_code")
    String currencyCode;

    @CsvBindByName(column = "stop_date")
    String stopDate;

    @CsvBindByName
    String description;

    @CsvBindByName
    BigDecimal rate;
}
