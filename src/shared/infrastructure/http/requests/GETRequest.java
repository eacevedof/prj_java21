package shared.infrastructure.http.requests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public final class GETRequest {
    public static GETRequest getInstance() {
        return new GETRequest();
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
    }
}
