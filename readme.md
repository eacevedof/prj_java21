- [deploy springboot code](https://github.com/todocodeacademy/clin_vet/tree/master/clinica_veterinaria)
- incluir springboot

#### mongo
- [mongodb](https://cloud.mongodb.com/v2/66354f14820bd01bda365c81#/overview?connectCluster=clusterdblogs0)
```java
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoClientConnectionExample {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://logsuser:<password>@clusterdblogs0.hqk4hhz.mongodb.net/?retryWrites=true&w=majority&appName=clusterdblogs0";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}
```
#### [fly.io](https://fly.io/docs/languages-and-frameworks/dockerfile/)

#### [redis](https://console.upstash.com/redis/69c1da72-4477-4869-a86d-c0bf83c3abd7)