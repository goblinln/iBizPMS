import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ProductLineService } from '../../service';
import ProductLineAuthService from '../../authservice/product-line/product-line-auth-service';

/**
 * 产品线（废弃）UI服务对象基类
 *
 * @export
 * @class ProductLineUIServiceBase
 */
export class ProductLineUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ProductLineUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/ProductLine.json";

    /**
     * Creates an instance of  ProductLineUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductLineUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ProductLineUIServiceBase
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
        this.authService = new ProductLineAuthService({context:this.context});
        this.dataService = new ProductLineService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ProductLineUIServiceBase
     */
    protected async initActionMap(): Promise<void> {
        const actions = this.entityModel?.getAllPSAppDEUIActions() as IPSAppDEUIAction[];
        if (actions && actions.length > 0) {
            for (const element of actions) {
                const targetAction: any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            }
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  ProductLineUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}