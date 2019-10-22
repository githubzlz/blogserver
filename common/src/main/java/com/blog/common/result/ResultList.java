package com.blog.common.result;

import java.util.List;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/22 8:49
 */
public class ResultList<T> {

    /**
     * 数据数量
     */
    private int size;

    /**
     * 数据集合
     */
    private List<T> list;

    public ResultList(int size, List<T> list) {
        this.size = size;
        this.list = list;
    }

    public static ResultList getList(List list){
        if(list == null){
            return new ResultList(0, null);
        }
        return new ResultList(list.size(), list);
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
