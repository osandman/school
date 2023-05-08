package net.osandman.school.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.osandman.school.dto.SubjectMark;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public class JsonProcess {
    static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    static {
//        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static void writeJsonToFile(Path path, String jsonString, boolean append) throws IOException {
        try (BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(path.toFile(), append))) {
            fos.write(jsonString.getBytes(StandardCharsets.UTF_8));
            fos.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
        }
    }

    public static void writeJsonToFile(Path path, Map<String, Object> jsonMap) throws IOException {
        try (FileWriter fileWriter = new FileWriter(path.toFile())) {
            fileWriter.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap));
        }
    }

    public static String getPrettyJsonString(String input) throws IOException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(input);
    }

    public static List<SubjectMark> getMarksFromJson(String jsonString) throws IOException {
        return getMarksFromJsonNode(objectMapper.readTree(jsonString));
    }

    public static List<SubjectMark> getMarksFromJson(Path jsonPath) throws IOException {
        return getMarksFromJsonNode(objectMapper.readTree(jsonPath.toFile()));
    }

    private static List<SubjectMark> getMarksFromJsonNode(JsonNode root) throws IOException {
        List<SubjectMark> listMarks = new ArrayList<>();
//        JsonNode root = objectMapper.readTree(new File("src/main/resources/subject_marks.json"));
        JsonNode subj = root.path("subjectMarks");
        for (JsonNode node : subj) {
            long subjId = node.get("subject").asLong();
            JsonNode subjectMarks = node.path("marks");
            Map<Long, Double> mapMarks = new HashMap<>();
            for (JsonNode mark : subjectMarks) {
                long personId = mark.get("person").asLong();
                double value = mark.get("value").asDouble();
                mapMarks.put(personId, value);
            }
            listMarks.add(new SubjectMark(subjId, mapMarks));
        }
        return listMarks;
    }

    public static <T> T[] getArrayJson(String inputString, Class<T[]> aClass) throws IOException {
        return objectMapper.readValue(inputString, aClass);
    }

    public static <T> T getJson(String str, Class<T> clazz) throws IOException {
        return objectMapper.readValue(str, clazz);
    }


    public static <T> void writeArrayJsonToFile(Path path, List<T> data) throws IOException {
        FileWriter fileWriter = new FileWriter(path.toFile());
        SequenceWriter sequenceWriter = objectMapper.writer().writeValuesAsArray(fileWriter);
        for (T currenData : data) {
            sequenceWriter.write(currenData);
        }
        sequenceWriter.close();
    }

    public static <T> List<T> getListFromJson(Path path, Class<T> aClass) throws IOException {
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, aClass);
        return objectMapper.readValue(path.toFile(), type);
    }

    public static <T> List<T> getListFromJson(String input, Class<T> aClass) throws IOException {
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, aClass);
        return objectMapper.readValue(input, type);
    }

    public static <K, V> Map<K, V> getMapFromJson(String input) throws IOException {
        TypeReference<HashMap<K, V>> typeRef = new TypeReference<>() {
        };
        return objectMapper.readValue(input, typeRef);
    }
}
