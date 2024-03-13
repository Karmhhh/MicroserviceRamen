package com.example.MicroserviceRamen.dto;

// {
//     "api-call-name": STRINGA,
//     "api-call-url": STRINGA,
//     "api-request-method": STRINGA,
//     "response": INTEGER,
//     "response-body": STRINGA
//     }

public class ConfigObj {
  

    private String apiCallName;
    private String apiCallUrl;
    private String apiRequestMethod;
    private Integer response;
    private String responseBody;

    public String getApiCallName() {
        return apiCallName;
    }

    public void setApiCallName(String apiCallName) {
        this.apiCallName = apiCallName;
    }

    public String getApiCallUrl() {
        return apiCallUrl;
    }

    public void setApiCallUrl(String apiCallUrl) {
        this.apiCallUrl = apiCallUrl;
    }

    public String getApiRequestMethod() {
        return apiRequestMethod;
    }

    public void setApiRequestMethod(String apiRequestMethod) {
        this.apiRequestMethod = apiRequestMethod;
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "{\n\t\"api-call-name\": \"" + apiCallName + "\",\n" +
                "\t\"api-call-url\": \"" + apiCallUrl + "\",\n" +
                "\t\"api-request-method\": \"" + apiRequestMethod + "\",\n" +
                "\t\"response\": \"" + response + "\",\n" +
                "\t\"response-body\": \"" + responseBody +
                "\"\n}";
    }

}
