import java.util.concurrent.atomic.AtomicInteger;

public class Item {
    private final String id;
    private String value;

    private static final AtomicInteger count = new AtomicInteger(0);  //initialize id as 0, increment everytime data is added

    public Item(String value) {
        this.id = String.valueOf(count.incrementAndGet());
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
