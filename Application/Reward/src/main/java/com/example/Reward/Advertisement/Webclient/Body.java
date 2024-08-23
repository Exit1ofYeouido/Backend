package com.example.Reward.Advertisement.Webclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Body {

    private String grantType;

    private String appKey;

    private String appSecret;

}
