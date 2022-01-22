package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.BadRequestException;
import ru.netology.model.Post;

import java.util.*;

// Stub
@Repository
public class PostRepository {
  
  private final Map<Long, Post> posts;
  private Long index;

  public PostRepository() {
    posts = new HashMap<>();
    index = -1L;
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

  public Post getById(long id) {
    if (posts.containsKey(id))
      return posts.get(id);
    else
      return null;
  }

  public synchronized Post save(Post post)  throws BadRequestException {
    long id = post.getId();
    if (posts.containsKey(id)){
      if (posts.get(id).isDeletionMark())
        throw new BadRequestException("Has been deleted");
      posts.put(index, post);
      return posts.get(id);
    }
    else {
      index++;
      posts.put(index, post);
      post.setId(index);
      return post;
    }
  }

  public synchronized void removeById(long id) {
    if (posts.containsKey(id))
      posts.get(id).setDeletionMark(true);
  }
}
