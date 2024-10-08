package com.example.Reward.Receipt.Entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptLog {
    @EmbeddedId
    private ReceiptLogKey receiptLogKey;
    private String storeName;
    private String price;
    private String imgPath;
    private String enterpriseName;
    private Long memberId;
}
