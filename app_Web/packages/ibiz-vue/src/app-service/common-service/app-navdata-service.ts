export interface NavDataElement {

    /**
     * 视图标题
     *
     * @memberof NavDataElement
     */
    title: any;

    /**
     * 视图数据
     *
     * @memberof NavDataElement
     */
    data: any;

    /**
     * 视图路径
     *
     * @memberof NavDataElement
     */
    path: string;

    /**
     * 视图打开模式（路由为true，非路由为false）
     *
     * @memberof NavDataElement
     */
    viewmode: boolean;

    /**
     * 视图类型
     *
     * @memberof NavDataElement
     */
    viewType: string;

    /**
     * 视图标识
     *
     * @memberof NavDataElement
     */
    tag: string;

    /**
     * 数据标识
     *
     * @memberof NavDataElement
     */
    key: any;
}

export interface ServiceState {

    /**
     * 行为
     *
     * @memberof ServiceState
     */
    action: string;

    /**
     * 名称
     *
     * @memberof ServiceState
     */
    name: any;

    /**
     * 数据
     *
     * @memberof ServiceState
     */
    data: any;

}

import { Subject } from 'rxjs';

/**
 * 导航数据服务
 * 
 * @export
 * @class CodeListService
 */
export class NavDataService {

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {NavDataService}
     * @memberof NavDataService
     */
    private static navDataService: NavDataService;

    /**
     * 服务状态
     * 
     * @memberof NavDataService
     */
    public serviceState: Subject<ServiceState> = new Subject();

    /**
     * 导航数据栈
     * 
     * @memberof NavDataService
     */
    public navDataStack: Array<NavDataElement> = [];

    /**
     * 初始化实例
     * 
     * @memberof NavDataService
     */
    constructor(opts: any = {}) { }

    /**
     * 获取 NavDataService 单例对象
     *
     * @static
     * @returns {NavDataService}
     * @memberof NavDataService
     */
    public static getInstance(store: any): NavDataService {
        if (!NavDataService.navDataService) {
            NavDataService.navDataService = new NavDataService();
        }
        return this.navDataService;
    }

    /**
     * 添加基础导航数据到栈中
     * 
     * @memberof NavDataService
     */
    public addNavData(curNavData: NavDataElement) {
        this.navDataStack.push(curNavData);
    }

    /**
     * 设置指定数据到基础导航数据栈中
     * 
     * @memberof NavDataService
     */
    public setNavDataByTag(tag: string, isSingleMode: boolean, data: any) {
        if (this.navDataStack.length > 0) {
            let tempIndex: number = this.navDataStack.findIndex((element: NavDataElement) => {
                return Object.is(element.tag, tag);
            })
            this.navDataStack[tempIndex].data = data;
            if (isSingleMode && data.srfkey && data.srfmajortext) {
                this.navDataStack[tempIndex].key = data.srfkey;
                this.navDataStack[tempIndex].title = data.srfmajortext;
            }
            return this.navDataStack[tempIndex];
        } else {
            return null;
        }
    }

    /**
     * 获取基础导航数据
     * 
     * @memberof NavDataService
     */
    public getNavData() {
        return this.navDataStack;
    }

    /**
     * 从导航数据栈中获取指定数据的前一条数据
     * 
     * @memberof NavDataService
     */
    public getPreNavData(tag: string) {
        if (this.navDataStack.length > 0) {
            let tempIndex: number = this.navDataStack.findIndex((element: NavDataElement) => {
                return Object.is(element.tag, tag);
            })
            return this.navDataStack[tempIndex - 1];
        } else {
            return null;
        }
    }

    /**
     * 从导航数据栈中指定数据
     * 
     * @memberof NavDataService
     */
    public removeNavData(tag: string) {
        if ((this.navDataStack.length > 0) && tag) {
            let tempIndex: number = this.navDataStack.findIndex((element: NavDataElement) => {
                return Object.is(element.tag, tag);
            })
            if (tempIndex !== -1) {
                this.navDataStack = this.navDataStack.slice(0, tempIndex + 1);
            }
        }
    }

    /**
     * 从导航数据栈中删除仅剩第一条数据
     * 
     * @memberof NavDataService
     */
    public removeNavDataFrist() {
        if (this.navDataStack.length > 0) {
            this.navDataStack = this.navDataStack.slice(0, 1);
        }
    }

    /**
     * 从导航数据栈中删除最后一条数据
     * 
     * @memberof NavDataService
     */
    public removeNavDataLast() {
        if (this.navDataStack.length > 0) {
            this.navDataStack.pop();
        }
    }

    /**
     * 从导航数据栈中删除所有数据
     * 
     * @memberof NavDataService
     */
    public removeAllNavData() {
        this.navDataStack = [];
    }

}