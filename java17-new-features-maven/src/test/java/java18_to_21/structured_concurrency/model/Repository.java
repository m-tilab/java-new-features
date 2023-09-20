package java18_to_21.structured_concurrency.model;

import java.util.concurrent.ThreadLocalRandom;

import static java18_to_21.structured_concurrency.util.SimpleLogger.log;

public final class Repository {

  private Repository() {}

  public static Order loadOrderFromOrderService(int orderId) throws InterruptedException {
    log("Loading order");
    try {
      Thread.sleep(ThreadLocalRandom.current().nextLong(500, 1000));
    } catch (InterruptedException e) {
      log("Order loading was interrupted");
      throw e;
    }

    if (ThreadLocalRandom.current().nextDouble() > 0.8) {
      log("Error loading order");
      throw new RuntimeException("Error loading order");
    }

    log("Finished loading order");
    return new Order();
  }

  public static Customer loadCustomerFromDatabase(int customerId) throws InterruptedException {
    log("Loading customer");
    try {
      Thread.sleep(ThreadLocalRandom.current().nextLong(500, 1000));
    } catch (InterruptedException e) {
      log("Customer loading was interrupted");
      throw e;
    }

    if (ThreadLocalRandom.current().nextDouble() > 0.8) {
      log("Error loading customer");
      throw new RuntimeException("Error loading customer");
    }

    log("Finished loading customer");
    return new Customer();
  }

  public static String loadInvoiceTemplateFromFile(String language) throws InterruptedException {
    log("Loading template");
    try {
      Thread.sleep(ThreadLocalRandom.current().nextLong(500, 1000));
    } catch (InterruptedException e) {
      log("Template loading was interrupted");
      throw e;
    }

    if (ThreadLocalRandom.current().nextDouble() > 0.8) {
      log("Error loading template");
      throw new RuntimeException("Error loading template");
    }

    log("Finished loading template");
    return "TEMPLATE";
  }
}
