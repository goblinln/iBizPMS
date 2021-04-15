import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbizproProductDailyService } from '../../service/ibizpro-product-daily/ibizpro-product-daily.service';
import IbizproProductDailyAuthService from '../../authservice/ibizpro-product-daily/ibizpro-product-daily-auth-service';

/**
 * 产品日报UI服务对象基类
 *
 * @export
 * @class IbizproProductDailyUIServiceBase
 */
export class IbizproProductDailyUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbizproProductDailyUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbizproProductDaily.json";

    /**
     * Creates an instance of  IbizproProductDailyUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProductDailyUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbizproProductDailyUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['ibizproproductdailyid'];
        this.authService = new IbizproProductDailyAuthService({context:this.context});
        this.dataService = new IbizproProductDailyService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbizproProductDailyUIServiceBase
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
     * @memberof  IbizproProductDailyUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
    }

}