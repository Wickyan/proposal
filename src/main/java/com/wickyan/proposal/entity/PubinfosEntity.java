package com.wickyan.proposal.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告表
 * Created by wickyan on 2020/3/10
 */
@Data
@TableName("pubinfos")
public class PubinfosEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 公告表ID
	 */
	@TableId(type = IdType.INPUT)
	private Long pubId;
	/**
	 * 公告标题
	 */
	private String pubTitle;
	/**
	 * 公告内容
	 */
	private String pubText;
	/**
	 * 创建人
	 */
	private Long userId;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	/**
	 * 点击数
	 */
	private Integer readCount;
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
