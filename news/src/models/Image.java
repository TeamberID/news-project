package models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Image {
    private Integer id;
    private String url;
}
