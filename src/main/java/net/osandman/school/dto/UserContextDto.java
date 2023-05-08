package net.osandman.school.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserContextDto {
    private long userId;
    private List<String> roles;
    private List<Object> children;
    private List<School> schools;
    private List<EduGroup> eduGroups;
    private String splitId;
    private long personId;
    private String shortName;
    private List<Long> schoolIds;
    private List<Long> groupIds;

    @Data
    public static class EduGroup {
        private long id;
        private String id_str;
        private List<Long> parentIds;
        private List<String> parentIds_str;
        private String type;
        private String name;
        private String fullName;
        private int parallel;
        private long timetable;
        private String timetable_str;
        private String status;
        private int studyyear;
        private Object subjects;
        private String journaltype;

        @Override
        public String toString() {
            return "EduGroup{" +
                    "id=" + id +
                    ", id_str='" + id_str + '\'' +
                    ", parentIds=" + parentIds +
                    ", parentIds_str=" + parentIds_str +
                    ", type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    ", fullName=" + fullName +
                    ", parallel=" + parallel +
                    ", timetable=" + timetable +
                    ", timetable_str='" + timetable_str + '\'' +
                    ", status='" + status + '\'' +
                    ", studyyear=" + studyyear +
                    ", subjects=" + subjects +
                    ", journaltype='" + journaltype + '\'' +
                    '}';
        }
    }

    @Data
    public static class School {
        private long id;
        private String name;
        private String type;
        private List<Long> groupIds;

        @Override
        public String toString() {
            return "School{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", groupIds=" + groupIds +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserContextDto{" +
                "userId=" + userId +
                ", shortName='" + shortName + '\'' +
                ", personId=" + personId +
                ", roles=" + roles +
                ", children=" + children +
                ", schools=" + schools +
                ", eduGroups=" + eduGroups +
                ", splitId='" + splitId + '\'' +
                ", schoolIds=" + schoolIds +
                ", groupIds=" + groupIds +
                '}';
    }
}
