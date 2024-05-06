package com.eduardoaf.balance.shared.infrastructure.http.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public final class  HttpResponse{

    public int statusCode;
    public String message;
    public Object data;

    public HttpResponse() {}

    public HttpResponse(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static HttpResponse getInstance(
        int statusCode,
        String message,
        Object data
    ) {
        return new HttpResponse(
            statusCode,
            message,
            data
        );
    }

    public ResponseEntity<?> getResponse200(String message, Object data) {
        var httpResponse = HttpResponse.getInstance(
                HttpStatus.OK.value(),
                message,
                data
        );
        return ResponseEntity.status(HttpStatus.OK).body(httpResponse);
    }

    public ResponseEntity<?> getResponse500(String message) {
        var httpResponse = HttpResponse.getInstance(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message,
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(httpResponse);
    }
}
