import { EntityBase } from 'ibiz-core';
import { IIbzMyTerritory } from '../interface';

/**
 * 我的地盘基类
 *
 * @export
 * @abstract
 * @class IbzMyTerritoryBase
 * @extends {EntityBase}
 * @implements {IIbzMyTerritory}
 */
export abstract class IbzMyTerritoryBase extends EntityBase implements IIbzMyTerritory {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbzMyTerritoryBase
     */
    get srfdename(): string {
        return 'IBZ_MYTERRITORY';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.realname;
    }
    set srfmajortext(val: any) {
        this.realname = val;
    }
    /**
     * 我的过期bug数
     */
    myebugs?: any;
    /**
     * nickname
     */
    nickname?: any;
    /**
     * fails
     */
    fails?: any;
    /**
     * 访问次数
     */
    visits?: any;
    /**
     * 电话
     */
    phone?: any;
    /**
     * 我的待办数
     */
    mytodocnt?: any;
    /**
     * 我的地盘
     */
    myterritorycnt?: any;
    /**
     * 我的bugs
     */
    mybugs?: any;
    /**
     * 职位
     */
    role?: any;
    /**
     * 真实姓名
     */
    realname?: any;
    /**
     * clientStatus
     */
    clientstatus?: any;
    /**
     * 最后登录
     */
    last?: any;
    /**
     * zipcode
     */
    zipcode?: any;
    /**
     * skype
     */
    skype?: any;
    /**
     * 我收藏的bugs
     */
    myfavoritebugs?: any;
    /**
     * 入职日期
     */
    join?: any;
    /**
     * score
     */
    score?: any;
    /**
     * 所属部门
     */
    dept?: any;
    /**
     * 账户
     */
    account?: any;
    /**
     * 我的收藏
     */
    myfavorites?: any;
    /**
     * 我的需求数
     */
    mystorys?: any;
    /**
     * 源代码账户
     */
    commiter?: any;
    /**
     * 手机
     */
    mobile?: any;
    /**
     * locked
     */
    locked?: any;
    /**
     * 通讯地址
     */
    address?: any;
    /**
     * 我的任务
     */
    mytasks?: any;
    /**
     * scoreLevel
     */
    scorelevel?: any;
    /**
     * 密码
     */
    password?: any;
    /**
     * ranzhi
     */
    ranzhi?: any;
    /**
     * 未关闭项目数
     */
    projects?: any;
    /**
     * slack
     */
    slack?: any;
    /**
     * 未关闭产品数
     */
    products?: any;
    /**
     * 微信
     */
    weixin?: any;
    /**
     * 我的过期任务数
     */
    myetasks?: any;
    /**
     * 过期项目数
     */
    eprojects?: any;
    /**
     * whatsapp
     */
    whatsapp?: any;
    /**
     * QQ
     */
    qq?: any;
    /**
     * 男女
     *
     * @type {('f' | 'm')} f: 女, m: 男
     */
    gender?: 'f' | 'm';
    /**
     * clientLang
     */
    clientlang?: any;
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * 我收藏的任务
     */
    myfavoritetasks?: any;
    /**
     * birthday
     */
    birthday?: any;
    /**
     * ip
     */
    ip?: any;
    /**
     * 我收藏的需求数
     */
    myfavoritestorys?: any;
    /**
     * 邮箱
     */
    email?: any;
    /**
     * 钉钉
     */
    dingding?: any;
    /**
     * avatar
     */
    avatar?: any;
    /**
     * ID
     */
    id?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbzMyTerritoryBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.realname = data.realname || data.srfmajortext;
    }
}
