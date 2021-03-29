import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbizproProductMonthlyService } from '../../service/ibizpro-product-monthly/ibizpro-product-monthly.service';
import IbizproProductMonthlyAuthService from '../../authservice/ibizpro-product-monthly/ibizpro-product-monthly-auth-service';

/**
 * 产品月报UI服务对象基类
 *
 * @export
 * @class IbizproProductMonthlyUIServiceBase
 */
export class IbizproProductMonthlyUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbizproProductMonthlyUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbizproProductMonthly.json";

    /**
     * Creates an instance of  IbizproProductMonthlyUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProductMonthlyUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbizproProductMonthlyUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['ibizproproductmonthlyid'];
        this.authService = new IbizproProductMonthlyAuthService({context:this.context});
        this.dataService = new IbizproProductMonthlyService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbizproProductMonthlyUIServiceBase
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
     * @memberof  IbizproProductMonthlyUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}