import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { StorySpecService } from '../../service';
import StorySpecAuthService from '../../authservice/story-spec/story-spec-auth-service';

/**
 * 需求描述UI服务对象基类
 *
 * @export
 * @class StorySpecUIServiceBase
 */
export class StorySpecUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof StorySpecUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/StorySpec.json";

    /**
     * Creates an instance of  StorySpecUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  StorySpecUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  StorySpecUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="org";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new StorySpecAuthService({context:this.context});
        this.dataService = new StorySpecService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  StorySpecUIServiceBase
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
     * @memberof  StorySpecUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}