package com.tejko.models.general;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.tejko.models.DatabaseEntityWithId;
import com.tejko.models.general.enums.LogLevel;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "log")
@RestResource(rel = "logs", path = "logs")
public class Log extends DatabaseEntityWithId {

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