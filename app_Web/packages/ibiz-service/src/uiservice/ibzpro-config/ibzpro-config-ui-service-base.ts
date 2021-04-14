import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzproConfigService } from '../../service/ibzpro-config/ibzpro-config.service';
import IbzproConfigAuthService from '../../authservice/ibzpro-config/ibzpro-config-auth-service';

/**
 * 系统配置表UI服务对象基类
 *
 * @export
 * @class IbzproConfigUIServiceBase
 */
export class IbzproConfigUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzproConfigUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzproConfig.json";

    /**
     * Creates an instance of  IbzproConfigUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzproConfigUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzproConfigUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IbzproConfigAuthService({context:this.context});
        this.dataService = new IbzproConfigService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzproConfigUIServiceBase
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
     * @memberof  IbzproConfigUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
    }

}