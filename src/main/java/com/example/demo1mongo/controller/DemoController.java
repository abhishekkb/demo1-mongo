package com.example.demo1mongo.controller;

import com.example.demo1mongo.entity.SomeCollection;
import com.example.demo1mongo.model.DemoReq;
import com.example.demo1mongo.model.UpdateReq;
import com.example.demo1mongo.repo.DemoCollectionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private final DemoCollectionRepo repo;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SomeCollection create(@RequestBody DemoReq demoReq){
        return repo.create(demoReq);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}")
    public SomeCollection update(@PathVariable String id, @RequestBody DemoReq demoReq){
        return repo.update(id, demoReq);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/update-all")
    public void update(@RequestBody UpdateReq updateReq, @RequestParam Map<String,String> allParams){
        repo.update(updateReq, allParams);
    }

}
