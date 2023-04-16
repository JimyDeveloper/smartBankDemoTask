package com.smartbank.demo.model.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class NotFoundException extends AbstractThrowableProblem {

  String userMessage;
  String developerMessage;

  public NotFoundException(String userMessage) {
    super(null, userMessage, Status.NOT_FOUND, userMessage);
    this.userMessage = userMessage;
  }

  public NotFoundException(String userMessage, String developerMessage) {
    super(null, userMessage, Status.NOT_FOUND, developerMessage);
    this.userMessage = userMessage;
    this.developerMessage = developerMessage;
  }
}
