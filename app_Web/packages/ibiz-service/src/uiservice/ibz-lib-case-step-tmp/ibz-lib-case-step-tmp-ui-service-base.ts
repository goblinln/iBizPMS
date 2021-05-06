import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzLibCaseStepTmpService } from '../../service';
import IbzLibCaseStepTmpAuthService from '../../authservice/ibz-lib-case-step-tmp/ibz-lib-case-step-tmp-auth-service';

/**
 * 用例库用例步骤UI服务对象基类
 *
 * @export
 * @class IbzLibCaseStepTmpUIServiceBase
 */
export class IbzLibCaseStepTmpUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzLibCaseStepTmpUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzLibCaseStepTmp.json";

    /**
     * Creates an instance of  IbzLibCaseStepTmpUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibCaseStepTmpUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzLibCaseStepTmpUIServiceBase
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
        this.authService = new IbzLibCaseStepTmpAuthService({context:this.context});
        this.dataService = new IbzLibCaseStepTmpService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzLibCaseStepTmpUIServiceBase
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
     * @memberof  IbzLibCaseStepTmpUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}