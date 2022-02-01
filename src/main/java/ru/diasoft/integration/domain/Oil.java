package ru.diasoft.integration.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Oil {
    private String producerCountry;
    private String type;
    private BigDecimal cost;
}

