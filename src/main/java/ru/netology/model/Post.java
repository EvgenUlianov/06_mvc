package ru.netology.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
public class Post {

  @Expose
  private long id;

  @Expose
  private String content;

  @Expose(deserialize = false, serialize = false)
  private boolean deletionMark;

  public Post() {
  }

  public Post(long id, String content) {
    this.id = id;
    this.content = content;
    this.deletionMark = false;
  }

//  public long getId() {
//    return id;
//  }
//
//  public void setId(long id) {
//    this.id = id;
//  }
//
//  public String getContent() {
//    return content;
//  }
//
//  public void setContent(String content) {
//    this.content = content;
//  }
}
