package az.edu.turing.profileapp.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private long id;
    private String username;
    private int age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String photoURL;
}
