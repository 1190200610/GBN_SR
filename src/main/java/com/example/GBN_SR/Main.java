package com.example.GBN_SR;

import io.vertx.core.Vertx;

public class Main {
  public static void main(String[] args) {
    Vertx.vertx().deployVerticle(new UdpClient());
    Vertx.vertx().deployVerticle(new UdpServer());
  }
}
