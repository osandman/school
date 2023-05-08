package net.osandman.school.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    private long id;
    private String id_str;
    private long personId;
    private String personId_str;
    private String name;
    private String email;
    private String login;
    private String fullName;
    private String fullNameInverse;
    private String firstName;
    private String middleName;
    private String lastName;
    private String shortName;
    private String locale;
    private String timezone;
    private String sex;
    private String photoSmall;
    private String photoMedium;
    private String photoLarge;
    private LocalDate birthday;
    private List<String> roles;
    private String phone;
}
