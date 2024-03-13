package com.example.MicroserviceRamen.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.lettuce.core.RedisClient;

@Configuration
public class ConfigFile {
    
    // Variabile definita in application.properties che prende il path dove stabilire la connessione
    @Value("${connection.variable}")
    private String connectionValueString;

    // Bean per settare il RedisClient, in questo modo sarà instanziato una sola ed unica volta e sempre disponibile in tutto il progetto
    @Bean
    public RedisClient connectionRedisClient() {
        return RedisClient.create(connectionValueString);
    }
}
