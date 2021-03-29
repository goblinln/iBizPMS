import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { SubStoryService } from '../../service/sub-story/sub-story.service';
import SubStoryAuthService from '../../authservice/sub-story/sub-story-auth-service';

/**
 * 需求UI服务对象基类
 *
 * @export
 * @class SubStoryUIServiceBase
 */
export class SubStoryUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof SubStoryUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/SubStory.json";

    /**
     * Creates an instance of  SubStoryUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SubStoryUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  SubStoryUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['status','isfavorites','ischild'];
        this.authService = new SubStoryAuthService({context:this.context});
        this.dataService = new SubStoryService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  SubStoryUIServiceBase
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
     * @memberof  SubStoryUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}