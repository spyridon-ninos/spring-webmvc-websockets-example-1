package com.ninos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.stream.IntStream;

@Component
public class MyWebsocketHandler implements WebSocketHandler {

  private static Logger logger = LoggerFactory.getLogger(MyWebsocketHandler.class);

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    String remoteHost = session
        .getRemoteAddress()
        .getHostName();

    String uri = session
        .getUri()
        .getPath();

    logger.info("host: {}, path: {}", remoteHost, uri);

    IntStream
      .range(0, 100)
      .forEach(i -> {
        try {
          session.sendMessage(new TextMessage(String.valueOf(i)));
        } catch (IOException e) {
          logger.error("Received error: {}", e.getMessage(), e);
        }
      });

    logger.info("Closing");

    try {
      session.close();
    } catch (IOException e) {
        logger.error("Received error: {}", e.getMessage(), e);
    }

    logger.info("Closed");
  }

  @Override
  public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    logger.info("Received message: {}", message.getPayload());
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    logger.info("Websocket closed: {}", closeStatus.getCode());
  }

  @Override
  public boolean supportsPartialMessages() {
    return false;
  }
}
