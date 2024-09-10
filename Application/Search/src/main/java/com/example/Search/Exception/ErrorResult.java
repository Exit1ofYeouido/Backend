package com.example.Search.Exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResult {
    private String error;
    private String message;
}
