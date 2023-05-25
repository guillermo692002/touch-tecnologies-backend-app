package biz.touchtechnologies.backendchallanege.util;

import java.util.Map;

public interface HttpClientAdapter {

    public <T> T doGet(String endpoint, Map<String, String> queryParams, Class<T> bodyResponseType);

    public <T, R> T doPost(String endpoint, R bodyRequest, Class<T> bodyResponseType);

    public <T> T doDelete(String endpoint, Class<T> bodyResponseType);

    public <T, R> T doPut(String endpoint, R bodyRequest, Class<T> bodyResponseType);
}
