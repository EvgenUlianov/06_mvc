package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.BadRequestException;
import ru.netology.exception.HasBeenDeleted;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
@Repository
public class PostRepository {
  
  private final Map<Long, Post> posts;
  private AtomicLong index;

  public PostRepository() {
    posts = new ConcurrentSkipListMap<>();
    index = new AtomicLong(-1L);
  }

  public List<Post> all() {
    Comparator<Post> comparator = (o1, o2) -> (int) (o1.getId() - o2.getId());
//    List<Post> result = new ArrayList<>();
//    result.addAll(posts.values().stream().toList());
//    return result;
    return posts.values().stream()
            .filter((post)->!post.isDeletionMark())
            .sorted(comparator)
            .toList();
  }

  public Post getById(long id)  throws NotFoundException, HasBeenDeleted {
    if (posts.containsKey(id)){
      Post post = posts.get(id);
      if (post.isDeletionMark())
        throw new HasBeenDeleted("Has been deleted");
      else
        return post;
    } else
      throw new NotFoundException("Has not found");
  }

  public Post save(Post post)  throws BadRequestException {
    long localIndex = index.incrementAndGet();
    posts.put(localIndex, post);
    post.setId(localIndex);
    return post;
  }

  public Post save(long id, Post post)  throws BadRequestException, NotFoundException, HasBeenDeleted {
    if (posts.containsKey(id)){
      if (posts.get(id).isDeletionMark())
        throw new HasBeenDeleted("Has been deleted");
      posts.put(id, post);
      return posts.get(id);
    }
    else {
      throw new NotFoundException("Has not found");
    }
  }

  public void removeById(long id) {
    if (posts.containsKey(id))
      posts.get(id).setDeletionMark(true);
  }
}
