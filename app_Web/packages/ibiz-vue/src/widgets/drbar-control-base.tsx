import { IPSAppView, IPSDEDRBar, IPSDEDRBarGroup, IPSDEDRCtrlItem, IPSDEEditForm, IPSNavigateContext, IPSNavigateParam } from '@ibiz/dynamic-model-api';
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
     * @description 菜单方向
     * @type {('horizontal' | 'vertical')}
     * @memberof DrbarControlBase
     */
    public menuDir: 'horizontal' | 'vertical' = 'vertical';

    /**
     * @description 显示模式（DEFAULT：默认模式，INDEXMODE：嵌入实体首页视图中）
     * @type {('DEFAULT' | 'INDEXMODE')}
     * @memberof DrbarControlBase
     */
    public showMode: 'DEFAULT' | 'INDEXMODE' = 'DEFAULT';

    /**
     * @description 菜单项数据
     * @type {any[]}
     * @memberof DrbarControlBase
     */
    public menuItems: any[] = [];

    /**
     * @description 静态参数变化
     * @param {*} newVal 新值
     * @param {*} oldVal 旧值
     * @memberof DrbarControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        super.onStaticPropsChange(newVal, oldVal);
        this.showMode = newVal.showMode == 'INDEXMODE' ? 'INDEXMODE' : 'DEFAULT';
        this.menuDir = newVal.showMode == 'INDEXMODE' ? 'horizontal' : newVal.menuDir == 'horizontal' ? 'horizontal' : 'vertical';
    }

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
        this.initDefaultSelection();
    }

    /**
     * @description 初始化默认选中
     * @memberof DrbarControlBase
     */
    public initDefaultSelection() {
        if (this.items.length > 0) {
            if (this.showMode == 'INDEXMODE') {
                this.selection = this.items.length > 1 ? this.items[1] : this.items[0];
            } else {
                this.selection = this.items[0];
            }
        }
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
            const editItemCaption = this.controlInstance.M?.editItemCaption;
            this.items.push({
                text: editItemCaption ? editItemCaption : formInstance.logicName,
                disabled: false,
                id: this.formName
            })
        }
        const ctrlItems: Array<IPSDEDRCtrlItem> = this.controlInstance.getPSDEDRCtrlItems() || [];
        const counterRef = this.controlInstance.getPSAppCounterRef();
        let counterService: any = undefined;
        if (counterRef) {
            counterService = Util.findElementByField(this.counterServiceArray, 'id', counterRef.id)?.service;
        }
        ctrlItems.forEach((item: IPSDEDRCtrlItem) => {
            let _item: any = {
                text: this.$tl(item.getCapPSLanguageRes()?.lanResTag, item.caption),
                disabled: true,
                id: item.name?.toLowerCase(),
                // TODO 图标接口目前包还没更新，先从M里拿
                iconcls: (item as any)?.M.getPSSysImage?.cssClass,
                icon: (item as any)?.M.getPSSysImage?.imagePath,
                view: item,
                // TODO 待接口修复后调整。
                groupCodeName: (item as any)?.M?.getPSDEDRBarGroup?.id || '',
            }
            if (item.counterId && counterService) {
                Object.assign(_item, {
                    counter: {
                        id: item.counterId,
                        count: counterService?.counterData?.[item.counterId.toLowerCase()],
                        offset: _item.groupCodeName ? [10, 19] : [-16, 19],
                        showZero: 1
                    }
                });
            }
            this.items.push(_item);
        })
        this.initMenuItems();
    }

    /**
     * @description 初始化菜单项集合
     * @memberof DrbarControlBase
     */
    public initMenuItems() {
        const groups = this.controlInstance.getPSDEDRBarGroups() || [];
        const menuItems: any[] = [];
        groups.forEach((group: IPSDEDRBarGroup) => {
            const items = this.items.filter((item: any) => { return Object.is(item.groupCodeName, group.id); });
            if (items.length == 1) {
                menuItems.push(items[0]);
            } else {
                menuItems.push({
                    text: this.$tl(group.getCapPSLanguageRes()?.lanResTag, group.caption),
                    codeName: group.id,
                    id: group.id,
                    hidden: group.hidden,
                    items: items,
                });
            }
        });
        const noGroupItems = this.items.filter((item: any) => { return !item.groupCodeName; });
        menuItems.push.apply(menuItems, noGroupItems);
        this.menuItems = [...menuItems];
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
        const newSelectItem = this.getItem($event);
        this.selection = newSelectItem;
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
                    const navigateContext: IPSNavigateContext[] = item.view.getPSNavigateContexts() || [];
                    if (navigateContext && navigateContext.length > 0) {
                        const localContext = Util.formatNavParam(navigateContext);
                        let _context: any = Util.computedNavData(args, this.context, this.viewparams, localContext);
                        item.localContext = _context;
                    }
                    const navigateParams: IPSNavigateParam[] = item.view.getPSNavigateParams() || [];
                    if (navigateParams && navigateParams.length > 0) {
                        const localViewParam = Util.formatNavParam(navigateParams);
                        let _param: any = Util.computedNavData(args, this.context, this.viewparams, localViewParam);
                        item.localViewParam = _param;
                    }
                }
            })
        }
        this.$forceUpdate();
    }

}
