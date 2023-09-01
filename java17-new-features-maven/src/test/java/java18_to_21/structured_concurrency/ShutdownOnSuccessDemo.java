package java18_to_21.structured_concurrency;

import java18_to_21.structured_concurrency.model.WeatherApiClient;
import java18_to_21.structured_concurrency.model.WeatherInfo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

import static java18_to_21.structured_concurrency.util.SimpleLogger.log;

public class ShutdownOnSuccessDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ShutdownOnSuccessDemo demo = new ShutdownOnSuccessDemo();
    demo.loadWeatherInfo();
  }

  WeatherInfo loadWeatherInfo() throws InterruptedException, ExecutionException {
    try (var scope = new StructuredTaskScope.ShutdownOnSuccess<WeatherInfo>()) {
      log("Forking tasks");
      scope.fork(() -> WeatherApiClient.loadWeatherInfo("provider-a"));
      scope.fork(() -> WeatherApiClient.loadWeatherInfo("provider-b"));
      scope.fork(() -> WeatherApiClient.loadWeatherInfo("provider-c"));

      log("Waiting for one task to finish");
      scope.join();

      log("Retrieving result");
      WeatherInfo result = scope.result();

      log("Done");
      return result;
    }
  }
}
