package net.osandman.school.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    private long id;
    private long userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String sex;
    private LocalDate dateBirth;
    private String email;
    private String subjects;
    private boolean houseMaster;
    private String education;
    private String scientificDegree;
    private LocalDate startDate;
    private LocalDate pedagogicalActivityDate;
    private boolean managingEmployee;
    private String nameManagingPosition;
    private boolean teachingStaff;
    private String nameTeacherPosition;
    private boolean trainingAndSupportStaff;
    private boolean servicePersonnel;
    private boolean medicalWorker;
    private String nameMedicalPosition;
    private boolean externalPartTime;

}
