package com.ing.bank.api.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SentAndReceivedMoneyResponseDTO {

    private Float moneySent;
    private Float moneyReceived;

}
