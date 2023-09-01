package java18_to_21.structured_concurrency;

import java18_to_21.structured_concurrency.model.Customer;
import java18_to_21.structured_concurrency.model.Invoice;
import java18_to_21.structured_concurrency.model.Order;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java18_to_21.structured_concurrency.model.Repository.loadCustomerFromDatabase;
import static java18_to_21.structured_concurrency.model.Repository.loadInvoiceTemplateFromFile;
import static java18_to_21.structured_concurrency.model.Repository.loadOrderFromOrderService;
import static java18_to_21.structured_concurrency.util.SimpleLogger.log;

public class ExecutorServiceDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorServiceDemo demo = new ExecutorServiceDemo();
    demo.createInvoice(10012, 61157, "en");
    demo.shutDown();
  }

  private final ExecutorService executor = Executors.newCachedThreadPool();

  Invoice createInvoice(int orderId, int customerId, String language)
      throws ExecutionException, InterruptedException {
    log("Submitting tasks");

    Future<Order> orderFuture = executor.submit(() -> loadOrderFromOrderService(orderId));

    Future<Customer> customerFuture = executor.submit(() -> loadCustomerFromDatabase(customerId));

    Future<String> invoiceTemplateFuture =
        executor.submit(() -> loadInvoiceTemplateFromFile(language));

    log("Waiting for order");
    Order order = orderFuture.get();

    log("Waiting for customer");
    Customer customer = customerFuture.get();

    log("Waiting for template");
    String invoiceTemplate = invoiceTemplateFuture.get();

    log("Generating invoice");
    Invoice invoice = Invoice.generate(order, customer, invoiceTemplate);

    log("Done");
    return invoice;
  }

  private void shutDown() {
    executor.shutdown();
  }
}
