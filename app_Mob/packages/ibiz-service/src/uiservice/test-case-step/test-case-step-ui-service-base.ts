import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { TestCaseStepService } from '../../service';
import TestCaseStepAuthService from '../../authservice/test-case-step/test-case-step-auth-service';

/**
 * 用例步骤UI服务对象基类
 *
 * @export
 * @class TestCaseStepUIServiceBase
 */
export class TestCaseStepUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof TestCaseStepUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestCaseStep.json";

    /**
     * Creates an instance of  TestCaseStepUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseStepUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  TestCaseStepUIServiceBase
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
        this.authService = new TestCaseStepAuthService({context:this.context});
        this.dataService = new TestCaseStepService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  TestCaseStepUIServiceBase
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
     * @memberof  TestCaseStepUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}