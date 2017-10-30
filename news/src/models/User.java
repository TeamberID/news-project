package models;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor

public class User {
    private Integer id;
    private String login;
    private String pass;
    private Boolean isAdmin;
}
