package ru.netology.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.exception.BadRequestException;
import ru.netology.exception.HasBeenDeleted;
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
    }catch (HasBeenDeleted e){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<Post>(post, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Post>  save(@RequestBody Post post) {
    final Post data;
    try {
      data = service.save(post);
    }catch (BadRequestException e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Post>(data, HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Post>  save(@PathVariable long id, @RequestBody Post post) {
    final Post data;
    try {
      data = service.save(id, post);
    }catch (HasBeenDeleted e){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }catch (BadRequestException e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }catch (NotFoundException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Post>(data, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public String removeById(@PathVariable long id) {
    service.removeById(id);
    return "";
  }
}
