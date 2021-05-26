import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzProReportlyActionService } from '../../service';
import IbzProReportlyActionAuthService from '../../authservice/ibz-pro-reportly-action/ibz-pro-reportly-action-auth-service';

/**
 * 汇报日志UI服务对象基类
 *
 * @export
 * @class IbzProReportlyActionUIServiceBase
 */
export class IbzProReportlyActionUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzProReportlyActionUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzProReportlyAction.json";

    /**
     * Creates an instance of  IbzProReportlyActionUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProReportlyActionUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzProReportlyActionUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="org";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IbzProReportlyActionAuthService({context:this.context});
        this.dataService = new IbzProReportlyActionService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzProReportlyActionUIServiceBase
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
     * @memberof  IbzProReportlyActionUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}