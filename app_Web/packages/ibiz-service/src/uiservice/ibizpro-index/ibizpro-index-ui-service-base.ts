import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbizproIndexService } from '../../service';
import IbizproIndexAuthService from '../../authservice/ibizpro-index/ibizpro-index-auth-service';

/**
 * 索引检索UI服务对象基类
 *
 * @export
 * @class IbizproIndexUIServiceBase
 */
export class IbizproIndexUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbizproIndexUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbizproIndex.json";

    /**
     * Creates an instance of  IbizproIndexUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproIndexUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbizproIndexUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="orgid";
        this.stateValue = 0;
        this.multiFormDEField = "indextype";
        this.indexTypeDEField = "indextype";
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IbizproIndexAuthService({context:this.context});
        this.dataService = new IbizproIndexService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbizproIndexUIServiceBase
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
     * @memberof  IbizproIndexUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('INDEXDEPICKUPVIEW:','INDEXDEPICKUPVIEW');
        this.allViewFuncMap.set('REDIRECTVIEW:','REDIRECTVIEW');
        this.allViewFuncMap.set(':','');
    }

}