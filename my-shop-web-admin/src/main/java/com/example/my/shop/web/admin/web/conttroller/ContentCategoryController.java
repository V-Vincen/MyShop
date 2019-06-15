package com.example.my.shop.web.admin.web.conttroller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.my.shop.commons.dto.BaseResult;
import com.example.my.shop.domain.TbContentCategory;
import com.example.my.shop.web.admin.service.TbContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id) {
        TbContentCategory tbContentCategory = null;

        //id不为空，则从数据库中获取
        if (id != null) {
            tbContentCategory = tbContentCategoryService.selectByPrimaryKey(id);
        } else {
            tbContentCategory = new TbContentCategory();
        }
        return tbContentCategory;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String contentCategoryList(Model model) {
        List<TbContentCategory> targetList = new ArrayList<>();
        List<TbContentCategory> sourceList = tbContentCategoryService.selectAll();
        sortList(sourceList, targetList, 0L);
        model.addAttribute("tbContentCategories", targetList);

        //return "content_category_list-treeTable"; //jquery-treeTable-1.4.2的写法（国人大神编写的插件）
        return "content_category_list_treetable";   //jQuery-treetable Plugin 3.2.0的写法（gtihub上的插件）
    }

    /**
     * 排序
     *
     * @param sourceList 数据源集合
     * @param targetList 排序后的集合
     * @param parentId   当前的父级类目 ID
     */
    private void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList, Long parentId) {
        for (TbContentCategory tbContentCategory : sourceList) {
            if (tbContentCategory.getParentId().equals(parentId)) {
                targetList.add(tbContentCategory);

                //判断有没有子节点，如果有则继续追加
                if (tbContentCategory.getIsParent()) {
                    for (TbContentCategory contentCategory : sourceList) {
                        if (contentCategory.getParentId().equals(tbContentCategory.getId())) {
                            sortList(sourceList, targetList, tbContentCategory.getId());
                            break;
                        }
                    }
                }

            }
        }
    }

    /**
     * @Method: contentCategoryForm
     * @Description: 跳转内容分类列表
     * @Param: []
     * @return: java.lang.String
     * @Author: Mr.Vincent
     * @Date: 2019/5/2
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String contentCategoryForm() {
        return "content_category_form_zTree";  //JQuery zTree core v3.5.40 (github上第二火的的树形图插件，国人大神写的)
        //return "content_category_form_jstree";   //jsTree - v3.3.8 (github上最火的树形图插件)
    }

    /**
     * @Method: contentCategoryFormEdit
     * @Description: 跳转内容分类列表, 下的修改form表单
     * @Param: []
     * @return: java.lang.String
     * @Author: Mr.Vincent
     * @Date: 2019/5/2
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String contentCategoryFormEdit() {
        return "content_category_form_edit";
    }

    /**
     * @Method: contentCategoryBySubCategories
     * @Description: 跳转内容分类列表的，添加下级类目表单
     * @Param: []
     * @return: java.lang.String
     * @Author: Mr.Vincent
     * @Date: 2019/5/2
     */
    @RequestMapping(value = "subCategories", method = RequestMethod.GET)
    public String contentCategoryBySubCategories(TbContentCategory tbContentCategory) {
        return "content_category_form_subCategories";
    }

    //======================= JQuery zTree core v3.5.40 (github上第二火的的树形图插件，国人大神写的) --> Begin ============================================================================================================================================================//
    @RequestMapping(value = "ztree/data", method = RequestMethod.POST)
    @ResponseBody
    public List<TbContentCategory> zTreeData(String id) {
        if (id == null) {
            id = "0";
        }
        List<TbContentCategory> tbContentCategories = tbContentCategoryService.selectByParentId(Long.parseLong(id));
        return tbContentCategories;
    }
//========================================= JQuery zTree core v3.5.40 --> End ============================================================================================================================================================//


//====================================== jsTree - v3.3.8 (github上最火的树形图插件)============================================================================================================================================================//
    //================================= 第一种写法：(这个写法还有问题，有待改进) --> Begin ==============================================================//

    /**
     * @Method: jstreeDataId
     * @Description: jsTree - v3.3.8 延迟加载ajax的方法，请求参数格式与github上的文档例子一样，但是页面不显示树状图有待查找问题
     * @Param: [id]
     * @return: java.lang.String
     * @Author: Mr.Vincent
     * @Date: 2019/5/11
     */
    @ResponseBody
    @RequestMapping(value = "jstree/data/id", method = RequestMethod.GET)
    public String jstreeDataId(String id) {
        if (id.equals("#") || StringUtils.isBlank(id)) {
            id = "0";
        }

        //JSON数据格式转换
        JSONArray arrayFa = new JSONArray();
        List<TbContentCategory> tbContentCategories = tbContentCategoryService.selectByParentId(Long.parseLong(id));
        for (TbContentCategory tbContentCategory : tbContentCategories) {
            JSONObject jsonObjectFa = new JSONObject();

            jsonObjectFa.put("id", tbContentCategory.getId());
            jsonObjectFa.put("text", tbContentCategory.getName());

            Long parentId = tbContentCategory.getId();
            List<TbContentCategory> tbContentCategoriesSon = tbContentCategoryService.selectByParentId(Long.parseLong(String.valueOf(parentId)));

            JSONArray arraySon = new JSONArray();
            for (TbContentCategory son : tbContentCategoriesSon) {
                JSONObject jsonObjectSon = new JSONObject();

                jsonObjectSon.put("id", son.getId());
                jsonObjectSon.put("text", "Child node");
                jsonObjectSon.put("children", "true");
                arraySon.add(jsonObjectSon);
            }
            jsonObjectFa.put("children", arraySon);
            arrayFa.add(jsonObjectFa);
        }
        //把从数据库查出来的list集合拼装成下面的JSON格式
        //[{
        //  "id":1,"text":"Root node","children":[
        //    {"id":2,"text":"Child node 1"},
        //    {"id":3,"text":"Child node 2"}
        //  ]
        //}]
        return arrayFa.toString();
    }
    //================================= 第一种写法：(这个写法还有问题，有待改进) --> End ==============================================================//

    //================================= 第二种写法：(这个写法可用) --> Begin ==============================================================//

    /**
     * @Method: jstreeData
     * @Description: jsTree - v3.3.8 ajax加载采用回调函数的方法，但是是把全部记录通过排序好后一起返回的
     * @Param: [id]
     * @return: java.util.List<com.example.my.shop.domain.TbContentCategory>
     * @Author: Mr.Vincent
     * @Date: 2019/5/11
     */
    @ResponseBody
    @RequestMapping(value = "jstree/data", method = RequestMethod.GET)
    public List<TbContentCategory> jstreeData(String id) {
        List<TbContentCategory> targetList = new ArrayList<>();
        List<TbContentCategory> sourceList = tbContentCategoryService.selectAll();
        sortList(sourceList, targetList, 0L);
        return targetList;
    }
    //================================= 第二种写法：(这个写法可用) --> End ==============================================================//
//====================================== jsTree - v3.3.8 (github上最火的树形图插件)============================================================================================================================================================//


    /**
     * @Method: save
     * @Description: 保存内容类表
     * @Param: [tbContent, model, redirectAttributes]
     * @return: java.lang.String
     * @Author: Mr.Vincent
     * @Date: 2019/5/15
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbContentCategoryService.save(tbContentCategory);

        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/category/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "content_category_form_zTree";
        }
    }


    /**
     * @Method: deleteByPrimaryKey
     * @Description: 根据id删除数据
     * @Param: [id]
     * @return: com.example.my.shop.commons.dto.BaseResult
     * @Author: Mr.Vincent
     * @Date: 2019/6/15
     */
    @RequestMapping(value = "deleteByPrimaryKey", method = RequestMethod.GET)
    public String deleteByPrimaryKey(@RequestParam("id") String id, Model model,RedirectAttributes redirectAttributes) {
        BaseResult baseResult = null;
        try {
            baseResult = tbContentCategoryService.deleteByPrimaryKey(Long.parseLong(id));
        } catch (Exception e) {
            e.getStackTrace();
            String errMsg = e.getMessage();
            BaseResult exceptionResult = BaseResult.fail(errMsg);
            model.addAttribute("baseResult", exceptionResult);

            return "/commons/500";
        }
        //删除成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/category/list";
        }
        //删除失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "/content/category/list";
        }
    }

}