import { SingletonMode } from '../../decorators/singleton-mode';
import { LayoutState } from 'ibiz-core';
import { on } from '../../utils';
import { Observable, Subject } from 'rxjs';

/**
 * 应用UI状态管理服务类
 *
 * @export
 * @class UIStateService
 */
@SingletonMode()
export class UIStateService {
    /**
     * 事件组
     *
     * @private
     * @type {*}
     * @memberof OsObject
     */
    private events = new Subject();
    /**
     * 缓存标识
     *
     * @protected
     * @type {string}
     * @memberof UIStateService
     */
    protected localStoreKey: string = 'UIStateCache';

    /**
     * 全局布局状态管理
     *
     * @type {LayoutState}
     * @memberof UIStateService
     */
    public layoutState!: LayoutState;

    /**
     * Creates an instance of UIStateService.
     * @memberof UIStateService
     */
    constructor() {
        try {
            const data: any = localStorage.getItem(this.localStoreKey);
            if (data) {
                this.fillLayoutState(JSON.parse(data));
            } else {
                this.fillLayoutState({});
            }
        } catch (error) {
            this.fillLayoutState({});
        }
        on(window, 'beforeunload', () => {
            localStorage.setItem(this.localStoreKey, JSON.stringify(this.layoutState));
        });
    }

    /**
     * 填充状态
     *
     * @protected
     * @param {*} data
     * @memberof UIStateService
     */
    protected fillLayoutState(data: any): void {
        this.layoutState = {
            styleMode: 'DEFAULT',
            theme: 'dark',
            contentBottomShow: true,
            contentHorizontalSplit: 0.23,
            contentVerticalSplit: 0.65,
            leftExpActiveIndex: 0,
            bottomExpActiveIndex: 0,
            leftExpContentShow: true,
            leftNavMenuCollapse: false,
            leftNavOpenedMenus: [],
        };
        Object.assign(this.layoutState, data);
    }

    /**
     * 改变布局状态
     *
     * @param {LayoutState} state
     * @memberof UIStateService
     */
    public changeLayoutState(state: any): void {
        if (state) {
            Object.assign(this.layoutState, state);
            for (const key in state) {
                this.events.next({ key, val: state[key] });
            }
        }
    }

    /**
     * 获取某项UI状态
     *
     * @template K
     * @param {K} key
     * @returns {*}
     * @memberof UIStateService
     */
    public getState<K extends keyof LayoutState>(key: K): any {
        return this.layoutState[key];
    }

    /**
     * 左侧导航内容区显示切换
     *
     * @param {boolean} [bool]
     * @memberof UIStateService
     */
    public leftExpContentShowChange(bool?: boolean): void {
        if (bool !== undefined) {
            this.layoutState.leftExpContentShow = bool;
        } else {
            this.layoutState.leftExpContentShow = !this.layoutState.leftExpContentShow;
        }
    }

    /**
     * 改变左侧菜单收缩状态
     *
     * @param {boolean} [bool]
     * @memberof UIStateService
     */
    public leftNavMenuCollapseChange(bool?: boolean): void {
        if (bool !== undefined) {
            this.layoutState.leftNavMenuCollapse = bool;
        } else {
            this.layoutState.leftNavMenuCollapse = !this.layoutState.leftNavMenuCollapse;
        }
    }

    /**
     * 是否为Style2模式
     *
     * @returns {boolean}
     * @memberof UIStateService
     */
    public isStyle2(): boolean {
        return this.layoutState.styleMode === 'STYLE2';
    }

    /**
     * 注册事件
     *
     * @param {string} [eventName]
     * @return {*}  {Observable<any>}
     * @memberof UIStateService
     */
    on(eventName?: string): Observable<any> {
        return this.events.asObservable();
    }
}
