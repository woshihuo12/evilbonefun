package com.hsj.egameserver.events;

import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


public class EventDispatcher {
    private static ExecutorService tpe = Executors.newCachedThreadPool(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = Executors.defaultThreadFactory().newThread(r);
            System.out.println("Thread created: " + thread);
            return thread;
        }
    });

    private Object sync = new Object();

    private Map<Class<? extends Event>, Map<EventListener, Filter>> listeners;

    public Map<Class<? extends Event>, Map<EventListener, Filter>> getListeners() {
        synchronized (sync) {
            if (listeners == null) {
                listeners = new HashMap<>();
            }
        }
        return listeners;
    }

    public void addEventListener(Class<? extends Event> c, EventListener listener) {
        addEventListener(c, listener, null);
    }

    public void addEventListener(Class<? extends Event> c, EventListener listener, Filter filter) {
        Map<Class<? extends Event>, Map<EventListener, Filter>> listeners = getListeners();
        synchronized (sync) {
            Map<EventListener, Filter> list = null;
            if (listeners.containsKey(c)) {
                list = listeners.get(c);
            } else {
                list = new HashMap<>();
                listeners.put(c, list);
            }
            if (!list.containsKey(listener)) {
                list.put(listener, filter);
            }
        }
    }

    public void removeEventListener(Class<? extends Event> c, EventListener listener) {
        Map<Class<? extends Event>, Map<EventListener, Filter>> listeners = getListeners();
        synchronized (sync) {
            if (c == null) {
                Iterator<Map.Entry<Class<? extends Event>, Map<EventListener, Filter>>> iterator = listeners.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Class<? extends Event>, Map<EventListener, Filter>> entry = iterator.next();
                    Map<EventListener, Filter> list = entry.getValue();
                    if (list.containsKey(listener)) {
                        list.remove(listener);
                        if (list.isEmpty()) {
                            iterator.remove();
                        }
                    }
                }
            } else {
                if (listeners.containsKey(c)) {
                    Map<EventListener, Filter> listenerFilterMap = listeners.get(c);
                    if (listenerFilterMap.containsKey(listener)) {
                        listenerFilterMap.remove(listener);
                        if (listenerFilterMap.isEmpty()) {
                            listeners.remove(c);
                        }
                    }
                }
            }
            if (listeners.isEmpty()) {
                this.listeners = null;
            }
        }
    }

    private List<Entry<EventListener, Filter>> findEntries(Event event) {
        Map<Class<? extends Event>, Map<EventListener, Filter>> listeners = getListeners();
        LinkedList<Entry<EventListener, Filter>> linkedList = new LinkedList<>();
        synchronized (sync) {
            for (Class<? extends Event> c : listeners.keySet()) {
                if (c.isInstance(event)) {
                    for (Entry<EventListener, Filter> entry : listeners.get(c).entrySet()) {
                        linkedList.add(entry);
                    }
                }
            }
        }
        return linkedList;
    }

    public <T extends Event> T createEvent(Class<T> eventClass, Object... args) {
        return Event.create(eventClass, this, args);
    }

    protected int fireEvent(Event event) {
        List<Entry<EventListener, Filter>> entries = findEntries(event);
        int counter = 0;
        for (Entry<EventListener, Filter> entry : entries) {
            counter++;
            try {
                EventListener listener = entry.getKey();
                Filter filter = entry.getValue();
                if (filter == null || filter.filter(event)) {
                    listener.handleEvent(event);
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error("Exception:", e);
            }
        }
        return counter;
    }

    protected int fireEventAsync(final Event event) {
        List<Entry<EventListener, Filter>> entries = findEntries(event);
        int counter = 0;
        for (final Entry<EventListener, Filter> entry : entries) {
            counter++;
            tpe.submit(
                    new Callable<Object>() {
                        @Override
                        public Object call() throws Exception {
                            try {
                                EventListener listener = entry.getKey();
                                Filter filter = entry.getValue();
                                if (filter == null || filter.filter(event)) {
                                    listener.handleEvent(event);
                                }
                            } catch (Exception e) {
                                LoggerFactory.getLogger(this.getClass()).warn("Exception:", e);
                                throw new RuntimeException(e);
                            }
                            return null;
                        }
                    }
            );
        }
        return counter;
    }

    public <T extends Event> int fireEvent(Class<T> eventclass, Object... args) {
        return fireEvent(createEvent(eventclass, args));
    }

    public static void shutDown() {
        tpe.shutdown();
    }
}
