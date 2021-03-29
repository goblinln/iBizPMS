import { IBizCalendarExpBarModel, IBizToolBarItemModel, Util } from 'ibiz-core';
import { ExpBarControlBase } from './expbar-control-base';
/**
 * 日历导航部件基类
 * 
 * 
 */
export class CalendarExpBarControlBase extends ExpBarControlBase {

    /**
     * 导航栏部件模型对象
     * 
     * @memberof CalendarExpBarControlBase
     */
    public controlInstance!: IBizCalendarExpBarModel;

    /**
     * 导航过滤项
     *
     * @type {*}
     * @memberof CalendarExpBarControlBase
     */
    public navFilter: any = {};

    /**
     * 导航关系
     *
     * @type {*}
     * @memberof CalendarExpBarControlBase
     */
    public navPSDer: any = {};

    /**
     * 导航关系
     *
     * @type {*}
     * @memberof CalendarExpBarControlBase
     */
    public eventMoreState: boolean = false;

    /**
     * 初始化日历导航部件实例
     *
     * @memberof AppDefaultCalendarExpBar
     */
    public async ctrlModelInit() {
        await super.ctrlModelInit();
        this.initNavView();
    }

    /**
     * 初始化导航视图参数
     * 
     * @memberof CalendarExpBarControlBase
     */
    public initNavView() {
        const calendarItems = this.$xDataControl?.calendarItems;
        let navViewName = {};
        let navParam = {};
        let navFilter = {};
        let navPSDer = {};
        if (calendarItems && calendarItems.length > 0) {
            calendarItems.forEach((item: any) => {
                const viewName = {
                    [item.itemType]: item.getNavPSAppView ? item.getNavPSAppView?.dynaModelFilePath : "",
                };
                Object.assign(navViewName, viewName);
                const param = {
                    [item.itemType]: {
                        navigateContext: this.initNavParam(item.getPSNavigateContexts),
                        navigateParams: this.initNavParam(item.getPSNavigateParams),
                    }
                }
                Object.assign(navParam, param);
                const filter = {
                    [item.itemType]: item.navFilter ? item.navFilter : "",
                }
                Object.assign(navFilter, filter);
                const psDer = {
                    [item.itemType]: item.getNavPSDER ? "n_" + item.getNavPSDER?.getPSPickupDEField?.codeName?.toLowerCase() + "_eq" : "",
                }
                Object.assign(navPSDer, psDer);
            })
        }
        this.navViewName = navViewName;
        this.navParam = navParam;
        this.navFilter = navFilter;
        this.navPSDer = navPSDer;
    }

    /**
     * 初始化导航参数
     * 
     * @param params 初始化参数
     * @memberof CalendarExpBarControlBase
     */
    public initNavParam(params: any) {
        if (params && params.length > 0) {
            let navParams: any = {};
            params.forEach((param: any) => {
                const navParam = {
                    [param.key]: param.rawValue ? param.value : "%" + param.value + "%",
                }
                Object.assign(navParams, navParam);
            });
            return navParams;
        } else {
            return null;
        }
    }


    /**
     * split值变化事件
     *
     * @memberof CalendarExpBarControlBase
     */
    public onSplitChange() {
        super.onSplitChange();
        if (this.$xDataControl) {
            const calendarContainer: any = (this.$refs[this.$xDataControl.name] as any).$refs.ctrl;
            if (calendarContainer.$refs[this.$xDataControl.codeName]) {
                const calendar: any = calendarContainer.$refs[this.$xDataControl.codeName];
                calendar.getApi().updateSize();
            }
        }
    }

    /**
     * 工具栏点击
     * 
     * @memberof CalendarExpBarControlBase
     */
    public handleItemClick(data: any, $event: any) {
        this.$emit('ctrl-event', { data: data, $event: $event });
    }

    /**
     * 执行搜索
     *
     * @memberof CalendarExpBarControlBase
     */
    public onSearch($event: any) {
        if (this.$xDataControl) {
            const calendarContainer: any = (this.$refs[this.$xDataControl.name] as any).$refs.ctrl;
            calendarContainer.searchEvents({ query: this.searchText });
        }
    }

    /**
     * 刷新
     *
     * @memberof CalendarExpBarControlBase
     */
    public refresh(args?: any): void {
        if (this.$xDataControl) {
            const calendarContainer: any = (this.$refs[this.$xDataControl.name] as any).$refs.ctrl;
            calendarContainer.refresh();
        }
    }

    /**
     * 日历部件的选中数据事件
     * 
     * @memberof CalendarExpBarControlBase
     */
    public onSelectionChange(args: any[], tag?: string, $event2?: any): void {
        let tempContext: any = {};
        let tempViewParam: any = {};
        if (args.length === 0) {
            this.calcToolbarItemState(true);
            return;
        }
        const arg: any = args[0];
        if (this.context) {
            Object.assign(tempContext, JSON.parse(JSON.stringify(this.context)));
        }
        const calendarItemEntity = this.$xDataControl?.getCalendarItem(arg.itemType)?.$appDataEntity;
        if (calendarItemEntity) {
            Object.assign(tempContext, { [calendarItemEntity.codeName?.toLowerCase()]: arg[calendarItemEntity.codeName?.toLowerCase()] });
            Object.assign(tempContext, { srfparentdename: calendarItemEntity.codeName, srfparentkey: arg[calendarItemEntity.codeName?.toLowerCase()] });
            if (this.navFilter && this.navFilter[arg.itemType] && !Object.is(this.navFilter[arg.itemType], "")) {
                Object.assign(tempViewParam, { [this.navFilter[arg.itemType]]: arg[calendarItemEntity.codeName?.toLowerCase()] });
            }
            if (this.navPSDer && this.navFilter[arg.itemType] && !Object.is(this.navPSDer[arg.itemType], "")) {
                Object.assign(tempViewParam, { [this.navPSDer[arg.itemType]]: arg[calendarItemEntity.codeName?.toLowerCase()] });
            }
            if (this.navParam && this.navParam[arg.itemType] && this.navParam[arg.itemType].navigateContext && Object.keys(this.navParam[arg.itemType].navigateContext).length > 0) {
                let _context: any = Util.computedNavData(arg, tempContext, tempViewParam, this.navParam[arg.itemType].navigateContext);
                Object.assign(tempContext, _context);
            }
            if (this.navParam && this.navParam[arg.itemType] && this.navParam[arg.itemType].navigateParams && Object.keys(this.navParam[arg.itemType].navigateParams).length > 0) {
                let _params: any = Util.computedNavData(arg, tempContext, tempViewParam, this.navParam[arg.itemType].navigateParams);
                Object.assign(tempViewParam, _params);
            }
        }
        this.selection = {};
        if (this.navViewName[arg.itemType]) {
            Object.assign(tempContext, { viewpath: this.navViewName[arg.itemType] })
        }
        Object.assign(this.selection, { view: { viewname: 'app-view-shell' }, context: tempContext, viewparam: tempViewParam });
        this.calcToolbarItemState(false);
        this.$emit("ctrl-event", { controlname: this.controlInstance.name, action: "selectionchange", data: args });
        this.handleEventMore();
    }

    /**
     * 处理日常更多按钮点击
     * 
     * @memberof CalendarExpBarControlBase
     */
    public handleEventMore() {
        if (!this.eventMoreState) {
            return;
        }
        let dom: any = document.querySelector('.fc-more-select');
        if (dom) {
            this.$nextTick(() => {
                dom.click();
                dom.className = 'fc-more';
                this.eventMoreState = false;
            })
        }
    }

    /**
     * 部件事件处理
     * 
     * @memberof CalendarExpBarControlBase
     */
    public onCtrlEvent(controlname: any, action: any, data: any) {
        if (controlname == this.$xDataControl.name) {
            if (action == 'eventlimitclick') {
                if (data) {
                    this.eventMoreState = true;
                }
            }
        }
        super.onCtrlEvent(controlname, action, data);
    }

}