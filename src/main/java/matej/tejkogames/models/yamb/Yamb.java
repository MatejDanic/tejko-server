package matej.tejkogames.models.yamb;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.rest.core.annotation.RestResource;

import matej.tejkogames.models.general.User;
import matej.tejkogames.utils.YambUtil;

@Entity
@Table(name = "game_yamb")
@RestResource(rel = "yambs", path = "yambs")
@TypeDef(name = "json_binary", typeClass = JsonBinaryType.class)
public class Yamb {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JsonIncludeProperties({ "id", "username" })
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private YambType type;

    @Column
    private int numberOfColumns;

    @Column
    private int numberOfDice;

    @Type(type = "json_binary")
    @Column(columnDefinition = "jsonb")
    private YambForm form;

    @Column
    private BoxType announcement;

    @Type(type = "json_binary")
    @Column(columnDefinition = "jsonb")
    private Set<Dice> diceSet;

    @Column
    private int rollCount;

	@ManyToOne
	@JsonIncludeProperties("id")
	@JoinColumn(name = "challenge_id")
	private YambChallenge challenge;

    public Yamb() {
    }

    public Yamb(User user, YambType type, int numberOfColumns, int numberOfDice) {
        this.type = type;
        this.numberOfColumns = numberOfColumns;
        this.numberOfDice = numberOfDice;
        this.announcement = null;
        this.rollCount = 0;
        this.form = YambUtil.generateYambForm(type, numberOfColumns, numberOfDice);
        this.diceSet = YambUtil.generateDiceSet(numberOfDice);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public YambType getType() {
        return type;
    }

    public void setType(YambType type) {
        this.type = type;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public YambForm getForm() {
        return form;
    }

    public void setForm(YambForm form) {
        this.form = form;
    }

    public BoxType getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(BoxType announcement) {
        this.announcement = announcement;
    }

    public Set<Dice> getDiceSet() {
        return diceSet;
    }

    public void setDiceSet(Set<Dice> diceSet) {
        this.diceSet = diceSet;
    }

    public int getRollCount() {
        return rollCount;
    }

    public void setRollCount(int rollCount) {
        this.rollCount = rollCount;
    }

}
