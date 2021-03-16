import { IBizTreeModel, IBizPickupTreeViewModel, PickupTreeViewEngine } from 'ibiz-core';
import { MDViewBase } from './MDViewBase';

export class PickupTreeViewBase extends MDViewBase {

    /**
     * 选中数据字符串
     * 
     * @type {string}
     * @memberof PickupTreeViewBase
     */
    public selectedData?: string;

    /**
     * 视图实例对象
     * 
     * @type {IBizPickupGridViewModel}
     * @memberof PickupTreeViewBase
     */
    public viewInstance!: IBizPickupTreeViewModel;

    /**
     * 树视图实例
     *
     * @public
     * @type {IBizTreeModel}
     * @memberof PickupTreeViewBase
     */
    public treeInstance !: IBizTreeModel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof PickupTreeViewBase
     */
    public engine: PickupTreeViewEngine = new PickupTreeViewEngine;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PickupTreeViewBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
        if(newVal?.selectedData && newVal.selectedData != oldVal?.selectedData){
            this.selectedData = newVal.selectedData;
        }
    }

    /**
     * 监听参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PickupTreeViewBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isSingleSelect = newVal.isSingleSelect;
        super.onStaticPropsChange(newVal, oldVal)
    }

    /**
     * 引擎初始化
     *
     * @public
     * @memberof PickupTreeViewBase
     */
    public engineInit(): void {
        let engineOpts = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            tree: (this.$refs[this.treeInstance.name] as any).ctrl,
            keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
            majorPSDEField: (this.viewInstance.appDataEntity.majorField.codeName).toLowerCase(),
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
    public computeTargetCtrlData(controlInstance:any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.dynamicProps,{
            selectedData: this.selectedData,
        })
        Object.assign(targetCtrlParam.staticProps,{
            isSingleSelect: this.isSingleSelect,
        })
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent };
    }

    /**
     * 初始化选择树视图实例
     * 
     * @memberof PickupTreeViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizPickupTreeViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.treeInstance = this.viewInstance.getControl('tree');        
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof PickupTreeViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.treeInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.treeInstance.name, on: targetCtrlEvent });
    }

    /**
     * 快速搜索
     *
     * @returns {void}
     * @memberof PickupTreeViewBase
     */
    public onSearch(): void {
        if (!this.treeInstance || !this.viewState) {
            return;
        }
        this.viewState.next({ tag: this.treeInstance.name, action: 'filter', data: { srfnodefilter: this.query } });
    }
}