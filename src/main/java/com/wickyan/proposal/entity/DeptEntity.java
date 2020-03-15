package com.wickyan.proposal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门
 * Created by wickyan on 2020/3/10
 */
@Data
@TableName("dept")
public class DeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 部门ID
	 */
	@TableId(type = IdType.INPUT)
	private Long deptId;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 主管ID
	 */
	private Long userId;
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
