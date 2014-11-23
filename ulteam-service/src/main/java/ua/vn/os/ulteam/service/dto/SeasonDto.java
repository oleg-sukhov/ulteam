package ua.vn.os.ulteam.service.dto;

import lombok.*;

/**
 * @author oleg.sukhov
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SeasonDto extends BaseDto {

    @Getter @Setter
    private String name;

    public static SeasonDtoBuilder builder() {
        return new SeasonDtoBuilder();
    }

    public static class SeasonDtoBuilder {
        private String name;
        private long id;

        public SeasonDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SeasonDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public SeasonDto build() {
            SeasonDto seasonDto = new SeasonDto(name);
            seasonDto.setId(id);
            return seasonDto;
        }
    }
}
