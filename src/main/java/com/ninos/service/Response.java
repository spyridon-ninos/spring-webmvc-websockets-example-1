package com.ninos.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
  private String response;

  public Response() {
    // default ctor
  }

  public Response(String response) {
    this.response = response;
  }

  public String getResponse() {
    return response;
  }

  @JsonProperty("response")
  public void setResponse(String response) {
    this.response = response;
  }
}
