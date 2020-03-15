package com.wickyan.proposal.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

/**
 * 提案
 * Created by wickyan on 2020/3/10
 */
@Data
@TableName("topic")
public class TopicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 提案ID
	 */
	@TableId(type = IdType.INPUT)
	private Long topicId;
	/**
	 * 提案人ID
	 */
	private Long userId;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 接受部门ID
	 */
	private Long deptId;
	/**
	 * 提案标题
	 */
	private String topicTitle;
	/**
	 * 提案内容
	 */
	private String topicText;
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
