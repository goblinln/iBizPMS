import { EntityBase } from 'ibiz-core';
import { IIBIZProPlugin } from '../interface';

/**
 * 系统插件基类
 *
 * @export
 * @abstract
 * @class IBIZProPluginBase
 * @extends {EntityBase}
 * @implements {IIBIZProPlugin}
 */
export abstract class IBIZProPluginBase extends EntityBase implements IIBIZProPlugin {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IBIZProPluginBase
     */
    get srfdename(): string {
        return 'IBIZPRO_PLUGIN';
    }
    get srfkey() {
        return this.ibizpropluginid;
    }
    set srfkey(val: any) {
        this.ibizpropluginid = val;
    }
    get srfmajortext() {
        return this.ibizpropluginname;
    }
    set srfmajortext(val: any) {
        this.ibizpropluginname = val;
    }
    /**
     * 版本
     */
    version?: any;
    /**
     * 类型
     */
    type?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 总下载量
     */
    downloadcount?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 最新版本下载地址
     */
    downloadurl?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 标签
     */
    tag?: any;
    /**
     * 总评分
     */
    score?: any;
    /**
     * 系统插件名称
     */
    ibizpropluginname?: any;
    /**
     * 系统插件标识
     */
    ibizpropluginid?: any;
    /**
     * 关键字
     */
    keyword?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 总评论数
     */
    commentcount?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IBIZProPluginBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibizpropluginid = data.ibizpropluginid || data.srfkey;
        this.ibizpropluginname = data.ibizpropluginname || data.srfmajortext;
    }
}
