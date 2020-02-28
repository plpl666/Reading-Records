package com.imooc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.BooksMapper;
import com.imooc.pojo.Books;
import com.imooc.pojo.vo.BooksVO;
import com.imooc.service.BookService;
import com.imooc.utils.PagedResult;
import com.imooc.utils.TimeAgoUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Resource
    private BooksMapper booksMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addBook(Books book) {
        booksMapper.insert(book);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Map<String,List<Books>> getUserBooks(String userId) {
        Map<String,List<Books>> bookData = new HashMap<>();
        bookData.put("allBooks",booksMapper.selectBooksByCreateTimeLimit6(userId));
        bookData.put("upBooks",booksMapper.selectBooksByUpTimeLimit6(userId));
        return bookData;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PagedResult<Books> getUserAllBooks(String userId, Integer page, Integer pageSize) {
        Books book = new Books();
        book.setUserId(userId);
        Page<Books> pages = PageHelper.startPage(page, pageSize);
        booksMapper.select(book);
        PageInfo<Books> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PagedResult<Books> getUserNotUpBooks(String userId, Integer status, Integer page, Integer pageSize) {
        Books book = new Books();
        book.setUserId(userId);
        book.setStatus(status);
        Page<Books> pages = PageHelper.startPage(page, pageSize);
        booksMapper.select(book);
        PageInfo<Books> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Books bookIsExist(Books book) {
        return booksMapper.selectOne(book);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void editBook(Books book) {
        booksMapper.updateByPrimaryKeySelective(book);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PagedResult<BooksVO> getUserAndBook(Integer page, Integer pageSize) {
        Page<BooksVO> pages = PageHelper.startPage(page, pageSize);
        List<BooksVO> booksVOList =  booksMapper.selectUserAndBook();
        for (BooksVO b : booksVOList) {
            b.setTimeAgo(TimeAgoUtils.format(b.getUpTime()));
        }
        PageInfo<BooksVO> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<Books> getRecommendBooks() {
        return booksMapper.selectRecommendBooks();
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PagedResult<BooksVO> getUserAndBookByDesc(String desc, Integer page, Integer pageSize) {
        Page<BooksVO> pages = PageHelper.startPage(page, pageSize);
        List<BooksVO> booksVOList =  booksMapper.selectUserAndBookByDesc(desc);
        for (BooksVO b : booksVOList) {
            b.setTimeAgo(TimeAgoUtils.format(b.getUpTime()));
        }
        PageInfo<BooksVO> pageList = pages.toPageInfo();
        return new PagedResult<>(pageList.getPageNum(), pageList.getPages(), pageList.getTotal(), pageList.getList());
    }
}
