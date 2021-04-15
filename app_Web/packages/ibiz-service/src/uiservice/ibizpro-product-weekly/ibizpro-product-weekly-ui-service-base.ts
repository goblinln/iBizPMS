import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbizproProductWeeklyService } from '../../service';
import IbizproProductWeeklyAuthService from '../../authservice/ibizpro-product-weekly/ibizpro-product-weekly-auth-service';

/**
 * 产品周报UI服务对象基类
 *
 * @export
 * @class IbizproProductWeeklyUIServiceBase
 */
export class IbizproProductWeeklyUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbizproProductWeeklyUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbizproProductWeekly.json";

    /**
     * Creates an instance of  IbizproProductWeeklyUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProductWeeklyUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbizproProductWeeklyUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.dynaInstTag = "";
        this.tempOrgIdDEField =null;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['ibizpro_productweeklyid'];
        this.authService = new IbizproProductWeeklyAuthService({context:this.context});
        this.dataService = new IbizproProductWeeklyService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbizproProductWeeklyUIServiceBase
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
     * @memberof  IbizproProductWeeklyUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set(':','');
    }

}