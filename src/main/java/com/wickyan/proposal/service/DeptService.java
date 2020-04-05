package com.wickyan.proposal.service;

import com.wickyan.proposal.dao.DeptDao;
import com.wickyan.proposal.entity.DeptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wickyan on 2020/4/6
 */


@Service("DeptService")
public class DeptService {
    @Autowired
    private DeptDao deptDao;

    public Map<Long, String> getMapOfDept() {
        List<DeptEntity> deptEntities = deptDao.selectList(null);
        Map<Long, String> dept = new HashMap<>();
        for (DeptEntity deptEntity : deptEntities) {
            dept.put(deptEntity.getDeptId(), deptEntity.getDeptName());
        }
        return dept;
    }
}
