import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ProductBranchService } from '../../service';
import ProductBranchAuthService from '../../authservice/product-branch/product-branch-auth-service';

/**
 * 产品的分支和平台信息UI服务对象基类
 *
 * @export
 * @class ProductBranchUIServiceBase
 */
export class ProductBranchUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ProductBranchUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductBranch.json";

    /**
     * Creates an instance of  ProductBranchUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductBranchUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ProductBranchUIServiceBase
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
        this.authService = new ProductBranchAuthService({context:this.context});
        this.dataService = new ProductBranchService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ProductBranchUIServiceBase
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
     * @memberof  ProductBranchUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}