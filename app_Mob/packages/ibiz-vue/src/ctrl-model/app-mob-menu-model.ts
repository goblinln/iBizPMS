import { IPSAppFunc, IPSApplication, IPSAppMenu, IPSAppMenuItem } from "@ibiz/dynamic-model-api";
import { GetModelService } from "ibiz-core";

/**
 * AppMobMenuModel 部件模型
 * 
 * @export
 * @class AppMobMenuModel
 */
export class AppMobMenuModel {

    /**
    * 菜单实例对象
    *
    * @memberof AppMobMenuModel
    */
    private MenuInstance !: IPSAppMenu;

    /**
    * 应用上下文
    *
    * @memberof AppMobMenuModel
    */
    private context: any = {};

    /**
    * 应用菜单数据
    *
    * @memberof AppMobMenuModel
    */
    private appMenus: Array<any> = [];

    /**
    * 应用功能数据
    *
    * @memberof AppMobMenuModel
    */
    private appFuncs: Array<any> = [];

    /**
    * Creates an instance of AppMobMenuModel.
    * 
    * @param {*} [opts]
    * 
    * @memberof AppMobMenuModel
    */
    constructor(context: any, opts: IPSAppMenu) {
        this.context = context;
        this.MenuInstance = opts;
    }

    /**
     * 初始化应用菜单
     *
     * @memberof AppMobMenuModel
     */
    public async initAppMenuItems() {
        let appMenuItems: Array<IPSAppMenuItem> | null = this.MenuInstance.getPSAppMenuItems();
        if (appMenuItems && (appMenuItems.length > 0)) {
            let application: IPSApplication = (await GetModelService(this.context)).getPSApplication();
            for (const menuItem of appMenuItems) {
                await this.initAppMenuItem(menuItem, application);
            }
        }
    }

    /**
     * 初始化应用菜单项
     *
     * @memberof AppMobMenuModel
     */
    public async initAppMenuItem(menuItem: IPSAppMenuItem, application: IPSApplication, sonMenuItemArray?: Array<any>) {
        let appMenuItem: any = {};
        if (!application.isFill) {
            await application.fill()
        }
        if (menuItem.M) {
            Object.assign(appMenuItem, { authtag: `${application.codeName}-${this.MenuInstance.codeName}-${menuItem.M.name}` });
            Object.assign(appMenuItem, { name: menuItem.M.name });
            Object.assign(appMenuItem, { caption: menuItem.caption });
            Object.assign(appMenuItem, { itemType: menuItem.itemType });
            Object.assign(appMenuItem, { counterid: menuItem.counterId });
            Object.assign(appMenuItem, { tooltip: menuItem.tooltip });
            Object.assign(appMenuItem, { expanded: menuItem.expanded });
            Object.assign(appMenuItem, { seperator: menuItem.seperator });
            Object.assign(appMenuItem, { hidden: menuItem.hidden });
            Object.assign(appMenuItem, { hidesidebar: menuItem.hideSideBar });
            Object.assign(appMenuItem, { opendefault: menuItem.openDefault });
            Object.assign(appMenuItem, { getPSSysImage: menuItem.getPSSysImage() });
            Object.assign(appMenuItem, { getPSSysCss: menuItem.getPSSysCss() });
            Object.assign(appMenuItem, { getPSAppFunc: menuItem.getPSAppFunc() });
            Object.assign(appMenuItem, { accessKey: menuItem.accessKey });
            Object.assign(appMenuItem, { getPSNavigateContexts: menuItem.getPSNavigateContexts() });
        }
        if (menuItem?.getPSAppMenuItems?.()) {
            let childMenus: Array<any> = [];
            Object.assign(appMenuItem, { getPSAppMenuItems: childMenus });
            for (const childMenuItem of menuItem.getPSAppMenuItems() as IPSAppMenuItem[]) {
                await this.initAppMenuItem(childMenuItem, application, childMenus);
            }
        }
        if (sonMenuItemArray) {
            sonMenuItemArray.push(appMenuItem);
        } else {
            this.appMenus.push(appMenuItem);
        }
    }

    /**
     * 初始化应用功能数据
     *
     * @memberof AppMobMenuModel
     */
    public async initAppFuncs() {
        let application: IPSApplication = (await GetModelService(this.context)).getPSApplication();
        if (application && application.getAllPSAppFuncs()) {
            for (const appFunc of application.getAllPSAppFuncs() as IPSAppFunc[]) {
                let tempAppFunc: any = {};
                Object.assign(tempAppFunc, { name: appFunc.name });
                Object.assign(tempAppFunc, { appfunctag: appFunc.codeName });
                Object.assign(tempAppFunc, { appFuncType: appFunc.appFuncType });
                Object.assign(tempAppFunc, { htmlPageUrl: appFunc.htmlPageUrl });
                Object.assign(tempAppFunc, { openMode: appFunc.openMode });
                Object.assign(tempAppFunc, { getPSAppView: appFunc.getPSAppView() });
                Object.assign(tempAppFunc, { getPSNavigateContexts: appFunc.getPSNavigateContexts() });
                Object.assign(tempAppFunc, { getPSNavigateParams: appFunc.getPSNavigateParams() });
                this.appFuncs.push(tempAppFunc);
            }
        }
    }

    /**
     * 获取所有应用功能
     *
     * @memberof AppMobMenuModel
     */
    public getAllFuncs() {
        return this.appFuncs;
    }

    /**
     * 获取所有菜单项
     *
     * @memberof AppMobMenuModel
     */
    public getAllMenuItems() {
        return this.appMenus;
    }

}