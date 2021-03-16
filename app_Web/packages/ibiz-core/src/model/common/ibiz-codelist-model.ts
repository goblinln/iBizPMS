import { mergeDeepLeft } from "ramda";

/**
 * 代码表模型
 *
 * @export
 * @class IBizCodeListModel
 */
export class IBizCodeListModel {
    /**
     * 代码表模型数据
     *
     * @private
     * @type {*}
     * @memberof IBizCodeListModel
     */
    private codeListModelData: any;

    /**
     * 默认模型数据
     * 
     * @memberof IBizCodeListModel
     */
    private defaultOption: any = {};

    /**
     * 应用上下文
     *
     * @memberof IBizCodeListModel
     */
    private context: any = {};

    /**
     * 代码表项集合
     *
     * @type {any[]}
     * @memberof IBizCodeListModel
     */
    private $codeItems: any[] = []

    /**
     * Creates an instance of IBizEditorModel.
     * IBizEditorModel 实例
     * 
     * @param {*} [opts={}]
     * @memberof IBizEditorModel
     */
    constructor(opts: any = {}, context: any) {
        this.context = context ? context : {};
        this.codeListModelData = mergeDeepLeft(opts, this.defaultOption);
        this.$codeItems = this.initCodeItems(this.codeListModelData.getPSCodeItems)
    }

    /**
     * 初始化代码表
     *
     * @memberof IBizCodeListModel
     */
    public initCodeItems(codeItems: any[]) {
        let result: any[] = [];
        if (codeItems?.length > 0) {
            for (const item of codeItems) {
                let obj: any = JSON.parse(JSON.stringify(item));
                // getPSCodeItems换成codeItems
                if (item?.getPSCodeItems?.length > 0) {
                    obj.codeItems = this.initCodeItems(item.getPSCodeItems);
                    delete obj.getPSCodeItems;
                }
                result.push(obj)
            }
        }
        return result;
    }

    /**
     * 代码表名称
     *
     * @readonly
     * @memberof IBizCodeListModel
     */
    get name() {
        return this.codeListModelData.name;
    }

    /**
     * 代码表代码名称
     *
     * @readonly
     * @memberof IBizCodeListModel
     */
    get codeName() {
        return this.codeListModelData.codeName;
    }

    /**
     * 代码表类型
     *
     * @readonly
     * @memberof IBizCodeListModel
     */
    get codeListType() {
        return this.codeListModelData.codeListType;
    }

    /**
     * 空值显示文本
     *
     * @readonly
     * @memberof IBizCodeListModel
     */
    get emptyText() {
        return this.codeListModelData.emptyText;
    }

    /**
     * 代码表项集合
     *
     * @readonly
     * @memberof IBizCodeListModel
     */
    get codeItems() {
        return this.$codeItems;
    }
}