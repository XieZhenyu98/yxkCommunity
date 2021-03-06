package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.ContentVo;
import com.xiezhenyu.entity.SonModuleVo;
import com.xiezhenyu.mapper.ContentMapper;
import com.xiezhenyu.model.ContentDo;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.ICollectionContentService;
import com.xiezhenyu.service.IContentService;
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
    private ICollectionContentService collectionContentService;

    @Override
    public ContentDo add(ContentDo contentDo) {
        contentDo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int num = contentMapper.insert(contentDo);
        ContentDo contentDB = contentMapper.selectOne(new QueryWrapper<ContentDo>().eq("user_id", contentDo.getUserId()).orderByDesc("time").last("limit 1"));
        return contentDB;
    }

    @Override
    public ContentVo getContentById(Long id) {
        ContentVo contentVo = new ContentVo();
        contentVo.setId(id);
        ContentVo contentVod = contentMapper.selectContentById(contentVo);
        ContentDo contentDo = new ContentDo()
                .setTimes(contentVod.getTimes() + 1)
                .setId(id)
                .setModuleId(contentVod.getSonModule().getId())
                .setTitle(contentVod.getTitle())
                .setContent(contentVod.getContent())
                .setTime(contentVod.getTime())
                .setUserId(contentVod.getUserVo().getId())
                .setTopping(contentVod.getTopping())
                .setMarvellous(contentVod.getMarvellous())
                .setMoney(contentVod.getMoney());
        contentMapper.updateById(contentDo);
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

    @Override
    public Page<ContentDo> selectListByUserId(Long userId, Integer limit, Integer offset) {
        Page<ContentDo> page = contentMapper.selectPage(new Page<>(limit, offset), new QueryWrapper<ContentDo>().eq("user_id", userId).orderByDesc("time"));
        return page;
    }

    @Override
    public CommonResult selectContentByCollectionContentIdList(Integer limit,Integer offset,Long userId) {
        Object[] contentIds = collectionContentService.getUserCollectionContentId(userId);
        if(contentIds==null){
            return CommonResult.successCommonResult(null,"查询成功");
        }
        String ids = "";
        int index = limit+offset;
        if(index > contentIds.length){
            index = contentIds.length;
        }
        for (int i = limit; i<index; i++){
            if(i==limit){
                ids =contentIds[i].toString();
            }else{
                ids = ids + "," + contentIds[i];
            }
        }
        List<ContentDo> list = contentMapper.selectList(new QueryWrapper<ContentDo>().inSql("id", ids));
        Page<ContentDo> page = new Page<>();
        page.setRecords(list);
        page.setTotal(contentIds.length);
        page.setSize(10);
        page.setCurrent(limit/10+1);
        CommonResult result = CommonResult.successCommonResult(page, "查询成功");
        return result;
    }

    @Override
    public List<ContentDo> getAllContent() {
        List<ContentDo> contentDos = contentMapper.selectList(new QueryWrapper<ContentDo>());
        return contentDos;
    }

}
