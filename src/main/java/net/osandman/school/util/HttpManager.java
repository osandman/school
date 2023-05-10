package net.osandman.school.util;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public final class HttpManager {

    private static CloseableHttpClient httpClient;

    private HttpManager() {
    }

    public static CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }

}
