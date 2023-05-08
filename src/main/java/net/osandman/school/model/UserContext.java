package net.osandman.school.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserContext {
    private String token;
    private String shortName;
    private long userId;
    private long personId;
    private long groupId;
    //    private String groupName;
    private long schoolId;
//    private String schoolName;
}
