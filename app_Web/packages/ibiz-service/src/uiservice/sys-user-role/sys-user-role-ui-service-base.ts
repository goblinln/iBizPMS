import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { SysUserRoleService } from '../../service';
import SysUserRoleAuthService from '../../authservice/sys-user-role/sys-user-role-auth-service';

/**
 * 用户角色关系UI服务对象基类
 *
 * @export
 * @class SysUserRoleUIServiceBase
 */
export class SysUserRoleUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof SysUserRoleUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/SysUserRole.json";

    /**
     * Creates an instance of  SysUserRoleUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUserRoleUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  SysUserRoleUIServiceBase
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
        this.authService = new SysUserRoleAuthService({context:this.context});
        this.dataService = new SysUserRoleService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  SysUserRoleUIServiceBase
     */
    protected async initActionMap(): Promise<void> {
        const actions = this.entityModel?.getAllPSAppDEUIActions() as IPSAppDEUIAction[];
        if (actions && actions.length > 0) {
            for (const element of actions) {
                const targetAction: any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            }
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  SysUserRoleUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}