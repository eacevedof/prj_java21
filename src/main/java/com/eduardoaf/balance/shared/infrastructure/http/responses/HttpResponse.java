package com.eduardoaf.balance.shared.infrastructure.http.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public final class  HttpResponse{

    public int statusCode;
    public String status;
    public String message;
    public Object data;

    public HttpResponse() {}

    public HttpResponse(
        int statusCode,
        String status,
        String message,
        Object data
    ) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static HttpResponse getInstance(
        int statusCode,
        String status,
        String message,
        Object data
    ) {
        return new HttpResponse(
            statusCode,
            status,
            message,
            data
        );
    }

    public ResponseEntity<?> getResponse200(String message, Object data) {
        var httpResponse = HttpResponse.getInstance(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                message,
                data
        );
        return ResponseEntity.status(HttpStatus.OK).body(httpResponse);
    }

    public ResponseEntity<?> getResponse(int statusCode, String message) {
        var httpResponse = HttpResponse.getInstance(
                statusCode,
                Integer.toString(statusCode),
                message,
                null
        );
        return ResponseEntity.status(statusCode).body(httpResponse);
    }

    public ResponseEntity<?> getResponse(int statusCode, String message, Object data) {
        var httpResponse = HttpResponse.getInstance(
                statusCode,
                Integer.toString(statusCode),
                message,
                data
        );
        return ResponseEntity.status(statusCode).body(httpResponse);
    }

    public ResponseEntity<?> getResponse400(String message) {
        var httpResponse = HttpResponse.getInstance(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                message,
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpResponse);
    }

    public ResponseEntity<?> getResponse500(String message) {
        var httpResponse = HttpResponse.getInstance(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                message,
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(httpResponse);
    }

}
