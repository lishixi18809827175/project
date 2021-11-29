package util;

import java.util.ArrayList;

/**
 *  封装了页面展示分页需要的所有信息
 */
public class PageInfo<T> {

    // 当前页数据集合   T: 先理解成 Object，任意类型都可以放
    private ArrayList<T> list;
    // 当前是第几页
    private Integer pageNo;
    // 一共有多少页
    private Integer totalPage;
    // 一页有多少条数据
    private Integer pageSize;

    public PageInfo() {
    }

    public PageInfo(ArrayList<T> list, Integer pageNo, Integer totalPage, Integer pageSize) {
        this.list = list;
        this.pageNo = pageNo;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "list=" + list +
                ", pageNo=" + pageNo +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
