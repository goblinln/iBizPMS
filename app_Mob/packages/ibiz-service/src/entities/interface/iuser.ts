import { IEntityBase } from 'ibiz-core';

/**
 * 用户
 *
 * @export
 * @interface IUser
 * @extends {IEntityBase}
 */
export interface IUser extends IEntityBase {
    /**
     * 密码
     */
    password?: any;
    /**
     * 通讯地址
     */
    address?: any;
    /**
     * 微信
     */
    weixin?: any;
    /**
     * 钉钉
     */
    dingding?: any;
    /**
     * fails
     */
    fails?: any;
    /**
     * slack
     */
    slack?: any;
    /**
     * ranzhi
     */
    ranzhi?: any;
    /**
     * 账户
     */
    account?: any;
    /**
     * locked
     */
    locked?: any;
    /**
     * avatar
     */
    avatar?: any;
    /**
     * scoreLevel
     */
    scorelevel?: any;
    /**
     * 真实姓名
     */
    realname?: any;
    /**
     * zipcode
     */
    zipcode?: any;
    /**
     * 所属部门
     */
    dept?: any;
    /**
     * 源代码账户
     */
    commiter?: any;
    /**
     * 职位
     */
    role?: any;
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * 最后登录
     */
    last?: any;
    /**
     * 用户编号
     */
    usersn?: any;
    /**
     * clientStatus
     *
     * @type {('online' | 'away' | 'busy' | 'offline')} online: online, away: away, busy: busy, offline: offline
     */
    clientstatus?: 'online' | 'away' | 'busy' | 'offline';
    /**
     * skype
     */
    skype?: any;
    /**
     * whatsapp
     */
    whatsapp?: any;
    /**
     * score
     */
    score?: any;
    /**
     * 性别
     *
     * @type {('f' | 'm')} f: 女, m: 男
     */
    gender?: 'f' | 'm';
    /**
     * 手机
     */
    mobile?: any;
    /**
     * clientLang
     */
    clientlang?: any;
    /**
     * 访问次数
     */
    visits?: any;
    /**
     * 入职日期
     */
    join?: any;
    /**
     * 邮箱
     */
    email?: any;
    /**
     * ip
     */
    ip?: any;
    /**
     * birthday
     */
    birthday?: any;
    /**
     * nickname
     */
    nickname?: any;
    /**
     * 电话
     */
    phone?: any;
    /**
     * ID
     */
    id?: any;
    /**
     * QQ
     */
    qq?: any;
}
