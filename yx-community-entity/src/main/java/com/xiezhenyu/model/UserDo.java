package com.xiezhenyu.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xiezhenyu.entity.UserVo;
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
@TableName("user")
@ToString
@Accessors(chain = true)
public class UserDo {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("email")
    private String email;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("sex")
    private Byte sex;
    @TableField("city")
    private String city;
    @TableField("personal_signature")
    private String personalSignature;
    @TableField("image")
    private String image;
    @TableField("activate_mailbox")
    private Byte activateMailbox;
    @TableField("diamonds")
    private Integer diamonds;
    @TableField("authentication")
    private String authentication;
    @TableField("joining_time")
    private String joiningTime;
    @TableField("last_time")
    private String lastTime;
    @TableField("experience")
    private Long experience;
    @TableField("level")
    private Integer level;
    @TableField("is_delete")
    private Integer isDelete;
    public UserVo toUserVo(){
        return new UserVo().setId(this.getId())
            .setEmail(this.getEmail())
            .setUsername(this.getUsername())
            .setSex(this.getSex())
            .setCity(this.getCity())
            .setPersonalSignature(this.getPersonalSignature())
            .setImage(this.getImage())
            .setActivateMailbox(this.getActivateMailbox())
            .setDiamonds(this.getDiamonds())
            .setAuthentication(this.getAuthentication())
            .setJoiningTime(this.getJoiningTime())
            .setLastTime(this.getLastTime())
            .setExperience(this.experience)
            .setLevel(this.level)
            .setIsDelete(this.isDelete);
    }
}
