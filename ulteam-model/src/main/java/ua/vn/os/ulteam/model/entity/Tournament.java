package ua.vn.os.ulteam.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author os
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
}
