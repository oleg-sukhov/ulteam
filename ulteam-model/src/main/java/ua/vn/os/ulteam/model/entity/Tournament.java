package ua.vn.os.ulteam.model.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

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
}
