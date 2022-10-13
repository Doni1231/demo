package prod.java.payload;


import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;
    private String firstName;
    private String lastName;

    private String password;

    private String prePassword;

    private String oldPassword;

    @NotNull
    private String phoneNumber;

    @Email
    private String email;

    private UUID sharingUserId;

    private UUID photoId;




}
