import { MobTreeViewEngine, MobTreeViewInterface, ModelTool } from 'ibiz-core';
import { MDViewBase } from './md-view-base';
import { IPSAppDETreeView, IPSDETree, IPSAppDataEntity, IPSAppDEField } from '@ibiz/dynamic-model-api';

/**
 * 树视图基类
 *
 * @export
 * @class MobTreeViewBase
 * @extends {MDViewBase}
 */
export class MobTreeViewBase extends MDViewBase implements MobTreeViewInterface {

    /**
     * 视图实例
     * 
     * @memberof MobTreeViewBase
     */
    public viewInstance!: IPSAppDETreeView;

    /**
     * 树视图实例
     * 
     * @memberof MobTreeViewBase
     */
    public treeInstance!: IPSDETree;

    /**
     * 视图引擎
     *
     * @public
     * @type {MobTreeViewEngine}
     * @memberof MobTreeViewBase
     */
    public engine: MobTreeViewEngine = new MobTreeViewEngine();

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof MobTreeViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        if (this.engine && this.treeInstance) {
            let engineOpts = Object.assign({
                view: this,
                parentContainer: this.$parent,
                p2k: '0',
                isLoadDefault: this.viewInstance.loadDefault,
                keyPSDEField: this.appDeCodeName.toLowerCase(),
                majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
                opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.opendata(args, fullargs, params, $event, xData);
                },
                newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.newdata(args, fullargs, params, $event, xData);
                },
                tree: (this.$refs[this.treeInstance.name] as any).ctrl,
            }, opts)
            if (this.searchFormInstance?.name && this.$refs[this.searchFormInstance.name]) {
                engineOpts.searchform = ((this.$refs[this.searchFormInstance.name] as any).ctrl);
            } else if (this.quickSearchFormInstance?.name && this.$refs[this.quickSearchFormInstance.name]) {
                engineOpts.searchform = ((this.$refs[this.quickSearchFormInstance.name] as any).ctrl);
            }
            if (this.searchBarInstance?.name && this.$refs[this.searchBarInstance.name]) {
                engineOpts.searchbar = ((this.$refs[this.searchBarInstance.name] as any).ctrl);
            }
            this.engine.init(engineOpts);
        }
    }

    /**
      * 初始化图表视图实例
      * 
      * @param opts 
      * @memberof MobTreeViewBase
      */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDETreeView;
        await super.viewModelInit();
        this.treeInstance = ModelTool.findPSControlByName('tree',this.viewInstance.getPSControls()) as IPSDETree;
    }

    /**
     * 渲染视图主体内容区
     * 
     * @returns
     * @memberof MobTreeViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.treeInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.treeInstance.name, on: targetCtrlEvent });
    }

    /**
     * 快速搜索值变化
     *
     * @param {*} event
     * @returns
     * @memberof ${srfclassname('${view.name}')}Base
     */
    public async quickValueChange(event: any) {
        let { detail } = event;
        if (!detail) {
            return;
        }
        let { value } = detail;
        this.query = value;
        const tree: any = (this.$refs[this.treeInstance.name] as any)?.ctrl;
        if (tree) {
            tree.webLoad(this.query);
        }
    }

}
