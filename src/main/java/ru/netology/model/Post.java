package ru.netology.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Post {
  @Expose(deserialize = false) // doesn't work too
  private long id;

  @Expose
  private String content;

  @JsonIgnore // doesn't work
  @Expose(deserialize = false, serialize = false) // doesn't work too
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
