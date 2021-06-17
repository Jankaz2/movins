package kazmierczak.jan.user;

public interface EventPublisher<T> {
    void publishEvent(T t);
}
