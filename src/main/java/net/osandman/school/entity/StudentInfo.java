package net.osandman.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_info")
public class StudentInfo {
    @Id
    @Column(name = "person_id")
    private long personId;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "photo_url")
    private String photoUrl;

    @OneToOne(mappedBy = "studentInfo")
    private Student student;
}
