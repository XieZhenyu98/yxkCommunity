package com.xiezhenyu.entity;

import com.xiezhenyu.model.SonModuleDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.catalina.User;

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

    private SonModuleDo sonModule;

    private String title;

    private String content;

    private String time;

    private UserVo userVo;

    private Long times;

    private byte topping;

    private byte marvellous;

    private Long adoptReplyId;

    private Integer money;

}
