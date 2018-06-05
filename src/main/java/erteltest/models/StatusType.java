package erteltest.models;

public enum StatusType {
    FRESH("Свежий"),
    STALE("Чёрствый");

    private String name;

    StatusType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}