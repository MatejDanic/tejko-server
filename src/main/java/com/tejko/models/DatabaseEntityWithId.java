package com.tejko.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class DatabaseEntityWithId extends DatabaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    UUID id;

    public DatabaseEntityWithId() {
        super();
    }

    public UUID getId() {
        return id;
    }

}
