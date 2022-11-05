package com.github.w;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.w.enums.JacksonSingleton;
import com.github.w.mode.GoFoundResponse;
import com.github.w.mode.IndexModel;
import com.github.w.mode.QueryModel;
import com.github.w.util.AssertUtil;
import com.github.w.util.HttpUtil;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.github.w.common.Constants.SUCCESS_BOOLEAN_VALUE;

/**
 * @author wsy
 * @date 2022/11/5 2:40 PM
 * @Description
 */
public class GoFoundClient {
    private static final Logger log = LoggerFactory.getLogger(GoFoundClient.class);

    private static final ObjectMapper OBJECT_MAPPER = JacksonSingleton.JACKSON.getInstance();

    private final String requestURL;

    public GoFoundClient(String requestURL) {
        this.requestURL = requestURL;
    }

    public GoFoundResponse addOrUpdateIndex(String databaseName, IndexModel indexModel) {
        AssertUtil.isNotNull(databaseName);
        AssertUtil.isNotNull(indexModel);

        Request.Builder builder = generateRequest();
        String url = generateURL(databaseName, "/api/index");
        try {
            RequestBody requestBody = RequestBody.create(OBJECT_MAPPER.writeValueAsBytes(indexModel));
            Request request = builder.url(url)
                    .post(requestBody)
                    .build();

            log.info("add or update index {}", OBJECT_MAPPER.writeValueAsString(indexModel));

            GoFoundResponse response = HttpUtil.sendRequest(request, GoFoundResponse.class);

            if (SUCCESS_BOOLEAN_VALUE.equals(response.getState())) {
                log.info("add or update index success {}", OBJECT_MAPPER.writeValueAsString(indexModel));
            }

            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public GoFoundResponse batchAddOrUpdateIndex(String databaseName, List<IndexModel> indexModelList) {
        AssertUtil.isNotNull(databaseName);
        AssertUtil.isNotEmpty(indexModelList);

        Request.Builder builder = generateRequest();
        String url = generateURL(databaseName, "/api/index/batch");
        try {
            RequestBody requestBody = RequestBody.create(
                    OBJECT_MAPPER.writeValueAsBytes(indexModelList),
                    MediaType.parse("application/json; charset=utf-8")
            );

            Request request = builder.url(url)
                    .post(requestBody)
                    .build();

            log.info("request batch add or update index {}", OBJECT_MAPPER.writeValueAsBytes(indexModelList));

            GoFoundResponse response = HttpUtil.sendRequest(request, GoFoundResponse.class);

            if (SUCCESS_BOOLEAN_VALUE.equals(response.getState())) {
                log.info("batch add or update index success {}", OBJECT_MAPPER.writeValueAsString(indexModelList));
            }

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GoFoundResponse removeIndex(String databaseName, Integer id) {
        AssertUtil.isNotNull(databaseName);
        AssertUtil.isNotNull(id);

        try {
            IndexModel indexModel = new IndexModel();
            indexModel.setId(id);

            Request.Builder builder = generateRequest();
            String url = generateURL(databaseName, "/api/index/remove");
            RequestBody requestBody = RequestBody.create(
                    OBJECT_MAPPER.writeValueAsBytes(indexModel),
                    MediaType.parse("application/json; charset=utf-8")
            );

            Request request = builder.url(url)
                    .post(requestBody)
                    .build();

            log.info("request remove index id {}", id);

            GoFoundResponse response = HttpUtil.sendRequest(request, GoFoundResponse.class);

            if (SUCCESS_BOOLEAN_VALUE.equals(response.getState())) {
                log.info("remove success index id {}", id);
            }

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GoFoundResponse query(String databaseName, QueryModel queryModel) {
        AssertUtil.isNotNull(databaseName);
        AssertUtil.isNotNull(queryModel);

        try {
            Request.Builder builder = generateRequest();
            String url = generateURL(databaseName, "/api/query");
            RequestBody requestBody = RequestBody.create(
                    OBJECT_MAPPER.writeValueAsBytes(queryModel),
                    MediaType.parse("application/json; charset=utf-8")
            );

            log.info("query database {} query param {}",
                    databaseName,
                    OBJECT_MAPPER.writeValueAsString(queryModel)
            );


            Request request = builder.url(url)
                    .post(requestBody)
                    .build();

            return HttpUtil.sendRequest(request, GoFoundResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GoFoundResponse removeDatabase(String databaseName) {
        AssertUtil.isNotNull(databaseName);

        try {
            Request.Builder builder = generateRequest();
            String url = generateURL(databaseName, "/api/db/drop");

            Request request = builder.url(url)
                    .get()
                    .build();

            log.info("request remove database {}", databaseName);

            GoFoundResponse response = HttpUtil.sendRequest(request, GoFoundResponse.class);

            if (SUCCESS_BOOLEAN_VALUE.equals(response.getState())) {
                log.info("remove database success {}", databaseName);
            }

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return return base request build
     */
    private Request.Builder generateRequest() {
        return new Request.Builder()
                .addHeader("content-type", "application/json");
    }

    private String generateURL(String databaseName, String suffix) {
        StringBuilder url = new StringBuilder(requestURL);

        url.append(suffix);
        url.append("?").append("database=").append(databaseName);

        return url.toString();
    }
}
