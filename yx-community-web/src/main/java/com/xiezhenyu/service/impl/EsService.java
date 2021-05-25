package com.xiezhenyu.service.impl;

import com.xiezhenyu.model.ContentDo;
import com.xiezhenyu.model.SearchKeywordsDo;
import com.xiezhenyu.service.IEsService;
import com.xiezhenyu.service.ISearchKeywordsService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Tim
 * @date 2021/5/25
 */
@Service
@Slf4j
public class EsService implements IEsService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private ISearchKeywordsService searchKeywordsService;
    /**
     * ES中帖子的索引
     */
    private static final String CONTENT_ES_INDEX = "yxkbbs_content";
    /**
     * ES的超时时间
     */
    private static final Integer ES_SEARCH_TIMEOUT = 60;

    @Override
    public List<Map<String,Object>> getContentByKeywords(String keywords, Integer pageNo, Integer pageSize) {
        // 向数据库添加查询记录
        SearchKeywordsDo searchKeywordsDo = new SearchKeywordsDo();
        searchKeywordsDo.setKeywords(keywords);
        searchKeywordsService.addKeywords(searchKeywordsDo);
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest(CONTENT_ES_INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);
        // 查询
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keywords, "title", "content");
        sourceBuilder.query(multiMatchQueryBuilder);
        sourceBuilder.timeout(new TimeValue(ES_SEARCH_TIMEOUT, TimeUnit.SECONDS));

        // title高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title").field("content");
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);
        // 执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("搜索出错:{}",e.getMessage());
        }

        // 解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for(SearchHit documentFields : searchResponse.getHits().getHits()){
            Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            HighlightField content = highlightFields.get("content");
            Map<String,Object> sourceAsMap = documentFields.getSourceAsMap();
            // 解析高亮的字段,将原来的字段换位我们高亮的字段即可
            if(title!=null){
                Text[] fragments = title.fragments();
                String n_title = "";
                for(Text text : fragments){
                    n_title += text;
                }
                sourceAsMap.put("title",n_title);
            }
            // 解析高亮的字段,将原来的字段换位我们高亮的字段即可
            if(content!=null){
                Text[] fragments = content.fragments();
                String n_content = "";
                for(Text text : fragments){
                    n_content += text;
                }
                sourceAsMap.put("content",n_content);
            }
            list.add(sourceAsMap);
        }
        return list;
    }
}
