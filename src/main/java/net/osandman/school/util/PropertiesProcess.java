package net.osandman.school.util;

import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesProcess {
    private static final String PROPERTY_FILE = "src/main/resources/request_url.properties";
    private PropertiesProcess() {
    }

    public static String getUrl(String requestName, Object... args) throws IOException {
        Properties urlString = new Properties();
        urlString.load(new FileReader(PROPERTY_FILE));
        String currentUrl = urlString.getProperty(requestName);
        String[] strArgs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            strArgs[i] = String.valueOf(args[i]);
        }
        return MessageFormat.format(currentUrl, strArgs);
    }

    public static Map<String, String> getTokens(String propertiesFileName) throws IOException {
        Properties init = new Properties();
        init.load(new FileReader(propertiesFileName));
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : init.entrySet()) {
            result.put((String) entry.getKey(), (String) entry.getValue());
        }
        return result;
    }
}
