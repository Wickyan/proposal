package com.wickyan.proposal.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by wickyan on 2020/4/30
 */
@Data
public class ChartTopicDto implements Serializable {

    @JSONField(name = "value",ordinal = 1)
    int count;
    @JSONField(name = "name",ordinal = 2)
    private String deptName;
}
