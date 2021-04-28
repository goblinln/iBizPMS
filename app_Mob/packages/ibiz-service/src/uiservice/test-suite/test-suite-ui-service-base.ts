import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { TestSuiteService } from '../../service';
import TestSuiteAuthService from '../../authservice/test-suite/test-suite-auth-service';

/**
 * 测试套件UI服务对象基类
 *
 * @export
 * @class TestSuiteUIServiceBase
 */
export class TestSuiteUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof TestSuiteUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestSuite.json";

    /**
     * Creates an instance of  TestSuiteUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TestSuiteUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  TestSuiteUIServiceBase
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
        this.authService = new TestSuiteAuthService({context:this.context});
        this.dataService = new TestSuiteService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  TestSuiteUIServiceBase
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
     * @memberof  TestSuiteUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MOBEDITVIEW:','MOBEDITVIEW');
        this.allViewFuncMap.set('MOBMDATAVIEW:','MOBMDATAVIEW');
    }

}