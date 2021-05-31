import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { TestService } from '../../service';
import TestAuthService from '../../authservice/test/test-auth-service';

/**
 * 产品UI服务对象基类
 *
 * @export
 * @class TestUIServiceBase
 */
export class TestUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof TestUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/Test.json";

    /**
     * Creates an instance of  TestUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TestUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  TestUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="orgid";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['status','istop'];
        this.authService = new TestAuthService({context:this.context});
        this.dataService = new TestService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  TestUIServiceBase
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
     * @memberof  TestUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}