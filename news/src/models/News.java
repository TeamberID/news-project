package models;

import lombok.*;

import java.util.Date;
import java.util.List;

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
    private String category;

    private List<Tag> tags;
}
