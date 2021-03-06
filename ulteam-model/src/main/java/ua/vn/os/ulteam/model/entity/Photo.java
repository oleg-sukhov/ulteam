package ua.vn.os.ulteam.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @Author os
 */
@javax.persistence.Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "photoAlbum")
public class Photo extends Entity {

    @Column(name = "photo_name", nullable = false)
    @Getter @Setter
    private String name;

    @Column(name = "photo_description")
    @Getter @Setter
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    @Getter @Setter
    private PhotoAlbum photoAlbum;
}
