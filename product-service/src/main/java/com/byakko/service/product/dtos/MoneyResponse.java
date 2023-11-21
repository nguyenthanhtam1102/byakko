package com.byakko.service.product.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Data
@AllArgsConstructor
@Builder
public class MoneyResponse {

    private BigDecimal raw;
    private String formatted;

    @JsonProperty("formatted_with_symbol")
    private String formattedWithSymbol;

    public static MoneyResponse toMoneyResponse(BigDecimal money) {
        if(money == null)
            throw new RuntimeException("Money must be not null");

        Locale vietnamLocale = new Locale("vi", "VN");
        NumberFormat currencyFormatterVN = NumberFormat.getCurrencyInstance(vietnamLocale);
        NumberFormat numberFormatterVN = NumberFormat.getNumberInstance(vietnamLocale);

        return MoneyResponse.builder()
                .raw(money)
                .formatted(numberFormatterVN.format(money))
                .formattedWithSymbol(currencyFormatterVN.format(money))
                .build();
    }

}
