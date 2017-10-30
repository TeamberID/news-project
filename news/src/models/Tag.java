package models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Tag {
    private Integer id;
    private String name;
}
