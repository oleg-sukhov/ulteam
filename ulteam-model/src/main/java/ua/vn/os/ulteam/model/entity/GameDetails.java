package ua.vn.os.ulteam.model.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Table;

/**
 * @author os
 */
@javax.persistence.Entity
@Table(name = "game_details")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class GameDetails extends Entity {

}
