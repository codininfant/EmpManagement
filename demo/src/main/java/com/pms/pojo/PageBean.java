package com.pms.pojo;

import com.github.pagehelper.Page;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {
    // 当前页码（从Page对象获取）
    private int pageNum;
    
    // 每页数量（从Page对象获取）
    private int pageSize;
    
    // 总记录数
    private long total;
    
    // 总页数（直接使用Page的pages属性）
    private int pages;
    
    // 当前页数据
    private List<T> list;

    /**
     * 专为PageHelper设计的构造方法
     * @param page PageHelper返回的Page对象
     */
    public PageBean(Page<T> page) {
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.list = page.getResult();
    }

    // 兼容旧版构造方法（可选）
    public PageBean(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    // public int getPageNum() { return pageNum; }
    // public int getPageSize() { return pageSize; }
    // public long getTotal() { return total; }
    // public int getPages() { return pages; }
    // public List<T> getList() { return list; }

    // 添加空数据静态方法（可选）
    public static <T> PageBean<T> empty() {
        return new PageBean<>(0, List.of());
    }
}
