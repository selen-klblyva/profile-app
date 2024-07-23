package az.edu.turing.profileapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {
        private long id;
        private String username;
        private int age;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String photoURL;
}
