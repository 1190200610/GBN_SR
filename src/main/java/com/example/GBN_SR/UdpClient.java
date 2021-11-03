package com.example.GBN_SR;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.*;
import io.vertx.core.datagram.DatagramSocketOptions;


public class UdpClient extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    DatagramSocket client_socket = vertx.createDatagramSocket(new DatagramSocketOptions());
    Buffer buffer = Buffer.buffer("content");
    client_socket.send(buffer, 1234, "127.0.0.1", asyncResult -> {
      if (asyncResult.succeeded()) {
        client_socket.handler(packet -> {
          String result = packet.data().toString();
          long timeID = vertx.setTimer(2000, id -> {
            System.out.println("And one second later this is printed");
            System.out.println("客户端收到回复：" + result);
          });
        });
      } else {
        System.out.println("连接失败！");
      }

    });
  }
}
