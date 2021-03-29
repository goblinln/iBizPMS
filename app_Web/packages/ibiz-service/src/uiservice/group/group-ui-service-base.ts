import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { GroupService } from '../../service/group/group.service';
import GroupAuthService from '../../authservice/group/group-auth-service';

/**
 * 群组UI服务对象基类
 *
 * @export
 * @class GroupUIServiceBase
 */
export class GroupUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof GroupUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/Group.json";

    /**
     * Creates an instance of  GroupUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  GroupUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  GroupUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new GroupAuthService({context:this.context});
        this.dataService = new GroupService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  GroupUIServiceBase
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
     * @memberof  GroupUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}