package ua.vn.os.ulteam.model.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author oleg.sukhov
 */
@javax.persistence.Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Tour extends Entity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    @Getter @Setter
    private Tournament tournament;

    @OneToMany(mappedBy = "tour")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Getter @Setter
    private List<Game> games;

    @Getter @Setter
    private String name;
}
