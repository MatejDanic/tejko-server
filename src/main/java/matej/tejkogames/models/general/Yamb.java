package matej.tejkogames.models.general;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.rest.core.annotation.RestResource;

import matej.tejkogames.interfaces.models.YambInterface;
import matej.tejkogames.models.general.payload.requests.YambRequest;
import matej.tejkogames.models.yamb.Box;
import matej.tejkogames.models.yamb.Dice;
import matej.tejkogames.models.yamb.YambForm;
import matej.tejkogames.models.yamb.enums.BoxType;
import matej.tejkogames.models.yamb.enums.ColumnType;
import matej.tejkogames.models.yamb.enums.YambType;
import matej.tejkogames.utils.YambUtil;

@Entity
@Table(name = "yamb")
@RestResource(rel = "yambs", path = "yambs")
@TypeDef(name = "json_binary", typeClass = JsonBinaryType.class)
public class Yamb implements YambInterface {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID id;

    @JsonIncludeProperties({ "id", "username" })
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;

    @Column
    private YambType type = YambType.CLASSIC;

    @Min(5)
    @Max(6)
    @Column(updatable = false)
    private int numberOfDice = 5;

    @Type(type = "json_binary")
    @Column(columnDefinition = "jsonb")
    private YambForm form;

    @Size(min = 1, max = 6)
    private String formCode = "DUFA";

    @Column
    private BoxType announcement = null;

    @Type(type = "json_binary")
    @Column(columnDefinition = "jsonb")
    private Set<Dice> diceSet;

    @Column
    private int rollCount = 0;

    @JsonIncludeProperties({ "user", "challenge" })
    @OneToOne(mappedBy = "yamb", cascade = CascadeType.MERGE)
    private UserYambChallenge userYambChallenge;

    @Column(nullable = false)
    private LocalDateTime startDate = LocalDateTime.now();

    @Column
    private LocalDateTime endDate;

    public Yamb() {
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

    public UserYambChallenge getUserYambChallenge() {
        return userYambChallenge;
    }

    public void setUserYambChallenge(UserYambChallenge userYambChallenge) {
        this.userYambChallenge = userYambChallenge;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void updateByRequest(YambRequest objectRequest) {
        if (objectRequest.getUser() != null) {
            this.setUser(objectRequest.getUser());
        }
        if (objectRequest.getUserYambChallenge() != null) {
            this.setUserYambChallenge(objectRequest.getUserYambChallenge());
        }
        if (objectRequest.getType() != null) {
            this.setType(objectRequest.getType());
        }
        if (objectRequest.getNumberOfDice() != null) {
            this.setNumberOfDice(objectRequest.getNumberOfDice());
        }
        if (objectRequest.getForm() != null) {
            this.setForm(objectRequest.getForm());
        }
        if (objectRequest.getDiceSet() != null) {
            this.setDiceSet(objectRequest.getDiceSet());
        }
        if (objectRequest.getAnnouncement() != null) {
            this.setAnnouncement(objectRequest.getAnnouncement());
        }
        if (objectRequest.getRollCount() != null) {
            this.setRollCount(objectRequest.getRollCount());
        }
    }

    @Override
    public boolean hasPermission(UUID userId) {
        return user.getId().equals(userId);
    }

    public void unfreezeAllDice() {
        for (Dice dice : diceSet) {
            dice.setFrozen(false);
        }
    }

    public void freezeAllDice() {
        for (Dice dice : diceSet) {
            dice.setFrozen(true);
        }
    }

    public void rollDice() {
        rollCount++;

        for (Dice dice : diceSet) {
            if (!dice.isFrozen()) {
                dice.roll();
            }
        }
    }

    public void fill(ColumnType columnType, BoxType boxType) {

        int score = YambUtil.calculateScore(diceSet, boxType);

        for (Box box : form.getColumnByType(columnType).getBoxes()) {
            if (box.getType() == boxType) {
                box.fill(score);
            } else if (box.isNext(columnType, boxType)) {
                box.setAvailable(true);
            }
        }

        form.updateSums(columnType);
        form.setAvailableBoxes(form.getAvailableBoxes() - 1);

        unfreezeAllDice();
        rollCount = 0;
        announcement = null;

    }

}
