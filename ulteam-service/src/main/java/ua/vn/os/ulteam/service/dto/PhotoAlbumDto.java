package ua.vn.os.ulteam.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PhotoAlbumDto {

    @Getter @Setter private long id;
    @Getter @Setter private String title;
    @Getter @Setter private String description;
    @Getter @Setter private String creationDateTime;
    @Getter @Setter private String author;
    @Getter @Setter private int numberOfPhotos;
    @Getter @Setter private PhotoDto avatar;
}
