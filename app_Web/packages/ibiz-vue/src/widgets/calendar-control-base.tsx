import { Vue, Component, Watch } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import FullCalendar from '@fullcalendar/vue';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import interactionPlugin from '@fullcalendar/interaction';
import { Util, ViewTool, ModelTool } from 'ibiz-core';
import { MDControlBase } from './md-control-base';
import { GlobalService } from 'ibiz-service';
import { AppCalendarService } from '../ctrl-service';
import { ContextMenu } from '../components/common/context-menu/context-menu';
import { AppDefaultContextMenu } from '../components/control/app-default-contextmenu/app-default-contextmenu';
import { AppViewLogicService } from '../app-service';
import { IPSAppDataEntity, IPSControlParam, IPSDECalendar, IPSDECMUIActionItem, IPSDEContextMenu, IPSDETBUIActionItem, IPSDEToolbar, IPSDEToolbarItem, IPSDEUIAction, IPSSysCalendar, IPSSysCalendarItem } from '@ibiz/dynamic-model-api';

/**
 * 应用日历部件基类
 *
 * @export
 * @class CalendarControlBase
 * @extends {MDControlBase}
 */
@Component({
    components: {
        FullCalendar,
        "app-default-contextmenu": AppDefaultContextMenu
    }
})
export class CalendarControlBase extends MDControlBase{

    /**
     * 数据视图模型实例
     * 
     * @type {*}
     * @memberof CalendarControlBase
     */
    public controlInstance!: IPSDECalendar;

    /**
     * 模型数据是否加载完成
     * 
     * @memberof CalendarControlBase
     */
    public controlIsLoaded:boolean = false;

    /**
     * 显示处理提示
     * 
     * @type {boolean}
     * @memberof CalendarControlBase
     */
    public showBusyIndicator: boolean = false;

    /**
     * 日历部件样式名
     *
     * @public
     * @type {any[]}
     * @memberof CalendarControlBase
     */
    public calendarClass: string = "calendar";

    /**
     * 日历默认打开样式
     * 
     * @type {string}
     * @memberof CalendarControlBase
     */
    public defaultView: string = 'dayGridMonth';

    /**
     * this引用
     *
     * @type {any}
     * @memberof CalendarControlBase
     */
    public thisRef: any = this;

    /**
     * 选中事件element元素
     *
     * @public
     * @type {any[]}
     * @memberof CalendarControlBase
     */
    public selectedEventElement:any;

    /**
     * 引用插件集合
     *
     * @public
     * @type {any[]}
     * @memberof CalendarControlBase
     */
    public calendarPlugins: any[] = [
        dayGridPlugin, 
        timeGridPlugin, 
        listPlugin, 
        interactionPlugin
    ];

    /**
     * 设置头部显示
     *
     * @public
     * @type {}
     * @memberof CalendarControlBase
     */
    public header: any = {
        left: 'prev,next today gotoDate',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    };

    /**
     * 按钮文本集合
     *
     * @public
     * @type {}
     * @memberof CalendarControlBase
     */
    public buttonText: any = {
        today: '今天',
        month: '月',
        week: '周',
        day: '天',
        list: '列'
    };

    /**
     * 自定义按钮集合
     *
     * @public
     * @type {}
     * @memberof CalendarControlBase
     */
    public customButtons: any = {
        gotoDate: {
          text: "跳转",
          click: this.openDateSelect
        }
    };

    /**
     * 模态显示控制变量
     *
     * @public
     * @type boolean
     * @memberof CalendarControlBase
     */
    public modalVisible: boolean = false;

    /**
     * 跳转日期
     *
     * @public
     * @type Date
     * @memberof CalendarControlBase
     */
    public selectedGotoDate: Date = new Date();

    /**
     * 日历部件动态参数
     * 
     * @type {any}
     * @memberof CalendarControlBase
     */
    public ctrlParams: any;

    /**
     * 打开时间选择模态
     *
     * @public
     * @memberof CalendarControlBase
     */
    public openDateSelect(){
        this.modalVisible = true;
    }

    /**
     * 跳转到指定时间
     *
     * @public
     * @memberof CalendarControlBase
     */
    public gotoDate(){
        let appCalendar: any = this.$refs[this.controlInstance?.codeName];
        let api = appCalendar?.getApi();
        if (api) {
            api.gotoDate(this.selectedGotoDate);
        }
    }

    /**
     * 有效日期范围
     *
     * @public
     * @type {}
     * @memberof CalendarControlBase
     */
    public validRange: any = {
        start:"0000-01-01",
        end:"9999-12-31"
    };

    /**
     * 默认加载日期
     *
     * @public
     * @type {}
     * @memberof CalendarControlBase
     */
    public defaultDate: any = Util.dateFormat(new Date());

    /**
     * 快速工具栏项
     * 
     * @type {Array<any>}
     * @memberof CalendarControlBase
     */
    public quickToolbarItems: Array<any> = [];

    /**
     * 快速工具栏模型
     * 
     * @type {*}
     * @memberof CalendarControlBase
     */
    public quickToolbarModels: any = {};

    /**
     * 设置按钮文本
     *
     * @public
     * @memberof CalendarControlBase
     */
    public setButtonText(){
        this.buttonText.today = this.$t('app.calendar.today'),
        this.buttonText.month = this.$t('app.calendar.month'),
        this.buttonText.week = this.$t('app.calendar.week'),
        this.buttonText.day = this.$t('app.calendar.day'),
        this.buttonText.list = this.$t('app.calendar.list')
        this.customButtons.gotoDate.text = this.$t('app.calendar.gotoDate')
    }

    /**
     * 监听语言变化
     *
     * @public
     * @memberof CalendarControlBase
     */
    @Watch('$i18n.locale')
    public onLocaleChange(newval: any, val: any) {
        this.setButtonText();
    }

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof CalendarControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal,oldVal);
    }

    /**
     * 监听部件参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof CalendarControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isSelectFirstDefault = newVal.isSelectFirstDefault ? true : false;
        super.onStaticPropsChange(newVal, oldVal)
    }

    /**
     * 部件模型数据初始化
     *
     * @memberof CalendarControlBase
     */
    public async ctrlModelInit(args?:any) {
        await super.ctrlModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppCalendarService(this.controlInstance);
            this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
        }
        this.initShowLegend();
        this.initActionModel();
        this.initQuickToolbar();
        this.calendarType = this.controlInstance?.calendarStyle;
        this.ctrlParams = this.controlInstance?.getPSControlParam()?.ctrlParams;
        if(Object.is(this.calendarType,'WEEK')){
            this.defaultView = 'timeGridWeek';
        } else if (Object.is(this.calendarType,'DAY')) {
            this.defaultView = 'timeGridDay';
        }
    }

    /**
     * 部件模型数据加载
     *
     * @memberof CalendarControlBase
     */
    public async ctrlModelLoad() {
        const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        if (calendarItems.length > 0) {
            for (const item of calendarItems) {
                await item.getPSAppDataEntity?.()?.fill?.();
                await item.getNavPSAppView?.()?.fill?.();
            }
        }
    }

    /**
     * 初始化日历项上下文菜单集合
     * 
     * @memberof CalendarControlBase
     */
    public initActionModel() {
        const calendarItems: Array<IPSSysCalendarItem> = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        let tempModel: any = {};
        if(calendarItems.length > 0) {
            calendarItems.forEach((item: IPSSysCalendarItem) => {
                const contextMenuItems: Array<IPSDEToolbarItem> = (item.getPSDEContextMenu() as IPSDEContextMenu)?.getPSDEToolbarItems() || [];
                if(contextMenuItems.length>0) {
                    contextMenuItems.forEach((toolbarItem: IPSDEToolbarItem) => {
                        this.initActionModelItem(toolbarItem,item,tempModel)
                    })
                }
            })
        }
        this.actionModel = {};
        Object.assign(this.actionModel, tempModel);
    }

    /**
     * 初始化上下菜单项
     * 
     * @param toolbarItem 
     * @param item 
     * @param tempModel 
     * @memberof CalendarControlBase
     */
    public initActionModelItem(toolbarItem: IPSDEToolbarItem,item: IPSSysCalendarItem,tempModel: any){
        let tempItem: any = {
            name: toolbarItem.name,
            nodeOwner: item.itemType
        }
        if (toolbarItem.itemType == 'DEUIACTION') {
            const uiAction: IPSDEUIAction = (toolbarItem as IPSDECMUIActionItem).getPSUIAction() as IPSDEUIAction;
            if (uiAction) {
                Object.assign(tempItem, {
                    type: item.itemType,
                    tag: uiAction.id,
                    visabled: true,
                    disabled: false,
                    actiontarget: uiAction.actionTarget,
                    noprivdisplaymode: uiAction.noPrivDisplayMode,
                    dataaccaction: uiAction.dataAccessAction
                })
            }
        }
        tempModel[`${item.itemType}_${toolbarItem.name}`] = tempItem;
        //TODO YY 上下文菜单子项
        // if(toolbarItem.getPSDEToolbarItems?.length > 0){
        //     for(let toolBarChild of toolbarItem.getPSDEToolbarItems){
        //         this.initActionModelItem(toolBarChild,item,tempModel)               
        //     }
        // }
    }

    /**
     * 初始化图例显示控制
     * 
     * @memberof CalendarControlBase
     */
    public initShowLegend() {
        if (Object.keys(this.isShowlegend).length > 0) {
            return;
        }
        const calendarItems: Array<IPSSysCalendarItem> = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        if(calendarItems.length>0) {
            calendarItems.forEach((item: IPSSysCalendarItem) => {
                this.isShowlegend[item.itemType as string] = true;
            })
        }
    }

    /**
     * 日程事件集合
     *
     * @public
     * @type {any[]}
     * @memberof CalendarControlBase
     */
    public events: any[] = [];

        /**
     * 日历项上下文菜单集合
     *
     * @type {string[]}
     * @memberof CalendarControlBase
     */
    public actionModel: any = {
        //TODO待补充
    }

    /**
     * 备份日历项上下文菜单
     *
     * @type {string[]}
     * @memberof CalendarControlBase
     */
     public copyActionModel: any;

    /**
     * 日历样式类型
     *
     * @public
     * @type {string}
     * @memberof CalendarControlBase
     */
    public calendarType: string = '';

    /**
     * 图例显示控制
     *
     * @public
     * @type {any}
     * @memberof CalendarControlBase
     */
    public isShowlegend: any = {};

    /**
     * 图例点击事件
     *
     * @public
     * @memberof CalendarControlBase
     */
    legendTrigger(itemType:string){
        const eventDisabled = this.$el.getElementsByClassName('event-disabled').length;
        if(Object.keys(this.isShowlegend).length != 1 && eventDisabled == Object.keys(this.isShowlegend).length -1 && this.isShowlegend[itemType]){
            return;
        }
        this.isShowlegend[itemType] = !this.isShowlegend[itemType];
        this.refresh();
    }

    /**
     * 查询参数缓存
     *
     * @public
     * @type {any}
     * @memberof CalendarControlBase
     */
    public searchArgCache: any = {};

    /**
     * 面板数据变化处理事件
     * @param {any} item 当前数据
     * @param {any} $event 面板事件数据
     *
     * @memberof CalendarControlBase
     */
    public onPanelDataChange(item:any,$event:any) {
        Object.assign(item, $event, {rowDataState:'update'});
    }

    /**
     * 搜索获取日程事件
     *
     * @param {*} $event 日期信息
     * @memberof CalendarControlBase
     */
    public searchEvents(fetchInfo?:any, successCallback?:any, failureCallback?:any ) {
        if (this.Environment && this.Environment.isPreviewMode) {
            this.events = [];
            return;
        }
        // 处理请求参数
        let start = (fetchInfo && fetchInfo.start) ? Util.dateFormat(fetchInfo.start) : null;
        let end = (fetchInfo && fetchInfo.end) ? Util.dateFormat(fetchInfo.end) : null;
        let arg = { start: start, end: end };
        if(fetchInfo && fetchInfo.query){
            Object.assign(arg,{query : fetchInfo.query});
        }
        const parentdata: any = {};
        this.$emit("ctrl-event", { controlname: this.controlInstance?.name, action: "beforeload", data: parentdata });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams?this.viewparams:'')));
        Object.assign(arg, { viewparams: tempViewParams });
        // 处理events数据
        let _this = this;
        let handleEvents = ()=>{
            if(_this.isSelectFirstDefault){
                // 模拟$event数据
                let tempEvent = JSON.parse(JSON.stringify(_this.events.length > 0?_this.events[0]:{}));
                _this.onEventClick(tempEvent,true);
                if(_this.events.length > 0){
                    _this.events[0].className = "select-first-event";
                }
                _this.calendarClass = "calendar select-first-calendar";
            }
            let filterEvents = this.events.filter((event:any)=>{
                return _this.isShowlegend[event.itemType];
            });
            if(successCallback){
                successCallback(filterEvents);
            }
            // 刷新日历的大小（仅fullcalendar组件使用）
            if(!Object.is(_this.calendarType,"TIMELINE") && !this.ctrlParams){
                let appCalendar: any = _this.$refs[this.controlInstance?.codeName];
                let api = appCalendar.getApi();
                if (api) {
                    api.updateSize();
                }
            }
        }
        if(JSON.stringify(arg) === JSON.stringify(this.searchArgCache)){
            handleEvents();
            return;
        }else{
            this.searchArgCache = arg;
        }
        const post: Promise<any> = this.service.search(this.loadAction, JSON.parse(JSON.stringify(this.context?this.context:'')), arg, this.showBusyIndicator);
        post.then((response: any) => {
            if (!response || response.status !== 200) {
                if (response.data && response.data.message) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                }
                return;
            }
            // 默认选中第一项
            this.events = response.data;
            handleEvents();
        }, (response: any) => {
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data && response.data.message ? response.data.message : "" });
        });
    }

    /**
     * 日期点击事件
     *
     * @param {*} $event 日期信息
     * @memberof CalendarControlBase
     */
    public onDateClick($event: any) {
        let date = $event.date;
        let datestr = $event.dateStr;
    }

    /**
     * 获取编辑视图信息
     *
     * @param {*} $event 事件信息
     * @memberof CalendarControlBase
     */
    public getEditView(deName: string) {
        let view: any = {};
        //TODO
        return view;
    }

    /**
     * 日程点击事件
     *
     * @param {*} $event calendar事件对象或event数据
     * @param {*} isOriginData true：$event是原始event数据，false：是组件
     * @param {*} $event timeline事件对象
     * @memberof CalendarControlBase
     */
    public onEventClick($event: any, isOriginData:boolean = false, $event2?: any) {
        // 处理event数据
        let event: any = {};
        if(isOriginData){
            event = JSON.parse(JSON.stringify($event));
        }else{
            event = Object.assign({title: $event.event.title, start: $event.event.start, end: $event.event.end}, $event.event.extendedProps);
        }
        // 点击选中样式
        let JSelement:any = null;
        if(!isOriginData && $event.el){
            JSelement = $event.el;
        }else if(isOriginData && $event2 && $event2.currentTarget){
            JSelement = $event2.currentTarget;
        }
        if(JSelement){
            this.calendarClass = "calendar";
            if(this.selectedEventElement){
                this.selectedEventElement.classList.remove("selected-event");
            }
            this.selectedEventElement = JSelement;
            this.selectedEventElement.classList.add("selected-event");
        }
        // 处理上下文数据
        let _this: any = this;
        let view: any = {};
        let _context: any = Object.assign({},this.context);
        let _viewparams:any = Object.assign({start:event.start,end:event.end},this.viewparams);
        const item: IPSSysCalendarItem = (((this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || []) as Array<IPSSysCalendarItem>).find((_item: IPSSysCalendarItem) => {
            return _item.itemType == event.itemType;
        }) as IPSSysCalendarItem;
        if(item) {
            const codeName = ((item.getPSAppDataEntity() as IPSAppDataEntity)?.codeName as string).toLowerCase();
            if(codeName) {
                _context[codeName] = event[codeName];
                view = this.getEditView(codeName);
            }
        }
        this.selections = [event];
        // 导航栏中不需要打开视图，只要抛出选中数据
        if(this.isSelectFirstDefault && event && (Object.keys(event).length >0)){
            this.$emit("ctrl-event", {controlname: this.controlInstance.name, action: "selectionchange", data: this.selections});
            return;
        }
        // 根据打开模式打开视图
        if(!view.viewname){
            return;
        } else if (Object.is(view.placement, 'INDEXVIEWTAB') || Util.isEmpty(view.placement)) {
            const routePath = ViewTool.buildUpRoutePath(_this.$route, this.context, view.deResParameters, view.parameters, [JSON.parse(JSON.stringify(_context))] , _viewparams);
            _this.$router.push(routePath);
        } else {
            let container: Subject<any> = new Subject();
            if (Object.is(view.placement, 'POPOVER')) {
                container = _this.$apppopover.openPop(isOriginData ? $event2 : $event.jsEvent, view,JSON.parse(JSON.stringify(_context)), _viewparams);
            } else if (Object.is(view.placement, 'POPUPMODAL')) {
                container = _this.$appmodal.openModal(view,  JSON.parse(JSON.stringify(_context)), _viewparams);
            } else if (view.placement.startsWith('DRAWER')) {
                container = _this.$appdrawer.openDrawer(view,  Util.getViewProps(_context, _viewparams));
            }
            container.subscribe((result: any) => {
                if (!result || !Object.is(result.ret, 'OK')) {
                    return;
                }
                // 刷新日历
                _this.refresh();
            });
        }
    }

    /**
     * 日历刷新
     *
     * @memberof CalendarControlBase
     */
    public refresh(args?:any) {
        if(Object.is(this.calendarType, 'TIMELINE')){
            this.searchEvents();
        } else if (this.ctrlParams) {
	        let calendarTimeLine: any = this.$refs['appCalendarTimeline'];
            if(calendarTimeLine){
                calendarTimeLine.refetchEvents();
            }
        } else {
            let calendarApi = (this.$refs[this.controlInstance?.codeName] as any).getApi();
            calendarApi.refetchEvents();
        }
        this.$forceUpdate();
    }

    /**
     * 日程拖动事件
     *
     * @param {*} $event 事件信息
     * @memberof CalendarControlBase
     */
    public onEventDrop($event: any) {
        if(this.isSelectFirstDefault){
          return;
        }
        let arg: any = {};
        let _context: any = Object.assign({},this.context);
        arg.start = Util.dateFormat($event.event.start);
        arg.end = Util.dateFormat($event.event.end);
        let itemType = $event.event._def.extendedProps.itemType;
        const item: IPSSysCalendarItem = (((this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || []) as Array<IPSSysCalendarItem>).find((_item: IPSSysCalendarItem) => {
            return _item.itemType == itemType;
        }) as IPSSysCalendarItem;
        if(item) {
            const codeName = ((item.getPSAppDataEntity() as IPSAppDataEntity)?.codeName as string).toLowerCase();
            if(codeName) {
                arg[codeName] = $event.event._def.extendedProps[codeName];
                _context[codeName] = $event.event._def.extendedProps[codeName];
            }
        }
        Object.assign(arg,{viewparams:this.viewparams});
        const post: Promise<any> = this.service.update(itemType, JSON.parse(JSON.stringify(_context)), arg, this.showBusyIndicator);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response || response.status !== 200) {
                if (response.data && response.data.message) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                }
                return;
            }
        }, (response: any) => {
            this.ctrlEndLoading();
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data && response.data.message ? response.data.message : "" });
        });
    }

    /**
     * 选中的数据
     *
     * @returns {any[]}
     * @memberof CalendarControlBase
     */
    public selections: any[] = [];

    /**
     * 应用状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof CalendarControlBase
     */
    public appStateEvent: Subscription | undefined;

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof CalendarControlBase
     */
    public getDatas(): any[] {
        return this.selections;
    }

    /**
     * 获取单项数据
     *
     * @returns {*}
     * @memberof CalendarControlBase
     */
    public getData(): any {
        return null;
    }

    /**
     * 日历视图部件初始化
     *
     * @memberof CalendarControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        if (Object.is(this.calendarType, 'TIMELINE')) {
            this.searchEvents();
        } else {
            this.setButtonText();
        }
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
            });
        }
    }

    /**
     * 部件挂载
     *
     * @memberof CalendarControlBase
     */
    public ctrlMounted(){
        super.ctrlMounted();
        let appCalendar: any = this.$refs[this.controlInstance?.codeName];
        if(appCalendar){
            let api = appCalendar.getApi();
            api.updateSize()
        }
    }

    //快速工具栏
    public quickToolbar: any;

    /**
     * 计算节点右键权限
     *
     * @param {*} data 日历项数据
     * @param {*} appEntityName 应用实体名称  
     * @returns
     * @memberof CalendarControlBase
     */
    public async computeNodeState(data:any,appEntityName:string) {
        let service = await new GlobalService().getService(appEntityName);
        if(this.copyActionModel && Object.keys(this.copyActionModel).length > 0) {
            if(service['Get'] && service['Get'] instanceof Function){
                let tempContext:any = Util.deepCopy(this.context);
                tempContext[appEntityName] = data[appEntityName];
                let targetData = await service.Get(tempContext,{}, false);
                ViewTool.calcTreeActionItemAuthState(targetData.data,this.copyActionModel,this.appUIService);
                return this.copyActionModel;
            }else{
                console.warn("获取数据异常");
                return this.copyActionModel;
            }
        }
        return this.copyActionModel;
    }

    /**
     * 事件绘制回调
     *
     * @param {*} info 信息
     * @memberof CalendarControlBase
     */
    public eventRender(info?:any,) {
        let data = Object.assign({title: info.event.title, start: info.event.start, end: info.event.end}, info.event.extendedProps);
        info.el.addEventListener('contextmenu', (event: MouseEvent) => {
            this.copyActionModel = {};
            Object.values(this.actionModel).forEach((item:any) =>{
                if(Object.is(item.nodeOwner,data.itemType)){
                    this.copyActionModel[item.name] = item;
                }
            })
            if(Object.keys(this.copyActionModel).length === 0){
                return;
            }
            let dataMapping: IPSSysCalendarItem = (((this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || []) as Array<IPSSysCalendarItem>).find((_item: IPSSysCalendarItem) => {
                return _item.itemType == data.itemType;
            }) as IPSSysCalendarItem;
            const appDECodeName = (dataMapping.getPSAppDataEntity() as IPSAppDataEntity)?.codeName as string;
            if(!appDECodeName){
                return;
            }
            this.computeNodeState(data, appDECodeName).then((result:any) => {
                let flag: boolean = false;
                if(Object.values(result).length>0){
                    flag = Object.values(result).some((item:any) =>{
                        return item.visabled === true;
                    })
                }
                if(flag){
                    let props = { data: data, renderContent: this.renderContextMenu };
                    let component = ContextMenu;
                    const vm:any = new Vue({
                        render(h) {
                            return h(component, { props });
                        }
                    }).$mount();
                    document.body.appendChild(vm.$el);
                    const comp: any = vm.$children[0];
                    comp.showContextMenu(event.clientX, event.clientY);
                }
            });
        });
    }

    public menuToolbar: Array<any> = [];

    /**
     * 绘制右键菜单
     *
     * @param {*} event
     * @returns
     * @memberof CalendarControlBase
     */
    public renderContextMenu(event: any) {
        if(!event || !event.itemType) {
            return null;
        }
        this.selections = [event];
        const calendarItems: Array<IPSSysCalendarItem> = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        if(calendarItems.length>0) {
            let item: IPSSysCalendarItem = calendarItems.find((_item: IPSSysCalendarItem) => {
                return _item.itemType == event.itemType;
            }) as IPSSysCalendarItem;
            const contextMenu = item.getPSDEContextMenu() as IPSDEContextMenu;
            if(contextMenu && contextMenu.controlType == "CONTEXTMENU") {
                let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(contextMenu);
                targetCtrlParam.dynamicProps.contextMenuActionModel = this.copyActionModel;
                return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: contextMenu.name, on: targetCtrlEvent });
            }
        }
        return null;
    }

    /**
     * 菜单项点击
     * 
     * @memberof CalendarControlBase
     */
    public itemClick(tag: any, $event: any) {
        AppViewLogicService.getInstance().executeViewLogic(`calendar_quicktoolbar_${tag}_click`, $event, this, {}, this.controlInstance.getPSAppViewLogics() || []);
    }


    /**
     * 时间点击
     *
     * @param {*} $event 当前时间
     * @param {*} jsEvent 原生事件对象  
     * @returns
     * @memberof CalendarControlBase
     */
    public onDayClick($event: any, jsEvent: any) {
        let _this: any = this;
        let content: any = this.renderBarMenu;
        const container: any = _this.$apppopover.openPopover(jsEvent, content, "left-end", true, 103, _this.quickToolbarItems.length * 34);
    }

    /**
     * 初始化快速工具栏
     *
     * @memberof CalendarControlBase
     */
    public initQuickToolbar() {
        const quickToolbaItems: Array<IPSDEToolbarItem> = (ModelTool.findPSControlByType('QUICKTOOLBAR', this.controlInstance.getPSControls() || []) as IPSDEToolbar)?.getPSDEToolbarItems() || [];
        if(quickToolbaItems.length > 0) {
            let items: Array<any> = quickToolbaItems.filter((item: IPSDEToolbarItem) => {
                return item.itemType == "DEUIACTION";
            });
            this.quickToolbarItems = [...items];
        }
        this.initQuickToolbarItemModel();
    }


    /**
     * 初始化快速工具栏模型
     *
     * @memberof CalendarControlBase
     */
    public initQuickToolbarItemModel() {
        const items: Array<IPSDEToolbarItem> = this.quickToolbarItems;
        let models: any = {};
        if(items.length>0) {
            items.forEach((_item: IPSDEToolbarItem) => {
                const item: IPSDETBUIActionItem = _item as IPSDETBUIActionItem;
                const uiAction: IPSDEUIAction = item.getPSUIAction() as IPSDEUIAction;
                let model: any = {};
                Object.assign(model, {
                    name: item.name?.toLowerCase(),
                    actiontarget: "NONE",
                    disabled: false,
                    visabled: true,
                    type: item.itemType,
                    noprivdisplaymode: uiAction?.noPrivDisplayMode,
                    dataaccaction: uiAction?.dataAccessAction,
                    uiaction: {
                        tag: uiAction?.uIActionTag ? uiAction.uIActionTag : uiAction.id ? uiAction.id : '',
                        target: uiAction?.actionTarget
                    }
                });
                Object.assign(models, { [`${item.name?.toLowerCase()}`]: model });
            })
        }
        this.quickToolbarModels = {};
        Object.assign(this.quickToolbarModels, models);
    }


    /**
     * 绘制快速工具栏项
     * 
     * @returns
     * @memberof CalendarControlBase
     */
    public renderBarMenu() {
        return (
            <div class="calendar-popover">
                <dropdown 
                    class="quick-toolbar"
                    trigger="custom"
                    visible={true}
                    on-on-click={($event: any, $event2: any) => this.itemClick($event, $event2)}>
                        <dropdown-menu slot="list">
                            {this.quickToolbarItems.map((item: any) => {
                                return (
                                    <dropdown-item
                                        name={item.name}
                                        v-show={this.quickToolbarModels[item.name].visabled}
                                        disabled={this.quickToolbarModels[item.name].disabled}>
                                            {item.showIcon && item.getPSSysImage ? 
                                                <i class={item.getPSSysImage?.cssClass}></i> : null}
                                            {item.showCaption ? 
                                                <span title={item.tooltip}>{item.caption}</span> : null}
                                    </dropdown-item>
                                );
                            })}
                        </dropdown-menu>
                </dropdown>
            </div>
        );
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof CalendarControlBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if(action == 'contextMenuItemClick'){
            AppViewLogicService.getInstance().executeViewLogic(`${controlname}_${data}_click`, undefined, this, {}, this.controlInstance?.getPSAppViewLogics() || []);
        }else{
            this.ctrlEvent({ controlname, action, data });
        }
    }

}
