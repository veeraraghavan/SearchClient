package org.project.searchclient;

import com.google.gson.Gson;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RestClient {
    public String post(Request request){

        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost("http://localhost:8080/api/v1/search");
            post.setEntity(new StringEntity(new Gson().toJson(request,Request.class)));
            post.addHeader("content-type","application/json");
            CloseableHttpResponse response = client.execute(post);
            return EntityUtils.toString(response.getEntity());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
