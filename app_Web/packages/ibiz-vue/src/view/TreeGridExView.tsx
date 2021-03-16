import { MDViewBase } from "./MDViewBase";
import { TreeGridExViewEngine, IBizTreeGridExViewModel, IBizTreeGridExModel } from 'ibiz-core'

/**
 * 实体树表格视图基类
 *
 * @export
 * @class DataViewBase
 * @extends {MDViewBase}
 */
export class TreeGridExView extends MDViewBase {
    /**
     * 树表格实例
     * 
     * @memberof TreeGridExView
     */
    public treeGridExInstance!: IBizTreeGridExModel;

    /**
     * 视图引擎
     *
     * @public
     * @type {ChartViewEngine}
     * @memberof TreeGridExView
     */
    public engine: TreeGridExViewEngine = new TreeGridExViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof TreeGridExView
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            treegridex: (this.$refs[this.viewInstance.viewTreeGridEx.name] as any).ctrl,
            p2k: '0',
            opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.opendata(args, fullargs, params, $event, xData);
            },
            newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.newdata(args, fullargs, params, $event, xData);
            },
            keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
            majorPSDEField: (this.viewInstance.appDataEntity.majorField.codeName).toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
    }

    /**
      * 初始化树表格视图实例
      * 
      * @param opts 
      * @memberof TreeGridExView
      */
    public async viewModelInit() {
        this.viewInstance = new IBizTreeGridExViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.treeGridExInstance = this.viewInstance.getControl('treegridex');        
    }


    /**
     * 渲染视图主体内容区
     * 
     * @memberof TreeGridExView
     */
    public renderMainContent() {
        const ref = "treegridex";
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.treeGridExInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: ref, on: targetCtrlEvent },);
    }

    /**
     * 快速搜索
     *
     * @returns {void}
     * @memberof TreeViewBase
     */
    public onSearch(): void {
        if (!this.treeGridExInstance || !this.viewState) {
            return;
        }
        this.viewState.next({ tag: this.treeGridExInstance.name, action: 'filter', data: { srfnodefilter: this.query } });
    }
}