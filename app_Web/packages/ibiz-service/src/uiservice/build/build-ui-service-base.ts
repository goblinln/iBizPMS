import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { BuildService } from '../../service/build/build.service';
import BuildAuthService from '../../authservice/build/build-auth-service';

/**
 * 版本UI服务对象基类
 *
 * @export
 * @class BuildUIServiceBase
 */
export class BuildUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof BuildUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/Build.json";

    /**
     * Creates an instance of  BuildUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  BuildUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  BuildUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new BuildAuthService({context:this.context});
        this.dataService = new BuildService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  BuildUIServiceBase
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
     * @memberof  BuildUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}