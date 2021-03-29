import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { PSSysAppService } from '../../service/pssys-app/pssys-app.service';
import PSSysAppAuthService from '../../authservice/pssys-app/pssys-app-auth-service';

/**
 * 系统应用UI服务对象基类
 *
 * @export
 * @class PSSysAppUIServiceBase
 */
export class PSSysAppUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof PSSysAppUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/PSSysApp.json";

    /**
     * Creates an instance of  PSSysAppUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  PSSysAppUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  PSSysAppUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new PSSysAppAuthService({context:this.context});
        this.dataService = new PSSysAppService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  PSSysAppUIServiceBase
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
     * @memberof  PSSysAppUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}