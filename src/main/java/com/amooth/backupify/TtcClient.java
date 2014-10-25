package com.amooth.backupify;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public class TtcClient {

    private final String host = "myttc.ca";

    public TtcClient() {
    }

    public Location getLocation(String location) throws URISyntaxException, IOException {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost(host)
                .setPath(String.format("/%s.json", location))
                .build();
        HttpGet httpGet = new HttpGet(uri);

        ResponseHandler<Location> responseHandler = new ResponseHandler<Location>() {
            @Override
            public Location handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                StatusLine statusLine = httpResponse.getStatusLine();
                HttpEntity httpEntity = httpResponse.getEntity();
                if (statusLine.getStatusCode() >= 300) {
                    throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                }
                if (httpEntity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }

                Gson gson = new GsonBuilder().create();
                ContentType contentType = ContentType.getOrDefault(httpEntity);
                Charset charset = contentType.getCharset();
                Reader reader = new InputStreamReader(httpEntity.getContent(), charset);
                return gson.fromJson(reader, Location.class);
            }
        };

        CloseableHttpClient httpClient = HttpClients.createDefault();
        return httpClient.execute(httpGet,responseHandler);
    }
}
