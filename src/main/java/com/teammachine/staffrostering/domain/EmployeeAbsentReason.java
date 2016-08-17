package com.teammachine.staffrostering.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EmployeeAbsentReason.
 */
@Entity
@Table(name = "employee_absent_reason")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmployeeAbsentReason implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "default_duration")
    private String defaultDuration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultDuration() {
        return defaultDuration;
    }

    public void setDefaultDuration(String defaultDuration) {
        this.defaultDuration = defaultDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeeAbsentReason employeeAbsentReason = (EmployeeAbsentReason) o;
        if(employeeAbsentReason.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, employeeAbsentReason.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EmployeeAbsentReason{" +
            "id=" + id +
            ", code='" + code + "'" +
            ", name='" + name + "'" +
            ", description='" + description + "'" +
            ", defaultDuration='" + defaultDuration + "'" +
            '}';
    }
}