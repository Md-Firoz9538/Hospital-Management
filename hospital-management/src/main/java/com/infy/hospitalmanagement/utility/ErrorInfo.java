package com.infy.hospitalmanagement.utility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class ErrorInfo {
    private String errorCode;
    private String errorMessage;

    public ErrorInfo(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorInfo() {
    }
}
