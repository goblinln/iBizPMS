import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBZProProductService } from '../../service/ibzpro-product/ibzpro-product.service';
import IBZProProductAuthService from '../../authservice/ibzpro-product/ibzpro-product-auth-service';

/**
 * 平台产品UI服务对象基类
 *
 * @export
 * @class IBZProProductUIServiceBase
 */
export class IBZProProductUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBZProProductUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBZProProduct.json";

    /**
     * Creates an instance of  IBZProProductUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBZProProductUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IBZProProductAuthService({context:this.context});
        this.dataService = new IBZProProductService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBZProProductUIServiceBase
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
     * @memberof  IBZProProductUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
    }

}