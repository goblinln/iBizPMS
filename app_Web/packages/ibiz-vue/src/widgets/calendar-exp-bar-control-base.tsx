import { IPSCalendarExpBar, IPSDECalendar, IPSSysCalendar, IPSSysCalendarItem } from '@ibiz/dynamic-model-api';
import { IPSAppDataEntity } from '@ibiz/dynamic-model-api/dist/types/app/dataentity/ipsapp-data-entity';
import { Util } from 'ibiz-core';
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
    public controlInstance!: IPSCalendarExpBar;

    /**
     * 数据部件
     *
     * @memberof CalendarExpBarControlBase
     */
    public $xDataControl!: IPSSysCalendar;

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
     * 初始化日历导航部件实例
     *
     * @memberof AppDefaultCalendarExpBar
     */
    public async ctrlModelInit() {
        await super.ctrlModelInit();
        this.initNavView();
    }

    /**
     * 初始化数据部件配置
     *
     * @memberof AppDefaultCalendarExpBar
     */
    public async handleXDataCtrlOptions() {
        await super.handleXDataCtrlOptions();
    }

    /**
     * 初始化导航视图参数
     * 
     * @memberof CalendarExpBarControlBase
     */
    public initNavView() {
        const calendarItems = this.$xDataControl?.getPSSysCalendarItems();
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
            const calendarContainer: any = (this.$refs[this.xDataControlName] as any).ctrl;
            if (calendarContainer.$refs[this.$xDataControl.codeName]) {
                const calendarApi: any = calendarContainer.$refs[this.$xDataControl.codeName].getApi();
                if (calendarApi) {
                    calendarApi.updateSize();
                    this.$forceUpdate();
                }
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
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        if (this.$xDataControl) {
            const calendarContainer: any = (this.$refs[this.xDataControlName] as any).ctrl;
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
            const calendarContainer: any = (this.$refs[this.xDataControlName] as any).ctrl;
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
        const calendarItem: IPSSysCalendarItem | null | undefined = ((this.$xDataControl as IPSSysCalendar).getPSSysCalendarItems() || []).find((item: IPSSysCalendarItem) => {
            return item.itemType === arg.itemType;
        });
        const calendarItemEntity: IPSAppDataEntity | null | undefined = calendarItem?.getPSAppDataEntity();
        if (calendarItem && calendarItemEntity) {
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
            if (calendarItem.getNavPSAppView()) {
                Object.assign(tempContext, {
                    viewpath: calendarItem.getNavPSAppView()?.modelPath
                })
            }
        }
        this.selection = {};
        if (this.navViewName[arg.itemType]) {
            Object.assign(tempContext, { viewpath: this.navViewName[arg.itemType] })
        }
        Object.assign(this.selection, { view: { viewname: 'app-view-shell' }, context: tempContext, viewparam: tempViewParam });
        this.calcToolbarItemState(false);
        this.$emit("ctrl-event", { controlname: this.controlInstance.name, action: "selectionchange", data: args });
    }

}