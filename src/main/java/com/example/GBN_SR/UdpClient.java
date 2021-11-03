package com.example.GBN_SR;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.*;
import io.vertx.core.datagram.DatagramSocketOptions;


public class UdpClient extends AbstractVerticle {
  //  private static int base = Data.BASE;
//  private static int nextSeq = Data.NEXT_SEQ;
//  private static int windowSize = Data.SIZE_WINDOWS;
//  private static int seqSize = Data.SIZE_SQE;
  private static final String DATA = "DATA";
  private static int status = 0;
  private static int numPack = 20;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    for (int i = 0; i < numPack; i++) {
      DatagramSocket client_socket = vertx.createDatagramSocket(new DatagramSocketOptions());
      System.out.println("客户端发送的ack: " + status);
      Buffer buffer = Buffer.buffer(new MyDatagram(status, DATA).toString());
      client_socket.send(buffer, 1234, "127.0.0.1", asyncResult -> {
        if (asyncResult.succeeded()) {
          client_socket.handler(packet -> {
            String result = packet.data().toString();
            System.out.println("服务器确认的ack：" + result);
            System.out.println("\n");
            status = status == 0 ? 1 : 0;
          });
        } else {
          System.out.println("连接失败！");
        }
      });
    }

  }
}

