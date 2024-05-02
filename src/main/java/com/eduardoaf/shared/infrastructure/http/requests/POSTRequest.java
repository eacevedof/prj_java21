package com.eduardoaf.shared.infrastructure.http.requests;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public final class POSTRequest {

    private static final String METHOD_POST = "POST";
    private int statusCode;

    public static POSTRequest getInstance() {
        return new POSTRequest();
    }

    public String sendRequest(String urlEndpoint, Map<String, String> postPayload) throws Exception {
        statusCode = -1;
        URL objUrl = URI.create(urlEndpoint).toURL();

        HttpURLConnection httpConnection = (HttpURLConnection) objUrl.openConnection();
        httpConnection.setRequestMethod(METHOD_POST);
        httpConnection.setDoOutput(true);

        Map<String, String> dictionary = new HashMap<>();
        String requestBody = getHashmapAsString(postPayload);

        DataOutputStream outputStream = new DataOutputStream(httpConnection.getOutputStream());
        outputStream.writeBytes(requestBody);
        outputStream.flush();
        outputStream.close();

        statusCode = httpConnection.getResponseCode();
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        StringBuilder responseAccumulator = new StringBuilder();

        String strLineRed;
        while ((strLineRed = bufferReader.readLine()) != null) {
            responseAccumulator.append(strLineRed);
        }
        bufferReader.close();
        return responseAccumulator.toString();
    }

    private String getHashmapAsString(Map<String, String> keysValues) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : keysValues.entrySet()) {
            if (!stringBuilder.isEmpty()) {
                stringBuilder.append("&");
            }
            stringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return stringBuilder.toString();
    }

    public int getStatusCode() { return statusCode; }
}
