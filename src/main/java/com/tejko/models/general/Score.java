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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "score")
@RestResource(rel = "scores", path = "scores")
public class Score extends DatabaseEntity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column
	private UUID id;

	@ManyToOne
	@JsonIncludeProperties({ "id", "name" })
	@JoinColumn(name = "app_id")
	private App app;

	@ManyToOne
	@JsonIncludeProperties({ "id", "username" })
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private int value;

	private Score() { }

	private Score(App app, User user, int value) {
		this.app = app;
		this.user = user;
		this.value = value;
	}

	public static Score create(App app, User user, int value) {
		return new Score(app, user, value);
	}

	public UUID getId() {
		return id;
	}

	public App getApp() {
		return app;
	}

	public User getUser() {
		return user;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}