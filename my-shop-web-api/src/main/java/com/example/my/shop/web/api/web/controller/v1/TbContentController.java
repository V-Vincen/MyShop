package com.example.my.shop.web.api.web.controller.v1;

import com.example.my.shop.commons.dto.BaseResult;
import com.example.my.shop.domain.TbContent;
import com.example.my.shop.web.api.service.TbContentService;
import com.example.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent = null;
        if (id == null) {
            tbContent = new TbContent();
        }
        return tbContent;
    }

    @ResponseBody
    @RequestMapping(value = "{category_id}", method = RequestMethod.GET)
    public BaseResult findContentByCategoryId(@PathVariable(value = "category_id") Long categoryId) {
        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(categoryId);
        if (tbContents != null && tbContents.size() > 0) {
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDTO tbConentDTO = new TbContentDTO();
                BeanUtils.copyProperties(tbContent,tbConentDTO);
                tbContentDTOS.add(tbConentDTO);
            }
        }
        return BaseResult.success("success",tbContentDTOS);
    }


}
