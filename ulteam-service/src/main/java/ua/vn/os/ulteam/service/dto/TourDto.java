package ua.vn.os.ulteam.service.dto;

import lombok.*;

/**
 * @author oleg.sukhov
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TourDto {

    @Getter @Setter
    private String name;

    public static TourDtoBuilder builder() {
        return new TourDtoBuilder();
    }

    public static class TourDtoBuilder {
        private String name;

        public TourDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TourDto build() {
            return new TourDto(name);
        }
    }

}
