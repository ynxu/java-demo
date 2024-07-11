package demo;

public class Base {
    private String order;

    String getOrder() {
        return order;
    }

    void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Base{" +
                "order='" + order + '\'' +
                '}';
    }
}
