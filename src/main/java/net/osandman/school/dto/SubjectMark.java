package net.osandman.school.dto;

import java.util.Map;

public record SubjectMark(long subjectId, Map<Long, Double> marks) {
}
