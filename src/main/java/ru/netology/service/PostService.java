package ru.netology.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.exception.BadRequestException;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository repository;

//  public PostService(PostRepository repository) {
//    this.repository = repository;
//  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) throws NotFoundException {
    final Post post = repository.getById(id);
    if (post == null)
      throw new NotFoundException("Has not found");
    if (post.isDeletionMark())
      throw new NotFoundException("Has been deleted, contact admin to restore");

    return post;
  }

  public Post save(Post post) throws BadRequestException {
    return repository.save(post);
  }

  public void removeById(long id) {
    repository.removeById(id);
  }
}

