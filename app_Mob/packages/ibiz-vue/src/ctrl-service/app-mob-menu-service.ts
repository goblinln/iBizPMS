
import { AppMobMenuModel } from 'ibiz-vue';
import { ControlServiceBase } from 'ibiz-core';
import { IPSAppMenu } from '@ibiz/dynamic-model-api';

/**
 * 菜单部件服务对象
 * 
 * 
 */
export class AppMobMenuService extends ControlServiceBase {

    /**
    * 菜单实例对象
    *
    * @memberof AppMobMenuService
    */
    public MenuInstance !: IPSAppMenu;

    /**
     * 部件模型
     *
     * @type {(any | null)}
     * @memberof AppMobMenuService
     */
    public model: AppMobMenuModel | null = null;

    /**
     * Creates an instance of AppMobMenuService.
     * 
     * @memberof AppMobMenuService
     */
    constructor(opts: IPSAppMenu) {
        super(opts);
        this.MenuInstance = opts;
    }

    /**
     * 初始化服务参数
     *
     * @memberof AppMobMenuService
     */
    public async initServiceParam(context: any, opts: IPSAppMenu) {
        this.model = new AppMobMenuModel(context, opts);
        await this.model.initAppMenuItems();
        await this.model.initAppFuncs()
    }

    /**
     * 获取所有菜单项
     *
     * @memberof AppMobMenuService
     */
    public getAllMenuItems() {
        return this.model?.getAllMenuItems();
    }

    /**
     * 获取所有应用功能
     *
     * @memberof AppMobMenuService
     */
    public getAllFuncs() {
        return this.model?.getAllFuncs();
    }
}