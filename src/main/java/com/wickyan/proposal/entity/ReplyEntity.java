package com.wickyan.proposal.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 回复
 * Created by wickyan on 2020/3/10
 */
@Data
@TableName("reply")
public class ReplyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 回复ID
	 */
	@TableId(type = IdType.INPUT)
	private Long replyId;
	/**
	 * 提案ID
	 */
	private Long topicId;
	/**
	 * 回复部门ID
	 */
	private Long deptId;
	/**
	 * 回复人ID
	 */
	private Long userId;
	/**
	 * 回复时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date replyTime;
	/**
	 * 回复内容
	 */
	private String replyText;
	/**
	 * 提案人打分
	 */
	private Integer replyScore;
	/**
	 * 提案人评价
	 */
	private String replyEvaluation;
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
