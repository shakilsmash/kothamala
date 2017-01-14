package com.ronniegnr.kothamala.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1;

    public enum Status { ACTIVE, INACTIVE, BANNED }

    private long id;
    private Status status;
    private String name;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    @Size(max = 10)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "name", length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", status=" + status +
                ", name=" + name +
                '}';
    }
}
