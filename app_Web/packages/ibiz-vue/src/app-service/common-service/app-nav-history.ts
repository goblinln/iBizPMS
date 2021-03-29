import qs from 'qs';
import { AppServiceBase, Util } from 'ibiz-core';
import { AppContextStore } from './app-context-store';
import { UIStateService } from './ui-state-service';
import { AppEvent } from '../../events/app-event';

/**
 * 历史记录项
 *
 * @export
 * @interface HistoryItem
 */
export interface HistoryItem {
    /**
     * 路由信息
     *
     * @type {*}
     * @memberof HistoryItem
     */
    to?: any;
    /**
     * 参数信息
     *
     * @type {*}
     * @memberof HistoryItem
     */
    meta?: any;
    /**
     * 视图标识
     *
     * @type {string}
     * @memberof HistoryItem
     */
    tag?: string;
    /**
     * 上下文
     *
     * @type {*}
     * @memberof HistoryItem
     */
    context?: any;
    /**
     * 标题
     *
     * @type {string}
     * @memberof HistoryItem
     */
    caption?: string;

    /**
     * 子标题
     *
     * @type {string}
     * @memberof HistoryItem
     */
    info?: string;
}

/**
 * 应用导航记录基类
 *
 * @export
 * @class AppNavHistory
 */
export class AppNavHistory {
    /**
     * 应用事件
     *
     * @memberof AppNavHistory
     */
    public readonly appEvent = AppEvent.getInstance();

    /**
     * 应用上下文仓库
     *
     * @type {AppContextStore}
     * @memberof AppNavHistory
     */
    public readonly contextStore: AppContextStore = new AppContextStore();

    /**
     * 界面UI状态服务
     *
     * @type {UIStateService}
     * @memberof AppNavHistory
     */
    public readonly uiStateService: UIStateService = new UIStateService();

    /**
     * 国际化
     * 
     * @memberof AppNavHistory
     */
    public i18n: any = AppServiceBase.getInstance().getI18n();

    /**
     * 路由记录缓存
     *
     * @type {HistoryItem[]}
     * @memberof AppNavHistory
     */
    public readonly historyList: HistoryItem[] = [];

    /**
     * 导航缓存，忽略判断的导航参数正则
     *
     * @type {RegExp}
     * @memberof AppNavHistory
     */
    public readonly navIgnoreParameters: RegExp = new RegExp(/(srftabactivate|srftreeexpactivate)/);

    /**
     * 首页mate信息
     *
     * @type {*}
     * @memberof AppNavHistory
     */
    public indexMeta: any = null;

    /**
     * 分页切换历史记录排序值（最大值表示离当前最近的路由）
     *
     * @type {*}
     * @memberof AppNavHistory
     */
    public static sortIndex: number = 0;

    /**
     * Creates an instance of AppNavHistory.
     *
     * @memberof AppNavHistory
     */
    constructor() {
        if (this.uiStateService.layoutState.styleMode === 'STYLE2') {
            addEventListener('hashchange', ({ oldURL, newURL }) => {
                if (this.historyList.length > 0) {
                    const param = this.calcRouteParam(oldURL);
                    const param2 = this.calcRouteParam(newURL);
                    const lastHistory = this.historyList[this.historyList.length - 1];
                    if (this.isRouteSame(param, lastHistory.to)) {
                        this.pop();
                    } else if (this.isRouteSame(param2, lastHistory.to) && this.historyList.length > 1) {
                        const item = this.historyList[this.historyList.length - 2];
                        if (this.isRouteSame(param, item.to)) {
                            this.historyList.splice(this.historyList.length - 2, 1);
                        }
                    }
                }
            });
        }
    }

    /**
     * 根据url计算路由参数
     *
     * @protected
     * @param {string} url
     * @returns {*}
     * @memberof AppNavHistory
     */
    protected calcRouteParam(url: string): any {
        const hash = url.substring(url.indexOf('#') + 1);
        const queryIndex = hash.indexOf('?');
        const path = queryIndex === -1 ? hash : hash.substring(0, queryIndex);
        const queryStr = queryIndex === -1 ? '' : hash.substring(queryIndex + 1);
        return { path, query: !Util.isEmpty(queryStr) ? qs.parse(queryStr) : {} };
    }

    /**
     * 根据视图标识查找记录
     *
     * @param {string} tag
     * @returns {*}
     * @memberof AppNavHistory
     */
    public findHistoryByTag(tag: string): any {
        return this.historyList.find((item) => Util.isExistAndNotEmpty(item.tag) && item.tag === tag);
    }

    /**
     * 查找路由缓存
     *
     * @param {*} page
     * @param {any[]} [list=this.historyList]
     * @returns {number}
     * @memberof AppNavHistory
     */
    public findHistoryIndex(page: any, list: any[] = this.historyList): number {
        if (!Util.isExist(page)) {
            return -1;
        }
        return list.findIndex((item: any) => {
            return this.isRouteSame(page, item.to);
        });
    }

    /**
     * 新旧路由是否相同
     *
     * @param {*} newRoute
     * @param {*} oldRoute
     * @returns {boolean}
     * @memberof AppNavHistory
     */
    public isRouteSame(newRoute: any, oldRoute: any): boolean {
        if (newRoute && oldRoute && Object.is(newRoute.path, oldRoute.path)) {
            return this.isQuerySame(newRoute.query, oldRoute.query);
        }
        return false;
    }

    /**
     * 判断查询参数是否相同，会排除预定义的忽略参数
     *
     * @param {*} newQuery 新查询参数
     * @param {*} oldQuery 旧查询参数
     * @returns {boolean}
     * @memberof AppNavHistory
     */
    public isQuerySame(newQuery: any, oldQuery: any): boolean {
        if (Object.keys(newQuery).length !== Object.keys(oldQuery).length) {
            return false;
        }
        for (const key in newQuery) {
            // 忽略的参数略过
            if (this.navIgnoreParameters.test(`|${key}|`)) {
                continue;
            }
            if (!Util.isExist(oldQuery) || !Object.is(oldQuery[key], newQuery[key])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 添加视图缓存
     *
     * @param {*} to 当前路由信息
     * @memberof AppNavHistory
     */
    public add(to: any): void {
        if (this.findHistoryIndex(to) === -1) {
            if (this.uiStateService.layoutState.styleMode === 'DEFAULT' && to?.matched?.length === 1) {
                return;
            }
            const item: any = {
                to,
                meta: JSON.parse(JSON.stringify(to.meta)),
                tag: '',
                context: {},
                sortIndex: AppNavHistory.sortIndex++
            };
            const { caption, info } = item.meta;
            item.caption = caption;
            item.info = '';
            this.historyList.push(item);
        }
    }

    /**
     * 修改当前项排序值（记录上一分页项）
     * 
     * @param item 
     */
    public updateSortIndex(item: any) {
        if(item) {
            item.sortIndex = ++AppNavHistory.sortIndex;
        }
    }

    /**
     * 删除视图缓存
     *
     * @param {HistoryItem} item
     * @memberof AppNavHistory
     */
    public remove(item: HistoryItem): void {
        const i = this.findHistoryIndex(item.to);
        if (i !== -1) {
            this.historyList.splice(i, 1);
        }
    }

    /**
     * 重置路由缓存
     *
     * @param {number} [num=0]
     * @memberof AppNavHistory
     */
    public reset(num: number = 0): void {
        this.historyList.splice(num, this.historyList.length);
    }

    /**
     * 设置指定缓存视图标题
     *
     * @param {({ tag: string, caption: string | null, info: string | null })} { tag, caption, info }
     * @returns {boolean}
     * @memberof AppNavHistory
     */
    public setCaption({ tag, caption, info }: { tag: string; caption?: string; info?: string }): boolean {
        const item: HistoryItem = this.findHistoryByTag(tag);
        if (item) {
            const meta = item.meta;
            if (caption) {
                meta.caption = caption;
            }
            if (Util.isExistAndNotEmpty(info)) {
                meta.info = info;
                item.info = info;
            }
            this.appEvent.emit('navHistoryItemChange', item);
        }
        return true;
    }

    /**
     * 设置路由视图标识
     *
     * @param {string} tag
     * @param {*} route
     * @returns {boolean}
     * @memberof AppNavHistory
     */
    public setViewTag(tag: string, route: any): boolean {
        const i = this.findHistoryIndex(route);
        if (i === -1) {
            return false;
        }
        const item = this.historyList[i];
        if (Util.isExistAndNotEmpty(item.tag)) {
            return false;
        }
        item.tag = tag;
        return true;
    }

    /**
     * 设置路由视图上下文
     *
     * @param {*} context
     * @param {*} tag
     * @returns {boolean}
     * @memberof AppNavHistory
     */
    public setViewContext(context: any, tag: any): boolean {
        const item = this.findHistoryByTag(tag);
        if (item) {
            item.context = context;
            return true;
        }
        return false;
    }

    /**
     * 删除其他缓存
     *
     * @param {HistoryItem} item
     * @memberof AppNavHistory
     */
    public removeOther(item: HistoryItem): void {
        const i = this.findHistoryIndex(item.to);
        if (i !== -1) {
            const page = this.historyList[i];
            this.historyList.splice(0, this.historyList.length);
            this.historyList.push(page);
        }
    }

    /**
     * 缓存后退
     *
     * @memberof AppNavHistory
     */
    public pop(): void {
        this.historyList.pop();
    }
}
