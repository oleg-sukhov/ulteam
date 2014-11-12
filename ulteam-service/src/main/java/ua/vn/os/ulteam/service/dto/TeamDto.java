package ua.vn.os.ulteam.service.dto;

import lombok.*;

/**
 * @author oleg.sukhov
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TeamDto {

    @Getter
    @Setter
    private String name;

    public static TeamDtoBuilder builder() {
        return new TeamDtoBuilder();
    }

    public static class TeamDtoBuilder {
        private String name;

        public TeamDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TeamDto build() {
            return new TeamDto(name);
        }
    }

}
