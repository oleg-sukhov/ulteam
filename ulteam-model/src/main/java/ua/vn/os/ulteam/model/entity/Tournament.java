package ua.vn.os.ulteam.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author oleg.sukhov
 */
@javax.persistence.Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Tournament extends Entity {

    @Column(nullable = false)
    @Getter @Setter
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season", nullable = false)
    private Season season;

    @OneToMany(fetch = FetchType.LAZY)
    @Getter @Setter
    private Set<Tour> tours;
}
