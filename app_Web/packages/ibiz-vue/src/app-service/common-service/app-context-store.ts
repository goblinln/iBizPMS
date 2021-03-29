import { Util } from 'ibiz-core';
/**
 * 全局上下文仓库基类
 *
 * @export
 * @class AppContextStoreBase
 */
export class AppContextStore {
    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppContextStore
     */
    public appContext: any = {};

    /**
     * 向上下文中设置数据，根据应用实体名称
     *
     * @param {*} context 上下文
     * @param {string} appDeName 应用实体名称
     * @param {{ data?: any, items?: any }} data 需要设置的数据
     * @memberof AppContextStore
     */
    public setContextData(context: any, appDeName: string, data: { data?: any; items?: any }) {
        if (Util.isExist(context) && Util.isExistAndNotEmpty(appDeName) && Util.isExist(data)) {
            context[`srf${appDeName}`] = data;
        }
    }

    /**
     * 从上下文中根据应用实体名称获取上下文数据
     *
     * @param {*} context 上下文
     * @param {string} [appDeName] 应用实体名称
     * @returns {{ data?: any, items?: any }}
     * @memberof AppContextStore
     */
    public getContextData(context: any, appDeName: string): { data?: any; items?: any } {
        const data: any = {};
        if (Util.isExist(context) && Util.isExistAndNotEmpty(appDeName) && Util.isExist(context[`srf${appDeName}`])) {
            Object.assign(data, context[`srf${appDeName}`]);
        }
        return data;
    }
}
