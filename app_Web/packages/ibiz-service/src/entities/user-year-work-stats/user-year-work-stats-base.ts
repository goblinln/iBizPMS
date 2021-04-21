import { EntityBase } from 'ibiz-core';
import { IUserYearWorkStats } from '../interface';

/**
 * 用户年度工作内容统计基类
 *
 * @export
 * @abstract
 * @class UserYearWorkStatsBase
 * @extends {EntityBase}
 * @implements {IUserYearWorkStats}
 */
export abstract class UserYearWorkStatsBase extends EntityBase implements IUserYearWorkStats {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof UserYearWorkStatsBase
     */
    get srfdename(): string {
        return 'IBZ_USERYEARWORKSTATS';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.title;
    }
    set srfmajortext(val: any) {
        this.title = val;
    }
    /**
     * 密码
     */
    password?: any;
    /**
     * 累计参与产品数
     */
    yearproductcnt?: any;
    /**
     * 累计创建Bug数
     */
    yearbugcnt?: any;
    /**
     * 微信
     */
    weixin?: any;
    /**
     * 累计创建用例数
     */
    yearcasecnt?: any;
    /**
     * 账号
     */
    account?: any;
    /**
     * QQ
     */
    qq?: any;
    /**
     * ranzhi
     */
    ranzhi?: any;
    /**
     * nickname
     */
    nickname?: any;
    /**
     * avatar
     */
    avatar?: any;
    /**
     * 月完成任务数
     */
    monthfinishtask?: any;
    /**
     * fails
     */
    fails?: any;
    /**
     * 入职日期
     */
    join?: any;
    /**
     * 累计创建需求数
     */
    yearstorycnt?: any;
    /**
     * 累计创建计划数
     */
    yearplancnt?: any;
    /**
     * 钉钉
     */
    dingding?: any;
    /**
     * ip
     */
    ip?: any;
    /**
     * 累计动态数
     */
    yearactioncnt?: any;
    /**
     * 手机
     */
    mobile?: any;
    /**
     * whatsapp
     */
    whatsapp?: any;
    /**
     * 用户编号
     */
    id?: any;
    /**
     * 邮箱
     */
    email?: any;
    /**
     * 累计工时数
     */
    yearestimatecnt?: any;
    /**
     * clientLang
     */
    clientlang?: any;
    /**
     * 月累计工时
     */
    montestimate?: any;
    /**
     * 真实用户名
     */
    realname?: any;
    /**
     * 源代码账户
     */
    commiter?: any;
    /**
     * slack
     */
    slack?: any;
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * 最后登录
     */
    last?: any;
    /**
     * 年度
     */
    curyear?: any;
    /**
     * 判断角色
     */
    judgerole?: any;
    /**
     * skype
     */
    skype?: any;
    /**
     * birthday
     */
    birthday?: any;
    /**
     * 电话
     */
    phone?: any;
    /**
     * 累计登录次数
     */
    yearvisits?: any;
    /**
     * score
     */
    score?: any;
    /**
     * 角色
     */
    role?: any;
    /**
     * clientStatus
     */
    clientstatus?: any;
    /**
     * 部门编号
     */
    dept?: any;
    /**
     * 标题
     */
    title?: any;
    /**
     * 当前月
     */
    curmonth?: any;
    /**
     * 月解决Bug数
     */
    montresolvedbug?: any;
    /**
     * 通讯地址
     */
    address?: any;
    /**
     * scoreLevel
     */
    scorelevel?: any;
    /**
     * 累计登录次数
     */
    visits?: any;
    /**
     * 累计日志数
     */
    yearlogcnt?: any;
    /**
     * locked
     */
    locked?: any;
    /**
     * 性别
     */
    gender?: any;
    /**
     * zipcode
     */
    zipcode?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof UserYearWorkStatsBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
