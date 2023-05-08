package net.osandman.school.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Entity
public class StudentInfo {
    private long personId;
    private String shortName;
    private LocalDate birthday;
    private String phone;
    private String email;
    private String photoUrl;
}
