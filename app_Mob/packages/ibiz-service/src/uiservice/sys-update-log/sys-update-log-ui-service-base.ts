import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { SysUpdateLogService } from '../../service';
import SysUpdateLogAuthService from '../../authservice/sys-update-log/sys-update-log-auth-service';

/**
 * 更新日志UI服务对象基类
 *
 * @export
 * @class SysUpdateLogUIServiceBase
 */
export class SysUpdateLogUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof SysUpdateLogUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysUpdateLog.json";

    /**
     * Creates an instance of  SysUpdateLogUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUpdateLogUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  SysUpdateLogUIServiceBase
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
        this.authService = new SysUpdateLogAuthService({context:this.context});
        this.dataService = new SysUpdateLogService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  SysUpdateLogUIServiceBase
     */
    protected async initActionMap() {
        if (this.entityModel && this.entityModel.getAllPSAppDEUIActions() && (this.entityModel.getAllPSAppDEUIActions() as IPSAppDEUIAction[]).length > 0) {
            for(let element of (this.entityModel.getAllPSAppDEUIActions() as IPSAppDEUIAction[])){
                const targetAction:any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            }
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  SysUpdateLogUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MOBEDITVIEW:','MOBEDITVIEW');
        this.allViewFuncMap.set('MOBMDATAVIEW:','MOBMDATAVIEW');
    }

}