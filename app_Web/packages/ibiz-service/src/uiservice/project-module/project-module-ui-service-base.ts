import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ProjectModuleService } from '../../service/project-module/project-module.service';
import ProjectModuleAuthService from '../../authservice/project-module/project-module-auth-service';

/**
 * 任务模块UI服务对象基类
 *
 * @export
 * @class ProjectModuleUIServiceBase
 */
export class ProjectModuleUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ProjectModuleUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/ProjectModule.json";

    /**
     * Creates an instance of  ProjectModuleUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectModuleUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ProjectModuleUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new ProjectModuleAuthService({context:this.context});
        this.dataService = new ProjectModuleService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ProjectModuleUIServiceBase
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
     * @memberof  ProjectModuleUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set(':','');
    }

}