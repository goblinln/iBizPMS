import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBIZProPluginService } from '../../service/ibizpro-plugin/ibizpro-plugin.service';
import IBIZProPluginAuthService from '../../authservice/ibizpro-plugin/ibizpro-plugin-auth-service';

/**
 * 系统插件UI服务对象基类
 *
 * @export
 * @class IBIZProPluginUIServiceBase
 */
export class IBIZProPluginUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBIZProPluginUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBIZProPlugin.json";

    /**
     * Creates an instance of  IBIZProPluginUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBIZProPluginUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBIZProPluginUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IBIZProPluginAuthService({context:this.context});
        this.dataService = new IBIZProPluginService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBIZProPluginUIServiceBase
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
     * @memberof  IBIZProPluginUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
    }

}