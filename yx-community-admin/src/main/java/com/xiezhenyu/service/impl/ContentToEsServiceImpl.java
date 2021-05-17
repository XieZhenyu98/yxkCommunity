package com.xiezhenyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.xiezhenyu.model.ContentDo;
import com.xiezhenyu.service.ContentToEsService;
import com.xiezhenyu.service.IContentService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Tim
 * @date 2021/5/17
 */
@Slf4j
@Service
public class ContentToEsServiceImpl implements ContentToEsService {

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
    private static final String ES_TIMEOUT = "2m";

    @Override
    public boolean contentToEs() {
        List<ContentDo> contents = contentService.getAllContent();
        // 把查询的数据放入es中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(ES_TIMEOUT);
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
}
