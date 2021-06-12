package com.epam.jwd.training.entity;

import java.util.Arrays;
import java.util.List;

public enum RoleType {
    ADMIN(1L, "Admin"),
    USER(2L, "User"),
    GUEST(3L, "Guest");

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
