package com.encircle360.oss.vatbird.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckVatIdResponse {

    private CountryCode countryCode;
    private String vatId;
    private boolean isValid;
}
