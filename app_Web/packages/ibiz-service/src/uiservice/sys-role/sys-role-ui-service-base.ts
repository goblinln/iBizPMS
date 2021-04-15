import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { SysRoleService } from '../../service';
import SysRoleAuthService from '../../authservice/sys-role/sys-role-auth-service';

/**
 * 系统角色UI服务对象基类
 *
 * @export
 * @class SysRoleUIServiceBase
 */
export class SysRoleUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof SysRoleUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/SysRole.json";

    /**
     * Creates an instance of  SysRoleUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SysRoleUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  SysRoleUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField =null;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new SysRoleAuthService({context:this.context});
        this.dataService = new SysRoleService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  SysRoleUIServiceBase
     */
    protected async initActionMap() {
        if (this.entityModel && this.entityModel.getAllPSAppDEUIActions && this.entityModel.getAllPSAppDEUIActions.length > 0) {
            this.entityModel.getAllPSAppDEUIActions.forEach(async (element: any) => {
                const targetAction:any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            });
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  SysRoleUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}