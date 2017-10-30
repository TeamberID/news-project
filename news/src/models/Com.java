package models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Com {
    private Integer id;
    private String description;
    private Date pubDate;
    private Integer authorId;
    private Integer newsId;
}
