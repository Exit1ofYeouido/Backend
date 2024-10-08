package com.example.Reward.Receipt.Exception.ReceiptExceptions;

import com.example.Reward.Receipt.Exception.ReceiptErrorStatus;
import com.example.Reward.Receipt.Exception.ReceiptException;
import lombok.Getter;

@Getter
public class StockNotFoundException extends ReceiptException {
    private String foundName;
    private String receiptURL;

    public StockNotFoundException(String foundName, String receiptURL) {
        super(ReceiptErrorStatus.STOCK_NOT_FOUND);
        this.foundName = foundName;
        this.receiptURL = receiptURL;
    }
}
