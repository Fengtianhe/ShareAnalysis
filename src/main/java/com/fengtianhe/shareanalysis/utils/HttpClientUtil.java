package com.fengtianhe.shareanalysis.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Http Client工具
 *
 * @author 冯天鹤
 * @version 1.0
 */
@Slf4j
public class HttpClientUtil {

    private static CloseableHttpClient HttpClientUtil = HttpClients.custom().build();

    private String url;

    private List<NameValuePair> nameValuePairs;

    private HttpUriRequest httpMessage;

    private boolean hasPost;

    private String jsonBody;

    private String cookie;

    public HttpClientUtil get(String url) {
        return service(url, new HttpGet(url), false);
    }

    public HttpClientUtil cookie(String cookie){
        this.cookie = cookie;
        return this;
    }
    public HttpClientUtil post(String url) {
        return service(url, new HttpPost(url), true);
    }

    private HttpClientUtil service(String url, HttpUriRequest httpMessage, boolean hasPost) {
        validateUrl(url);
        initFlag(url, hasPost);
        this.httpMessage = httpMessage;
        return this;
    }

    private void validateUrl(String url) {
        log.debug("验证URL[{}]是否为空", url);
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException();
        }
    }

    public String execute() throws IOException {
        setParameter();
        log.debug("访问请求地址[{}]获取结果", this.url);
        CloseableHttpResponse response = HttpClientUtil.execute(httpMessage);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        return result;
    }

    public <T> T execute(Class<T> clazz) throws IOException {
        return JSON.parseObject(execute(), clazz);
    }

    private void setParameter() throws UnsupportedEncodingException {
        log.debug("添加请求参数到请求主体上");
        if (httpMessage instanceof HttpGet) {
            if (nameValuePairs != null && !nameValuePairs.isEmpty()) {
                String tmp = URLEncodedUtils.format(nameValuePairs, "utf-8");
                this.url += (this.url.contains("?") ? "&" : "?") + tmp;
            }
            ((HttpGet) httpMessage).setURI(URI.create(this.url));
        } else if (httpMessage instanceof HttpPost) {
            if (StringUtils.isNotEmpty(this.jsonBody)) {
                httpMessage.addHeader("Content-type", "application/json; charset=utf-8");
                httpMessage.setHeader("Accept", "application/json");
                ((HttpPost) httpMessage).setEntity(new StringEntity(this.jsonBody, StandardCharsets.UTF_8));
            } else {
                httpMessage.addHeader("Content-type", "application/x-www-form-urlencoded");
                ((HttpPost) httpMessage).setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
            }
        }
    }

    private void initFlag(String url, boolean hasPost) {
        this.url = url;
        this.hasPost = hasPost;
    }

    public HttpClientUtil addHeader(String key, String value) {
        log.debug("校验请求主体是否存在");
        if (this.httpMessage == null) {
            throw new NullPointerException("未调用get或post方法");
        }
        log.debug("设置请求头");
        this.httpMessage.setHeader(key, value);
        return this;
    }

    public HttpClientUtil addAllHeader(Map<String, String> headers) {
        log.debug("校验请求主体是否存在");
        if (this.httpMessage == null) {
            throw new NullPointerException("未调用get或post方法");
        }
        log.debug("设置请求头");
        for (String key : headers.keySet()) {
            this.httpMessage.setHeader(key, headers.get(key));
        }
        return this;
    }

    public synchronized HttpClientUtil addParameter(String key, String value) {
        if (this.nameValuePairs == null) {
            this.nameValuePairs = new ArrayList<>();
        }
        log.debug("添加请求参数[{}, {}]到内存", key, value);
        this.nameValuePairs.add(new BasicNameValuePair(key, value));
        return this;
    }

    public synchronized HttpClientUtil addAllParameter(Map<String, String> params) {
        if (this.nameValuePairs == null) {
            this.nameValuePairs = new ArrayList<>();
        }
        for (String key : params.keySet()) {
            this.nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
        }
        return this;
    }

    public synchronized HttpClientUtil addJsonBody(String json) {
        this.jsonBody = json;
        return this;
    }

    public Map<String, String> getAllParameter() {
        int size = this.nameValuePairs.size();
        Map<String, String> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            map.put(this.nameValuePairs.get(i).getName(), this.nameValuePairs.get(i).getValue());
        }
        return map;
    }
}
