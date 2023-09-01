package java18_to_21.structured_concurrency;

import java18_to_21.structured_concurrency.model.Customer;
import java18_to_21.structured_concurrency.model.Invoice;
import java18_to_21.structured_concurrency.model.Order;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

import static java18_to_21.structured_concurrency.model.Repository.loadCustomerFromDatabase;
import static java18_to_21.structured_concurrency.model.Repository.loadInvoiceTemplateFromFile;
import static java18_to_21.structured_concurrency.model.Repository.loadOrderFromOrderService;
import static java18_to_21.structured_concurrency.util.SimpleLogger.log;

public class StructuredConcurrencyDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    StructuredConcurrencyDemo demo = new StructuredConcurrencyDemo();
    demo.createInvoice(10012, 61157, "en");
  }

  Invoice createInvoice(int orderId, int customerId, String language) throws InterruptedException {
    try (var scope = new StructuredTaskScope<>()) {
      log("Forking tasks");
      Subtask<Order> orderSubtask = scope.fork(() -> loadOrderFromOrderService(orderId));

      Subtask<Customer> customerSubtask = scope.fork(() -> loadCustomerFromDatabase(customerId));

      Subtask<String> invoiceTemplateSubtask =
          scope.fork(() -> loadInvoiceTemplateFromFile(language));

      log("Waiting for all tasks to finish");
      scope.join();

      log("Retrieving results");
      Order order = orderSubtask.get();
      Customer customer = customerSubtask.get();
      String invoiceTemplate = invoiceTemplateSubtask.get();

      log("Generating invoice");
      Invoice invoice = Invoice.generate(order, customer, invoiceTemplate);

      log("Done");
      return invoice;
    }
  }
}
