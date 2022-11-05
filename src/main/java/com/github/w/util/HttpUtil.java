package com.github.w.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.w.enums.JacksonSingleton;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author wsy
 * @date 2022/11/5 2:41 PM
 * @Description
 */
public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = JacksonSingleton.JACKSON.getInstance();

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    private static final Long DEFAULT_VALID_MILLIS;

    private static final Integer DEFAULT_MAX_RETRY_TIMES;

    static {
        // 后续可以考虑读配置文件
        DEFAULT_VALID_MILLIS = TimeUnit.SECONDS.toMillis(10);
        DEFAULT_MAX_RETRY_TIMES = 3;
    }

    public static String sendRequest(Request request) {
        return sendRequest(request, DEFAULT_MAX_RETRY_TIMES, DEFAULT_VALID_MILLIS);
    }

    public static <T> T sendRequest(Request request, Integer maxRetry, Class<T> clazz) {
        String responseJson = sendRequest(request, maxRetry, DEFAULT_VALID_MILLIS);
        try {
            return OBJECT_MAPPER.readValue(responseJson, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T sendRequest(Request request, Class<T> clazz) {
        String responseJson = sendRequest(request, DEFAULT_MAX_RETRY_TIMES, DEFAULT_VALID_MILLIS);
        try {
            return OBJECT_MAPPER.readValue(responseJson, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sendRequest(Request request, Integer maxRetryTimes, Long validMillis) {
        int retryTimes = 0;
        long startTimeMillis = System.currentTimeMillis();
        long currentTimeMillis = 0;
        do {
            currentTimeMillis = System.currentTimeMillis();

            Call call = OK_HTTP_CLIENT.newCall(request);

            if (startTimeMillis + validMillis < currentTimeMillis && !call.isCanceled()) {
                call.cancel();
                throw new RuntimeException("current http request Expired");
            }

            Response response = null;

            try {
                 response = call.execute();

                if (response.isSuccessful()) {
                    return response.body().string();
                } else {
                    log.error("current http execute exception [{}]", response.message());
                }
            } catch (Throwable t) {
                log.error("current http execute exception [{}]", ExceptionUtil.getStackTrace(t));
            }

            retryTimes++;
        } while (retryTimes < maxRetryTimes);

        throw new RuntimeException("current http request Maximum number of requests exceeded");
    }

}
