package ua.vn.os.ulteam.service.dto;

import lombok.*;

/**
 * @author oleg.sukhov
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SeasonDto {

    @Getter @Setter
    private String name;

    public static SeasonDtoBuilder builder() {
        return new SeasonDtoBuilder();
    }

    public static class SeasonDtoBuilder {
        private String name;

        public SeasonDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SeasonDto build() {
            return new SeasonDto(name);
        }
    }
}
