import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { PlanTempletDetailService } from '../../service';
import PlanTempletDetailAuthService from '../../authservice/plan-templet-detail/plan-templet-detail-auth-service';

/**
 * 计划模板详情UI服务对象基类
 *
 * @export
 * @class PlanTempletDetailUIServiceBase
 */
export class PlanTempletDetailUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof PlanTempletDetailUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/PlanTempletDetail.json";

    /**
     * Creates an instance of  PlanTempletDetailUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  PlanTempletDetailUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  PlanTempletDetailUIServiceBase
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
        this.authService = new PlanTempletDetailAuthService({context:this.context});
        this.dataService = new PlanTempletDetailService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  PlanTempletDetailUIServiceBase
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
     * @memberof  PlanTempletDetailUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}