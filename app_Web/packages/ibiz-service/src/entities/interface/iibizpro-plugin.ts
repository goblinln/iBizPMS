import { IEntityBase } from 'ibiz-core';

/**
 * 系统插件
 *
 * @export
 * @interface IIBIZProPlugin
 * @extends {IEntityBase}
 */
export interface IIBIZProPlugin extends IEntityBase {
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
}
