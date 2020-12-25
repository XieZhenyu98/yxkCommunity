package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.ContentVo;
import com.xiezhenyu.model.ContentDo;
import io.swagger.models.auth.In;

import java.util.ArrayList;
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
    public ContentDo add(ContentDo contentDo);

    /**
     * 通过id查找帖子
     * @param id
     * @return
     */
    public ContentVo getContentById(Long id);

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

    /**
     * 查看所有帖子
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<ContentVo> selectListVo(Integer limit, Integer offset);

    /**
     * 通过用户ID查询该用户的回复
     * @param userId
     * @param limit
     * @param offset
     * @return
     */
    public Page<ContentDo> selectListByUserId(Long userId, Integer limit, Integer offset);

    /**
     * 通过帖子Id列表查询收藏帖子列表
     * @param limit
     * @param offset
     * @param userId
     * @return
     */
    public List<ContentDo> selectContentByCollectionContentIdList(Integer limit,Integer offset,Long userId);

}
