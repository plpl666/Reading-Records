package com.imooc.mapper;

import com.imooc.pojo.Books;
import com.imooc.pojo.vo.BooksVO;
import com.imooc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksMapper extends MyMapper<Books> {

    /**
     * 根据创建时间查询前5条图书
     * @param userId 用户编号
     * @return 图书列表
     */
    List<Books> selectBooksByCreateTimeLimit6(@Param("userId") String userId);

    /**
     * 根据分享时间查询前5条图书
     * @param userId 用户编号
     * @return 图书列表
     */
    List<Books> selectBooksByUpTimeLimit6(@Param("userId") String userId);

    /**
     * 查询所有用户分享
     * @return BooksVO对象
     */
    List<BooksVO> selectUserAndBook();

    /**
     * 查询图书中被分享最多的前四条
     * @return 图书集合
     */
    List<Books> selectRecommendBooks();

    /**
     * 通过描述查询所有用户分享
     * @param desc 描述信息
     * @return BooksVO对象
     */
    List<BooksVO> selectUserAndBookByDesc(@Param("desc") String desc);
}