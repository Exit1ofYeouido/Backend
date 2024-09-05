package com.example.Search.Common.Entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "stock",indexes = {
        @Index(name = "idx_stock_name",columnList = "name")
})
public class Stock {

    @Id
    @Column(name = "stock_code", columnDefinition = "char(6)")
    private String code;

    @Column(name = "name")
    private String name;

}