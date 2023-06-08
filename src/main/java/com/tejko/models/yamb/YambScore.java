package com.tejko.models.yamb;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tejko.models.general.Score;
import com.tejko.models.general.User;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "score")
@RestResource(rel = "scores", path = "scores")
@DiscriminatorValue("Yamb")
public class YambScore extends Score {

	private YambScore() { }

	private YambScore(User user, int value) {
		super(user, value);
	}

	public static YambScore create(User user, int value) {
		return new YambScore(user, value);
	}

}