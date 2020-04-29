package cmsClient.Http;

import cmsClient.Http.requests.GetHttp;
import cmsClient.Http.requests.HttpRequest;

import java.io.IOException;
import java.net.MalformedURLException;

public class HttpRequestFactory {

    private String baseurl ;

    public HttpRequestFactory(String baseurl){
        this.baseurl = baseurl;
    }

    public HttpRequest createGetRequest(String url) throws MalformedURLException {
        try {
            return new GetHttp(baseurl+url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
