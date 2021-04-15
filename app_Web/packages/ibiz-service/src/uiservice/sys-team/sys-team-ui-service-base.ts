import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { SysTeamService } from '../../service';
import SysTeamAuthService from '../../authservice/sys-team/sys-team-auth-service';

/**
 * 组UI服务对象基类
 *
 * @export
 * @class SysTeamUIServiceBase
 */
export class SysTeamUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof SysTeamUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/SysTeam.json";

    /**
     * Creates an instance of  SysTeamUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SysTeamUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  SysTeamUIServiceBase
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
        this.authService = new SysTeamAuthService({context:this.context});
        this.dataService = new SysTeamService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  SysTeamUIServiceBase
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
     * @memberof  SysTeamUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('PICKUPVIEW:','PICKUPVIEW');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
    }

}