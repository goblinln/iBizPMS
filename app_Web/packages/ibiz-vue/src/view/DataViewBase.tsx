import { IBizDataViewViewModel, DataViewEngine, IBizDataViewModel } from 'ibiz-core';
import { MDViewBase } from './MDViewBase';

/**
 * 数据视图基类
 *
 * @export
 * @class DataViewBase
 * @extends {MainViewBase}
 */
export class DataViewBase extends MDViewBase {

    /**
     * 数据视图视图实例
     * 
     * @memberof GanttViewBase
     */
    public viewInstance!: IBizDataViewViewModel;
    
    /**
     * 卡片视图部件实例
     * 
     * @memberof DataViewBase
     */
    public dataViewInstance!: IBizDataViewModel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof DataViewBase
     */
    public engine: DataViewEngine = new DataViewEngine();
    
    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof DataViewBase
     */
    public engineInit(opts: any = {}): void {
        if(this.engine && this.dataViewInstance){
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
                dataview: (this.$refs[this.dataViewInstance.name] as any).ctrl,
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
     * 初始化数据视图实例
     * 
     * @memberof DataViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizDataViewViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.dataViewInstance = this.viewInstance.getControl('dataview');
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof DataViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.dataViewInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.dataViewInstance.name, on: targetCtrlEvent });
    }

    /**
     * 快速搜索
     *
     * @param {*} $event
     * @memberof DataViewBase
     */
    public onSearch($event: any): void {
        const refs: any = this.$refs;
        if (refs[this.dataViewInstance?.name]) {
            refs[this.dataViewInstance?.name].refresh();
        }
    }
}