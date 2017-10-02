package com.hsj.egameserver.events;

import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

public class Event {
    private EventDispatcher source;

    protected Event() {

    }

    public EventDispatcher getSource() {
        return source;
    }

    public void setSource(EventDispatcher source) {
        this.source = source;
    }

    public static <T extends Event> T create(Class<T> eventClass, EventDispatcher source, Object... args) {
        for (Constructor<?> constructor : eventClass.getConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length != args.length) {
                continue;
            }
            for (int i = 0; i < args.length; i++) {
                Object obj = args[i];
                if (obj != null) {
                    if (!obj.getClass().isAssignableFrom(parameterTypes[i])) {
                        continue;
                    }
                }
            }

            T event = null;
            try {
                Constructor<T> parameterizedConstructor = (Constructor<T>) eventClass.getConstructor(parameterTypes);
                event = parameterizedConstructor.newInstance(args);
                event.setSource(source);
                return event;
            } catch (Exception e) {
                LoggerFactory.getLogger(Event.class).error("Exception", e);
                throw new RuntimeException(e);
            }
        }

        throw new RuntimeException("No matching constructors found on: " + eventClass);
    }
}
