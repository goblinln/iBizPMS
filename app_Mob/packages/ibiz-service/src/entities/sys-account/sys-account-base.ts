import { EntityBase } from 'ibiz-core';
import { ISysAccount } from '../interface';

/**
 * 系统用户基类
 *
 * @export
 * @abstract
 * @class SysAccountBase
 * @extends {EntityBase}
 * @implements {ISysAccount}
 */
export abstract class SysAccountBase extends EntityBase implements ISysAccount {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SysAccountBase
     */
    get srfdename(): string {
        return 'SYS_USER';
    }
    get srfkey() {
        return this.userid;
    }
    set srfkey(val: any) {
        this.userid = val;
    }
    get srfmajortext() {
        return this.personname;
    }
    set srfmajortext(val: any) {
        this.personname = val;
    }
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
     * 我的过期bug数
     */
    myebugs?: any;
    /**
     * 未关闭产品数
     */
    products?: any;
    /**
     * 我的需求数
     */
    mystorys?: any;
    /**
     * 未关闭项目数
     */
    projects?: any;
    /**
     * 我的地盘
     */
    myterritorycnt?: any;
    /**
     * 原密码
     */
    originalpassword?: any;
    /**
     * 我的收藏
     */
    myfavorites?: any;
    /**
     * 我的过期任务数
     */
    myetasks?: any;
    /**
     * 新密码
     */
    newpassword?: any;
    /**
     * 过期项目数
     */
    eprojects?: any;
    /**
     * 我收藏的需求数
     */
    myfavoritestorys?: any;
    /**
     * 项目成员
     */
    projectteamcnt?: any;
    /**
     * 今日截止待办
     */
    mytodocntjz?: any;
    /**
     * 我的bugs
     */
    mybugs?: any;
    /**
     * 我的待办数
     */
    mytodocnt?: any;
    /**
     * 剩余里程碑
     */
    leftlcbcnt?: any;
    /**
     * 我收藏的bugs
     */
    myfavoritebugs?: any;
    /**
     * 重复密码
     */
    repeatpassword?: any;
    /**
     * 我的任务
     */
    mytasks?: any;
    /**
     * 我收藏的任务
     */
    myfavoritetasks?: any;
    /**
     * 剩余里程碑（今日到期）
     */
    leftlcbjzcnt?: any;
    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SysAccountBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.userid = data.userid || data.srfkey;
        this.personname = data.personname || data.srfmajortext;
    }
}
