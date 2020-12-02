package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.mapper.ContentMapper;
import com.xiezhenyu.model.ContentDo;
import com.xiezhenyu.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tim
 */
@Service
public class ContentImpl implements IContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public boolean add(ContentDo contentDo) {
        contentDo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int num = contentMapper.insert(contentDo);
        return num > 0;
    }

    @Override
    public ContentDo getContentById(Long id) {
        ContentDo contentDo = contentMapper.selectById(id);
        Long times = contentDo.getTimes()+1;
        contentMapper.updateById(new ContentDo().setTimes(times).setId(id));
        return contentDo;
    }

    @Override
    public Page<ContentDo> list(Integer limit, Integer offset) {
        return contentMapper.selectPage(new Page<ContentDo>(limit, offset), null);
    }

    @Override
    public Page<ContentDo> listByModuleId(Long module_id, Integer limit, Integer offset) {
        return contentMapper.selectPage(new Page<ContentDo>(limit,offset), new QueryWrapper<ContentDo>().eq("module_id",module_id));
    }

    @Override
    public ContentDo updateContent(ContentDo contentDo) {
        int num = contentMapper.updateById(contentDo);
        if(num > 0){
            return contentMapper.selectById(contentDo.getId());
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteContentById(Long id) {
        int num = contentMapper.deleteById(id);
        return num > 0;
    }

}
