import { IBizTreeModel, IBizTreeViewModel, TreeViewEngine } from 'ibiz-core';
import { MDViewBase } from './mdview-base';

/**
 * 实体树视图视图基类
 *
 * @export
 * @class TreeViewBase
 * @extends {MDViewBase}
 */
export class TreeViewBase extends MDViewBase {

    /**
     * 视图实例
     *
     * @memberof TreeViewBase
     */
    public viewInstance!: IBizTreeViewModel;

    /**
     * 树视图实例
     *
     * @public
     * @type {IBizTreeModel}
     * @memberof TreeViewBase
     */
    public treeInstance !:IBizTreeModel;

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
                keyPSDEField: (this.viewInstance.appDataEntity?.codeName)?.toLowerCase(),
                majorPSDEField: (this.viewInstance.appDataEntity.majorField?.codeName)?.toLowerCase(),
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
        this.viewInstance = new IBizTreeViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit()
        this.treeInstance = this.viewInstance.getControl('tree');
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
