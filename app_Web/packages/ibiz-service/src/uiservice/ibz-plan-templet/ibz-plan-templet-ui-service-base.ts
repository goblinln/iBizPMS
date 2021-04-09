import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzPlanTempletService } from '../../service/ibz-plan-templet/ibz-plan-templet.service';
import IbzPlanTempletAuthService from '../../authservice/ibz-plan-templet/ibz-plan-templet-auth-service';

/**
 * 计划模板UI服务对象基类
 *
 * @export
 * @class IbzPlanTempletUIServiceBase
 */
export class IbzPlanTempletUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzPlanTempletUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzPlanTemplet.json";

    /**
     * Creates an instance of  IbzPlanTempletUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzPlanTempletUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzPlanTempletUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IbzPlanTempletAuthService({context:this.context});
        this.dataService = new IbzPlanTempletService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzPlanTempletUIServiceBase
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
     * @memberof  IbzPlanTempletUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
    }

}