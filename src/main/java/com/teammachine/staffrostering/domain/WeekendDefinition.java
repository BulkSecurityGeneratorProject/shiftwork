package com.teammachine.staffrostering.domain;

import com.teammachine.staffrostering.domain.enumeration.DayOfWeek;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A WeekendDefinition.
 */
@Entity
@Table(name = "weekend_definition")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WeekendDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @ElementCollection(targetClass = DayOfWeek.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "weekend_day", joinColumns = @JoinColumn(name = "weekend_definition_id"))
    @Column(name = "day_of_week")
    private Set<DayOfWeek> days = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDays(Set<DayOfWeek> days) {
        this.days = days;
    }

    public List<DayOfWeek> getDays() {
        return days.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WeekendDefinition weekendDefinition = (WeekendDefinition) o;
        if (weekendDefinition.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, weekendDefinition.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WeekendDefinition{" +
                "id=" + id +
                ", description='" + description + "'" +
                '}';
    }
}
