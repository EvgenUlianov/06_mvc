package ru.netology.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.exception.BadRequestException;
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
  public Post getById(@PathVariable long id) throws NotFoundException{
    return service.getById(id);
  }

  @PostMapping
  public Post save(@RequestBody Post post)  throws BadRequestException {
    final Post data = service.save(post);
    return data;
  }

  @DeleteMapping("/{id}")
  public String removeById(@PathVariable long id) {
    service.removeById(id);
    return "";
  }
}
