package models;

import lombok.*;

import java.sql.Date;

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
    private User user;
}
