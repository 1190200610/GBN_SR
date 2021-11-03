package com.example.GBN_SR;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.*;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.net.SocketAddress;


public class UdpServer extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    DatagramSocket server_socket = vertx.createDatagramSocket(new DatagramSocketOptions());
    server_socket.listen(1234, "0.0.0.0", asyncResult -> {
      System.out.println("开始建立连接");
      if (asyncResult.succeeded()) {
        // 获取本地服务地址和端口
        SocketAddress address = server_socket.localAddress();
        String localHost = address.host();
        int localPort = address.port();
        System.out.println("服务器：" + localHost +":"+ localPort);
        server_socket.handler(packet -> {
          // 获取远端访问地址和端口，并且返回消息
          SocketAddress sendAddress = packet.sender();
          String senderHost = sendAddress.host();
          int senderPort = sendAddress.port();
          System.out.println("客户端：" + senderHost+":"+senderPort);
          String result = packet.data().toString();
          System.out.println("服务器接收到的数据：" + result);
          server_socket.send("Message got", senderPort, senderHost);
        });
      } else {
        System.out.println("监听失败！");
      }
    });
  }

}
