package java18_to_21.structured_concurrency;

import java18_to_21.structured_concurrency.model.Customer;
import java18_to_21.structured_concurrency.model.Invoice;
import java18_to_21.structured_concurrency.model.Order;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

import static java18_to_21.structured_concurrency.model.RepositoryWithScopedValue.loadCustomerFromDatabase;
import static java18_to_21.structured_concurrency.model.RepositoryWithScopedValue.loadInvoiceTemplateFromFile;
import static java18_to_21.structured_concurrency.model.RepositoryWithScopedValue.loadOrderFromOrderService;
import static java18_to_21.structured_concurrency.util.SimpleLogger.log;

public class StructuredConcurrencyWithScopedValueDemo {

    public static void main(String[] args) throws Exception {
        StructuredConcurrencyWithScopedValueDemo demo = new StructuredConcurrencyWithScopedValueDemo();
        demo.createInvoice(10012, 61157, "en");
    }

    public static final ScopedValue<String> INVOICE_NUMBER = ScopedValue.newInstance();

    Invoice createInvoice(int orderId, int customerId, String language) throws Exception {
        return ScopedValue.callWhere(INVOICE_NUMBER, "2023-437",
                () -> {
                    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                        log("Forking tasks");
                        Subtask<Order> orderSubtask = scope.fork(() -> loadOrderFromOrderService(orderId));

                        Subtask<Customer> customerSubtask =
                                scope.fork(() -> loadCustomerFromDatabase(customerId));

                        Subtask<String> invoiceTemplateSubtask =
                                scope.fork(() -> loadInvoiceTemplateFromFile(language));

                        log("Waiting for all tasks to finish");
                        scope.join();
                        scope.throwIfFailed();

                        log("Retrieving results");
                        Order order = orderSubtask.get();
                        Customer customer = customerSubtask.get();
                        String invoiceTemplate = invoiceTemplateSubtask.get();

                        log("Generating invoice");
                        Invoice invoice = Invoice.generate(order, customer, invoiceTemplate);

                        log("Done");
                        return invoice;
                    }
                });
    }

}
