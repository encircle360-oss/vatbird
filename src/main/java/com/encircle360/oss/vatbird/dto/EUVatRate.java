package com.encircle360.oss.vatbird.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EUVatRate {

    String territoryCode;
    String currencyCode;
    String description;
    BigDecimal rate;
}
