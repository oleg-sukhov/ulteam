package ua.vn.os.ulteam.service.dto;

import lombok.*;

/**
 * @author oleg.sukhov
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TourDto extends BaseDto {

    @Getter @Setter
    private String name;

    public static TourDtoBuilder builder() {
        return new TourDtoBuilder();
    }

    public static class TourDtoBuilder {
        private String name;
        private long id;

        public TourDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TourDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public TourDto build() {
            TourDto tourDto = new TourDto(name);
            tourDto.setId(id);
            return tourDto;
        }
    }

}
