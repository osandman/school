package net.osandman.school.model;

import net.osandman.school.dto.TeacherDto;
import net.osandman.school.dto.UserDto;
import net.osandman.school.service.ApiRequest;
import net.osandman.school.util.PropertiesProcess;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TeacherCreator {

    private final SchoolContext schoolContext;
    private List<TeacherDto> teacherDtos;

//    public
//    List<TeacherDto> teacherDtos = ApiRequest.getDataList("", TeacherDto.class, schoolContext.getTokenHeaders().get(0));


    public TeacherCreator(SchoolContext schoolContext) {
        this.schoolContext = schoolContext;
        fillTeachersAndInfo();
    }

    public List<TeacherDto> getTeacherDtos() {
        return teacherDtos;
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
