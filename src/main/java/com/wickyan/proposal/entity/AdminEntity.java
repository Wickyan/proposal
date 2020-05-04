package com.wickyan.proposal.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员
 * Created by wickyan on 2020/3/10
 */
@Data
@TableName("admin")
public class AdminEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 管理员ID
	 */
	@TableId(type = IdType.INPUT)
	private Integer adminId;
	/**
	 * 管理员名
	 */
	private String adminName;
	/**
	 * 密码
	 */
	private String adminPsw;
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
