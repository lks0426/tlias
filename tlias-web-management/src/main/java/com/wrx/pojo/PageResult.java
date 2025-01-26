package com.wrx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果封装类
 * @param <T> 返回类型
 *           根据接口文档，Result的返回的data是个对象，里面需要返回两个参数
 *           total 返回总条数
 *           rows 返回查询的结果集合
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    // 根据接口文档
    private Long total;
    private List<T> rows;
}
