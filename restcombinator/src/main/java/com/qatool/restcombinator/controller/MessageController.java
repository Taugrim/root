package com.qatool.restcombinator.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qatool.restcombinator.exeptions.NotFoundExeption;
import combinator.Combinator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static combinator.Combinator.combinationsUUID;
import static combinator.Combinator.fabric;

@Slf4j
@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 4;
    public List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "first");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "second");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "thrid");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        combinationsUUID(List.of(
                        fabric("a", List.of("a1")),
                        fabric("q", List.of("q1", "q2")),
                        fabric("w", List.of("w1", "w2", "w3")),
                        fabric("e", List.of("e1", "e2")),
                        fabric("r", List.of("r1"))
                )
        );
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getMessage(id);
    }

    private Map<String, String> getMessage(String id) {
        return messages.stream().filter(q -> q.get("id").equals(id)).
                findFirst().orElseThrow(NotFoundExeption::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        log.info("create {}", String.valueOf(message.get("text")));
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }
 @PostMapping("{json}")
//    @GetMapping("{json}")
//    public  String createJson(@PathVariable  String json) {
//    public  String createJson(@RequestBody String message) {
    public   Map<UUID, Map<String, String>>  createJson(@RequestBody String json) {
//    public   Map<UUID, Map<String, String>>  createJson(@RequestBody String message) {
     ObjectMapper objectMapper = new ObjectMapper();
     List<Map.Entry<UUID, Map<String, String>>> json1=null;
     String s="";
     try {
         Map<String, Object> map
                 = objectMapper.readValue(json,Map.class);
        json1 = combinationsUUID(((Map<String, Object>) objectMapper
                 .readValue(json, Map.class).get("json"))
                 .entrySet().stream().map(q -> fabric((String) q.getKey(), (List<String>) q.getValue()))
                 .collect(Collectors.toList()));
          s = objectMapper.writeValueAsString(json1);
          log.info("json={}",s);
     } catch (JsonProcessingException e) {

     }
//     log.info("create {}", String.valueOf(message.get("text")));
//        message.put("id", String.valueOf(counter++));
//        messages.add(message);
        return json1.stream() .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
//        return json;
//        return s;
//        return "jhlkjhkl";
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        log.info("update {}", String.valueOf(message.get("text")));
        Map<String, String> messageFromDb = getMessage(id);
        messageFromDb.putAll(message);
        messageFromDb.put("id", id);
        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable String id) {
        log.info("delete {}", id);
        Map<String, String> message = getMessage(id);
        messages.remove(message);
        return id;
    }
}
