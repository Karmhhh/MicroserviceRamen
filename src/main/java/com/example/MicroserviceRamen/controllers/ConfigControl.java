package com.example.MicroserviceRamen.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.MicroserviceRamen.Service.EncriptService;
import com.example.MicroserviceRamen.Service.ServiceConfig;
import com.example.MicroserviceRamen.dto.ConfigObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class ConfigControl {

    @Autowired
    private ServiceConfig configService;

    @GetMapping("/fratm")
    public ResponseEntity<ConfigObj> getMethodName() {
        ConfigObj obj = new ConfigObj();
        obj.setApiCallName("/huehuehue");
        obj.setApiCallUrl("FRAAAAATM.it");
        obj.setApiRequestMethod("POST");
        obj.setResponse(5);
        obj.setResponseBody("dhvcuevh");
        return ResponseEntity.ok(obj);
    }

    // Aggiunge le condfigurazioni, nel body aggiungi questo json :
    /*
     * {
     * "apiCallName": "",
     * "apiCallUrl": "",
     * "apiRequestMethod": "",
     * "response": "int",
     * "responseBody": ""
     * }
     */
    @PostMapping("/addConfig")
    public ResponseEntity<String> addConfig(@RequestBody ConfigObj entity) {

        configService.addConfiguration(EncriptService.encrypt(entity.toString()), entity.getApiCallName());

        return ResponseEntity
                .ok("Configuratin Added. \n" + entity.toString() + EncriptService.encrypt(entity.toString()));
    }

    @DeleteMapping("/deleteConfig")
    public ResponseEntity<String> deleteConfig(@RequestParam String entityName) {

        configService.deleteConfigByKey(entityName);

        return ResponseEntity.ok("Configuratin with key " + entityName + " Deleted. \n");
    }

    @GetMapping("/getByKey")
    public ResponseEntity<String> getByKay(@RequestParam String entityName) {
        String decoded = EncriptService.decrypt(configService.getConfigByKey(entityName));
        return ResponseEntity.ok("Your Configuration: >>>\n" + decoded);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        String x = configService.deleteAllKeys();
        return ResponseEntity.ok(x);
    }

    @GetMapping("/getAllConfig")
    public String getAll() {

        return configService.getAllKeys();
    }

}
