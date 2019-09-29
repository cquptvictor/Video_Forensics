package com.edu.victor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties({"pageNumber","filter"})
public class Page<T> {

    // 当前页数
    private int currentPage = 1;
    // 总页数
    private int totalPage;
    // 每页显示条数
    private int pageNum = 4;
    // 总条数
    private int totalNumber;
    //数据存放
    private List<T> pageData;
    //过滤条件
    private Map<String,Object> filter;

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        int totalPage = totalNumber % pageNum != 0 ? totalNumber/pageNum+1 : totalNumber/pageNum;
        this.setTotalPage(totalPage);
        this.totalNumber = totalNumber;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
}
