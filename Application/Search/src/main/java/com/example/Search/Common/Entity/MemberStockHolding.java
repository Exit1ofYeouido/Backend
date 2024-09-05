package com.example.Search.Common.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberStockHolding {

    @Id
    @GeneratedValue
    @Column(name="member_stock_holding_id")
    private Long id;

    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "code")
    private Stock stock;
}
