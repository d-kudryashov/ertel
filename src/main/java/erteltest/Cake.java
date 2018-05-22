package erteltest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cakes")
public class Cake {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "status")
    @Enumerated
    private StatusType statusType;

    public Cake() {
    }

    public Cake(String name, StatusType statusType) {
        this.name = name;
        this.statusType = statusType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }
}
