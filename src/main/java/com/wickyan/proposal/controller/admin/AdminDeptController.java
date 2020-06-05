package com.wickyan.proposal.controller.admin;

import com.wickyan.proposal.dao.DeptDao;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.DeptEntity;
import com.wickyan.proposal.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wickyan on 2020/4/4
 */
@Controller
public class AdminDeptController {

    @Autowired
    private DeptDao deptDao;
    //部门管理页面
    @GetMapping({"/admin/department"})
    public String index(Model model, HttpSession session) {
        if(null == session.getAttribute("adminEntity")) {
            return "admin/login";
        }
        model.addAttribute("adminPage", "department");
        List<DeptEntity> deptEntities = deptDao.selectList(null);
        model.addAttribute("deptEntities", deptEntities);
        return "admin/department";
    }
    //修改页面
    @GetMapping({"/admin/department-edit/{deptId}"})
    public String index2(@PathVariable("deptId") Long deptId,
                         Model model) {
        model.addAttribute("adminPage", "department");
        DeptEntity editDeptEntity = deptDao.selectById(deptId);
        System.out.println(editDeptEntity);
        model.addAttribute("editDeptEntity", editDeptEntity);

        return "admin/department-edit";
    }
    //修改
    @PostMapping({"/admin/department-edit/{deptId}"})
    public String index3(@PathVariable("deptId") Long deptId,
                         @RequestParam(value = "deptName") String deptName,
                         Model model) {
        model.addAttribute("adminPage", "department");
        DeptEntity editDeptEntity = deptDao.selectById(deptId);
        System.out.println(editDeptEntity);
        editDeptEntity.setDeptName(deptName);
        int result = deptDao.updateById(editDeptEntity);
        System.out.println(result==1 ? "部门名称更新成功": "部门名称更新失败");
        return "redirect:/admin/department";
    }
    //删除
    @GetMapping({"/admin/department-del/{deptId}"})
    public String index4(@PathVariable("deptId") Long deptId,
                         Model model) {
        model.addAttribute("adminPage", "department");
        DeptEntity editDeptEntity = deptDao.selectById(deptId);
        int result = deptDao.deleteById(editDeptEntity);
        System.out.println(result==1 ? "部门删除成功": "部门删除失败");
        return "redirect:/admin/department";
    }
    //新增
    @PostMapping({"/admin/department-new"})
    public String index4(@RequestParam(value = "deptName") String deptName,
                         Model model) {
        model.addAttribute("adminPage", "department");
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDeptName(deptName);
        int result = deptDao.insert(deptEntity);
        System.out.println(result==1 ? "部门新增成功": "部门新增失败");
        return "redirect:/admin/department";
    }
}