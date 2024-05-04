package com.eduardoaf.balance.shared.infrastructure.http.requests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

public final class GETRequest {

    private static final String METHOD_GET = "GET";
    private int statusCode;

    public static GETRequest getInstance() {
        return new GETRequest();
    }

    public String sendRequest(String urlEndpoint) throws Exception {
        statusCode = -1;
        URL objUrl = URI.create(urlEndpoint).toURL();
        HttpURLConnection connection = (HttpURLConnection) objUrl.openConnection();
        connection.setRequestMethod(METHOD_GET);
        statusCode = connection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String lineRed;
        StringBuilder responseAccumulator = new StringBuilder();
        while ((lineRed = bufferedReader.readLine()) != null) {
            responseAccumulator.append(lineRed);
        }
        bufferedReader.close();
        return responseAccumulator.toString();
    }

    public int getStatusCode() { return statusCode; }
}
