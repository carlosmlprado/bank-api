package com.ing.bank.api.dto;

import lombok.Data;

@Data
public class SpentAndReceivedMoneyResponseDTO {

    private Float moneySpent;
    private Float moneyReceived;
    private Float total;

}
