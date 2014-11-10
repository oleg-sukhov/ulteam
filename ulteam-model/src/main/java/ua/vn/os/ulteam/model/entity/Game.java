package ua.vn.os.ulteam.model.entity;

import lombok.*;
import ua.vn.os.ulteam.model.entity.converter.LocalDatePersistenceConverter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 *  This class represent information about single game
 *
 *  @author oleg.sukhov
 */
@javax.persistence.Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Game extends Entity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_team", nullable = false)
    @Getter @Setter
    private Team ownerTeam;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_team", nullable = false)
    @Getter @Setter
    private Team guestTeam;

    @Column(name = "owner_team_goals")
    @Getter @Setter
    private short ownerTeamGoals;

    @Column(name = "guest_team_goals")
    @Getter @Setter
    private short guestTeamGoals;

    @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "game_date")
    @Getter @Setter
    private LocalDate gameDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tour", nullable = false)
    @Getter @Setter
    private Tour tour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_details", nullable = false)
    @Getter @Setter
    private GameDetails gameDetails;

}
