package shared.infrastructure.file;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public final class FileGetContent {

    public static FileGetContent getInstance() {
        return new FileGetContent();
    }

    public String GetContentFromUrlOrFail(String urlEndpoint) throws Exception
    {
        // URL of the JSON file
        URL url = URI.create(urlEndpoint).toURL();
        // Open connection to the URL
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(strLine);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
