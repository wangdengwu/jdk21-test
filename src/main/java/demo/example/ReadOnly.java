package demo.example;

public class ReadOnly {
    public final String name;
    public final String address;

    public ReadOnly(String name, String address) {
        assert name != null;
        assert address != null;
        assert !name.isEmpty();
        assert !address.isEmpty();
        this.name = name;
        this.address = address;
    }

    @SuppressWarnings("all")
    public String send() {
        return STR."""
                send \{name} to \{address}""";
    }
}

record ReadOnlyRecord(String name, String address) {
    public ReadOnlyRecord {
        assert name != null;
        assert address != null;
        assert !name.isEmpty();
        assert !address.isEmpty();
    }

    @SuppressWarnings("all")
    public String send() {
        return STR."""
                send \{name} to \{address}""";
    }
}