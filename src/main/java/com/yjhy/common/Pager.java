package com.yjhy.common;

/**
 * Created by husong on 2018/4/6.
 */
public class Pager {
    private final static Integer DEFAULT_PAGESIZE = 10;
    private Integer start;
    private Integer pageSize;
    private Integer total;
    private Integer totalSize;

    public Pager(){};

    private Pager (Integer start,Integer pageSize,Integer total){
        this.start = start;
        this.pageSize = pageSize;
        this.total = total;
    };

    public static Pager buildWithStart(Integer start,Integer pageSize,Integer total){
        if(total < 0){
            throw new IllegalArgumentException("非法的总数!");
        }
        start = start<0||start>=total?0:start;
        pageSize = pageSize == null || pageSize<=0?DEFAULT_PAGESIZE:pageSize;
        return new Pager(start,pageSize,total);
    }
    public static Pager buildWithPage(Integer currentPage,Integer pageSize,Integer total){
        if(total < 0){
            throw new IllegalArgumentException("非法的总数!");
        }
        pageSize = pageSize == null || pageSize<=0?DEFAULT_PAGESIZE:pageSize;
        int totalSize =  new Double(Math.ceil(total*1.0/pageSize)).intValue();
        currentPage = currentPage == null || currentPage<1||currentPage>totalSize?1:currentPage;
        return new Pager( pageSize*(currentPage-1),pageSize,total);
    }

    public Integer getTotalSize() {
        return Integer.valueOf(String.valueOf(Math.ceil(total*1.0/pageSize)));
    }

    public Integer getStart() {return start;}

    public void setStart(Integer start) {this.start = start;}

    public Integer getTotal() {return total;}

    public Integer getPageSize() { return pageSize;}

    public void setPageSize(Integer pageSize) {this.pageSize = pageSize;}
}
