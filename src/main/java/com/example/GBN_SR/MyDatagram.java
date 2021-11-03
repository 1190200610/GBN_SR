package com.example.GBN_SR;

public class MyDatagram {
  private int seq;
  private String data;
  private final static String END = "EOF0";

  public MyDatagram(int seq, String data) {
    this.seq = seq;
    this.data = data;
  }

  public int getSeq() {
    return seq;
  }

  public String getData() {
    return data;
  }

  public void setSeq(int seq) {
    this.seq = seq;
  }

  public void setData(String data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "seq=" + seq +
      ", data=" + data + ", end="+ END;
  }
}
