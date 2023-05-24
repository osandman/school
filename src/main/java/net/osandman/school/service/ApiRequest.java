package net.osandman.school.service;

import net.osandman.school.dto.SubjectMarkDto;
import net.osandman.school.util.HttpProcess;

import java.util.List;
import java.util.Map;


public class ApiRequest {

    public static <T> T getData(String url, Class<T> clazz, Map<String, String> headers) {
        String jsonString = getEntityString(url, headers);
        return JsonProcess.getJson(jsonString, clazz);
    }

    public static <T> List<T> getDataList(String url, Class<T> clazz, Map<String, String> headers) {
        String jsonString = getEntityString(url, headers);
        return JsonProcess.getListFromJson(jsonString, clazz);
    }

    public static List<SubjectMarkDto> getSubjectMark(String url, Map<String, String> headers) {
        String jsonString = getEntityString(url, headers);
        return JsonProcess.getMarksFromJson(jsonString);
    }

    private static String getEntityString(String url, Map<String, String> headers) {
        return HttpProcess.getEntityFromGetRequest(url, headers);
    }
}
