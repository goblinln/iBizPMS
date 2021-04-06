import { IBizGridModel, GridViewEngine, IBizGridViewModel } from 'ibiz-core';
import { MDViewBase } from './mdview-base';

/**
 * 表格视图基类
 *
 * @export
 * @class GridViewBase
 * @extends {MDViewBase}
 */
export class GridViewBase extends MDViewBase {

    /**
     * 视图实例
     * 
     * @memberof GridViewBase
     */
    public viewInstance!: IBizGridViewModel;

    /**
     * 表格实例
     * 
     * @memberof GridViewBase
     */
    public gridInstance!: IBizGridModel;

    /**
     * 表格行数据默认激活模式
     * 0 不激活
     * 1 单击激活
     * 2 双击激活
     *
     * @protected
     * @type {(0 | 1 | 2)}
     * @memberof GridViewBase
     */
    protected gridRowActiveMode: 0 | 1 | 2 = 0;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof GridViewBase
     */
    public engine: GridViewEngine = new GridViewEngine();

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof GridViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        if (this.engine && this.gridInstance) {
            let engineOpts = Object.assign({
                view: this,
                parentContainer: this.$parent,
                p2k: '0',
                isLoadDefault: this.viewInstance?.loadDefault,
                keyPSDEField: (this.viewInstance?.appDataEntity?.codeName).toLowerCase(),
                majorPSDEField: (this.viewInstance?.appDataEntity?.majorField?.codeName)?.toLowerCase(),
                opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.opendata(args, fullargs, params, $event, xData);
                },
                newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.newdata(args, fullargs, params, $event, xData);
                },
                grid: (this.$refs[this.gridInstance?.name] as any).ctrl,
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
     * 初始化表格视图实例
     * 
     * @param opts 
     * @memberof GridViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizGridViewModel(this.staticProps?.modeldata, this.context);
        await this.viewInstance?.loaded();
        await super.viewModelInit();
        this.gridInstance = this.viewInstance?.getControl('grid');
        this.gridRowActiveMode = this.viewInstance?.gridRowActiveMode;        
    }
    
    /**
     * 渲染视图主体内容区
     * 
     * @memberof GridViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.gridInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.viewInstance?.viewGrid?.name, on: targetCtrlEvent },);
    }

    /**
     * 快速搜索
     *
     * @param {*} $event
     * @memberof GridViewBase
     */
    public onSearch($event: any): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        const refs: any = this.$refs;
        if (refs[this.gridInstance?.name] && refs[this.gridInstance.name].ctrl) {
            refs[this.gridInstance?.name].ctrl.load(this.context, true);
        }
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof GridViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps, {
            isOpenEdit: this.viewInstance?.rowEditDefault,
            gridRowActiveMode: this.viewInstance?.gridRowActiveMode,
        })
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof GridViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if(action) {
            switch (action) {
                case "save":
                    this.$emit("view-event", { action: "drdatasaved", data: data });
                    break;
                case "search":
                    this.onSearch(data);
                    break;
                default:
                    super.onCtrlEvent(controlname, action, data);
                    break;
            }
        }
    }

}
