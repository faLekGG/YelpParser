package com.varteq.service.adapter;

import java.util.function.Consumer;

@FunctionalInterface
public interface ScheduleParserExecutor<T> {
  void scheduleParsingTask(Consumer<T> consumer, int sizeOfThePool);
}
