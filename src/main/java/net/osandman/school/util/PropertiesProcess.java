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

    public static String getUrl(String requestName, Object... args) {
        Properties urlString = new Properties();
        try {
            urlString.load(new FileReader(PROPERTY_FILE));
            String currentUrl = urlString.getProperty(requestName);
            String[] strArgs = new String[args.length];
            for (int i = 0; i < args.length; i++) {
                strArgs[i] = String.valueOf(args[i]);
            }
            return MessageFormat.format(currentUrl, strArgs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> getTokens(String propertiesFileName) {
        Properties init = new Properties();
        try {
            init.load(new FileReader(propertiesFileName));
            Map<String, String> result = new HashMap<>();
            for (Map.Entry<Object, Object> entry : init.entrySet()) {
                result.put((String) entry.getKey(), (String) entry.getValue());
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
