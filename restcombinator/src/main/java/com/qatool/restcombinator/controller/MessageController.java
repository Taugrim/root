package com.qatool.restcombinator.controller;

import com.qatool.restcombinator.exeptions.NotFoundExeption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
