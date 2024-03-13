package com.example.MicroserviceRamen.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MicroserviceRamen.dto.ConfigObj;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@Service
public class ServiceConfig {
    // Il client
    @Autowired
    private RedisClient client;


    public void addConfiguration(ConfigObj obj) {
        // Connessione sincrona al server Redis
        try (StatefulRedisConnection<String, String> connection = client.connect()) {
            // Eseguire operazioni su Redis
            RedisCommands<String, String> syncCommands = connection.sync();
            syncCommands.set(obj.getApiCallName(), obj.toString()); // Salva un oggetto stringa

        } // La connessione viene chiusa automaticamente alla fine del blocco try

        // Chiudi il client Lettuce
        client.shutdown();
    }

    public void deleteConfigByKey(String key) {
        try (StatefulRedisConnection<String, String> connection = client.connect()) {
            RedisCommands<String, String> syncCommands = connection.sync();
            syncCommands.del(key);
        }
        client.shutdown();

    }

    public String getConfigByKey(String key) {
        try (StatefulRedisConnection<String, String> connection = client.connect()) {
            RedisCommands<String, String> syncCommands = connection.sync();
            String myConfig = syncCommands.get(key);
            return myConfig;
        }

    }

    public String deleteAllKeys() {
        try (StatefulRedisConnection<String, String> connection = client.connect()) {
            RedisCommands<String, String> syncCommands = connection.sync();

            // Elimina tutte le chiavi e i relativi valori
            syncCommands.flushall();

            return "All Configurations deleted";

        }
    }

    public String getAllKeys() {
        try (StatefulRedisConnection<String, String> connection = client.connect()) {
            RedisCommands<String, String> syncCommands = connection.sync();

            // Ottenere tutte le chiavi
            Iterable<String> keys = syncCommands.keys("*");

            StringBuilder result = new StringBuilder();
            for (String key : keys) {
                // Ottenere il valore associato alla chiave
                String value = syncCommands.get(key);
                result.append("# Key: ").append(key).append("\n - Value: ").append(value).append("\n");
            }

            return result.toString();

        }
    }
}
