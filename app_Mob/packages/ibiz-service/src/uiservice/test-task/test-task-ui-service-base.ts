import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { TestTaskService } from '../../service';
import TestTaskAuthService from '../../authservice/test-task/test-task-auth-service';

/**
 * 测试版本UI服务对象基类
 *
 * @export
 * @class TestTaskUIServiceBase
 */
export class TestTaskUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof TestTaskUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask.json";

    /**
     * Creates an instance of  TestTaskUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TestTaskUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  TestTaskUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="org";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['status'];
        this.authService = new TestTaskAuthService({context:this.context});
        this.dataService = new TestTaskService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  TestTaskUIServiceBase
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
     * @memberof  TestTaskUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MOBEDITVIEW:','MOBEDITVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MOBMDATAVIEW:','MOBMDATAVIEW');
        this.allViewFuncMap.set(':','');
    }

}