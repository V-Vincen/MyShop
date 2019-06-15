package com.example.my.shop.web.admin.web.conttroller;

import com.example.my.shop.commons.dto.BaseResult;
import com.example.my.shop.domain.TbContent;
import com.example.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "content")
public class ContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent TbContent = null;

        //id不为空，则从数据库中获取
        if (id != null) {
            TbContent = tbContentService.selectByPrimaryKey(id);
        } else {
            TbContent = new TbContent();
        }
        return TbContent;
    }

    /**
     * @Method: userList
     * @Description: 跳转到用户列表
     * @Param: []
     * @return: java.lang.String
     * @Author: Mr.Vincent
     * @Date: 2019/5/2
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String contentList() {
        return "content_list";
    }

    /** 
     * @Method:        contentForm
     * @Description:    跳转内容填写表单
     * @Param:         []
     * @return:        java.lang.String
     * @Author:        Mr.Vincent
     * @Date:          2019/5/15
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String contentForm() {
        return "content_form_jstree";
    }

    /**
     * @Method: selectAll
     * @Description: 分页
     * @Param: [request]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: Mr.Vincent
     * @Date: 2019/5/15
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> selectAll(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String fuzzySearch = request.getParameter("fuzzySearch");
        Boolean flag;
        if (fuzzySearch.equals("true")) {
            flag = true;
        } else {
            flag = false;
        }
        String fuzzy = request.getParameter("fuzzy");

        String title = request.getParameter("title");
        String subTitle = request.getParameter("subTitle");
        String titleDesc = request.getParameter("titleDesc");

        String pageStr = request.getParameter("page");
        String rowStr = request.getParameter("row");

        int page = pageStr == null ? 0 : Integer.parseInt(pageStr);
        int row = rowStr == null ? 10 : Integer.parseInt(rowStr);

        map.put("flag", flag);
        map.put("fuzzy", fuzzy);
        map.put("title", title);
        map.put("subTitle", subTitle);
        map.put("titleDesc", titleDesc);

        return tbContentService.selectAll(map, page, row);
    }

    /**
     * @Method: save
     * @Description: 保存内容类表
     *
     * @Param: [tbContent, model, redirectAttributes]
     * @return: java.lang.String
     * @Author: Mr.Vincent
     * @Date: 2019/5/15
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbContentService.save(tbContent);

        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "content_form_jstree";
        }
    }

    /**
     * @Method:        deleteByPrimaryKey
     * @Description:    根据id删除数据
     * @Param:         [id]
     * @return:        com.example.my.shop.commons.dto.BaseResult
     * @Author:        Mr.Vincent
     * @Date:          2019/6/15
     */
    @RequestMapping(value = "deleteByPrimaryKey",method = RequestMethod.POST )
    @ResponseBody
    public BaseResult deleteByPrimaryKey(@RequestParam("id") String id){
        return tbContentService.deleteByPrimaryKey(Long.parseLong(id));
    }

    /**
     * @Method:        deleteMulti
     * @Description:    批量删除
     * @Param:         [ids]
     * @return:        com.example.my.shop.commons.dto.BaseResult
     * @Author:        Mr.Vincent
     * @Date:          2019/6/15
     */
    @RequestMapping(value = "deleteMulti",method = RequestMethod.POST )
    @ResponseBody
    public BaseResult deleteMulti(@RequestParam("ids") String ids){
        BaseResult baseResult = BaseResult.fail();
        if(StringUtils.isNotBlank(ids)){
            String[] idArray = StringUtils.split(ids,",");
            int count = tbContentService.deleteMulti(idArray);
            if(count>0){
                baseResult = baseResult.success(String.format("%s",String.valueOf(count)));
            }
        }
        return baseResult;
    }
}
