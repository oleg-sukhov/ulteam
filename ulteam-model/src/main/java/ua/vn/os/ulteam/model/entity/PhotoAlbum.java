package ua.vn.os.ulteam.model.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author os.
 */
@javax.persistence.Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PhotoAlbum extends Entity {

    @Column(nullable = false)
    @Getter
    @Setter
    private String title;

    @Column(nullable = false)
    @Getter
    @Setter
    private String description;

    //TODO: thing about serialization java 8 LocalDataTime
    //@Column(name="creation_date", nullable = false)
    @Transient
    @Getter
    @Setter
    private LocalDateTime creationDateTime;

    @Column(name="fs_location_path", nullable = false)
    @Getter
    @Setter
    private String fileSystemLocationPath;

    @Column(nullable = false)
    @Getter
    @Setter
    private String author;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photoAlbum")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Getter
    @Setter
    private List<Photo> photos;

}
