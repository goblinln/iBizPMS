import { mergeDeepLeft } from "ramda";

/**
 * 视图界面行为
 */
export class IBizViewActionModel {

    /**
     * 视图界面行为数据
     * 
     * @memberof IBizViewActionModel
     */
    private viewActionData: any;

    /**
     * 应用上下文
     * 
     * @memberof IBizViewActionModel
     */
    protected context: any = {};

    /**
     * 默认模型数据
     * 
     * @memberof IBizViewActionModel
     */
    protected defaultOption: any = {};

    /**
     * 父容器对象
     * 
     * @memberof IBizViewActionModel
     */
    private parentContainer: any;

    /**
     * 初始化 IBizViewActionModel 对象
     * 
     * @param opts 视图逻辑参数
     * @param otherOpts 额外参数
     * 
     * @memberof IBizViewActionModel
     */
    public constructor(opts: any, otherOpts: any, context?: any) {
        this.viewActionData = mergeDeepLeft(opts, this.defaultOption);
        this.parentContainer = otherOpts;
        this.context = context ? context : {};
    }

    /**
     * 数据部件codeName
     * 
     * @memberof IBizViewActionModel
     */
    get xDataControlName() {
        return this.viewActionData.xDataControlName;
    }

    /**
     * 视图界面行为name
     * 
     * @memberof IBizViewActionModel
     */
    get name() {
        return this.viewActionData.name;
    }

    /**
     * 映射界面行为
     * 
     * @memberof IBizViewActionModel
     */
    get getPSUIAction() {
        return this.viewActionData.getPSUIAction;
    }

    /**
     * 获取父容器对象
     * 
     * @memberof IBizViewActionModel
     */
    get getParentContainer() {
        return this.parentContainer;
    }
}