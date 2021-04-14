import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { SuiteCaseService } from '../../service/suite-case/suite-case.service';
import SuiteCaseAuthService from '../../authservice/suite-case/suite-case-auth-service';

/**
 * 套件用例UI服务对象基类
 *
 * @export
 * @class SuiteCaseUIServiceBase
 */
export class SuiteCaseUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof SuiteCaseUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/SuiteCase.json";

    /**
     * Creates an instance of  SuiteCaseUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SuiteCaseUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  SuiteCaseUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new SuiteCaseAuthService({context:this.context});
        this.dataService = new SuiteCaseService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  SuiteCaseUIServiceBase
     */
    protected async initActionMap() {
        if (this.entityModel && this.entityModel.getAllPSAppDEUIActions && this.entityModel.getAllPSAppDEUIActions.length > 0) {
            this.entityModel.getAllPSAppDEUIActions.forEach(async (element: any) => {
                const targetAction:any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            });
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  SuiteCaseUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}