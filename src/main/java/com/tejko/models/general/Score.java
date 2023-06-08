package com.tejko.models.general;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.tejko.models.DatabaseEntityWithId;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "score")
@RestResource(rel = "scores", path = "scores")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="app", discriminatorType = DiscriminatorType.STRING)
public abstract class Score extends DatabaseEntityWithId {

	@ManyToOne
	@JsonIncludeProperties({ "id", "username" })
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private int value;

	protected Score() { }

	protected Score(User user, int value) {
		this.user = user;
		this.value = value;
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