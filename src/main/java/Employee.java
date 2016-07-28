import org.hibernate.validator.constraints.NotBlank;

import java.util.UUID;

public class Employee {

    private String id = UUID.randomUUID().toString();

    @NotBlank
    private String title;

    @NotBlank
    private String name;

    public Employee() {
    }

    public Employee(String title, String name) {
        super();
        this.title = title;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }
}