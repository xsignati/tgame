package events;

import java.util.*;
import java.util.function.Consumer;

public class EventBus {
    private final Map<String, Set<Consumer<?>>> subscribers = new HashMap<>();
    public  <T> void register(Class<T> eventType, Consumer<T> methodToRun){
        subscribers.computeIfAbsent(eventType.getSimpleName(), x -> new HashSet<>()).add(methodToRun);
    }

    public <T> void deregister(Class<T> eventType, Consumer<T> methodToDeregister){
        subscribers.getOrDefault(eventType.getSimpleName(), Collections.emptySet()).remove(methodToDeregister);
    }

    public <T> void post(T event){
        subscribers.getOrDefault(event.getClass().getSimpleName(), Collections.emptySet()).stream().map(t-> (Consumer<T>)t).forEach(methodToRun -> methodToRun.accept(event));
    }
}