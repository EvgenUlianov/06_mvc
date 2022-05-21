package ru.netology.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HasBeenDeleted extends RuntimeException {
  public HasBeenDeleted() {
  }

  public HasBeenDeleted(String message) {
    super(message);
  }

  public HasBeenDeleted(String message, Throwable cause) {
    super(message, cause);
  }

  public HasBeenDeleted(Throwable cause) {
    super(cause);
  }

  public HasBeenDeleted(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
