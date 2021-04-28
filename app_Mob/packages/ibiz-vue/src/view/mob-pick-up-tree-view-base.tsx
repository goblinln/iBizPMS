import { MobPickupTreeViewEngine, ModelTool } from 'ibiz-core';
import { MDViewBase } from './md-view-base';
import { IPSAppDETreeView, IPSDETree } from '@ibiz/dynamic-model-api';

export class MobPickupTreeViewBase extends MDViewBase {

    /**
     * 选中数据字符串
     * 
     * @type {string}
     * @memberof MobPickupTreeViewBase
     */
    public selectedData?: string;

    /**
     * 视图实例对象
     * 
     * @type {IBizPickupGridViewModel}
     * @memberof MobPickupTreeViewBase
     */
    public viewInstance!: IPSAppDETreeView;

    /**
     * 树视图实例
     *
     * @public
     * @type {IBizTreeModel}
     * @memberof MobPickupTreeViewBase
     */
    public treeInstance !: IPSDETree;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobPickupTreeViewBase
     */
    public engine: MobPickupTreeViewEngine = new MobPickupTreeViewEngine();

    /**
     * 搜索值
     *
     * @memberof MobPickupTreeViewBase
     */
    public query = "";

    /**
     * 快速搜索
     *
     * @memberof MobPickupTreeViewBase
     */
    public quickSearch(value: any) {
        this.query = value;
        this.viewState.next({ tag: 'tree', action: 'quicksearch', data: value });
    }

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MobPickupTreeViewBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
        if (newVal?.selectedData && newVal.selectedData != oldVal?.selectedData) {
            this.selectedData = newVal.selectedData;
        }
    }

    /**
     * 监听参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MobPickupTreeViewBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isSingleSelect = newVal.isSingleSelect;
        super.onStaticPropsChange(newVal, oldVal)
    }

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MobPickupTreeViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        let engineOpts = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            tree: (this.$refs[this.treeInstance.name] as any).ctrl,
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
        this.engine.init(engineOpts);
    }


    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof PickupGridViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.dynamicProps, {
            selectedData: this.selectedData,
        })
        Object.assign(targetCtrlParam.staticProps, {
            isSingleSelect: this.isSingleSelect,
        })
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent };
    }

    /**
     * 初始化选择树视图实例
     * 
     * @memberof MobPickupTreeViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDETreeView;
        await super.viewModelInit();
        this.treeInstance = ModelTool.findPSControlByName("tree",this.viewInstance.getPSControls());
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobPickupTreeViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.treeInstance);
        return <div class="view-container app-mob-pickup-mdview">
            <div class="view-content">
                {this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.treeInstance.name, on: targetCtrlEvent })}
            </div>
        </div>
    }

}