import { MDViewBase } from "./mdview-base";
import { TreeGridExViewEngine, ModelTool, TreeGridExViewInterface } from 'ibiz-core';
import { IPSAppDETreeGridExView, IPSDETreeGridEx } from '@ibiz/dynamic-model-api';

/**
 * 实体树表格视图基类
 *
 * @export
 * @class TreeGridExView
 * @extends {MDViewBase}
 * @implements {TreeGridExViewInterface}
 */
export class TreeGridExView extends MDViewBase implements TreeGridExViewInterface {

    /**
     * 视图实例
     *
     * @memberof TreeViewBase
     */
     public viewInstance!: IPSAppDETreeGridExView;

    /**
     * 树表格实例
     * 
     * @memberof TreeGridExView
     */
    public treeGridExInstance!: IPSDETreeGridEx;

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
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            treegridex: (this.$refs[this.treeGridExInstance.name] as any).ctrl,
            p2k: '0',
            opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.opendata(args, fullargs, params, $event, xData);
            },
            newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.newdata(args, fullargs, params, $event, xData);
            },
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
    }

    /**
      * 初始化树表格视图实例
      * 
      * @memberof TreeGridExView
      */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDETreeGridExView;
        await super.viewModelInit();
        this.treeGridExInstance = ModelTool.findPSControlByName('treegridex',this.viewInstance.getPSControls()) as IPSDETreeGridEx;        
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