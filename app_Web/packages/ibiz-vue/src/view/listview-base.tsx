import { IPSAppDataEntity, IPSAppDEField, IPSAppDEListView, IPSDEList } from '@ibiz/dynamic-model-api';
import { ListViewEngine, ModelTool } from 'ibiz-core';
import { MDViewBase } from './mdview-base';


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
    public viewInstance!: IPSAppDEListView;

    /**
     * 列表实例
     * 
     * @memberof ListViewBase
     */
    public listInstance!: IPSDEList;

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
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        if (this.engine && this.listInstance) {
            let engineOpts = Object.assign({
                view: this,
                parentContainer: this.$parent,
                p2k: '0',
                isLoadDefault: (this.viewInstance as IPSAppDEListView)?.loadDefault,
                keyPSDEField: (ModelTool.getViewAppEntityCodeName(this.viewInstance) as string).toLowerCase(),
                majorPSDEField: (ModelTool.getAppEntityMajorField(this.viewInstance.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName.toLowerCase(),
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
     * 初始化列表视图实例
     * 
     * @memberof ListViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDEListView;
        await super.viewModelInit();
        this.listInstance = ModelTool.findPSControlByName('list', this.viewInstance.getPSControls()) as IPSDEList;
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof ListViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.listInstance);
        //  todo 临时传入视图 部件模型拿不到视图
        Object.assign(targetCtrlParam.staticProps, {
            viewStyle: this.viewInstance.viewStyle
        });
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.listInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 快速搜索
     *
     * @param {*} $event
     * @memberof ListViewBase
     */
    public onSearch($event: any): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        const refs: any = this.$refs;
        if (refs[this.listInstance?.name]?.ctrl) {
            refs[this.listInstance?.name].ctrl.load({ arg: this.context, queryParam: $event });
        }
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof ListViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if(action == 'save'){
            this.$emit("view-event", { action: "drdatasaved", data: data });
        }else{
            super.onCtrlEvent(controlname, action, data);
        }
    }
}
