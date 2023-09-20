package java18_to_21.structured_concurrency.util;

public final class SimpleLogger {

  private SimpleLogger() {}

  public static void log(String description) {
    System.out.printf("[%s] %s%n", Thread.currentThread(), description);
  }
}
