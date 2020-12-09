package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.ContentVo;
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
    public boolean add(ContentDo contentDo) {
        contentDo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int num = contentMapper.insert(contentDo);
        return num > 0;
    }

    @Override
    public ContentVo getContentById(Long id) {
        ContentDo contentDo = contentMapper.selectById(id);
        ContentVo contentVo = contentDo.toVo(sonModuleService.selectById(contentDo.getModuleId()), userService.getUserById(contentDo.getUserId()).toUserVo(), replyService.selectCountByUser(contentDo.getUserId()));
        Long times = contentDo.getTimes()+1;
        contentMapper.updateById(new ContentDo().setTimes(times).setId(id));
        return contentVo;
    }

    @Override
    public Page<ContentVo> list(Integer limit, Integer offset) {
        Page<ContentDo> page = contentMapper.selectPage(new Page<ContentDo>(limit, offset), null);
        List<ContentDo> records = page.getRecords();
        Page<ContentVo> pageVo = new Page<>();
        List<ContentVo> list = new ArrayList<>();
        for (ContentDo contentDo : records){
            Long sonModuleId = contentDo.getModuleId();
            Long userId = contentDo.getUserId();
            ContentVo contentVo = contentDo.toVo(sonModuleService.selectById(sonModuleId),userService.getUserById(userId).toUserVo(),replyService.selectCountByContent(contentDo.getId()));
            list.add(contentVo);
        }
        pageVo.setRecords(list).setTotal(page.getTotal()).setCurrent(page.getCurrent()).setSize(page.getSize());
        return pageVo;
    }

    @Override
    public Page<ContentVo> listByModuleId(Long module_id, Integer limit, Integer offset) {
        Page<ContentDo> page = contentMapper.selectPage(new Page<ContentDo>(limit, offset), new QueryWrapper<ContentDo>().eq("module_id", module_id));
        List<ContentDo> records = page.getRecords();
        Page<ContentVo> pageVo = new Page<>();
        List<ContentVo> list = new ArrayList<>();
        for (ContentDo contentDo : records){
            Long sonModuleId = contentDo.getModuleId();
            Long userId = contentDo.getUserId();
            ContentVo contentVo = contentDo.toVo(sonModuleService.selectById(sonModuleId),userService.getUserById(userId).toUserVo(),replyService.selectCountByContent(contentDo.getId()));
            list.add(contentVo);
        }
        pageVo.setRecords(list).setTotal(page.getTotal()).setCurrent(page.getCurrent()).setSize(page.getSize());
        return pageVo;
    }

    @Override
    public List<ContentVo> listOfTop() {
        List<ContentVo> list = new ArrayList<>();
        List<ContentDo> toppingList = contentMapper.selectList(new QueryWrapper<ContentDo>().eq("topping", 1));
        for (ContentDo contentDo : toppingList){
            Long sonModuleId = contentDo.getModuleId();
            Long userId = contentDo.getUserId();
            ContentVo contentVo = contentDo.toVo(sonModuleService.selectById(sonModuleId),userService.getUserById(userId).toUserVo(),replyService.selectCountByContent(contentDo.getId()));
            list.add(contentVo);
        }
        return list;
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
