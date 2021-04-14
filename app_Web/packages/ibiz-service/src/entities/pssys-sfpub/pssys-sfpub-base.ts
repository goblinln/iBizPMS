import { EntityBase } from 'ibiz-core';
import { IPSSysSFPub } from '../interface';

/**
 * 后台服务架构基类
 *
 * @export
 * @abstract
 * @class PSSysSFPubBase
 * @extends {EntityBase}
 * @implements {IPSSysSFPub}
 */
export abstract class PSSysSFPubBase extends EntityBase implements IPSSysSFPub {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof PSSysSFPubBase
     */
    get srfdename(): string {
        return 'PSSYSSFPUB';
    }
    get srfkey() {
        return this.pssyssfpubid;
    }
    set srfkey(val: any) {
        this.pssyssfpubid = val;
    }
    get srfmajortext() {
        return this.pssyssfpubname;
    }
    set srfmajortext(val: any) {
        this.pssyssfpubname = val;
    }
    /**
     * 用户标记
     */
    usertag?: any;
    /**
     * 系统
     */
    pssystemid?: any;
    /**
     * 服务目录
     */
    pubfolder?: any;
    /**
     * 服务框架扩展
     */
    pssfstyleverid?: any;
    /**
     * 用户标记4
     */
    usertag4?: any;
    /**
     * 发布标记4
     */
    pubtag4?: any;
    /**
     * 服务框架
     */
    pssfstyleid?: any;
    /**
     * 删除模式
     */
    removeflag?: any;
    /**
     * 引用系统组件
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    subsyspkgflag?: 'yes' | 'no';
    /**
     * 默认后台服务
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    defaultpub?: 'yes' | 'no';
    /**
     * 文档模板样式
     */
    docpssfstylename?: any;
    /**
     * 用户标记3
     */
    usertag3?: any;
    /**
     * 服务框架参数
     */
    pssfstyleparamname?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 代码名称
     */
    codename?: any;
    /**
     * 用户标记2
     */
    usertag2?: any;
    /**
     * 后台服务架构名称
     */
    pssyssfpubname?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 发布标记
     */
    pubtag?: any;
    /**
     * 基类代码包名
     */
    baseclspkgcodename?: any;
    /**
     * 版本号
     */
    verstr?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 发布标记3
     */
    pubtag3?: any;
    /**
     * 服务框架参数
     */
    pssfstyleparamid?: any;
    /**
     * 文档模板样式
     */
    docpssfstyleid?: any;
    /**
     * 备注
     */
    memo?: any;
    /**
     * 代码包名
     */
    pkgcodename?: any;
    /**
     * 后台服务架构标识
     */
    pssyssfpubid?: any;
    /**
     * 系统
     */
    pssystemname?: any;
    /**
     * 发布内容类型
     */
    contenttype?: any;
    /**
     * 用户分类
     */
    usercat?: any;
    /**
     * 发布标记2
     */
    pubtag2?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 服务框架参数
     */
    styleparams?: any;
    /**
     * 父后台服务体系
     */
    ppssyssfpubname?: any;
    /**
     * 父后台服务体系
     */
    ppssyssfpubid?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof PSSysSFPubBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.pssyssfpubid = data.pssyssfpubid || data.srfkey;
        this.pssyssfpubname = data.pssyssfpubname || data.srfmajortext;
    }
}
