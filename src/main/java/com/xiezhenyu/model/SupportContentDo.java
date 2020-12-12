package com.xiezhenyu.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tim
 * @date 2020/12/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("support_content")
public class SupportContentDo {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "content_id")
    private Long contentId;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "user_id")
    private Long userId;

}
