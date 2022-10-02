package prod.java.entity.enums;

public enum StatusEnum {
    PENDING("Pending"),
    RECEIVED("Received"),
    REJECTED("Rejected"),
    EXPIRED("EXPIRED");

    public String name;

    StatusEnum(String name) {
        this.name = name;
    }
}
