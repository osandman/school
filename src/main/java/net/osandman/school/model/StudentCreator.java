package net.osandman.school.model;

import net.osandman.school.dto.Person;
import net.osandman.school.dto.UserDto;
import net.osandman.school.entity.Student;
import net.osandman.school.entity.StudentInfo;
import net.osandman.school.service.ApiRequest;
import net.osandman.school.util.PropertiesProcess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentCreator {
    private SchoolContext schoolContext;
    private List<Student> students;
    private List<StudentInfo> studentsInfo;
    private Map<String, List<Person>> personsMap;

    private StudentCreator() {
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private final StudentCreator newStudentCreator;

        private Builder() {
            newStudentCreator = new StudentCreator();
            newStudentCreator.students = new ArrayList<>();
            newStudentCreator.studentsInfo = new ArrayList<>();
            newStudentCreator.personsMap = new HashMap<>();
        }

        public Builder setContext(SchoolContext schoolContext) {
            newStudentCreator.schoolContext = schoolContext;
            return this;
        }

        public Builder createStudents() {
            newStudentCreator.fillStudentsAndInfo();
            return this;
        }

        public StudentCreator build() {
            return newStudentCreator;
        }

    }

    public List<Student> getStudents() {
        return students;
    }

    public List<StudentInfo> getStudentsInfo() {
        return studentsInfo;
    }

    private void fillStudentsAndInfo() {
        sendRequestsAndGetPersons();
        for (Map.Entry<String, List<Person>> entry : personsMap.entrySet()) {
            for (Person person : entry.getValue()) {
                try {
                    UserDto userDto = ApiRequest.getData(
                            PropertiesProcess.getUrl("userV1", person.userId()),
                            UserDto.class, Map.of("Access-Token", entry.getKey()));
                    UserContext userContext = schoolContext.getUserContexts()
                            .stream()
                            .filter(el -> el.getToken().equals(entry.getKey()))
                            .findFirst()
                            .orElseThrow();
                    students.add(createStudent(userContext, userDto));
                    studentsInfo.add(createStudentInfo(userDto));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Student createStudent(UserContext userContext, UserDto userDto) throws IOException {
        return Student.builder()
                .userId(userDto.getId())
                .personId(userDto.getPersonId())
                .groupId(userContext.getGroupId())
                .schoolId(userContext.getSchoolId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .middleName(userDto.getMiddleName())
                .sex(userDto.getSex())
                .build();
    }

    private StudentInfo createStudentInfo(UserDto userDto) {
        return StudentInfo.builder()
                .personId(userDto.getPersonId())
                .shortName(userDto.getShortName())
                .birthday(userDto.getBirthday())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .photoUrl(userDto.getPhotoLarge())
                .build();
    }

    private void sendRequestsAndGetPersons() {
        for (UserContext userContext : schoolContext.getUserContexts()) {
            try {
                List<Person> persons = ApiRequest.getDataList(
                        PropertiesProcess.getUrl("personsV2",
                                userContext.getGroupId()),
                        Person.class,
                        Map.of("Access-Token", userContext.getToken())
                );
                personsMap.put(userContext.getToken(), persons);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
