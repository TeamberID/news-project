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
public class News {
    private Integer id;
    private String title;
    private String description;
    private Date pubDate;
    private Integer imageId;
    private Integer tagId;
    private String imageSrc;
    private Tag tag;
    private  Integer rating;

}
