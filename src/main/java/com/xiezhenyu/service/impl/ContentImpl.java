package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.ContentVo;
import com.xiezhenyu.entity.SonModuleVo;
import com.xiezhenyu.mapper.ContentMapper;
import com.xiezhenyu.model.ContentDo;
import com.xiezhenyu.service.IContentService;
import com.xiezhenyu.service.IReplyService;
import com.xiezhenyu.service.ISonModuleService;
import com.xiezhenyu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tim
 */
@Service
public class ContentImpl implements IContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISonModuleService sonModuleService;

    @Autowired
    private IReplyService replyService;

    @Override
    public ContentDo add(ContentDo contentDo) {
        contentDo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int num = contentMapper.insert(contentDo);
        ContentDo contentDB = contentMapper.selectOne(new QueryWrapper<ContentDo>().eq("user_id", contentDo.getUserId()).orderByDesc("time").last("limit 1"));
        return contentDB;
    }

    @Override
    public ContentVo getContentById(Long id) {
//        ContentDo contentDo = contentMapper.selectById(id);
//        ContentVo contentVo = contentDo.toVo(sonModuleService.selectById(contentDo.getModuleId()), userService.getUserById(contentDo.getUserId()).toUserVo(), replyService.selectCountByUser(contentDo.getUserId()));
//        Long times = contentDo.getTimes()+1;
//        contentMapper.updateById(contentDo.setTimes(times));
        ContentVo contentVo = new ContentVo();
        contentVo.setId(id);
        ContentVo contentVod = contentMapper.selectContentById(contentVo);
        return contentVod;
    }


    @Override
    public Page<ContentVo> listByModuleId(Long module_id, Integer limit, Integer offset) {
        SonModuleVo sonModuleVo = new SonModuleVo();
        sonModuleVo.setId(module_id);
        ContentVo contentVo = new ContentVo();
        contentVo.setSonModule(sonModuleVo);
        ArrayList<ContentVo> contentVos = contentMapper.selectListVo(limit, offset, contentVo);
        Page<ContentVo> pageVo = new Page<>(limit,offset);
        pageVo.setRecords(contentVos);
        return pageVo;
    }

    @Override
    public List<ContentVo> listOfTop() {
        ArrayList<ContentVo> contentVos = contentMapper.selectListVo(null, null, new ContentVo().setTopping((byte) 1));
        return contentVos;
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

    @Override
    public ArrayList<ContentVo> selectListVo(Integer limit, Integer offset) {
        ArrayList<ContentVo> contentVos = contentMapper.selectListVo(limit, offset,null);
        return contentVos;
    }

}
