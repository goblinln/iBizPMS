import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzPlanTempletDetailService } from '../../service/ibz-plan-templet-detail/ibz-plan-templet-detail.service';
import IbzPlanTempletDetailAuthService from '../../authservice/ibz-plan-templet-detail/ibz-plan-templet-detail-auth-service';

/**
 * 计划模板详情UI服务对象基类
 *
 * @export
 * @class IbzPlanTempletDetailUIServiceBase
 */
export class IbzPlanTempletDetailUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzPlanTempletDetailUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzPlanTempletDetail.json";

    /**
     * Creates an instance of  IbzPlanTempletDetailUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzPlanTempletDetailUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzPlanTempletDetailUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IbzPlanTempletDetailAuthService({context:this.context});
        this.dataService = new IbzPlanTempletDetailService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzPlanTempletDetailUIServiceBase
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
     * @memberof  IbzPlanTempletDetailUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}