import { IPSNavigateContext, IPSNavigateParam } from "@ibiz/dynamic-model-api";
import { DrtabControlInterface, Util } from "ibiz-core";
import { MainControlBase } from "./main-control-base";

/**
 * 数据关系分页部件基类
 *
 * @export
 * @class DrtabControlBase
 * @extends {MainControlBase}
 */
export class DrtabControlBase extends MainControlBase implements DrtabControlInterface {

    /**
     * 数据关系分页部件数组
     *
     * @type {Array<any>}
     * @memberof DrtabControlBase
     */
    public drtabItems: any;

    /**
     * 是否显示插槽
     *
     * @type {boolean}
     * @memberof DrtabControlBase
     */
    public isShowSlot: boolean = true;

    /**
     * 监听静态参数变化
     *
     * @memberof DrtabControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isShowSlot = newVal.isShowSlot === false ? false : true;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 初始化数据关系分页部件模型
     *
     * @type {*}
     * @memberof DrtabControlBase
     */
    public async ctrlModelInit() {
        await super.ctrlModelInit();
        this.initDrtabBasicData();
    }

    /**
     * 数据关系分页部件初始化
     *
     * @memberof DrtabControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: { tag: string, action: string, data: any }) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('state', action)) {
                    this.handleFormChange(data);
                }
            });
        }
    }

    /**
     * 初始化数据关系分页部件基础数据
     *
     * @memberof DrtabControlBase
     */
    public initDrtabBasicData() {
        if (this.controlInstance.getPSDEDRTabPages() && this.controlInstance.getPSDEDRTabPages().length > 0) {
            this.drtabItems = [];
            this.controlInstance.getPSDEDRTabPages().forEach((element: any) => {
                this.drtabItems.push(Object.assign(element, { disabled: true }));
            });
        }
    }

    /**
     * 选中项
     *
     * @param {string} tabPaneName 分页name
     * @memberof DrtabControlBase
     */
    public tabPanelClick(tabPaneName: string): void {
        this.onCtrlEvent(this.controlInstance.name, 'selectionchange', [{ id: tabPaneName }])
    }

    /**
     * 处理表单数据变更
     *
     * @param {any} args 表单数据
     * @memberof AppDrtabBase
     */
    public handleFormChange(args: any) {
        if (args && Object.is(args.srfuf, '1')) {
            this.drtabItems.forEach((drtabItem: any) => {
                // 取消禁用
                drtabItem.disabled = false;
                // 设置导航参数
                this.drtabItems.forEach((drtabItem: any) => {
                    if (
                        drtabItem.getPSNavigateContexts() &&
                        (drtabItem?.getPSNavigateContexts() as IPSNavigateContext[])?.length > 0
                    ) {
                        const localContext = Util.formatNavParam(drtabItem.getPSNavigateContexts());
                        let _context: any = Util.computedNavData(args, this.context, this.viewparams, localContext);
                        drtabItem.localContext = _context;
                    }
                    if (
                        drtabItem?.getPSNavigateParams() &&
                        (drtabItem.getPSNavigateParams() as IPSNavigateParam[])?.length > 0
                    ) {
                        const localViewParam = Util.formatNavParam(drtabItem.getPSNavigateParams());
                        let _param: any = Util.computedNavData(args, this.context, this.viewparams, localViewParam);
                        drtabItem.localViewParam = _param;
                    }
                })
            })
        }
        this.$forceUpdate();
    }
}
