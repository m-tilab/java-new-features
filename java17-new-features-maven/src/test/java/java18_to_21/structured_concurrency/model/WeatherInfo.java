package java18_to_21.structured_concurrency.model;

public record WeatherInfo(double temperature) {
  public WeatherInfo combineWith(WeatherInfo other) {
    return new WeatherInfo((this.temperature + other.temperature) / 2.0);
  }
}
