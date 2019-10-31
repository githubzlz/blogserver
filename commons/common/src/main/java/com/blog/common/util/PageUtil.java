package com.blog.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.result.PageInfo;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 10:35
 */
public class PageUtil {

    /**
     * 将用PageInfo接收到的数据传递给IPage
     * @param pageInfo 分页数据
     * @return IPage
     */
    public static IPage getIpage(PageInfo pageInfo){
        return new Page(pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    /**
     * 将IPage的信息给PageInfo返回
     * @param iPage  iPage
     * @param pageInfo pageInfo
     * @return pageInfo
     */
    public static PageInfo getPageInfo(IPage iPage, PageInfo pageInfo){

        pageInfo.setTotalSize(iPage.getTotal());
        pageInfo.setTotalPageNum(iPage.getPages());
        if(iPage.getRecords() != null){
            pageInfo.setList(iPage.getRecords());
        }

        return pageInfo;
    }

}
