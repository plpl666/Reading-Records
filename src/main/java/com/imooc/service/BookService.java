package com.imooc.service;

import com.imooc.pojo.Books;
import com.imooc.pojo.vo.BooksVO;
import com.imooc.utils.PagedResult;

import java.util.List;
import java.util.Map;

public interface BookService {

    /**
     * 判断用户所添加的图书是否存在
     * @param book 图书对象
     * @return 图书对象
     */
    Books bookIsExist(Books book);

    /**
     * 用户添加图书
     * @param book 图书对象
     */
    void addBook(Books book);

    /**
     * 获取用户图书(按添加或分享时间的前5本)
     * @param userId 用户编号
     * @return 图书列表集合
     */
    Map<String,List<Books>> getUserBooks(String userId);

    /**
     * 分页查询用户所有图书 (所有分享图书或所有未分享图书)
     * @param userId 用户编号
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return PagedResult对象
     */
    PagedResult<Books> getUserAllBooks(String userId, Integer page, Integer pageSize);

    /**
     * 修改图书信息
     * @param book 图书对象
     */
    void editBook(Books book);

    /**
     * 分页查询用户所有未分享图书
     * @param userId 用户编号
     * @param status 图书状态: 1.已分享 2.未分享
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return PagedResult对象
     */
    PagedResult<Books> getUserNotUpBooks(String userId, Integer status, Integer page, Integer pageSize);

    /**
     * 分页查询所有用户分享
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return PagedResult对象
     */
    PagedResult<BooksVO> getUserAndBook(Integer page, Integer pageSize);

    /**
     * 获取图书分享次数最多的前四条
     * @return 图书集合
     */
    List<Books> getRecommendBooks();

    /**
     * 通过描述分页查询所有用户分享
     * @param desc 描述信息
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return PagedResult对象
     */
    PagedResult<BooksVO> getUserAndBookByDesc(String desc, Integer page, Integer pageSize);

}
