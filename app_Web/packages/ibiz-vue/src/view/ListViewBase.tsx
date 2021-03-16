import { IBizListModel, ListViewEngine, IBizListViewModel } from 'ibiz-core';
import { MDViewBase } from './MDViewBase';


/**
 * 列表视图基类
 *
 * @export
 * @class ListViewBase
 * @extends {MDViewBase}
 */
export class ListViewBase extends MDViewBase {

    /**
     * 视图实例
     * 
     * @memberof ListViewBase
     */
    public viewInstance!: IBizListViewModel;

    /**
     * 列表实例
     * 
     * @memberof ListViewBase
     */
    public listInstance!: IBizListModel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof ListViewBase
     */
    public engine: ListViewEngine = new ListViewEngine();

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof ListViewBase
     */
    public engineInit(opts: any = {}): void {
        if(this.engine && this.listInstance){
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
                list: (this.$refs[this.listInstance.name] as any).ctrl,
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
     * @memberof ListViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizListViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.listInstance = this.viewInstance.getControl('list');
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof ListViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.listInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.viewInstance.viewList.name, on: targetCtrlEvent });
    }

    /**
     * 快速搜索
     *
     * @param {*} $event
     * @memberof ListViewBase
     */
    public onSearch($event: any): void {
        const refs: any = this.$refs;
        if (refs[this.listInstance?.name]?.ctrl) {
            refs[this.listInstance?.name].ctrl.load({});
        }
    }
}
