package com.qatool.restcombinator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qatool.restcombinator.exeptions.NotFoundExeption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import types.ContainerC;

import java.util.*;
import java.util.stream.Collectors;

import static combinator.Combinator.*;

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
    public String getJson()  {
           return   "["+combinationsC(List.of(
                           fabric("a", List.of("a1")),
                           fabric("q", List.of("q1", "q2")),
                           fabric("w", List.of("w1", "w2", "w3")),
                           fabric("e", List.of("e1", "e2")),
                           fabric("r", List.of("r1"))
                   )
           )
                   .stream().map(q->q.toString()).collect(Collectors.joining(","))+"]";
//       return List.of("1","22","3");
    }
 @PostMapping("{json}")
    public    List<ContainerC<String, String>>  createJson(@RequestBody String json) {
     ObjectMapper objectMapper = new ObjectMapper();
     List<ContainerC<String, String>> json1=null;
     String s="";
     try {
         Map<String, Object> map
                 = objectMapper.readValue(json,Map.class);
        json1 = combinationsC(((Map<String, Object>) objectMapper
                 .readValue(json, Map.class).get("json"))
                 .entrySet().stream().map(q -> fabric((String) q.getKey(), (List<String>) q.getValue()))
                 .collect(Collectors.toList()));
          s = objectMapper.writeValueAsString(json1);
          log.info("json={}",s);
     } catch (JsonProcessingException e) {

     }
        return json1;
    }

}
