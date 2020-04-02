package com.wickyan.proposal.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * Created by wickyan on 2020/3/10
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId(type = IdType.INPUT)
	private Long userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 部门ID
	 */
	private Long deptId;
	/**
	 * 身份证号
	 */
	private String idcardNum;
	/**
	 * 密码
	 */
	private String userPsw;
	/**
	 * 是否修改密码
	 */
	private Integer pswChanged;
	/**
	 * 手机号
	 */
	private String mobil;
	/**
	 * 邮箱
	 */
	private String mail;
	/**
	 * 角色
	 */
	private Integer role;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	/**
	 * 逻辑删除
	 */
	@TableLogic
	private Integer deleted;
	/**
	 * 
	 */
	private Integer version;

}
