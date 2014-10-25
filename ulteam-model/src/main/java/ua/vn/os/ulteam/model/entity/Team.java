package ua.vn.os.ulteam.model.entity;

import lombok.*;

import javax.persistence.Column;

/**
 * @author oleg.sukhov
 */
@javax.persistence.Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Team extends Entity {

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String name;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String town;

    @Column(name = "logo", nullable = false, length = 20, unique = true)
    @Getter @Setter
    private String logoName;

}
