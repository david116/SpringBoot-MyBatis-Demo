package com.happy.ssmdemo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/* 和页面模板如Thymeleaf直接绑定，渲染页面返回数据
 * 注意注解@Controller和@RestController的区别，前者返回text/html,后者返回如JSON格式
 */
@Controller
@RequestMapping(value = "/users", produces={"text/html;charset=UTF-8"})     // 通过这里配置使下面的映射都在/users下
public class ReturnHtmlUserController {
    @Autowired
    private UserMapper userMapper;

    /*
    *  仅为展示用，没有分页，也没有关联编辑和删除。调试遗留
    */
    @GetMapping("/")
    public String index(ModelMap map) {
        List<User> userList = userMapper.findAll();
        map.addAttribute("userList", userList);
        return "users";
    }

    @RequestMapping("/delete")
    public String deleteUser(User c) throws Exception {
        userMapper.delete(c.getId());
        return "redirect:/users/list";
    }
    @RequestMapping("/update")
    public String updateUser(User u) throws Exception {
        userMapper.update(u);
        return "redirect:/users/list";
    }

    @RequestMapping("/edit")
    public String listUser(long id, Model m) throws Exception {
        User u= userMapper.findById(id);
        m.addAttribute("u", u);
        return "edit";
    }

    @RequestMapping("/add")
    public String listCategory(User u) throws Exception {
        userMapper.insert(u.getName(),u.getAge());
        return "redirect:/users/list";
    }

    @RequestMapping("/list")
    public String listUser(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "8") int size) throws Exception {
        PageHelper.startPage(start,size,"id asc");
        List<User> cs=userMapper.findAll();
        PageInfo<User> page = new PageInfo<>(cs);
        m.addAttribute("page", page);
        return "list";
    }
    
}