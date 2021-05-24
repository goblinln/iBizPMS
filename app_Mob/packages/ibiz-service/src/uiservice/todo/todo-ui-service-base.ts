import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { TodoService } from '../../service';
import TodoAuthService from '../../authservice/todo/todo-auth-service';

/**
 * 待办UI服务对象基类
 *
 * @export
 * @class TodoUIServiceBase
 */
export class TodoUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof TodoUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/Todo.json";

    /**
     * Creates an instance of  TodoUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TodoUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  TodoUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="org";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['status'];
        this.authService = new TodoAuthService({context:this.context});
        this.dataService = new TodoService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  TodoUIServiceBase
     */
    protected async initActionMap() {
        if (this.entityModel && this.entityModel.getAllPSAppDEUIActions() && (this.entityModel.getAllPSAppDEUIActions() as IPSAppDEUIAction[]).length > 0) {
            for(let element of (this.entityModel.getAllPSAppDEUIActions() as IPSAppDEUIAction[])){
                const targetAction:any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            }
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  TodoUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MOBMDATAVIEW:','MOBMDATAVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MOBEDITVIEW:','MOBEDITVIEW');
    }

}