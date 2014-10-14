package ua.vn.os.ulteam.service.dto;

import lombok.*;
import lombok.libs.com.zwitserloot.cmdreader.FullName;

/**
 * @author oleg.sukhov
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GameDto {

    @Getter @Setter
    private String ownerTeam;

    @Getter @Setter
    private String ownerTeamLogoUrl;

    @Getter @Setter
    private String guestTeam;

    @Getter @Setter
    private String guestTeamLogoUrl;

    @Getter @Setter
    private short ownerTeamGoals;

    @Getter @Setter
    private short guestTeamGoals;

    @Getter @Setter
    private String date;

    @Getter @Setter
    private String tournament;

    @Getter @Setter
    private String tour;

    public static GameDtoBuilder builder() {
        return new GameDtoBuilder();
    }

    public static class GameDtoBuilder {
        private String ownerTeam;
        private String ownerTeamLogoUrl;
        private String guestTeam;
        private String guestTeamLogoUrl;
        private short ownerTeamGoals;
        private short guestTeamGoals;
        private String date;
        private String tournament;
        private String tour;

        public GameDtoBuilder() {
        }

        public GameDtoBuilder ownerTeam(final String ownerTeam) {
            this.ownerTeam = ownerTeam;
            return this;
        }

        public GameDtoBuilder guestTeam(final String guestTeam) {
            this.guestTeam = guestTeam;
            return this;
        }

        public GameDtoBuilder ownerTeamLogoUrl(final String ownerTeamLogoUrl) {
            this.ownerTeamLogoUrl = ownerTeamLogoUrl;
            return this;
        }

        public GameDtoBuilder guestTeamLogoUrl(final String guestTeamLogoUrl) {
            this.guestTeamLogoUrl = guestTeamLogoUrl;
            return this;
        }

        public GameDtoBuilder ownerTeamGoals(final short ownerTeamGoals) {
            this.ownerTeamGoals = ownerTeamGoals;
            return this;
        }

        public GameDtoBuilder guestTeamGoals(final short guestTeamGoals) {
            this.guestTeamGoals = guestTeamGoals;
            return this;
        }

        public GameDtoBuilder date(final String date) {
            this.date = date;
            return this;
        }

        public GameDtoBuilder tournament(final String tournament) {
            this.tournament = tournament;
            return this;
        }

        public GameDtoBuilder tour(final String tour) {
            this.tour = tour;
            return this;
        }

        public GameDto build() {
            return new GameDto(ownerTeam, ownerTeamLogoUrl, guestTeam, guestTeamLogoUrl,
                               ownerTeamGoals, guestTeamGoals, date, tournament, tour);
        }
    }
}
