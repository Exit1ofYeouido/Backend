package com.example.Reward.Receipt.Exception.ReceiptExceptions;

import com.example.Reward.Receipt.Exception.ReceiptErrorStatus;
import com.example.Reward.Receipt.Exception.ReceiptException;
import lombok.Getter;

@Getter
public class InvalidFileExtensionException extends ReceiptException {
    private final String extension;
    private static final String validExtentions = "가능한 확장자 : jpg, jpeg, png, svg";

    public InvalidFileExtensionException(String extension) {
        super(ReceiptErrorStatus.INVALID_FILE_EXTENSION);
        this.extension = extension;
    }

    public String getValidExtentions() {
        return validExtentions;
    }
}
