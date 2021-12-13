package com.ing.bank.api.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SentAndReceivedMoneyResponseDTO {

    private Float moneySent;
    private Float moneyReceived;
    private Float total;
    private String customerName;

}
