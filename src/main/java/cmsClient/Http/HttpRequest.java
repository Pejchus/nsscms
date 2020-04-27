package cmsClient.Http;

public class HttpRequest {
    String baseurl = "http://localhost:8080";
    String content;
    String url;

    HttpRequest(){
        url = baseurl + "/rest/manager/1";
        content="";
    }
}
