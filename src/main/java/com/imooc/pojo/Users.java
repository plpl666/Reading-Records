package com.imooc.pojo;

import javax.persistence.*;

public class Users {
    /**
     * 用户编号
     */
    @Id
    private String id;

    /**
     * 电话号码
     */
    @Column(name = "phone_num")
    private String phoneNum;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像路径
     */
    @Column(name = "face_url")
    private String faceUrl;

    /**
     * 获取用户编号
     *
     * @return id - 用户编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置用户编号
     *
     * @param id 用户编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取电话号码
     *
     * @return phone_num - 电话号码
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置电话号码
     *
     * @param phoneNum 电话号码
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取头像路径
     *
     * @return face_url - 头像路径
     */
    public String getFaceUrl() {
        return faceUrl;
    }

    /**
     * 设置头像路径
     *
     * @param faceUrl 头像路径
     */
    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }
}