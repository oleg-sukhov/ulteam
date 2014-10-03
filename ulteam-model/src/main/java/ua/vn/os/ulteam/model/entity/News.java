package ua.vn.os.ulteam.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

/** This class represent news with relevant information
 *
 * @author oleg.sukhov
 */
@javax.persistence.Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class News extends Entity {

    @Column(nullable = false)
    @Getter @Setter private String title;

    @Column(name = "short_description", nullable = false)
    @Getter @Setter private String shortDescription;

    @Column(name = "modification_date", nullable = false)
    @Getter @Setter private LocalDateTime modificationDate;

    @Column(nullable = false)
    @Getter @Setter private long views;

    @Column(name = "news_content")
    @Getter @Setter
    private String newsContent;

    @Column(nullable = false)
    @Getter @Setter
    private byte[] picture = new byte[0];
}