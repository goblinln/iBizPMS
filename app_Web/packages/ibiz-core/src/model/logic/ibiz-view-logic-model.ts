import { mergeDeepLeft } from "ramda";
import { IBizAppUILogicModel } from "./ibiz-app-ui-logic-model";
import { IBizViewActionModel } from "./ibiz-view-action-model";

/**
 * 视图逻辑
 */
export class IBizViewLogicModel {

    /**
     * 视图逻辑数据
     * 
     * @memberof IBizViewLogicModel
     */
    private viewLogicData: any;

    /**
     * 应用上下文
     * 
     * @memberof IBizViewLogicModel
     */
    protected context: any = {};

    /**
     * 默认模型数据
     * 
     * @memberof IBizViewLogicModel
     */
    protected defaultOption: any = {};

    /**
     * 父容器对象
     * 
     * @memberof IBizViewLogicModel
     */
    private parentContainer: any;

    /**
     * 初始化 IBizViewLogicModel 对象
     * 
     * @param opts 视图逻辑参数
     * @param otherOpts 额外参数
     * 
     * @memberof IBizViewLogicModel
     */
    public constructor(opts: any, otherOpts: any, context?: any) {
        this.viewLogicData = mergeDeepLeft(opts, this.defaultOption);
        this.parentContainer = otherOpts;
        this.context = context ? context : {};
    }

    /**
     * 获取事件参数
     * 
     * @memberof IBizViewLogicModel
     */
    get eventArg() {
        return this.viewLogicData.eventArg;
    }

    /**
     * 获取事件名称
     * 
     * @memberof IBizViewLogicModel
     */
    get eventNames() {
        return this.viewLogicData.eventNames;
    }

    /**
     * 获取触发事件部件名称
     * 
     * @memberof IBizViewLogicModel
     */
    get getPSViewCtrlName() {
        return this.viewLogicData.getPSViewCtrlName;
    }

    /**
     * 逻辑触发
     * 
     * @memberof IBizViewLogicModel
     */
    get logicTrigger() {
        return this.viewLogicData.logicTrigger;
    }

    /**
     * 逻辑类型
     * 
     * @memberof IBizViewLogicModel
     */
    get logicType() {
        return this.viewLogicData.logicType;
    }

    /**
     * 逻辑名称
     * 
     * @memberof IBizViewLogicModel
     */
    get name() {
        return this.viewLogicData.name;
    }

    /**
     * 视图界面行为
     * 
     * @memberof IBizViewLogicModel
     */
    get getPSAppViewUIAction() {
        if (Object.is(this.logicType, "APPVIEWUIACTION")) {
            let tempViewAction = this.viewLogicData.getPSAppViewUIAction;
            let targetParam: any = this.parentContainer.getPSAppViewUIActions.find((item: any) => {
                return item.name === tempViewAction.id;
            })
            return targetParam;
        } else {
            return null;
        }
    }

    /**
     * 应用界面预置逻辑
     * 
     * @memberof IBizViewLogicModel
     */
    get getPSAppUILogic() {
        if (!Object.is(this.logicType, "APPVIEWUIACTION")) {
            return new IBizAppUILogicModel(this.viewLogicData.getPSAppUILogic, this.parentContainer);
        } else {
            return null;
        }
    }

    /**
     * 获取父容器对象
     * 
     * @memberof IBizViewLogicModel
     */
    get getParentContainer() {
        return this.parentContainer;
    }

}