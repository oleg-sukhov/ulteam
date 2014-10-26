package ua.vn.os.ulteam.service.dto;

import lombok.*;

/**
 * @author oleg.sukhov
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TournamentDto {

    @Getter @Setter
    private String name;

    public static TournamentDtoBuilder builder() {
        return new TournamentDtoBuilder();
    }

    public static class TournamentDtoBuilder {
        private String name;

        public TournamentDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TournamentDto build() {
            return new TournamentDto(name);
        }
    }

}
