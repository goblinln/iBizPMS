import { IEntityBase } from 'ibiz-core';

/**
 * 动态数据看板
 *
 * @export
 * @interface IDynaDashboard
 * @extends {IEntityBase}
 */
export interface IDynaDashboard extends IEntityBase {
    /**
     * 用户标识
     */
    userid?: any;
    /**
     * 动态数据看板标识
     */
    dynadashboardid?: any;
    /**
     * 动态数据看板名称
     */
    dynadashboardname?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 模型标识
     */
    modelid?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 应用标识
     */
    appid?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 模型
     */
    model?: any;
}
