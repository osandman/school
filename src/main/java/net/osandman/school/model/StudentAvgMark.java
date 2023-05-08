package net.osandman.school.model;

import lombok.Data;
import net.osandman.school.entity.Student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Data
public final class StudentAvgMark {
    private final Student student;
    private final Map<String, Double> avgMarks;
    private double avgAllMark;

    public double getAvgMark() {
        if (avgAllMark == 0 && avgMarks.size() != 0) {
            avgAllMark = avgMarks.values().stream().mapToDouble(el -> el).average().getAsDouble();
            BigDecimal bigDecimal = BigDecimal.valueOf(avgAllMark);
            avgAllMark = bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        return avgAllMark;
    }

    @Override
    public String toString() {
        return "студент '" + student.getFirstName() + " " + student.getLastName() + "', " +
                "средний балл = " + getAvgMark() + "\n" +
                "средние оценки: " + avgMarks
                ;
    }
}
