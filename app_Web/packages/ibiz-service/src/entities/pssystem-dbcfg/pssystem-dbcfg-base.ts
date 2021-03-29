import { EntityBase } from 'ibiz-core';
import { IPSSystemDBCfg } from '../interface';

/**
 * 系统数据库基类
 *
 * @export
 * @abstract
 * @class PSSystemDBCfgBase
 * @extends {EntityBase}
 * @implements {IPSSystemDBCfg}
 */
export abstract class PSSystemDBCfgBase extends EntityBase implements IPSSystemDBCfg {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof PSSystemDBCfgBase
     */
    get srfdename(): string {
        return 'PSSYSTEMDBCFG';
    }
    get srfkey() {
        return this.pssystemdbcfgid;
    }
    set srfkey(val: any) {
        this.pssystemdbcfgid = val;
    }
    get srfmajortext() {
        return this.pssystemdbcfgname;
    }
    set srfmajortext(val: any) {
        this.pssystemdbcfgname = val;
    }
    /**
     * 表空间2名称
     */
    tabspace2?: any;
    /**
     * 资源状态
     */
    resstate?: any;
    /**
     * 空值排序
     */
    nullvalorder?: any;
    /**
     * 用户标记3
     */
    usertag3?: any;
    /**
     * 默认表空间名称
     */
    tabspace?: any;
    /**
     * 默认数据源
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    defaultflag?: 'yes' | 'no';
    /**
     * 备注
     */
    memo?: any;
    /**
     * 无数据库模式
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    nodbinstmode?: 'yes' | 'no';
    /**
     * 用户标记4
     */
    usertag4?: any;
    /**
     * 系统数据库名称
     */
    pssystemdbcfgname?: any;
    /**
     * 系统
     */
    pssystemname?: any;
    /**
     * 资源信息
     */
    resinfo?: any;
    /**
     * 用户标记
     */
    usertag?: any;
    /**
     * 自定义参数
     */
    userparams?: any;
    /**
     * 资源就绪时间
     */
    resreadytime?: any;
    /**
     * 发布外键
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    pubfkeyflag?: 'yes' | 'no';
    /**
     * 系统数据库标识
     */
    pssystemdbcfgid?: any;
    /**
     * 用户标记2
     */
    usertag2?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 发布模型注释
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    pubcommentflag?: 'yes' | 'no';
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 表空间3名称
     */
    tabspace3?: any;
    /**
     * 支持Web管理
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    enablewebtool?: 'yes' | 'no';
    /**
     * 发布数据库模型
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    pubdbmodelflag?: 'yes' | 'no';
    /**
     * 对象名称转换
     */
    objnamecase?: any;
    /**
     * 发布索引
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    pubindexflag?: 'yes' | 'no';
    /**
     * 附加模式名称
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    appendschema?: 'yes' | 'no';
    /**
     * 系统
     */
    pssystemid?: any;
    /**
     * 数据库模式名称
     */
    dbschemaname?: any;
    /**
     * 表空间4名称
     */
    tabspace4?: any;
    /**
     * 用户分类
     */
    usercat?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 发布视图
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    pubviewflag?: 'yes' | 'no';

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof PSSystemDBCfgBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.pssystemdbcfgid = data.pssystemdbcfgid || data.srfkey;
        this.pssystemdbcfgname = data.pssystemdbcfgname || data.srfmajortext;
    }
}
