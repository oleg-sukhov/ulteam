package ua.vn.os.ulteam.service.dto;

import lombok.*;

/**
 * @Author os
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PhotoDto extends BaseDto {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String url;
}
