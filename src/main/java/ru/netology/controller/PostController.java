package ru.netology.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService service;

  @GetMapping
  public List<Post> all() {
    final List<Post> data = service.all();
    return data;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Post> getById(@PathVariable long id){
    Post post;
    try {
      post = service.getById(id);
    }catch (NotFoundException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @PostMapping
  public Post  save(@RequestBody Post post) {
    return service.save(post);
  }

  @PatchMapping
  public ResponseEntity<Post> saveWithId(@RequestBody Post post) {
    final Post data;
    try {
      data = service.saveWithId(post);
    }catch (NotFoundException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(data, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public void removeById(@PathVariable long id) {
    service.removeById(id);
  }
}
