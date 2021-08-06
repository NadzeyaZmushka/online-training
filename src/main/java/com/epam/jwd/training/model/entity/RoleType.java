package com.epam.jwd.training.model.entity;

import java.util.Arrays;
import java.util.List;

/**
 * Enum contains all types of roles
 *
 * @author Nadzeya Zmushka
 */
public enum RoleType {
    ADMIN(1L, "ADMIN"),
    USER(2L, "USER"),
    GUEST(3L, "GUEST");

    public static final List<RoleType> ROLE_TYPE_LIST = Arrays.asList(values());

    private final Long id;
    private final String name;

    RoleType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<RoleType> valuesAsList() {
        return ROLE_TYPE_LIST;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static RoleType resolvedById(long id) {
        for (RoleType roleType : values()) {
            if (roleType.getId().equals(id)) {
                return roleType;
            }
        }
        //???
        throw new IllegalArgumentException("Role with this id was not found");
    }

    public static RoleType resolvedByName(String name) {
        for (RoleType roleType : values()) {
            if (roleType.getName().equals(name)) {
                return roleType;
            }
        }
        throw new IllegalArgumentException("Role with this name was not found");
    }

}
