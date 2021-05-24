import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBZProProductHistoryService } from '../../service';
import IBZProProductHistoryAuthService from '../../authservice/ibzpro-product-history/ibzpro-product-history-auth-service';

/**
 * 产品操作历史UI服务对象基类
 *
 * @export
 * @class IBZProProductHistoryUIServiceBase
 */
export class IBZProProductHistoryUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBZProProductHistoryUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBZProProductHistory.json";

    /**
     * Creates an instance of  IBZProProductHistoryUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductHistoryUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBZProProductHistoryUIServiceBase
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
        this.authService = new IBZProProductHistoryAuthService({context:this.context});
        this.dataService = new IBZProProductHistoryService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBZProProductHistoryUIServiceBase
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
     * @memberof  IBZProProductHistoryUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}