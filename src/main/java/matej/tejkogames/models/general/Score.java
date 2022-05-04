package matej.tejkogames.models.general;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.RestResource;
import matej.tejkogames.interfaces.models.ScoreInterface;
import matej.tejkogames.models.general.payload.requests.ScoreRequest;

@Entity
@Table(name = "score")
@RestResource(rel = "scores", path = "scores")
public class Score implements ScoreInterface {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private UUID id;

	@ManyToOne
	@JsonIncludeProperties({ "id", "name" })
	@JoinColumn(name = "game_id")
	private Game game;

	@ManyToOne
	@JsonIncludeProperties({ "id", "username" })
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private Integer value;

	@Column(nullable = false)
	private LocalDateTime date;

	public Score() {
	}

	public Score(Integer value) {
		this.value = value;
		this.date = LocalDateTime.now();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public void updateByRequest(ScoreRequest objectRequest) {
		if (objectRequest.getGame() != null) {
			this.setGame(objectRequest.getGame());
		}
		if (objectRequest.getUser() != null) {
			this.setUser(objectRequest.getUser());
		}
		if (objectRequest.getDate() != null) {
			this.setDate(objectRequest.getDate());
		}
		if (objectRequest.getValue() != null) {
			this.setValue(objectRequest.getValue());
		}
	}

	@Override
	public boolean hasPermission(UUID userId) {
		return user.getId().equals(userId);
	}

}