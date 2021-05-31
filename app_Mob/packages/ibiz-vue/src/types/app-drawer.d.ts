import Vue, { VNode, CreateElement } from "vue";
import { Subject } from "rxjs";

/**
 * 抽屉实例
 *
 * @export
 * @interface AppDrawer
 */
export declare interface AppDrawer {
    /**
     * 打开抽屉
     *
     * @param {({ viewname: string, title: string, width?: number, height?: number, placement?: 'DRAWER_LEFT' | 'DRAWER_RIGHT' })} view
     * @param {*} [dynamicProps] 动态视图参数
     * @param {*} [staticProps] 静态视图参数
     * @returns {Subject<any>}
     * @memberof AppDrawer
     */
    openDrawer(view: { viewname: string, title: string, width?: number, height?: number, placement?: 'DRAWER_LEFT' | 'DRAWER_RIGHT' }, dynamicProps?: any, staticProps?: any): Subject<any>;

    /**
     * 打开上方抽屉
     *
     * @param {({ viewname: string, title: string, width?: number, height?: number, placement?: 'DRAWER_LEFT' | 'DRAWER_RIGHT' })} view
     * @param {*} [dynamicProps] 动态视图参数
     * @param {*} [staticProps] 静态视图参数
     * @returns {Subject<any>}
     * @memberof AppDrawer
     */
    openTopDrawer(view: { viewname: string, title: string, width?: number, height?: number, placement?: 'DRAWER_TOP' }, dynamicProps?: any, staticProps?: any): Subject<any>;
}