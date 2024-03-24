package com.example.consumer.service.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class Filter {
    @Schema(description = "The field, filter applied to", example = "statistic", required = true)
    private String key;
    @Schema(description = "Optional join table name(if need to apply the filter to joined table field)",
            example = "measurements", required = false)
    private String joinTable;
    @Schema(description = "Operator", example = "MATCH", required = true)
    private Operator operator;
    @Schema(description = "Value", example = "VAL", required = true)
    private Object value;
    public enum Operator {
        EQUAL, MATCH, GREATER, LESS, GREATER_EQUAL, LESS_EQUAL
    }
}
