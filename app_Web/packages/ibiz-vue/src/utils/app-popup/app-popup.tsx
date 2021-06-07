import { DrawerController, DrawerItem, DrawerContainer } from '@ibiz/drawer-vue';
import { AppServiceBase } from 'ibiz-core';
import { createUUID, isArray, notNilEmpty } from 'qx-util';
import { Subject, Observable } from 'rxjs';

/**
 * 模态
 *
 * @export
 * @class AppPopup
 */
export class AppPopup {
    protected store: any;
    protected i18n: any;

    /**
     * 飘窗实例
     *
     * @author chitanda
     * @date 2021-06-02 13:06:56
     * @protected
     * @type {DrawerItem[]}
     */
    protected drawerList: DrawerItem[] = [];

    constructor() {
        this.initData();
    }

    protected initData(): void {
        if (!this.store) {
            const appService = AppServiceBase.getInstance();
            this.store = appService.getAppStore();
            this.i18n = appService.getI18n();
        }
    }

    protected addDrawerItem(item: DrawerItem): void {
        this.drawerList.push(item);
        DrawerController.exp.setItems(this.drawerList);
    }

    protected removeDrawerItem(id: string): void {
        const i = this.drawerList.findIndex(item => item.id === id);
        this.drawerList.splice(i, 1);
        DrawerController.exp.setItems(this.drawerList);
    }

    /**
     * 顶部抽屉模式打开视图
     *
     * @param {{ viewname: string, title: string, width?: number, height?: number }} view 视图
     * @param {*} [viewParam={}] 视图参数
     * @param {any[]} deResParameters 关系实体参数对象
     * @param {any[]} parameters 当前应用视图参数对象
     * @param {any[]} args 多项数据
     * @param {*} [data={}] 行为参数
     * @returns {Observable<any>}
     * @memberof AppTopDrawerContainer
     */
    openDrawer(
        view: { viewname: string; title: string; width?: number; height?: number; placement?: string },
        dynamicProps: any = {},
        staticProps: any = {},
    ): Observable<any> {
        this.initData();
        const subject: Subject<any> = new Subject();
        this.open(view, dynamicProps, staticProps).then((data: any) => {
            subject.next(data);
            subject.complete();
            subject.unsubscribe();
        });
        return subject.asObservable();
    }

    /**
     * 打开上飘窗
     *
     * @author chitanda
     * @date 2021-06-02 10:06:32
     * @protected
     * @param {{ viewname: string; title: string; width?: number; height?: number; placement?: string }} view
     * @param {*} [dynamicProps={}]
     * @param {*} [staticProps={}]
     * @return {*}  {Promise<any>}
     */
    protected async open(
        view: { viewname: string; title: string; width?: number; height?: number; placement?: string },
        dynamicProps: any = {},
        staticProps: any = {},
    ): Promise<any> {
        // 计算展示层级
        const zIndex = this.store.getters.getZIndex() + 100;
        this.store.commit('updateZIndex', zIndex);
        // 基本输入参数补充
        Object.assign(staticProps, { viewDefaultUsage: false, noViewCaption: true });
        const ref = await DrawerController.present({
            caption: view.title,
            overlayIndex: zIndex,
            component: DrawerContainer as any,
            componentProps: { store: this.store, i18n: this.i18n, propsData: { dynamicProps, staticProps } },
        });
        // 缓存打卡界面信息
        const self: DrawerItem = {
            id: createUUID(),
            ref,
            caption: view.title,
        };
        this.addDrawerItem(self);
        // 侦听关闭行为
        const result = await ref.onDidDismiss();
        this.removeDrawerItem(self.id);
        return { ret: notNilEmpty(result) ? 'OK' : 'NONE', datas: isArray(result) ? result : [result] };
    }
}

// 模态服务控制器实例
export const appPopup: AppPopup = new AppPopup();
