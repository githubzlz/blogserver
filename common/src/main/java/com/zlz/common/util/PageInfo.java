package com.zlz.common.util;

import java.util.List;

/**
 * 封装了分页数据
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 10:26
 */
public class PageInfo<T> {

    /**
     * 当前页码
     */
    private Long pageNum;

    /**
     * 每页的数据数量
     */
    private Long pageSize;

    /**
     * 数据总量
     */
    private Long totalSize;

    /**
     * 按当前分页数量分页，共有的页数
     */
    private Long totalPageNum;

    /**
     * 封装查询到的信息
     */
    private List<T> list;

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public Long getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Long totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
