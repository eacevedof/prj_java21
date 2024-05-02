package shared.infrastructure.http.requests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public final class POSTRequest {
    public static POSTRequest getInstance() {
        return new POSTRequest();
    }

    public String get(String urlEndpoint) throws Exception {
        URL objUrl = URI.create(urlEndpoint).toURL();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(objUrl.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(strLine);
        }
        bufferedReader.close();
        return stringBuilder.toString();

        URL url = new URL("https://api.example.com/resource");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        String requestBody = "param1=value1&param2=value2";

        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(requestBody);
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }
}
