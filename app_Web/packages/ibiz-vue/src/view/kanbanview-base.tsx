import { IPSAppDataEntity, IPSAppDEField, IPSAppDEKanbanView, IPSDEKanban } from "@ibiz/dynamic-model-api";
import { KanBanViewEngine, ModelTool, KanbanViewInterface } from "ibiz-core";
import { MDViewBase } from "./mdview-base";


/**
 * 实体看板视图基类
 *
 * @export
 * @class KanbanViewBase
 * @extends {MDViewBase}
 * @implements {KanbanViewInterface}
 */
export class KanbanViewBase extends MDViewBase implements KanbanViewInterface {
    /**
     * 看板视图实例
     * 
     * @memberof KanbanViewBase
     */
    public viewInstance!: IPSAppDEKanbanView;

    /**
     * 看板实例
     * 
     * @memberof KanbanViewBase
     */
    public kanbanInstance!: IPSDEKanban;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof KanbanViewBase
     */
    public engine: KanBanViewEngine = new KanBanViewEngine();

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof KanbanViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        if (this.engine && this.kanbanInstance) {
            let engineOpts = Object.assign({
                view: this,
                parentContainer: this.$parent,
                p2k: '0',
                isLoadDefault: this.viewInstance?.loadDefault,
                keyPSDEField: (ModelTool.getViewAppEntityCodeName(this.viewInstance) as string).toLowerCase(),
                majorPSDEField: (ModelTool.getAppEntityMajorField(this.viewInstance.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName.toLowerCase(),
                opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.opendata(args, fullargs, params, $event, xData);
                },
                newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.newdata(args, fullargs, params, $event, xData);
                },
                kanban: (this.$refs[this.kanbanInstance.name] as any).ctrl,
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
     * 初始化看板视图实例
     * 
     * @param opts 
     * @memberof KanbanViewBase
     */
    public async viewModelInit(arg?: any) {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDEKanbanView
        await super.viewModelInit();
        this.kanbanInstance = ModelTool.findPSControlByName('kanban', this.viewInstance.getPSControls()) as IPSDEKanban;
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof KanbanViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.kanbanInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.kanbanInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 快速搜索
     *
     * @param {*} $event
     * @memberof KanbanViewBase
     */
    public onSearch($event: any): void {
        const refs: any = this.$refs;
        if (refs[this.kanbanInstance?.name] && refs[this.kanbanInstance.name].ctrl) {
            refs[this.kanbanInstance.name].ctrl.load(this.context);
        }
    }
}