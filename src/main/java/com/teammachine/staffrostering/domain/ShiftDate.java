package com.teammachine.staffrostering.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teammachine.staffrostering.domain.enumeration.DayOfWeek;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A ShiftDate.
 */
@Entity
@Table(name = "shift_date")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ShiftDate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "day_index")
    private Integer dayIndex;

    @Column(name = "date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @OneToMany(mappedBy = "shiftDate")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Shift> shiftList = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(Integer dayIndex) {
        this.dayIndex = dayIndex;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Set<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(Set<Shift> shifts) {
        this.shiftList = shifts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShiftDate shiftDate = (ShiftDate) o;
        if(shiftDate.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, shiftDate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ShiftDate{" +
            "id=" + id +
            ", dayIndex='" + dayIndex + "'" +
            ", date='" + date + "'" +
            ", dayOfWeek='" + dayOfWeek + "'" +
            '}';
    }
}
