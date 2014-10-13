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
public class Season extends Entity {

    @Column(nullable = false)
    @Getter @Setter
    private String name;
}
