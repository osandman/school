package net.osandman.school.dto;

import java.util.Map;

public record SubjectMarkDto(long subjectId, Map<Long, Double> marks) {
}
