package com.imooc.controller;

import com.imooc.pojo.*;
import com.imooc.pojo.bo.BooksBO;
import com.imooc.pojo.bo.UsersBO;
import com.imooc.service.*;
import com.imooc.utils.FileUtils;
import com.imooc.utils.IMoocJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Sid sid;

    @Resource
    private UserService userService;

    @Resource
    private BookService bookService;

    @Resource
    private ReceptService receptService;

    @Resource
    private UserFanService userFanService;

    @Resource
    private SearchRecordService searchRecordService;

    @PostMapping("/register")
    public IMoocJSONResult register(@RequestBody Users user) {
        if (!StringUtils.isNoneBlank(user.getPhoneNum(),user.getNickname(),user.getPassword())) {
            return IMoocJSONResult.errorMsg("所有字段不能为空!");
        }
        Users users = new Users();
        users.setPhoneNum(user.getPhoneNum());
        if (userService.userIsExist(users) != null){
            return IMoocJSONResult.errorMsg("该手机号已经注册!");
        }
        user.setId(sid.nextShort());
        userService.addUser(user);
        return IMoocJSONResult.ok();
    }

    @PostMapping("/login")
    public IMoocJSONResult login(@RequestBody Users user) {
        if (!StringUtils.isNoneBlank(user.getPhoneNum(),user.getPassword())) {
            return IMoocJSONResult.errorMsg("所有字段不能为空!");
        }
        Users users = userService.userIsExist(user);
        if (users == null) {
            return IMoocJSONResult.errorMsg("手机号或密码错误!");
        }
        return IMoocJSONResult.ok(users);
    }

    @PostMapping("/fansAndFollowsNum")
    public IMoocJSONResult fansNum(@RequestBody Users user) {
        if (StringUtils.isBlank(user.getId())) {
            return IMoocJSONResult.errorMsg("用户信息不能为空!");
        }
        return IMoocJSONResult.ok(userService.getFansAndFollows(user.getId()));
    }

    @PostMapping("/uploadFaceBase64")
    public IMoocJSONResult uploadFaceBase64(@RequestBody UsersBO usersBO) throws Exception {
        //获取前端传来的base64字符串,然后转为文件对象再上传
        String base64Data = usersBO.getFaceData();
        String time = String.valueOf(new Date().getTime());
//        String userFacePath = "/_book_app/" + usersBO.getId() + "/face/" + "userFace64-" + time + ".png";
//        String faceUrl = "/" + usersBO.getId() + "/face/" + "userFace64-" + time + ".png";
        String userFacePath = "D:\\_book_app\\" + usersBO.getId() + "\\face\\" + "userFace64-" + time + ".png";
        String faceUrl = "\\" + usersBO.getId() + "\\face\\" + "userFace64-" + time + ".png";
        FileUtils.base64ToFile(userFacePath,base64Data);
        //更新用户头像
        Users user = new Users();
        user.setId(usersBO.getId());
        user.setFaceUrl(faceUrl);
        userService.editUser(user);
        return IMoocJSONResult.ok(userService.getUser(usersBO.getId()));
    }

    @PostMapping("/updateNickname")
    public IMoocJSONResult updateNickname(@RequestBody Users user) {
        if (!StringUtils.isNoneBlank(user.getId(),user.getNickname())) {
            return IMoocJSONResult.errorMsg("用户信息不能为空!");
        }
        userService.editUser(user);
        return IMoocJSONResult.ok(userService.getUser(user.getId()));
    }

    @PostMapping("/updatePhoneNum")
    public IMoocJSONResult updatePhoneNum(@RequestBody Users user) {
        if (!StringUtils.isNoneBlank(user.getId(),user.getPhoneNum())) {
            return IMoocJSONResult.errorMsg("用户信息不能为空!");
        }
        Users users = new Users();
        users.setPhoneNum(user.getPhoneNum());
        if (userService.userIsExist(users) != null){
            return IMoocJSONResult.errorMsg("该手机号已经注册!");
        }
        userService.editUser(user);
        return IMoocJSONResult.ok(userService.getUser(user.getId()));
    }

    @PostMapping("/updatePassword")
    public IMoocJSONResult updatePassword(@RequestBody Users user) {
        if (!StringUtils.isNoneBlank(user.getId(),user.getPassword())) {
            return IMoocJSONResult.errorMsg("用户信息不能为空!");
        }
        userService.editUser(user);
        return IMoocJSONResult.ok(userService.getUser(user.getId()));
    }

    @PostMapping("/addBook")
    public IMoocJSONResult addBook(@RequestBody Books book) {
        if (!StringUtils.isNoneBlank(book.getName(),book.getUserId())) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        Books books = new Books();
        books.setIsbn(book.getIsbn());
        books.setUserId(book.getUserId());
        if (bookService.bookIsExist(books) != null) {
            return IMoocJSONResult.errorMsg("图书已存在请勿重复添加!");
        }
        book.setId(sid.nextShort());
        book.setSchedule(0);
        book.setStatus(0);
        book.setCreateTime(new Date());
        bookService.addBook(book);
        return IMoocJSONResult.ok();
    }

    @PostMapping("/manualAddBook")
    public IMoocJSONResult manualAddBook(@RequestBody BooksBO booksBO) throws Exception {
        if (!StringUtils.isNoneBlank(booksBO.getIsbn(),booksBO.getName(),booksBO.getAuthor(),booksBO.getPublishing(),booksBO.getImgData(),booksBO.getUserId())) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        Books book = new Books();
        book.setIsbn(booksBO.getIsbn());
        book.setUserId(booksBO.getUserId());
        if (bookService.bookIsExist(book) != null) {
            return IMoocJSONResult.errorMsg("图书已存在请勿重复添加!");
        }
        String base64Data = booksBO.getImgData();
        String time = String.valueOf(new Date().getTime());
//        String imgPath = "/_book_app/bookImg/" + time + ".png";
//        String databasePath = "http://39.105.147.182/book_app" + "/bookImg/" + time + ".png";
        String imgPath = "D:\\_book_app\\bookImg\\" + time + ".png";
        String databasePath = "http://192.168.43.195:8080" + "\\bookImg\\" + time + ".png";
        FileUtils.base64ToFile(imgPath,base64Data);
        book.setId(sid.nextShort());
        book.setName(booksBO.getName());
        book.setAuthor(booksBO.getAuthor());
        book.setPublishing(booksBO.getPublishing());
        book.setImgUrl(databasePath);
        book.setGist("");
        book.setSellTime("");
        book.setSchedule(0);
        book.setStatus(0);
        book.setCreateTime(new Date());
        bookService.addBook(book);
        return IMoocJSONResult.ok();
    }

    @PostMapping("/getUserBooks")
    public IMoocJSONResult getUserBooks(@RequestBody Users user) {
        if (StringUtils.isBlank(user.getId())) {
            return IMoocJSONResult.errorMsg("用户信息不能为空");
        }
        return IMoocJSONResult.ok(bookService.getUserBooks(user.getId()));
    }

    @PostMapping("/getUserAllBooks")
    public IMoocJSONResult getUserAllBooks(@RequestParam("userId")String userId,
                                           @RequestParam("page") Integer page) {
        if (StringUtils.isBlank(userId) || page == null) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        return IMoocJSONResult.ok(bookService.getUserAllBooks(userId,page,8));
    }

    @PostMapping("/getBookDesc")
    public IMoocJSONResult getBookDesc(@RequestBody Books book) {
        if (StringUtils.isBlank(book.getId())) {
            return IMoocJSONResult.errorMsg("图书信息不能为空");
        }
        return IMoocJSONResult.ok(bookService.bookIsExist(book));
    }

    @PostMapping("/setBookSchedule")
    public IMoocJSONResult setBookSchedule(@RequestBody Books book) {
        if (!StringUtils.isNoneBlank(book.getId(),book.getUserId()) && book.getSchedule() != null) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        bookService.editBook(book);
        return IMoocJSONResult.ok();
    }

    @PostMapping("/getUserBooksByStatus")
    public IMoocJSONResult getUserBooksByStatus(@RequestParam("userId")String userId,
                                           @RequestParam("status")Integer status,
                                           @RequestParam("page") Integer page) {
        if (StringUtils.isBlank(userId) || status == null || page == null) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        return IMoocJSONResult.ok(bookService.getUserNotUpBooks(userId,status,page,8));
    }

    @PostMapping("/upBook")
    public IMoocJSONResult upBook(@RequestBody Books book) {
        if (StringUtils.isBlank(book.getId())) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        book.setStatus(1);
        book.setUpTime(new Date());
        bookService.editBook(book);
        return IMoocJSONResult.ok();
    }

    @PostMapping("/cancelUpBook")
    public IMoocJSONResult cancelUpBook(@RequestBody Books book) {
        if (StringUtils.isBlank(book.getId())) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        book.setStatus(0);
        book.setUpTime(new Date());
        bookService.editBook(book);
        return IMoocJSONResult.ok();
    }

    @PostMapping("/getUserAndUserUpBooks")
    public IMoocJSONResult getUserAndUserUpBooks(@RequestParam("page")Integer page) {
        if (page == null) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        return IMoocJSONResult.ok(bookService.getUserAndBook(page,6));
    }

    @PostMapping("/addRecept")
    public IMoocJSONResult addRecept(@RequestBody Recepts recept) {
        if (!StringUtils.isNoneBlank(recept.getTitle(),recept.getContent(),recept.getUserId())) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        recept.setId(sid.nextShort());
        recept.setFlow(0);
        recept.setCreateTime(new Date());
        receptService.addRecept(recept);
        return IMoocJSONResult.ok();
    }

    @PostMapping("/getRecepts")
    public IMoocJSONResult getRecepts(@RequestParam("page") Integer page, @RequestParam("userId") String userId) {
        if (page == null) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        return IMoocJSONResult.ok(receptService.getRecepts(userId,page,8));
    }

    @PostMapping("/watchRecept")
    public IMoocJSONResult watchRecept(@RequestParam("receptId") String receptId, @RequestParam("flow") Integer flow) {
        if (StringUtils.isBlank(receptId) || flow == null) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        Recepts recept = new Recepts();
        recept.setId(receptId);
        recept.setFlow(++flow);
        receptService.editReceptFlow(recept);
        return IMoocJSONResult.ok();
    }

    @PostMapping("/queryUserIsFans")
    public IMoocJSONResult queryUserIsFans(@RequestParam("userId") String userId, @RequestParam("fanId") String fanId) {
        if (!StringUtils.isNoneBlank(userId,fanId)) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        UsersFans userFan = new UsersFans();
        userFan.setUserId(userId);
        userFan.setFanId(fanId);
        if (userFanService.userIsFan(userFan) != null) {
            return IMoocJSONResult.ok("已关注");
        } else {
            return IMoocJSONResult.ok("关注");
        }
    }

    @PostMapping("/followUser")
    public IMoocJSONResult followUser(@RequestParam("userId") String userId, @RequestParam("fanId") String fanId) {
        if (!StringUtils.isNoneBlank(userId,fanId)) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        UsersFans userFan = new UsersFans();
        userFan.setId(sid.nextShort());
        userFan.setUserId(userId);
        userFan.setFanId(fanId);
        userFanService.addUserFan(userFan);
        return IMoocJSONResult.ok("已关注");
    }

    @PostMapping("/cancelFollowUser")
    public IMoocJSONResult cancelFollowUser(@RequestParam("userId") String userId, @RequestParam("fanId") String fanId) {
        if (!StringUtils.isNoneBlank(userId,fanId)) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        UsersFans userFan = new UsersFans();
        userFan.setUserId(userId);
        userFan.setFanId(fanId);
        userFanService.removeUserFan(userFan);
        return IMoocJSONResult.ok("关注");
    }

    @PostMapping("/getUserMsg")
    public IMoocJSONResult getUserMsg(@RequestParam("userId") String userId) {
        if (StringUtils.isBlank(userId)) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        return IMoocJSONResult.ok(userService.getUser(userId));
    }

    @PostMapping("/getRecommendBooks")
    public IMoocJSONResult getRecommendBooks() {
        return IMoocJSONResult.ok(bookService.getRecommendBooks());
    }

    @PostMapping("/getUserFollows")
    public IMoocJSONResult getUserFollows(@RequestParam("userId") String userId, @RequestParam("page") Integer page) {
        if (StringUtils.isBlank(userId) || page == null) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        return IMoocJSONResult.ok(userService.getUserFollows(userId,page,8));
    }

    @PostMapping("/getUserFans")
    public IMoocJSONResult getUserFans(@RequestParam("userId") String userId, @RequestParam("page") Integer page) {
        if (StringUtils.isBlank(userId) || page == null) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        return IMoocJSONResult.ok(userService.getUserFans(userId,page,8));
    }

    @PostMapping("/getReceptsByDesc")
    public IMoocJSONResult getReceptsByDesc(@RequestParam("desc") String desc, @RequestParam("page") Integer page) {
        if (desc == null || page == null) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        if (StringUtils.isNoneBlank(desc) && page == 1) {
            SearchRecords searchRecord = new SearchRecords();
            searchRecord.setId(sid.nextShort());
            searchRecord.setReceptRecord(desc);
            searchRecordService.addSearchRecord(searchRecord);
        }
        return IMoocJSONResult.ok(receptService.getReceptsByDesc(desc,page,8));
    }

    @PostMapping("/getUserAndUserUpBooksByDesc")
    public IMoocJSONResult getUserAndUserUpBooksByDesc(@RequestParam("desc") String desc, @RequestParam("page") Integer page) {
        if (desc == null || page == null) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        if (StringUtils.isNoneBlank(desc) && page == 1) {
            SearchRecords searchRecord = new SearchRecords();
            searchRecord.setId(sid.nextShort());
            searchRecord.setBookRecord(desc);
            searchRecordService.addSearchRecord(searchRecord);
        }
        return IMoocJSONResult.ok(bookService.getUserAndBookByDesc(desc,page,8));
    }

    @PostMapping("/getReceptHotRecord")
    public IMoocJSONResult getReceptHotRecord() {
        return IMoocJSONResult.ok(searchRecordService.getReceptHotRecord());
    }

    @PostMapping("/getBookHotRecord")
    public IMoocJSONResult getBookHotRecord() {
        return IMoocJSONResult.ok(searchRecordService.getBookHotRecord());
    }

    @PostMapping("/editBookMsg")
    public IMoocJSONResult editBookMsg(@RequestBody Books book) {
        if (StringUtils.isBlank(book.getId())) {
            return IMoocJSONResult.errorMsg("信息不能为空!");
        }
        bookService.editBook(book);
        return IMoocJSONResult.ok();
    }


}
