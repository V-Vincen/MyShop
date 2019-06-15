package com.example.my.shop.commons.dto;

import com.example.my.shop.commons.persistence.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
  * @ProjectName:
  * @Package:        com.example.my.shop.commons.dto
  * @ClassName:      PageInfo
  * @Description:    分页对象
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/5/6 21:55
  * @Version:        1.0.0
  */
public class PageInfo<T extends BaseEntity> implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
