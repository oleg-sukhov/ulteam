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

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String town;

    @Getter @Setter
    private String logoUrl;

    public static TeamDtoBuilder builder() {
        return new TeamDtoBuilder();
    }

    public static class TeamDtoBuilder {
        private String name;
        private String town;
        private String logoUrl;

        public TeamDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TeamDtoBuilder town(String town) {
            this.town = town;
            return this;
        }

        public TeamDtoBuilder logoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
            return this;
        }

        public TeamDto build() {
            return new TeamDto(name, town, logoUrl);
        }
    }

}
