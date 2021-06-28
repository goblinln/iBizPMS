import { IPSAppView, IPSDEDRBar, IPSDEDRCtrlItem, IPSDEEditForm, IPSNavigateContext, IPSNavigateParam } from '@ibiz/dynamic-model-api';
import { DrbarControlInterface, ModelTool, Util } from "ibiz-core";
import { MainControlBase } from "./main-control-base";

/**
 * 数据关系栏部件基类
 *
 * @export
 * @class DrbarControlBase
 * @extends {MainControlBase}
 */
export class DrbarControlBase extends MainControlBase implements DrbarControlInterface{

    /**
     * 数据关系栏部件实例对象
     * 
     * @type {IPSDEDRBar}
     * @memberof DrbarControlBase
     */
    public controlInstance!: IPSDEDRBar;

    /**
     * 表单实例事项
     * 
     * @type {IPSDEEditForm}
     * @memberof DrbarControlBase
     */
    public formInstance?: IPSDEEditForm;

    /**
     * 表单名称
     * 
     * @type {string}
     * @memberof DrbarControlBase
     */
    public formName: string = '';

    /**
     * 数据选中项
     *
     * @type {*}
     * @memberof DrbarControlBase
     */
    public selection: any = {};

    /**
     * 关系栏数据项
     * 
     * @type {any[]}
     * @memberof DrbarControlBase
     */
    public items: any[] = [];

    /**
     * 默认打开项
     * 
     * @type {string[]}
     * @memberof DrbarControlBase
     */
    public defaultOpeneds: string[] = [];

    /**
     * 父数据
     * 
     * @type {*}
     * @memberof DrbarControlBase
     */
    public parentData: any = {};

    /**
     * 宽度
     * 
     * @type {number}
     * @memberof DrbarControlBase
     */
    public width: number = 240;

    /**
     * 部件模型初始化
     * 
     * @memberof DrbarControlBase
     */
    public async ctrlModelInit() {
        await super.ctrlModelInit();
        this.width = this.controlInstance.width >= 240 ? this.controlInstance.width : 240;
        this.initDrbarBasicData();
    }

    /**
     * 部件初始化
     * 
     * @memberof DrbarControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('state', action)) {
                    this.handleFormChange(data);
                }
            });
        }
        this.selection = this.items[0];
    }

    /**
     * 初始化关系栏数据项
     * 
     * @memberof DrbarControlBase
     */
    public initDrbarBasicData() {
        const formInstance = ModelTool.findPSControlByType('FORM', (this.controlInstance.getParentPSModelObject?.() as IPSAppView).getPSControls?.() || []);
        if (formInstance) {
            this.formName = formInstance.name?.toLowerCase();
            this.items.push({
                text: formInstance.logicName,
                disabled: false,
                id: this.formName
            })
        }
        const ctrlItems: Array<IPSDEDRCtrlItem> = this.controlInstance.getPSDEDRCtrlItems() || [];
        ctrlItems.forEach((item: IPSDEDRCtrlItem) => {
            this.items.push({
                text: this.$tl(item.getCapPSLanguageRes()?.lanResTag, item.caption),
                disabled: true,
                id: item.name?.toLowerCase(),
                iconcls: (item as any).getPSSysImage?.()?.cssClass,
                icon: (item as any).getPSSysImage?.()?.imagePath,
                view: item
            })
        })
    }

    /**
     * 获取子项
     *
     * @param {any[]} items
     * @param {string} id
     * @returns {*}
     * @memberof DrbarControlBase
     */
    public getItem(id: string): any {
        return this.items.find((item: any) => {
           return item.id == id;
        });
    }

    /**
     * 节点选中
     *
     * @param {*} $event
     * @memberof DrbarControlBase
     */
    public onSelect($event: any, isFirst: boolean = false): void {
        if ($event == this.selection.id) {
            return;
        }
        this.selection = this.getItem($event);
        this.ctrlEvent({ controlname: this.controlInstance.name, action: 'selectionchange', data: [this.selection] })
    }

    /**
     * 设置关系项状态
     *
     * @param {any[]} items
     * @param {boolean} state
     * @memberof DrbarControlBase
     */
    public handleFormChange(args: any) {
        if (args && Object.is(args.srfuf, '1')) {
            this.items.forEach((item: any) => {
                // 取消禁用
                item.disabled = false;
                if (item.view) {
                    // 设置导航参数
                    if (
                        item.view.getPSNavigateContexts() &&
                        (item.view.getPSNavigateContexts() as IPSNavigateContext[])?.length > 0
                    ) {
                        const localContext = Util.formatNavParam(item.view.getPSNavigateContexts());
                        let _context: any = Util.computedNavData(args, this.context, this.viewparams, localContext);
                        item.localContext = _context;
                    }
                    if (
                        item?.view.getPSNavigateParams() &&
                        (item.view.getPSNavigateParams() as IPSNavigateParam[])?.length > 0
                    ) {
                        const localViewParam = Util.formatNavParam(item.view.getPSNavigateParams());
                        let _param: any = Util.computedNavData(args, this.context, this.viewparams, localViewParam);
                        item.localViewParam = _param;
                    }
                }
            })
        }
        this.$forceUpdate();
    }

}
