package org.jfm.domain.exceptions;

public class SqsException extends RuntimeException {
  public SqsException(String message) {
    super(message);
  }
}
