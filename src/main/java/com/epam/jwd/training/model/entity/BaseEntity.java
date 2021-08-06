package com.epam.jwd.training.model.entity;

import java.util.Objects;

/**
 * Class of base entity
 *
 * @author Nadzeya Zmushka
 */
public abstract class BaseEntity {

    private Long id;

    public BaseEntity() {
    }

    /**
     * Instantiates a new BaseEntity.
     *
     * @param id the id
     */
    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id=" + id +
                '}';
    }

}
