package java18_to_21.structured_concurrency;

import java18_to_21.structured_concurrency.model.WeatherInfo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.atomic.AtomicReference;

public class CombinedWeatherInfoTaskScope extends StructuredTaskScope<WeatherInfo> {

  private final AtomicReference<WeatherInfo> firstResult = new AtomicReference<>();
  private final AtomicReference<WeatherInfo> secondResult = new AtomicReference<>();
  private final AtomicReference<Throwable> firstException = new AtomicReference<>();

  @Override
  protected void handleComplete(Subtask<? extends WeatherInfo> subtask) {
    if (subtask.state() == Subtask.State.SUCCESS) {
      WeatherInfo result = subtask.get();
      if (!firstResult.compareAndSet(null, result)) {
        if (secondResult.compareAndSet(null, result)) {
          shutdown();
        }
      }
    } else {
      firstException.compareAndSet(null, subtask.exception());
    }
  }

  public WeatherInfo result() throws ExecutionException {
    WeatherInfo firstWeatherInfo = firstResult.get();
    WeatherInfo secondWeatherInfo = secondResult.get();
    if (firstWeatherInfo != null && secondWeatherInfo != null) {
      return firstWeatherInfo.combineWith(secondWeatherInfo);
    }

    Throwable exception = firstException.get();
    if (exception != null) {
      throw new ExecutionException(exception);
    }

    throw new IllegalStateException("Neither two completed subtasks nor an exception");
  }
}
