package ua.vn.os.ulteam.model.entity;

import lombok.*;

import javax.persistence.Column;

/**
 * @author os
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

    @Column(nullable = false)
    @Getter @Setter
    private byte[] logo;
}
