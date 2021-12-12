package com.ing.bank.api.dto.transaction;

import lombok.Data;

@Data
public class SpentAndReceivedMoneyResponseDTO {

    private Float moneySpent;
    private Float moneyReceived;
    private Float total;

}
