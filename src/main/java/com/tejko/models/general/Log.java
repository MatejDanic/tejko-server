package com.tejko.models.general;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.tejko.models.DatabaseEntity;
import com.tejko.models.general.enums.LogLevel;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "log")
@RestResource(rel = "logs", path = "logs")
public class Log extends DatabaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID id;

    @ManyToOne
    @JsonIncludeProperties({ "id", "username" })
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LogLevel level;

    @Column(columnDefinition = "TEXT")
    private String content;


    private Log() { }

    private Log(User user, LogLevel level, String content) {
        this.user = user;
        this.level = level;
        this.content = content;
    }

    public static Log create(User user, LogLevel level, String content) {
        return new Log(user, level, content);
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}