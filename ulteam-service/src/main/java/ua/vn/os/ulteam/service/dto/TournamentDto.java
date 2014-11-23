package ua.vn.os.ulteam.service.dto;

import lombok.*;

/**
 * @author oleg.sukhov
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TournamentDto extends BaseDto {

    @Getter @Setter
    private String name;

    public static TournamentDtoBuilder builder() {
        return new TournamentDtoBuilder();
    }

    public static class TournamentDtoBuilder {
        private long id;
        private String name;

        public TournamentDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TournamentDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public TournamentDto build() {
            TournamentDto tournamentDto = new TournamentDto(name);
            tournamentDto.setId(id);
            return tournamentDto;
        }
    }

}
