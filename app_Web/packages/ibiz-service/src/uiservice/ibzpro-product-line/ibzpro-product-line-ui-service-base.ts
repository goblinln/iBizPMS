import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBZProProductLineService } from '../../service';
import IBZProProductLineAuthService from '../../authservice/ibzpro-product-line/ibzpro-product-line-auth-service';

/**
 * 产品线UI服务对象基类
 *
 * @export
 * @class IBZProProductLineUIServiceBase
 */
export class IBZProProductLineUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBZProProductLineUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBZProProductLine.json";

    /**
     * Creates an instance of  IBZProProductLineUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductLineUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBZProProductLineUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="orgid";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IBZProProductLineAuthService({context:this.context});
        this.dataService = new IBZProProductLineService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBZProProductLineUIServiceBase
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
     * @memberof  IBZProProductLineUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
    }

}