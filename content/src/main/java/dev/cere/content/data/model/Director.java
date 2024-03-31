package dev.cere.content.data.model;

import java.time.LocalDate;
import java.util.Objects;

public class Director {
    private Long id;
    private String name;
    private LocalDate dob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(getName(), director.getName())
                && Objects.equals(getDob(), director.getDob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDob());
    }

    @Override
    public String toString() {
        return "Director{" + "id=" + id + ", name='" + name + '\'' + ", dob=" + dob + '}';
    }
}
