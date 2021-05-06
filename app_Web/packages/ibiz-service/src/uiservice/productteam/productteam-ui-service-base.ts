import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { PRODUCTTEAMService } from '../../service';
import PRODUCTTEAMAuthService from '../../authservice/productteam/productteam-auth-service';

/**
 * 产品团队UI服务对象基类
 *
 * @export
 * @class PRODUCTTEAMUIServiceBase
 */
export class PRODUCTTEAMUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof PRODUCTTEAMUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/PRODUCTTEAM.json";

    /**
     * Creates an instance of  PRODUCTTEAMUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  PRODUCTTEAMUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  PRODUCTTEAMUIServiceBase
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
        this.authService = new PRODUCTTEAMAuthService({context:this.context});
        this.dataService = new PRODUCTTEAMService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  PRODUCTTEAMUIServiceBase
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
     * @memberof  PRODUCTTEAMUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}