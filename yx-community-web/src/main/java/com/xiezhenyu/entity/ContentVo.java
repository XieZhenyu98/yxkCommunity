package com.xiezhenyu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Tim
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ContentVo {

    private Long id;

    private String title;

    private String content;

    private String time;

    private Long times;

    private byte topping;

    private byte marvellous;

    private Long adoptReplyId;

    private Integer money;

    private Integer replyNum;

    private UserVo userVo;

    private SonModuleVo sonModule;

}
