import { IEntityBase } from 'ibiz-core';

/**
 * 系统用户
 *
 * @export
 * @interface ISysUser
 * @extends {IEntityBase}
 */
export interface ISysUser extends IEntityBase {
    /**
     * 用户标识
     */
    userid?: any;
    /**
     * 用户全局名
     */
    username?: any;
    /**
     * 用户姓名
     */
    personname?: any;
    /**
     * 用户工号
     */
    usercode?: any;
    /**
     * 密码
     */
    password?: any;
    /**
     * 登录名
     */
    loginname?: any;
    /**
     * 区属
     */
    domains?: any;
    /**
     * 主部门
     */
    mdeptid?: any;
    /**
     * 主部门代码
     */
    mdeptcode?: any;
    /**
     * 主部门名称
     */
    mdeptname?: any;
    /**
     * 业务编码
     */
    bcode?: any;
    /**
     * 岗位标识
     */
    postid?: any;
    /**
     * 岗位代码
     */
    postcode?: any;
    /**
     * 岗位名称
     */
    postname?: any;
    /**
     * 单位
     */
    orgid?: any;
    /**
     * 单位代码
     */
    orgcode?: any;
    /**
     * 单位名称
     */
    orgname?: any;
    /**
     * 昵称别名
     */
    nickname?: any;
    /**
     * 性别
     */
    sex?: any;
    /**
     * 出生日期
     */
    birthday?: any;
    /**
     * 证件号码
     */
    certcode?: any;
    /**
     * 联系方式
     */
    phone?: any;
    /**
     * 邮件
     */
    email?: any;
    /**
     * 社交账号
     */
    avatar?: any;
    /**
     * 地址
     */
    addr?: any;
    /**
     * 照片
     */
    usericon?: any;
    /**
     * 样式
     */
    theme?: any;
    /**
     * 语言
     */
    lang?: any;
    /**
     * 字号
     */
    fontsize?: any;
    /**
     * 备注
     */
    memo?: any;
    /**
     * 保留
     */
    reserver?: any;
    /**
     * 超级管理员
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    superuser?: 'yes' | 'no';
    /**
     * 原密码
     */
    originalpassword?: any;
    /**
     * 新密码
     */
    newpassword?: any;
    /**
     * 重复密码
     */
    repeatpassword?: any;
}
