import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { AuthServiceRegister } from '../../register';
import { GlobalService } from '../../service';

/**
 * 项目汇报用户任务UI服务对象基类
 *
 * @export
 * @class IbzproProjectUserTaskUIServiceBase
 */
export class IbzproProjectUserTaskUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzproProjectUserTaskUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzproProjectUserTask.json";

    /**
     * Creates an instance of  IbzproProjectUserTaskUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzproProjectUserTaskUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 加载应用实体模型数据
     *
     * @memberof  IbzproProjectUserTaskUIServiceBase
     */
     protected async loaded() {
        await super.loaded();
        this.authService = await AuthServiceRegister.getInstance().getService(this.context,`${this.entityModel?.codeName.toLowerCase()}`);
        this.dataService = await new GlobalService().getService(`${this.entityModel?.codeName}`);
        await this.authService.loaded();
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzproProjectUserTaskUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField =null;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzproProjectUserTaskUIServiceBase
     */
    protected async initActionMap(): Promise<void> {
        const actions = this.entityModel?.getAllPSAppDEUIActions() as IPSAppDEUIAction[];
        if (actions && actions.length > 0) {
            for (const element of actions) {
                const targetAction: any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            }
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  IbzproProjectUserTaskUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set(':','');
    }

}