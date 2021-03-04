package com.happy.ssmdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// produces={"application/json;charset=UTF-8" , app/json 是为了获取JSON数据，UTF-8是为了中文乱码
@RestController
@RequestMapping(value = "/users", produces={"application/json;charset=UTF-8"})     // 通过这里配置使下面的映射都在/users下
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 处理"/users/"的GET请求，用来获取用户列表
     *
     * @return
     */
    @GetMapping("/")
    public List<User> getAllUserList() {
        List<User> userList = userMapper.findAll();
        System.out.println("用户列表");
        for(User user : userList) {
            System.out.println("name[" + user.getName() + "], age:[" + user.getAge() + "]");
        }

        return userList;
    }

    /**
     * 处理"/users/"的POST请求，用来创建User
     *
     * @param user
     * @return
     */
    @PostMapping("/")
    public String postUser(@RequestBody User user) {
        // @RequestBody注解用来绑定通过http请求中application/json类型上传的数据
        Map<String, Object> map = new HashMap<>();
        //System.out.println("输入参数：name[" + user.getName() + "], age:[" + user.getAge() + "]");
        //userMapper.insert(user.getName(), user.getAge()); // 也可用这个方法
        map.put("name", user.getName());
        map.put("age", user.getAge());

        userMapper.insertByMap(map);

        return "success";
    }

    /**
     * 处理"/users/qn"的GET请求，根据URL参数来获取相关Users信息
     *
     * @param ?name=xx&name=xx
     * @return
     */

    @GetMapping("/qn")
    public List<User> getUsersByNames(@RequestParam("name") List<String> nameArr) {
        System.out.println("Names:" + nameArr);
        //System.out.println("Ages" + ageArr);
        return userMapper.findByNames(nameArr);
    }

    /**
     * 处理"/users/qn"的GET请求，根据URL参数中年龄上下限来获取相关Users信息
     *
     * @param ?age1=xx&age2=xx
     * @return
     */

    @GetMapping("/qa")
    public List<User> getUsersByAges(@RequestParam("age1") Integer age1,
                                     @RequestParam("age2") Integer age2) {

        return userMapper.findByAgeScope(age1, age2);
    }

    /**
     * 处理"/users/q"的GET请求，根据URL参数（年龄和姓名列表）来获取相关Users信息
     *
     * @param ?name=xx&name=xx&age1=xx&age2==
     * @return
     */

    @GetMapping("/q")
    public List<User> getUsersByNamesAndAgeScope(@RequestParam("name") List<String> nameArr,
                                                 @RequestParam("age1") Integer age1,
                                                 @RequestParam("age2") Integer age2) {

        return userMapper.findByNamesAndAgeScope(nameArr,age1,age2);
    }


    /**
     * 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
     *
     * @param id
     * @return
     */

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        // url中的id可通过@PathVariable绑定到函数的参数中
        return userMapper.findById(id);
    }


    /**
     * 处理"/users/{name}"的GET请求，用来获取url中name值的User信息
     *
     * @param name
     * @return
     */
    /*
    @GetMapping("/{name}")
    public User getUser(@PathVariable String name) {
        // url中的id可通过@PathVariable绑定到函数的参数中
        return userMapper.findByName(name);
    }
    */

    /**
     * 处理"/users/{id}"的PUT请求，用来更新User信息
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = userMapper.findById(id);
        u.setName(user.getName());
        u.setAge(user.getAge());

        userMapper.update(u);

        return "success";
    }

    /**
     * 处理"/users/{id}"的DELETE请求，用来删除User
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userMapper.delete(id);
        return "success";
    }

}