import { IBizControlBaseModel } from '../ibiz-control-base-model';
import { AppServiceBase, DynamicService } from '../../../service';
import { IBizAppFuncModel } from '../../logic/ibiz-app-func-model';

/**
 * 菜单模型
 *
 * @export
 * @class IBizAppMenuModel
 */
export class IBizAppMenuModel extends IBizControlBaseModel{
    
    /**
     * 主菜单对象集合
     * 
     * @private
     * @type {Map<string,any>}
     * @memberof IBizAppMenuModel
     */
    private $menu: Map<string,any> = new Map();

    /**
     * 应用功能集合
     * 
     * @private
     * @type {Map<string,IBizAppFuncModel>}
     * @memberof IBizAppMenuModel
     */
    private $appFunc: Map<string,IBizAppFuncModel> = new Map();
    
    /**
     * 应用代码名称
     * 
     * @memberof IBizAppMenuModel
     */
    private $appCodeName!: string;

    /**
     * 显示处理提示
     * 
     * @memberof IBizAppMenuModel
     */
    get showBusyIndicator(){
        return this.controlModelData.showBusyIndicator;
    }

    /**
     * 菜单
     * 
     * @memberof IBizAppMenuModel
     */
    get getPSAppMenuItems(){
        return this.controlModelData.getPSAppMenuItems;
    }

    /**
     * 是否自定义
     * 
     * @memberof IBizAppMenuModel
     */
    get enableCustomize(){
        return this.controlModelData.enableCustomize;
    }

    /**
     * 加载模型数据
     *
     * @memberof IBizAppMenuModel
     */
    public async loaded() {
        await super.loaded();
        let appModelData: any = AppServiceBase.getInstance().getAppModelDataObject();
        if(!appModelData){
            return;
        }
        this.$appCodeName = appModelData.codeName;
        if (appModelData.getAllPSAppFuncs?.length>0) {
            for(const funcItem of appModelData.getAllPSAppFuncs) {
                this.$appFunc.set(funcItem.codeName,this.initAppFunc(funcItem));
            }
        }
        this.initAllMenuItem(this.getPSAppMenuItems);
    }

    /**
     * 初始化菜单
     * 
     * @memberof IBizAppMenuModel
     */
    private initAllMenuItem(menus: any[]){
        for (const menuItem of menus) {
            this.$menu.set(menuItem.name,this.initMenu(menuItem));
        }
    }

    /**
     * 初始化菜单
     *
     * @protected
     * @param {*} menu 菜单项
     * @memberof IBizAppMenuModel
     */
    private initMenu(menu: any){
        Object.assign(menu, {authtag: this.$appCodeName+'-'+this.codeName+'-'+menu.name} );
        if (menu.getPSAppMenuItems?.length > 0 ) {
            menu.getPSAppMenuItems.forEach((menuItem: any) => {
                this.initMenu(menuItem);
            })
        }
        return menu;
    }

    /**
     * 初始化应用功能
     *
     * @protected
     * @param {*} appFunc
     * @memberof IBizAppMenuModel
     */
    private initAppFunc(appFunc: any){
        return new IBizAppFuncModel(appFunc);
    }

    /**
     * 获取指定菜单项
     * 
     * @returns
     * @memberof IBizAppMenuModel
     */
    public getMenuItem(name: string){
        return this.$menu.get(name);
    }

    /**
     * 获取指定应用功能
     * 
     * @returns
     * @memberof IBizAppMenuModel
     */
    public getAppFunc(id: string){
        return this.$appFunc.get(id);
    }

    /**
     * 获取所有菜单项
     *
     * @returns
     * @memberof IBizAppMenuModel
     */
    public getAllMenuItem(){
        let tempMenuItems: Array<any> = [];
        this.$menu.forEach((value: any, key: string) => {
            tempMenuItems.push(value);
        })
        return tempMenuItems;
    }

    /**
     * 获取所有应用功能
     * 
     * @returns
     * @memberof IBizAppMenuModel 
     */
    public getAllAppFunc(){
        let tempAppFunc: Array<IBizAppFuncModel> = [];
        this.$appFunc.forEach((value: IBizAppFuncModel, key: string) => {
            tempAppFunc.push(value);
        })
        return tempAppFunc;
    }
}