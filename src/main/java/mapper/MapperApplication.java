package mapper;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MapperApplication {

    public static void main(String[] args) {

        MongoClient mongoClient = MongoClients.create("mongodb+srv://francisca:ifes@mapper-2x1ao.mongodb.net/test?retryWrites=true&w=majority");

        SpringApplication.run(MapperApplication.class, args);
    }

}
