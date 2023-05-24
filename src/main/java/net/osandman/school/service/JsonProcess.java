package net.osandman.school.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.osandman.school.dto.SubjectMarkDto;

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

    public static void writeJsonToFile(Path path, String jsonString, boolean append) {
        try (BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(path.toFile(), append))) {
            fos.write(jsonString.getBytes(StandardCharsets.UTF_8));
            fos.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeJsonToFile(Path path, Map<String, Object> jsonMap) {
        try (FileWriter fileWriter = new FileWriter(path.toFile())) {
            fileWriter.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPrettyJsonString(String input) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(input);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<SubjectMarkDto> getMarksFromJson(String jsonString) {
        try {
            return getMarksFromJsonNode(objectMapper.readTree(jsonString));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<SubjectMarkDto> getMarksFromJson(Path jsonPath) {
        try {
            return getMarksFromJsonNode(objectMapper.readTree(jsonPath.toFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<SubjectMarkDto> getMarksFromJsonNode(JsonNode root) {
        List<SubjectMarkDto> listMarks = new ArrayList<>();
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
            listMarks.add(new SubjectMarkDto(subjId, mapMarks));
        }
        return listMarks;
    }

    public static <T> T[] getArrayJson(String inputString, Class<T[]> aClass) {
        try {
            return objectMapper.readValue(inputString, aClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getJson(String str, Class<T> clazz) {
        try {
            return objectMapper.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> void writeArrayJsonToFile(Path path, List<T> data) {
        try (FileWriter fileWriter = new FileWriter(path.toFile());
             SequenceWriter sequenceWriter = objectMapper.writer().writeValuesAsArray(fileWriter)) {
            for (T currenData : data) {
                sequenceWriter.write(currenData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> getListFromJson(Path path, Class<T> aClass) {
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, aClass);
        try {
            return objectMapper.readValue(path.toFile(), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> getListFromJson(String input, Class<T> aClass) {
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, aClass);
        try {
            return objectMapper.readValue(input, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> Map<K, V> getMapFromJson(String input) {
        TypeReference<HashMap<K, V>> typeRef = new TypeReference<>() {
        };
        try {
            return objectMapper.readValue(input, typeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
