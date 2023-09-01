package java18_to_21.structured_concurrency;

import java18_to_21.structured_concurrency.model.WeatherApiClient;
import java18_to_21.structured_concurrency.model.WeatherInfo;

import java.util.concurrent.ExecutionException;

import static java18_to_21.structured_concurrency.util.SimpleLogger.log;

public class CombinedWeatherInfoDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CombinedWeatherInfoDemo demo = new CombinedWeatherInfoDemo();
    WeatherInfo weatherInfo = demo.loadCombinedWeatherInfo();
    log("Result: " + weatherInfo);
  }

  WeatherInfo loadCombinedWeatherInfo() throws InterruptedException, ExecutionException {
    try (var scope = new CombinedWeatherInfoTaskScope()) {
      log("Forking tasks");
      scope.fork(() -> WeatherApiClient.loadWeatherInfo("provider-a"));
      scope.fork(() -> WeatherApiClient.loadWeatherInfo("provider-b"));
      scope.fork(() -> WeatherApiClient.loadWeatherInfo("provider-c"));
      scope.fork(() -> WeatherApiClient.loadWeatherInfo("provider-d"));

      log("Waiting for result combined from first two responses");
      scope.join();

      log("Retrieving result");
      WeatherInfo result = scope.result();

      log("Done");
      return result;
    }
  }
}
