package com.xiezhenyu.entity;

import com.xiezhenyu.model.ContentDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

/**
 * @author Tim
 * @date 2020/12/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalReplyVo {

    private Long id;

    private String time;

    private String content;

    private ContentDo contentDo;

}
