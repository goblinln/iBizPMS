import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 树表格视图模型类
 * 
 * @class IBizTreeGridExViewModel
 */
export class IBizTreeGridExViewModel extends IBizMdViewModel {

    /**
     * 树表格部件
     */
    private $treeGridEx: any = {};

    /**
     * 初始化AppGridView对象
     * 
     * @param opts 额外参数
     * @memberof IBizGridViewModel
     */
    public constructor(opts: any, context: any) {
        super(opts, context);
    }

    /**
     * 加载模型数据（实体树表格）
     * 
     * @memberof IBizGridViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$treeGridEx = this.getControlByName('treegridex');
    }

    /**
     * 是否自动加载
     * 
     * @memberof IBizGridViewModel
     */
    get isAutoLoad() {
        return this.viewTreeGridEx.getPSControlParam.autoLoad;
    }

    /**
     * 是否显示提示
     * 
     * @memberof IBizGridViewModel
     */
    get showBusyIndicator() {
        return this.viewTreeGridEx.getPSControlParam.showBusyIndicator;
    }

    /**
     * 获取树表格部件
     * 
     * @memberof IBizGridViewModel
     */
    get viewTreeGridEx() {
        return this.$treeGridEx;
    }
} 