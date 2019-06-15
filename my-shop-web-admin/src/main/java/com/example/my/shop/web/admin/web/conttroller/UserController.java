package com.example.my.shop.web.admin.web.conttroller;

import com.example.my.shop.commons.dto.BaseResult;
import com.example.my.shop.commons.dto.PageInfo;
import com.example.my.shop.domain.TbUser;
import com.example.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
  * @ProjectName:
  * @Package:        com.example.my.shop.web.admin.web.conttroller
  * @ClassName:      TbUserController
  * @Description:    用户列表
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/5/2 0:04
  * @Version:        1.0.0
  */
@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser = null;

        //id不为空，则从数据库中获取
        if(id != null){
            tbUser = tbUserService.selectByPrimaryKey(id);
        }
        else {
            tbUser = new TbUser();
        }
        return tbUser;
    }

    /**
     * @Method:        userList
     * @Description:   跳转到用户列表
     * @Param:         []
     * @return:        java.lang.String
     * @Author:        Mr.Vincent
     * @Date:          2019/5/2
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String userList(){
        return "user_list";
    }

    /**
     * @Method:        userForm
     * @Description:    跳转用户表单页
     * @Param:         []
     * @return:        java.lang.String
     * @Author:        Mr.Vincent
     * @Date:          2019/5/2
     */
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String userForm(){
        return "user_form";
    }

    /**
     * @Method:        save
     * @Description:    保存用户信息
     * @Param:         [tbUser, redirectAttributes]
     * @return:        java.lang.String
     * @Author:        Mr.Vincent
     * @Date:          2019/5/2
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = null;

        try {
            baseResult = tbUserService.save(tbUser);
        }catch (Exception e){
            e.getStackTrace();
            String errMsg = String.format("%s",e.getMessage());

            BaseResult exceptionResult = BaseResult.fail(errMsg);
            model.addAttribute("baseResult",exceptionResult);

            return "/commons/500";
        }

        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }

    }

    /**
     * @Method:        deleteMulti
     * @Description:    批量删除
     * @Param:         [ids]
     * @return:        com.example.my.shop.commons.dto.BaseResult
     * @Author:        Mr.Vincent
     * @Date:          2019/5/7
     */
    @RequestMapping(value = "deleteMulti",method = RequestMethod.POST )
    @ResponseBody
    public BaseResult deleteMulti(@RequestParam("ids") String ids){
        BaseResult baseResult = BaseResult.fail();
        if(StringUtils.isNotBlank(ids)){
            String[] idArray = StringUtils.split(ids,",");
            int count = tbUserService.deleteMulti(idArray);
            if(count>0){
                baseResult = baseResult.success(String.format("%s",String.valueOf(count)));
            }
        }
        return baseResult;
    }

    /**
     * @Method:        deleteByPrimaryKey
     * @Description:    根据id删除
     * @Param:         [id]
     * @return:        com.example.my.shop.commons.dto.BaseResult
     * @Author:        Mr.Vincent
     * @Date:          2019/5/7
     */
    @RequestMapping(value = "deleteByPrimaryKey",method = RequestMethod.POST )
    @ResponseBody
    public BaseResult deleteByPrimaryKey(@RequestParam("id") String id){
        return tbUserService.deleteByPrimaryKey(Long.parseLong(id));
    }


    /**
     * @Method:        page
     * @Description:    分页查询加模糊查询
     * @Param:         [request, tbUser]
     * @return:        com.example.my.shop.commons.dto.PageInfo<com.example.my.shop.domain.TbUser>
     * @Author:        Mr.Vincent
     * @Date:          2019/5/7
     */
    @RequestMapping(value = "page",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<TbUser> page(HttpServletRequest request,TbUser tbUser){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        String likeSearch = request.getParameter("likeSearch");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        PageInfo<TbUser> pageInfo = tbUserService.page(start, length, draw,tbUser,likeSearch);
        return pageInfo;
    }

    /**
     * @Method:        showDetail
     * @Description:    根据id查询，显示详情
     * @Param:         [id]
     * @return:        com.example.my.shop.domain.TbUser
     * @Author:        Mr.Vincent
     * @Date:          2019/5/9
     */
    @RequestMapping(value = "showDetail",method = RequestMethod.POST)
    @ResponseBody
    public TbUser showDetail(@RequestParam("id") Long id){
        return tbUserService.selectByPrimaryKey(id);
    }

}
