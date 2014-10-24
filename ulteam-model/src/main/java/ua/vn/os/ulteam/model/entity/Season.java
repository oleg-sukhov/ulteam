package ua.vn.os.ulteam.model.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;

/**
 * @author oleg.sukhov
 */
@javax.persistence.Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Season extends Entity {

    @NaturalId
    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String name;
}
