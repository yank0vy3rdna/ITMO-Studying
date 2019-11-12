@FunctionalInterface
public interface ThrowingConsumer<Integer, E extends NamingException>{
    void print(Integer t);
}