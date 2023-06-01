package net.osandman.school.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertiesProcess {
    private static final String PROP_URL = "request_url.properties";
    private static final String INIT_URL = "init.properties";

    private PropertiesProcess() {
    }

    public static String getUrl(String requestName, Object... args) {
        try (InputStream urlPropResource = PropertiesProcess.class.getClassLoader().getResourceAsStream(PROP_URL)) {
            Properties urlString = new Properties();
            urlString.load(urlPropResource);
            String currentUrl = urlString.getProperty(requestName);
            return MessageFormat.format(currentUrl, Arrays.stream(args).map(String::valueOf).toArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> getTokens() {
        try (InputStream tokensInputStream = PropertiesProcess.class.getClassLoader().getResourceAsStream(INIT_URL)) {
            Properties init = new Properties();
            init.load(tokensInputStream);
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
