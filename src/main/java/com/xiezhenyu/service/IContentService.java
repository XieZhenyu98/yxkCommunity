package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.ContentVo;
import com.xiezhenyu.model.ContentDo;

import java.util.List;

/**
 * @author Tim
 */
public interface IContentService {
    /**
     * 添加帖子
     * @param contentDo
     * @return
     */
    public boolean add(ContentDo contentDo);

    /**
     * 通过id查找帖子
     * @param id
     * @return
     */
    public ContentDo getContentById(Long id);

    /**
     * 查看所有帖子列表
     * @param limit  分页大小
     * @param offset 偏移值
     * @return
     */
    public Page<ContentDo> list(Integer limit, Integer offset);

    /**
     * 查看指定板块帖子列表
     * @param limit  分页大小
     * @param offset 偏移值
     * @param module_id 板块id
     * @return
     */
    public Page<ContentVo> listByModuleId(Long module_id,Integer limit, Integer offset);

    /**
     * 查看所有置顶帖子
     * @return
     */
    public List<ContentVo> listOfTop();

    /**
     * 修改帖子
     * @param contentDo
     * @return
     */
    public ContentDo updateContent(ContentDo contentDo);

    /**
     * 通过ID删除帖子
     * @param id
     * @return
     */
    public boolean deleteContentById(Long id);

}
