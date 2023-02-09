package com.qatool.restcombinator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qatool.restcombinator.exeptions.NotFoundExeption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static combinator.Combinator.combinationsUUID;
import static combinator.Combinator.fabric;

@Slf4j
@RestController
@RequestMapping("js/js")
public class JsController {
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



    private Map<String, String> getMessage(String id) {
        return messages.stream().filter(q -> q.get("id").equals(id)).
                findFirst().orElseThrow(NotFoundExeption::new);
    }

   @GetMapping
//    public List<String> getJson()  {
    public List<Map.Entry<UUID, Map<String, String>>> getJson()  {
           return   combinationsUUID(List.of(
                           fabric("a", List.of("a1")),
                           fabric("q", List.of("q1", "q2")),
                           fabric("w", List.of("w1", "w2", "w3")),
                           fabric("e", List.of("e1", "e2")),
                           fabric("r", List.of("r1"))
                   )
           );
//       return List.of("1","22","3");
    }
 @PostMapping("{json}")
    public   Map<UUID, Map<String, String>>  createJson(@RequestBody String json) {
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
        return json1.stream() .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

}
