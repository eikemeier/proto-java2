package com.example;

import com.example.v1.Hello;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.JsonFormat;
import java.io.UncheckedIOException;
import java.util.logging.Logger;

public final class ProtoTest {
  private static final Logger LOG = Logger.getLogger(ProtoTest.class.getSimpleName());

  private ProtoTest() {}

  public static void main(String... args) {
    Timestamp time = Timestamp.newBuilder().setSeconds(946_684_800L).build();
    Hello hello = Hello.newBuilder().setTime(time).build();
    LOG.info(() -> String.format("Hello %s", toJson(hello)));
  }

  private static String toJson(MessageOrBuilder message) {
    try {
      return JsonFormat.printer().print(message);
    } catch (InvalidProtocolBufferException e) {
      throw new UncheckedIOException(e);
    }
  }
}
