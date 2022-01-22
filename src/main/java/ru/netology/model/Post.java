package ru.netology.model;

import lombok.Data;

@Data
public class Post {
  private long id;
  private String content;
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