import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { AuthServiceRegister } from '../../register';
import { GlobalService } from '../../service';

/**
 * 需求UI服务对象基类
 *
 * @export
 * @class IBZProStoryUIServiceBase
 */
export class IBZProStoryUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBZProStoryUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBZProStory.json";

    /**
     * Creates an instance of  IBZProStoryUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProStoryUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 加载应用实体模型数据
     *
     * @memberof  IBZProStoryUIServiceBase
     */
     protected async loaded() {
        await super.loaded();
        this.authService = await AuthServiceRegister.getInstance().getService(this.context,`${this.entityModel?.codeName.toLowerCase()}`);
        this.dataService = await new GlobalService().getService(`${this.entityModel?.codeName}`);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBZProStoryUIServiceBase
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
     * @memberof  IBZProStoryUIServiceBase
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
     * @memberof  IBZProStoryUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}