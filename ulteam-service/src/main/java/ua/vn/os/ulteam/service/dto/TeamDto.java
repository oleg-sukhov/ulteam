package ua.vn.os.ulteam.service.dto;

import lombok.*;

/**
 * @author oleg.sukhov
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TeamDto extends BaseDto {

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
        private long id;
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

        public TeamDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public TeamDto build() {
            TeamDto teamDto = new TeamDto(name, town, logoUrl);
            teamDto.setId(id);
            return teamDto;
        }
    }

}
