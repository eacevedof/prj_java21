package shared.infrastructure.file;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public final class FileGetContent {

    public static FileGetContent getInstance() {
        return new FileGetContent();
    }
    
    @org.jetbrains.annotations.NotNull
    public String GetContentFromUrlOrFail(String urlEndpoint)
    {
        // URL of the JSON file
        URL url = URI.create(urlEndpoint).toURL();

        // Open connection to the URL
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        // Read data from the URL
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();

        return stringBuilder.toString();
        // Print the content fetched from the URL
        System.out.println(stringBuilder.toString());
    }
}
