package net.osandman.school.model;

import lombok.Data;
import net.osandman.school.dto.UserContextDto;
import net.osandman.school.service.ApiRequest;
import net.osandman.school.util.PropertiesProcess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolContext {
    private final Map<String, UserContextDto> userContextDtos = new HashMap<>();
    private final List<UserContext> userContexts = new ArrayList<>();
    private final String propertiesFileName;

    public SchoolContext(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
        createUserContexts();
    }

    public List<UserContext> getUserContexts() {
        return userContexts;
    }

    public List<Map<String, String>> getTokenHeaders() {
        List<Map<String, String>> headers = new ArrayList<>();
        for (UserContext context : userContexts) {
            headers.add(Map.of("Access-Token", context.getToken()));
        }
        return headers;
    }

    private void createUserContexts() {
        fillDtoUserContexts();
        for (Map.Entry<String, UserContextDto> dto : userContextDtos.entrySet()) {
            userContexts.add(UserContext.builder()
                    .token(dto.getKey())
                    .shortName(dto.getValue().getShortName())
                    .personId(dto.getValue().getPersonId())
                    .userId(dto.getValue().getUserId())
                    .groupId(dto.getValue().getGroupIds().get(0))
                    .schoolId(dto.getValue().getSchoolIds().get(0))
                    .build());
        }
    }


    private void fillDtoUserContexts() {
        try {
            String contextUrl = PropertiesProcess.getUrl("schoolContext");
            Map<String, String> map = PropertiesProcess.getTokens(propertiesFileName);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                UserContextDto userContextDto = ApiRequest.getData(contextUrl,
                        UserContextDto.class,
                        Map.of("Access-Token", entry.getValue()));
//                System.out.println(userContextDto);
                userContextDtos.put(entry.getValue(), userContextDto);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
