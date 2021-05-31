import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ProductReleaseService } from '../../service';
import ProductReleaseAuthService from '../../authservice/product-release/product-release-auth-service';

/**
 * 发布UI服务对象基类
 *
 * @export
 * @class ProductReleaseUIServiceBase
 */
export class ProductReleaseUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ProductReleaseUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductRelease.json";

    /**
     * Creates an instance of  ProductReleaseUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductReleaseUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ProductReleaseUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="org";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['status'];
        this.authService = new ProductReleaseAuthService({context:this.context});
        this.dataService = new ProductReleaseService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ProductReleaseUIServiceBase
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
     * @memberof  ProductReleaseUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MOBMDATAVIEW:','MOBMDATAVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MOBPICKUPVIEW:','MOBPICKUPVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MOBEDITVIEW:','MOBEDITVIEW');
    }

}