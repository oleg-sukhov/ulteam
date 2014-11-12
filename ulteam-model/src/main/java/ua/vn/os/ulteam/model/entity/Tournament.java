package ua.vn.os.ulteam.model.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author oleg.sukhov
 */
@javax.persistence.Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "tours")
public class Tournament extends Entity {

    @Column(nullable = false)
    @Getter @Setter
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season", nullable = false)
    private Season season;

    @OneToMany(mappedBy = "tournament")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Getter @Setter
    private List<Tour> tours;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="TournamentTeam",
            joinColumns={@JoinColumn(name="tournament_id")},
            inverseJoinColumns={@JoinColumn(name="team_id")})
    private Set<Team> teams;
}
