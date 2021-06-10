import { TreeViewEngine, ModelTool, TreeViewInterface } from 'ibiz-core';
import { IPSAppDETreeView, IPSDETree } from '@ibiz/dynamic-model-api';
import { MDViewBase } from './mdview-base';

/**
 * 实体树视图视图基类
 *
 * @export
 * @class TreeViewBase
 * @extends {MDViewBase}
 * @implements {TreeViewInterface}
 */
export class TreeViewBase extends MDViewBase implements TreeViewInterface {

    /**
     * 视图实例
     *
     * @memberof TreeViewBase
     */
    public viewInstance!: IPSAppDETreeView;

    /**
     * 树视图实例
     *
     * @public
     * @type {IPSDETree}
     * @memberof TreeViewBase
     */
    public treeInstance !:IPSDETree;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof TreeViewBase
     */
    public engine: TreeViewEngine = new TreeViewEngine();
    
    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof TreeViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        if(this.engine && this.treeInstance){
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
            } else if(this.quickSearchFormInstance?.name && this.$refs[this.quickSearchFormInstance.name] ){
                engineOpts.searchform = ((this.$refs[this.quickSearchFormInstance.name] as any).ctrl);
            }
            if(this.searchBarInstance?.name && this.$refs[this.searchBarInstance.name]) {
                engineOpts.searchbar = ((this.$refs[this.searchBarInstance.name] as any).ctrl);
            }
            this.engine.init(engineOpts);
        }
    }

    /**
     * 初始化列表视图实例
     *
     * @memberof AppDefaultTreeView
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDETreeView;
        await super.viewModelInit()
        this.treeInstance = ModelTool.findPSControlByName('tree',this.viewInstance.getPSControls()) as IPSDETree;
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof AppDefaultEditView
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.treeInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.treeInstance.name, on: targetCtrlEvent });
    }
    
    /**
     * 快速搜索
     *
     * @returns {void}
     * @memberof TreeViewBase
     */
    public onSearch(): void {
        if (!this.treeInstance || !this.viewState) {
            return;
        }
        this.viewState.next({ tag: this.treeInstance.name, action: 'filter', data: { srfnodefilter: this.query } });
    }
}
