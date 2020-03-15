package com.wickyan.proposal.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 交送
 * Created by wickyan on 2020/3/10
 */
@Data
@TableName("resend")
public class ResendEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 交送ID
	 */
	@TableId(type = IdType.INPUT)
	private Long resendId;
	/**
	 * 提案ID
	 */
	private Long topicId;
	/**
	 * 交送部门ID
	 */
	private Long deptId;
	/**
	 * 交送次数
	 */
	private Integer resendCount;
	/**
	 * 交送人ID
	 */
	private Long userId;
	/**
	 * 交送原因
	 */
	private String resendReason;
	/**
	 * 交送时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 退回人员
	 */
	private Long backUserId;
	/**
	 * 退回时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	/**
	 * 退回原因
	 */
	private String backReason;
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
