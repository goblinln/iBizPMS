import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ProductPlanService } from '../../service/product-plan/product-plan.service';
import ProductPlanAuthService from '../../authservice/product-plan/product-plan-auth-service';

/**
 * 产品计划UI服务对象基类
 *
 * @export
 * @class ProductPlanUIServiceBase
 */
export class ProductPlanUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ProductPlanUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/ProductPlan.json";

    /**
     * Creates an instance of  ProductPlanUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductPlanUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ProductPlanUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['statuss'];
        this.authService = new ProductPlanAuthService({context:this.context});
        this.dataService = new ProductPlanService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ProductPlanUIServiceBase
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
     * @memberof  ProductPlanUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
    }

}