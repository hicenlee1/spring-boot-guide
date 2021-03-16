package com.example.demo.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageWrap<T extends List> {
    private T data;
    private int pageSize;
    private int totalPage;
    private int recordCount;

    public PageWrap(T t, int recordCount, int pageSize) {
        this.data = t;
        this.recordCount = recordCount;
        this.pageSize = pageSize;
        this.totalPage = computeTotalPage(recordCount, pageSize);
    }

    public static int computeTotalPage(int recordCount, int pageSize) {
        if (recordCount % pageSize == 0) {
            return recordCount / pageSize;
        } else {
            return recordCount / pageSize + 1;
        }
    }

    public static <T extends List> PageWrap<T> createPageWrap(T data, int recordCount, int pageSize) {
        return new PageWrap<T>(data, recordCount, pageSize);
    }

    public static <T extends List> PageWrap<T> createPageWrap(T data, int pageSize) {
        PageInfo pageInfo = new PageInfo(data);
        return new PageWrap<T>(data, (int) pageInfo.getTotal(), pageSize);
    }
}
