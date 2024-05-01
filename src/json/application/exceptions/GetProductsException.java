package json.application.exceptions;

import shared.infrastructure.exceptions.AbstractException;

public final class GetProductsException extends AbstractException {

    public GetProductsException(String message, int statusCode) {
        super(message, statusCode);
    }

    public static void ErrorOnReadingEndpoint(String endpoint) throws Exception {
        throw new GetProductsException("Error reading endpoint:".concat(endpoint), 500);
    }
}
