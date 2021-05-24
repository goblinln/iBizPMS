import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBZProProductActionService } from '../../service';
import IBZProProductActionAuthService from '../../authservice/ibzpro-product-action/ibzpro-product-action-auth-service';

/**
 * 产品日志UI服务对象基类
 *
 * @export
 * @class IBZProProductActionUIServiceBase
 */
export class IBZProProductActionUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBZProProductActionUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBZProProductAction.json";

    /**
     * Creates an instance of  IBZProProductActionUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductActionUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBZProProductActionUIServiceBase
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
        this.authService = new IBZProProductActionAuthService({context:this.context});
        this.dataService = new IBZProProductActionService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBZProProductActionUIServiceBase
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
     * @memberof  IBZProProductActionUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}