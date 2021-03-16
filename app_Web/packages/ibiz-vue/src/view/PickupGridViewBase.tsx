import { MDViewBase } from './MDViewBase';
import { IBizGridModel, IBizPickupGridViewModel, PickupGridViewEngine, Util } from 'ibiz-core';

export class PickupGridViewBase extends MDViewBase {

    /**
     * 选中数据字符串
     * 
     * @type {string}
     * @memberof PickupGridViewBase
     */
    public selectedData?: string;

    /**
     * 表格选择视图实例对象
     * 
     * @type {IBizPickupGridViewModel}
     * @memberof PickupGridViewBase
     */
    public viewInstance!: IBizPickupGridViewModel;

    /**
     * 表格实例对象
     * 
     * @type {IBizGridModel}
     * @memberof PickupGridViewBase
     */
    private gridInstance!: IBizGridModel;

        /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof PickupGridViewBase
     */
    public engine: PickupGridViewEngine = new PickupGridViewEngine;

    /**
     * 表格行数据默认激活模式
     * 0 不激活
     * 1 单击激活
     * 2 双击激活
     *
     * @type {(number | 0 | 1 | 2)}
     * @memberof PickupGridViewBase
     */
    public gridRowActiveMode: number | 0 | 1 | 2 = 2;

    /**
     * 引擎初始化
     *
     * @public
     * @memberof PickupGridViewBase
     */
    public engineInit(opts: any): void {
        if(this.engine && this.gridInstance){
            let engineOpts = Object.assign({
                view: this,
                parentContainer: this.$parent,
                p2k: '0',
                isLoadDefault: this.viewInstance.loadDefault,
                keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
                majorPSDEField: (this.viewInstance.appDataEntity.majorField?.codeName)?.toLowerCase(),
                opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.opendata(args, fullargs, params, $event, xData);
                },
                newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.newdata(args, fullargs, params, $event, xData);
                },
                grid:(this.$refs[this.gridInstance.name] as any).ctrl,
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
     * 初始化分页导航视图实例
     * 
     * @memberof PickupGridViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizPickupGridViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.gridRowActiveMode = this.viewInstance.gridRowActiveMode;
        this.gridInstance = this.viewInstance.getControl('grid');        
    }

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PickupGridViewBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
        if(newVal?.selectedData && newVal.selectedData != oldVal?.selectedData){
            this.selectedData = newVal.selectedData;
        }
    }

    /**
     * 监听视图静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PickupGridViewBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isSingleSelect = newVal.isSingleSelect;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof PickupGridViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.gridInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.gridInstance.name, on: targetCtrlEvent });
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
            gridRowActiveMode: this.viewInstance.gridRowActiveMode,
            isSingleSelect: this.isSingleSelect,
        })
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent };
    }

}