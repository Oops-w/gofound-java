package com.github.w;

import com.github.w.mode.GoFoundResponse;
import com.github.w.mode.IndexModel;
import com.github.w.mode.QueryModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wsy
 * @date 2022/11/5 3:44 PM
 * @Description
 */
public class GoFoundClintTest {
    public static void main(String[] args) {
        GoFoundClient goFoundClient = new GoFoundClient("http://localhost:5678");

        IndexModel indexModel = new IndexModel();
        indexModel.setId(1);
        indexModel.setText("hello");

        Map<String, Object> map = new HashMap<>();
        map.put("name", "w");
        map.put("age", 20);
        map.put("desc", "hello world");

        indexModel.setDocument(map);
        goFoundClient.addOrUpdateIndex("w", indexModel);

        QueryModel queryModel = new QueryModel();
        queryModel.setQuery("hello");
        GoFoundResponse response = goFoundClient.query("w", queryModel);

        System.out.println(response.getData());

        queryModel.setQuery("world");
        response = goFoundClient.query("w", queryModel);
        System.out.println(response.getData());
    }

    private static void testRemoveDatabase(GoFoundClient goFoundClient) {
        GoFoundResponse response = goFoundClient.removeDatabase("w");
        System.out.println(response.getState());
        System.out.println(response.getMessage());
    }

    private static void testQuery(GoFoundClient goFoundClient) {
        QueryModel queryModel = new QueryModel();
        queryModel.setQuery("hello world");

        GoFoundResponse response = goFoundClient.query("w", queryModel);

        System.out.println(response.getMessage());
        System.out.println(response.getState());
        System.out.println(response.getData());
    }

    private static void testRemoveIndex(GoFoundClient goFoundClient) {
        GoFoundResponse response = goFoundClient.removeIndex("w", 0);
        System.out.println(response.getState());
        System.out.println(response.getMessage());
    }

    private static void testBatchAddOrUpdateIndex(GoFoundClient goFoundClient) {
        List<IndexModel> indexModelList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            IndexModel indexModel = new IndexModel();

            Map<String, Object> map = new HashMap<>();

            map.put("context","hello world");

            indexModel.setId(i);
            indexModel.setText("w" + i);
            indexModel.setDocument(map);

            indexModelList.add(indexModel);
        }
        GoFoundResponse response = goFoundClient.batchAddOrUpdateIndex("w", indexModelList);
        System.out.println(response.getState());
        System.out.println(response.getMessage());
    }

    private static void testAddOrUpdateIndex(GoFoundClient goFoundClient) {
        IndexModel indexModel = new IndexModel();

        Map<String, Object> map = new HashMap<>();

        map.put("context","hello world");

        indexModel.setId(123);
        indexModel.setText("w");
        indexModel.setDocument(map);

        GoFoundResponse response = goFoundClient.addOrUpdateIndex("w", indexModel);
        System.out.println(response.getState());
        System.out.println(response.getMessage());
    }
}
