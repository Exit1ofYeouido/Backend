package com.example.Reward.Advertisement.Kafka.Service;

import com.example.Reward.Advertisement.Kafka.Dto.GiveStockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaOutputService {
    private final KafkaTemplate<String,Object> kafkaTemplate;


    public void giveStock(Long memId, double stockAmount,String code) {

        kafkaTemplate.send("give-stock",GiveStockDto.builder()
                .memId(memId)
                .amount(stockAmount)
                .code(code).build());
    }


}
