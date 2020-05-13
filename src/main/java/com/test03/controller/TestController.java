package com.test03.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test03.mapper.SysMenuMaper;
import com.test03.pojo.SysMenus;
import com.test03.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther wise wu
 * @Date 2020/5/11 11:48
 * @Description
 */
@Controller
public class TestController {
     @Autowired
     private SysMenuMaper sysMenuMaper;
    @RequestMapping("/test01")
    @ResponseBody
     public  List<EasyUITree> testMenu(@RequestParam
                                                   (value = "id",defaultValue = "1") Long parentId){
        List<EasyUITree> treeList=new ArrayList<>();
        System.out.println("测试开始");
       /* QueryWrapper<SysMenus> queryWrapper=new QueryWrapper<>();
        List<SysMenus> sysMenus = sysMenuMaper.selectList(queryWrapper);
        EasyUITree  easyUITree= new EasyUITree();
        easyUITree.setText(sysMenus);
        return  sysMenus;*/
         //List<SysMenus> menusList=new ArrayList<>();
         QueryWrapper<SysMenus> queryWrapper=new QueryWrapper<>();
         queryWrapper.eq("parent_id", parentId);
        List<SysMenus> menusList = sysMenuMaper.selectList(queryWrapper);
        for (SysMenus sysMenus : menusList) {
             Long id=sysMenus.getId();
             String text=sysMenus.getName();
             String state=sysMenus.getIsParent()?"closed":"open";
            EasyUITree tree =new EasyUITree(id, text, state);
              treeList.add(tree);
        }
        return treeList;
    }
    @RequestMapping("/test")
 public String doTest(){
        return "testmenu";
 }

  @RequestMapping("/dologin")
  public String login(){

         return "login";
  }
  @RequestMapping("/index")
  public String index(HttpServletRequest request, HttpServletResponse response){
      String username = request.getParameter("username");
      System.out.println("跳转首页的用户名:" +username);
      request.setAttribute("username", username);
        return "index";
  }

    @RequestMapping("{module}/{moduleUI}")
    public String doModuleUI(
            @PathVariable String moduleUI) {
        return  moduleUI;
    }

}
