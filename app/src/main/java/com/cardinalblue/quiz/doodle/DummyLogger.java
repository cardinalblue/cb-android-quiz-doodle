package com.cardinalblue.quiz.doodle;

import android.util.Log;

public class DummyLogger implements ILogger {

    @Override
    public void d(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void sendEvent(String... messages) {
        final StringBuilder builder = new StringBuilder();
        for (String message : messages) {
            builder.append(message);
        }
        Log.d("event", builder.toString());
    }
}
