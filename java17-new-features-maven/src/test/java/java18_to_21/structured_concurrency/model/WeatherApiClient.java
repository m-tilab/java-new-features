package java18_to_21.structured_concurrency.model;

import java.util.concurrent.ThreadLocalRandom;

import static java18_to_21.structured_concurrency.util.SimpleLogger.log;

public final class WeatherApiClient {

  private WeatherApiClient() {}

  public static WeatherInfo loadWeatherInfo(String provider) throws InterruptedException {
    log("Loading weather info from provider " + provider);
    try {
      Thread.sleep(ThreadLocalRandom.current().nextLong(500, 1000));
    } catch (InterruptedException e) {
      log("Loading weather info from provider " + provider + " was interrupted");
      throw e;
    }

    if (ThreadLocalRandom.current().nextDouble() > 0.8) {
      log("Error loading weather info from provider " + provider);
      throw new RuntimeException("Error loading weather info from provider " + provider);
    }

    WeatherInfo weatherInfo = new WeatherInfo(ThreadLocalRandom.current().nextDouble(0.0, 45.0));
    log("Finished loading weather info from provider " + provider + ": " + weatherInfo);
    return weatherInfo;
  }
}
