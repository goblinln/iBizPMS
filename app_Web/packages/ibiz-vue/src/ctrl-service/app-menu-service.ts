import { IPSAppMenu } from '@ibiz/dynamic-model-api';
import { ControlServiceBase } from 'ibiz-core';
import { AppMenuModel } from 'ibiz-vue';

/**
 * 菜单部件服务对象
 * 
 * 
 */
export class AppMenuService extends ControlServiceBase {

    /**
    * 菜单实例对象
    *
    * @memberof AppMenuService
    */
   public MenuInstance !: IPSAppMenu;

    /**
     * Creates an instance of AppMenuService.
     * 
     * @memberof AppMenuService
     */
    constructor(opts: IPSAppMenu, context?: any) {
        super(opts, context);
        this.MenuInstance = opts;
    }

    /**
     * 初始化服务参数
     *
     * @memberof AppMenuService
     */
    public async initServiceParam(context:any,opts: IPSAppMenu) {
        this.model = new AppMenuModel(context,opts);
        await this.model.initAppMenuItems();
        await this.model.initAppFuncs();
    }

    /**
     * 获取所有菜单项
     *
     * @memberof AppMenuService
     */
    public getAllMenuItems(){
        return this.model.getAllMenuItems();
    }

    /**
     * 获取所有应用功能
     *
     * @memberof AppMenuService
     */
    public getAllFuncs(){
        return this.model.getAllFuncs();
    }

}