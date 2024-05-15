package com.eduardoaf.balance.mod_shared.infrastructure.enums;

public enum HttpStatusCodeEnum {
    // Informational
    CONTINUE(100),
    SWITCHING_PROTOCOLS(101),
    PROCESSING(102),

    // HttpResponse
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204),
    RESET_CONTENT(205),
    PARTIAL_CONTENT(206),

    // Redirection
    MOVED_PERMANENTLY(301),
    FOUND(302),
    SEE_OTHER(303),
    NOT_MODIFIED(304),
    TEMPORARY_REDIRECT(307),
    PERMANENT_REDIRECT(308),

    // Client Error
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    CONFLICT(409),
    GONE(410),
    LENGTH_REQUIRED(411),
    PAYLOAD_TOO_LARGE(413),
    UNSUPPORTED_MEDIA_TYPE(415),
    TOO_MANY_REQUESTS(429),

    // Server Error
    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    SERVICE_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504);

    private final int code;

    HttpStatusCodeEnum(int code) {
        this.code = code;
    }

    public int getValue() {
        return code;
    }
}
