package ua.vn.os.ulteam.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class NewsDto extends BaseDto {

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String shortDescription;

    @Getter @Setter
    private String modificationDate;

    @Getter @Setter
    private String views;

    @Getter @Setter
    private String newsContent;

    @Getter @Setter
    private String picture;
}
