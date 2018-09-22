package com.ssm.tmall.util;

/**
 * 分页工具类
 */
public class Page {
    private int start;  // 本页开始记录条数的数值
    private int count;  // 每页显示记录的条数
    private int total;  // 总的记录条数(下标需要减一)
    private String param;   // 参数

    private static final int defaultCount = 5;  // 默认每页显示五条数据

    // 无参构造，默认显示
    public Page() {
        count = defaultCount;
    }

    // 带参构造
    public Page(int start, int count) {
        this.start = start;
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        if(start>=0){
            this.start = start;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                ", param='" + param + '\'' +
                '}';
    }

    // 获取最后一页的起始记录下标
    public int getLast(){
        if(total == 0){
            return 0;
        }

        // 能被每页记录条数整除
        if(total % count == 0) {
            return total - count;
        } else {
            return total - (total % count);
        }
    }

    // 判断是否有前一页
    public boolean isHasPrevoiuse() {
        // SQL语句的limit字句限制从1-x条记录使用limit 0,x(记录条数的下标从0开始)
        return start == 0;
    }

    // 判断是是否有下一页
    public boolean isHasNext() {

        // 比较本页初始记录下标和最后一页初始记录下标
        return start != this.getLast();
    }

    // 获取总页数
    public int getPageCount() {
        int temp = total % count;
        int temp2 = total / count;

        // 不够一页算一页
        return total <= count ? 1 : temp == 0 ? temp2 : temp2 + 1;
    }
}
