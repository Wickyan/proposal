package com.wickyan.proposal.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by wickyan on 2020/4/30
 */
@Data
public class ChartTopicDto implements Serializable {
    @JSONField(name = "name")
    private String deptName;
    @JSONField(name = "value")
    int count;
}
