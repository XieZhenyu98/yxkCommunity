package com.xiezhenyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.xiezhenyu.model.ContentDo;
import com.xiezhenyu.service.ContentEsService;
import com.xiezhenyu.service.IContentService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Tim
 * @date 2021/5/17
 */
@Slf4j
@Service
public class ContentEsServiceImpl implements ContentEsService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private IContentService contentService;

    /**
     * ES中帖子的索引
     */
    private static final String CONTENT_ES_INDEX = "yxkbbs_content";
    /**
     * ES的超时时间
     */
    private static final String ES_INSET_TIMEOUT = "2m";

    private static final Integer ES_SEARCH_TIMEOUT = 60;

    @Override
    public boolean contentToEs() {
        List<ContentDo> contents = contentService.getAllContent();
        // 把查询的数据放入es中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(ES_INSET_TIMEOUT);
        for (int i = 0; i < contents.size(); i++) {
            bulkRequest.add(new IndexRequest(CONTENT_ES_INDEX)
                    .source(JSON.toJSONString(contents.get(i)), XContentType.JSON)
                    .id(contents.get(i).getId().toString()));
        }
        BulkResponse bulk = null;
        try {
            bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("帖子内容插入失败");
            e.printStackTrace();
        }
        return !bulk.hasFailures();
    }

    @Override
    public List<Map<String, Object>> searchPage(String keywords, int pageNo, int pageSize) {
        if(pageNo<=1){
            pageNo=1;
        }

        // 条件搜索
        SearchRequest searchRequest = new SearchRequest(CONTENT_ES_INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);

        // 精准查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("content", keywords);
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(ES_SEARCH_TIMEOUT, TimeUnit.SECONDS));

        // 执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("执行搜索失败",e.getMessage());
        }

        // 解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for(SearchHit documentFields : searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }

        // 返回结果
        return list;
    }
}
