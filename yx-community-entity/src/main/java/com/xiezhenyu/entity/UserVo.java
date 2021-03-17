package com.xiezhenyu.entity;

import com.xiezhenyu.model.UserDo;
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
public class UserVo {

    private Long id;

    private String email;

    private String username;

    private Byte sex;

    private String city;

    private String personalSignature;

    private String image;

    private Byte activateMailbox;

    private Integer diamonds;

    private String authentication;

    private String joiningTime;

    private String lastTime;

    private Long experience;

    private Integer level;
}
