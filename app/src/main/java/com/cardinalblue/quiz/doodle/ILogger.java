package com.cardinalblue.quiz.doodle;

public interface ILogger {

    void d(String tag, String message);

    void sendEvent(String... messages);
}
