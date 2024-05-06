package com.eduardoaf.balance.shared.infrastructure.http.responses;

import com.eduardoaf.balance.shared.infrastructure.enums.HttpStatusCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public record HttpResponse(
    int statusCode,
    String message,
    Object data
) {

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

    public static HttpResponse getInstance(
            int statusCode,
            Object data
    ) {
        return new HttpResponse(
            statusCode,
            "",
            data
        );
    }

    public ResponseEntity<?> getResponse(
        int statusCode,
        String message,
        Object data
    ) {
        return ResponseEntity.status(statusCode)
                    .body(HttpResponse.getInstance(
                        statusCode,
                        message,
                        data
                    ));
    }

    public ResponseEntity<?> getResponse(
            int statusCode,
            String message
    ) {
        //HttpStatus.INTERNAL_SERVER_ERROR
        return ResponseEntity.status(statusCode)
                .body(HttpResponse.getInstance(
                        statusCode,
                        message,
                        null
                ));
    }

    public ResponseEntity<?> getResponse200(String message, Object data) {
        int statusCode = HttpStatus.OK.value();
        var httpResponse = HttpResponse.getInstance(
                statusCode,
                message,
                data
        );
        return ResponseEntity.status(HttpStatus.OK).body(httpResponse);
    }    
}
