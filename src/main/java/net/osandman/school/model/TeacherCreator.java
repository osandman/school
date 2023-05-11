package net.osandman.school.model;

import net.osandman.school.dto.TeacherDto;
import net.osandman.school.dto.UserDto;
import net.osandman.school.entity.Teacher;
import net.osandman.school.service.ApiRequest;
import net.osandman.school.util.PropertiesProcess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherCreator {

    private final SchoolContext schoolContext;
    private List<TeacherDto> teacherDtos;
    private List<Teacher> teachers;

//    public
//    List<TeacherDto> teacherDtos = ApiRequest.getDataList("", TeacherDto.class, schoolContext.getTokenHeaders().get(0));


    public TeacherCreator(SchoolContext schoolContext) {
        this.schoolContext = schoolContext;
        createTeachers();
    }

    private List<TeacherDto> getTeacherDtos() {
        return teacherDtos;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    private void createTeachers() {
        fillTeachersAndInfo();
        teachers = new ArrayList<>();
        for (TeacherDto dto : teacherDtos) {
            Teacher current = Teacher.builder()
                    .id(dto.id)
                    .userId(dto.userId)
                    .firstName(dto.firstName)
                    .lastName(dto.lastName)
                    .middleName(dto.middleName)
                    .sex(dto.sex)
                    .dateBirth(dto.dateBirth)
                    .email(dto.email)
                    .subjects(dto.subjects)
                    .houseMaster(dto.houseMaster)
                    .education(dto.education)
                    .scientificDegree(dto.scientificDegree)
                    .startDate(dto.startDate)
                    .pedagogicalActivityDate(dto.pedagogicalActivityDate)
                    .managingEmployee(dto.managingEmployee)
                    .nameManagingPosition(dto.nameManagingPosition)
                    .teachingStaff(dto.teachingStaff)
                    .nameTeacherPosition(dto.nameTeacherPosition)
                    .trainingAndSupportStaff(dto.trainingAndSupportStaff)
                    .servicePersonnel(dto.servicePersonnel)
                    .medicalWorker(dto.medicalWorker)
                    .nameMedicalPosition(dto.nameMedicalPosition)
                    .externalPartTime(dto.externalPartTime)
                    .build();
            teachers.add(current);
        }
    }

    private void fillTeachersAndInfo() {
        for (UserContext context : schoolContext.getUserContexts()) {
            try {
                teacherDtos = ApiRequest.getDataList(
                        PropertiesProcess.getUrl("teachers", context.getSchoolId()),
                        TeacherDto.class, Map.of("Access-Token", context.getToken()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
