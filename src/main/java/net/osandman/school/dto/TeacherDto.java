package net.osandman.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

public class TeacherDto {
    @JsonProperty("Id")
    public long id;
    @JsonProperty("IdStr")
    public String idStr;
    @JsonProperty("UserId")
    public long userId;
    @JsonProperty("UserIdStr")
    public String userIdStr;
    @JsonProperty("FirstName")
    public String firstName;
    @JsonProperty("LastName")
    public String lastName;
    @JsonProperty("MiddleName")
    public String middleName;
    @JsonProperty("ShortName")
    public String shortName;
    @JsonProperty("Sex")
    public String sex;
    @JsonProperty("DateBirth")
    public LocalDate dateBirth;
    @JsonProperty("Email")
    public String email;
    @JsonProperty("Subjects")
    public String subjects;
    @JsonProperty("HouseMaster")
    public boolean houseMaster;
    @JsonProperty("Education")
    public String education;
    @JsonProperty("ScientificDegree")
    public String scientificDegree;
    @JsonProperty("StartDate")
    public LocalDate startDate;
    @JsonProperty("PedagogicalActivityDate")
    public LocalDate pedagogicalActivityDate;
    @JsonProperty("ManagingEmployee")
    public boolean managingEmployee;
    @JsonProperty("NameManagingPosition")
    public String nameManagingPosition;
    @JsonProperty("TeachingStaff")
    public boolean teachingStaff;
    @JsonProperty("NameTeacherPosition")
    public String nameTeacherPosition;
    @JsonProperty("TrainingAndSupportStaff")
    public boolean trainingAndSupportStaff;
    @JsonProperty("ServicePersonnel")
    public boolean servicePersonnel;
    @JsonProperty("MedicalWorker")
    public boolean medicalWorker;
    @JsonProperty("NameMedicalPosition")
    public String nameMedicalPosition;
    @JsonProperty("ExternalPartTime")
    public boolean externalPartTime;
}
