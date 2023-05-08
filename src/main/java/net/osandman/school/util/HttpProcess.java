package net.osandman.school.util;

import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public final class HttpProcess {

    public String getEntityFromGetRequest(String url, Map<String, String> headers) {
        HttpGet requestGet = new HttpGet(url);
        for (Map.Entry<String, String> header : headers.entrySet()) {
            requestGet.addHeader(header.getKey(), header.getValue());
        }
        requestGet.addHeader("Accept", "application/json");
        requestGet.addHeader("Content-Type", "application/json");
        requestGet.addHeader("Accept-Charset", "utf-8");
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(requestGet)) {
            System.out.println(response.getStatusLine().toString());
//            printHeaders(response);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printHeaders(CloseableHttpResponse response) {
        Header[] headers = response.getAllHeaders();
        for (Header header : headers) {
            for (HeaderElement element : header.getElements()) {
                System.out.println(element.getName() + " = " + element.getValue());
            }
        }
    }

}
