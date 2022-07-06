import { Vue, Component, Watch } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Util, ViewTool, ModelTool, MobCalendarControlInterface } from 'ibiz-core';
import { MDControlBase } from './md-control-base';
import { GlobalService } from 'ibiz-service';
import { AppMobCalendarService } from '../ctrl-service';
import { AppViewLogicService } from '../app-service';
import { ViewOpenService } from '../app-service/common-service/view-open-service';
import { IPSAppDataEntity, IPSAppDEMobCalendarView, IPSDECalendar, IPSDECMUIActionItem, IPSDEContextMenu, IPSDETBUIActionItem, IPSDEToolbar, IPSDEToolbarItem, IPSDEUIAction, IPSSysCalendar, IPSSysCalendarItem, IPSAppViewRef, IPSAppDEView } from '@ibiz/dynamic-model-api';

import moment from 'moment';
@Component({
    components: {

    }
})
export class MobCalendarControlBase extends MDControlBase implements MobCalendarControlInterface{

    /**
     * 数据视图模型实例
     * 
     * @type {*}
     * @memberof MobCalendarControlBase
     */
    public controlInstance!: IPSDECalendar;

    /**
     * 模型数据是否加载完成
     * 
     * @memberof MobCalendarControlBase
     */
    public controlIsLoaded:boolean = false;

    /**
     * 显示处理提示
     * 
     * @type {boolean}
     * @memberof MobCalendarControlBase
     */
    public showBusyIndicator: boolean = true;

    /**
     * 当前年份
     *
     * @type {string}
     * @memberof MobCalendarControlBase
     */
    protected year: number = 0;

    /**
     * 当前月份(0~11)
     *
     * @type {string}
     * @memberof MobCalendarControlBase
     */
    protected month: number = 0;

    /**
     * 开始时间
     *
     * @type {*}
     * @memberof MobCalendarControlBase
     */
    protected start: any;

    /**
     * 标志数据
     * @type {Array<any>}
     * @memberof MobCalendarControlBase
     */
    public sign: Array<any> = []
    
    /**
     * 结束时间
     *
     * @type {*}
     * @memberof MobCalendarControlBase
     */
    protected end: any;

    /**
     * 当前日期
     * @type {*}
     * @memberof MobCalendarControlBase
     */
    protected currentDate:any = new Date();

    /**
     * 当前天
     *
     * @type {string}
     * @memberof MobCalendarControlBase
     */
    protected day: number = 0;

    /**
     * 标记数组
     *
     * @type {Array<any>}
     * @memberof MobCalendarControlBase
     */
    protected markDate: Array<any> = [];

    /**
     * 事件时间
     *
     * @memberof MobCalendarControlBase
     */
    public eventsDate :any = {};

    /**
     * 日历项集合对象
     *
     * @type {*}
     * @memberof MobCalendarControlBase
     */
    public calendarItems: any = {};

    /**
     * 应用状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof MobCalendarControlBaseBase
     */
    public appStateEvent: Subscription | undefined;

    /**
     * 日历数据项模型
     *
     * @type {Map<string, any>}
     * @memberof MobCalendarControlBase
     */
    public calendarItemsModel: Map<string, any> = new Map([]);

    /**
     * 初始化日历数据项模型
     *
     * @memberof MobCalendarControlBase
     */    
    public initCalendarItemsModel(){
      const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
      if (calendarItems.length > 0) {   
          calendarItems.forEach((calendarItem:IPSSysCalendarItem)=>{
            const _appde = calendarItem.getPSAppDataEntity();
            const idField = calendarItem.getIdPSAppDEField();
            const keyField = ModelTool.getAppEntityKeyField(_appde);
            const majorField = ModelTool.getAppEntityMajorField(_appde);
            let itemType = calendarItem.itemType.toLowerCase();
            let obj = {
              appde: _appde?.codeName?.toLowerCase(),
              keyPSAppDEField: idField?.codeName ? idField.codeName.toLowerCase() : keyField?.codeName?.toLowerCase(),
              majorPSAppDEField : majorField ? majorField?.codeName.toLowerCase() : 'srfmajortext'
            }
            this.calendarItemsModel.set(itemType,obj);
          })
      }
    }

    /**
     * 部件模型数据加载
     *
     * @memberof MobCalendarControlBase
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
     * 日历显示状态
     *
     * @memberof MobCalendarControlBase
     */
    public show = false;

    /**
     *  选中日期
     * 
     * @type {Array<any>}
     * @memberof MobCalendarControlBase
     */
    protected tileContent: Array<any> = [];

    /**
     *  默认选中
     * 
     * @type {Array<any>}
     * @memberof MobCalendarControlBase
     */
    protected value: Array<any> = [];

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof MobCalendarControlBase
     */
    public getDatas(): any[] {
      return [];
    }
    
    /**
     * 时间轴加载条数
     *
     * @type {arr}
     * @memberof MobCalendarControlBase
     */  
    public count :Array<any> = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20];

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof MobCalendarControlBase
     */
    public getData(): any {
      return {};
    }

    /**
     * 日历部件样式名
     *
     * @public
     * @type {any[]}
     * @memberof MobCalendarControlBase
     */
    public calendarClass: string = "app-mob-calendar";

    /**
     * this引用
     *
     * @type {any}
     * @memberof MobCalendarControlBase
     */
    public thisRef: any = this;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MobCalendarControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal,oldVal);
    }

    /**
     * 监听部件参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MobCalendarControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isSelectFirstDefault = newVal.isSelectFirstDefault ? true : false;
        this.isChoose = newVal.isChoose ? true : false;
        super.onStaticPropsChange(newVal, oldVal)
    }

    /**
     * 部件模型数据初始化
     *
     * @memberof MobCalendarControlBase
     */
    public async ctrlModelInit(args?:any) {
        await super.ctrlModelInit();
        if (!(this.Environment?.isPreviewMode)) {
            this.service = new AppMobCalendarService(this.controlInstance);
            this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
        }
        this.initActionModel();
        this.calendarStyle = (this.controlInstance as IPSSysCalendar).calendarStyle;
        this.initEvendata();
        this.initActiveItem();
        this.initCalendarItemsModel();
        this.initIllustration();
    }

    /**
     * 初始化日历项上下文菜单集合
     * 
     * @memberof MobCalendarControlBase
     */
    public initActionModel() {
        const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        let tempModel: any = {};
        if(calendarItems?.length>0) {
            calendarItems.forEach((item: IPSSysCalendarItem) => {
                if(item.getPSDEContextMenu() && item.getPSDEContextMenu()?.getPSDEToolbarItems()) {
                    const toobarItems = item.getPSDEContextMenu()?.getPSDEToolbarItems() as IPSDEToolbarItem[];
                    if (toobarItems?.length > 0) {
                      toobarItems.forEach((toolbarItem: IPSDEToolbarItem) => {
                          this.initActionModelItem(toolbarItem,item,tempModel)
                      })
                    }
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
     * @memberof MobCalendarControlBase
     */
    public initActionModelItem(toolbarItem: IPSDEToolbarItem,item: IPSSysCalendarItem,tempModel: any){
        let tempItem: any = {
            name: toolbarItem.name,
            nodeOwner: item.itemType
        }
        if(toolbarItem.itemType == 'DEUIACTION') {
          const uiAction: IPSDEUIAction = (toolbarItem as IPSDECMUIActionItem).getPSUIAction() as IPSDEUIAction;
            tempItem.type = item.itemType;
            tempItem.tag = uiAction.id;
            tempItem.visabled = true;
            tempItem.disabled = false;
            if(uiAction?.actionTarget && uiAction?.actionTarget != ""){
                tempItem.actiontarget = uiAction.actionTarget
            }
            if(uiAction.noPrivDisplayMode) {
                tempItem.noprivdisplaymode = uiAction.noPrivDisplayMode;
            }
            if(uiAction.dataAccessAction) {
                tempItem.dataaccaction = uiAction.dataAccessAction;
            }
        }
        tempModel[`${item.itemType}_${toolbarItem.name}`] = tempItem;
        const toolbarItems = (toolbarItem as IPSDETBUIActionItem)?.getPSDEToolbarItems() || [];
        if(toolbarItems?.length > 0){
            for(let toolBarChild of toolbarItems){
                this.initActionModelItem(toolBarChild,item,tempModel)               
            }
        }
    }

    /**
     * 日程事件集合
     *
     * @public
     * @type {any[]}
     * @memberof MobCalendarControlBase
     */
    public events: any[] = [];

    /**
     * 日历样式类型
     *
     * @public
     * @type {string}
     * @memberof MobCalendarControlBase
     */
    public calendarStyle: string = '';

    /**
     * 日历视图部件初始化
     *
     * @memberof MobCalendarControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        this.initcurrentTime();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }:any) => {
                if (!Object.is(this.name, tag)) {
                    return;
                }
                if (Object.is(action, "load")) {
                    this.formatData(this.currentDate, data);
                }
            });
        }
    }

    /**
     * 事件绘制数据
     *
     * @memberof MobCalendarControlBase
     */
    public evendata :any = {}

    /**
     * 初始化事件绘制数据
     *
     * @memberof MobCalendarControlBase
     */
    public initEvendata(){
      const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
      let tempEvendata: any = {};
      if(calendarItems?.length>0) {
          calendarItems.forEach((item: IPSSysCalendarItem) => {
            tempEvendata[item.itemType.toLowerCase()] = []
          })
      }
      this.evendata = {};
      Object.assign(this.evendata, tempEvendata);
    }

    /**
     * 图标信息
     *
     * @memberof MobCalendarControlBase
     */
    public illustration:any = []

    /**
     * 初始化图标信息
     *
     * @memberof MobCalendarControlBase
     */    
    public initIllustration(){
      const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
      if(calendarItems?.length>0) {
        calendarItems.forEach((item: IPSSysCalendarItem) => {
            let tempIllustration: any = {};
            tempIllustration['color'] = item.bKColor;
            tempIllustration['text'] = item.name;
            this.illustration.push(tempIllustration);
          })
      }      
    }

    /**
     * 激活项
     *
     * @type {string}
     * @memberof MobCalendarControlBase
     */
    protected activeItem: string = '';

    /**
     * 初始化激活项
     *
     * @memberof MobCalendarControlBase
     */    
    public initActiveItem(){
      const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
      if (calendarItems && calendarItems[0]) {
        this.activeItem = calendarItems[0].itemType.toLowerCase();
      }
    }

    /**
     * 分页节点切换
     *
     * @param {*} $event
     * @returns
     * @memberof MobCalendarControlBase
     */
    public ionChange($event:any) {
        let { detail: _detail } = $event;
        if (!_detail) {
            return ;
        }
        let { value: _value } = _detail;
        if (!_value) {
            return ;
        }
        this.activeItem = _value;
    }

    /**
     * 查询天数
     *
     * @memberof MobCalendarControlBase
     */
    protected selectday(year: any, month: any, weekIndex: any) {
        this.value = [year, month, this.day];
    }

    /**
     * 上一月事件的回调方法
     *
     * @memberof MobCalendarControlBase
     */
    public prev(year: any, month: any, weekIndex: any) {
        if(this.calendarStyle == "MONTH_TIMELINE" || this.calendarStyle == "MONTH"){
            this.selectday(year, month, this.day);
            this.formatData(new Date(year+'/'+month+'/'+'1'));
        }
        if(this.calendarStyle == "WEEK_TIMELINE" || this.calendarStyle == "WEEK"){
            this.countWeeks(year,month,weekIndex);
        }
    }

    /**
     * 下一月事件的回调方法
     *
     * @memberof MobCalendarControlBase
     */
    public next(year: any, month: any, weekIndex: any) {
        if(this.calendarStyle == "MONTH_TIMELINE" || this.calendarStyle == "MONTH" ){
            this.selectday(year, month, this.day);
            this.formatData(new Date(year+'/'+month+'/'+'1'));
        }
        if(this.calendarStyle == "WEEK_TIMELINE" || this.calendarStyle == "WEEK"){
            this.countWeeks(year,month,weekIndex);
        }
    }

    /**
     * 删除
     *
     * @param {any[]} datas
     * @returns {Promise<any>}
     * @memberof MobCalendarControlBase
     */
    public async remove(datas: any[]): Promise<any> {
        const calendarItemModel: any = this.calendarItemsModel.get(this.activeItem);
        let { appde, keyPSAppDEField, majorPSAppDEField }: { appde: string, keyPSAppDEField: string, majorPSAppDEField: string } = calendarItemModel;
        let arg: any = {};
        let keys: Array<string> = [];
        let infoStr: string = '';
        datas.forEach((data: any, index: number) => {
            keys.push(data[keyPSAppDEField]);
            if (index < 5) {
                if (!Object.is(infoStr, '')) {
                    infoStr += '、';
                }
                infoStr += data[majorPSAppDEField];
            }
        });

        if (datas.length < 5) {
            infoStr = infoStr + this.$t('app.message.totle') + datas.length + this.$t('app.message.data');
        } else {
            infoStr = infoStr + '...' + this.$t('app.message.totle') + datas.length + this.$t('app.message.data');
        }
        return new Promise((resolve, reject) => {
            const _remove = async () => {
                let _context: any = { [appde]: keys.join(';') }
                const response: any = await this.service.delete(this.activeItem, { ...this.context, ..._context }, arg, this.showBusyIndicator);
                if (response && response.status === 200) {
                    this.$Notice.success((this.$t('app.message.deleteSccess') as string));
                    this.formatData(this.currentDate);
                    resolve(response);
                } else {
                    const { error: _data } = response;
                    this.$Notice.error(_data?.message);
                    reject(response);
                }
            }

            this.$dialog.confirm({
                title: (this.$t('app.message.warning') as string),
                message: this.$t('app.message.confirmToDelete') + infoStr +','+ this.$t('app.message.unrecoverable') + '？',
            }).then(() => {
                _remove();
            }).catch(() => {
            });
        });
    }

    /**
     * 选择年份事件的回调方法
     *
     * @memberof MobCalendarControlBase
     */
    public selectYear(year: any) {
      this.value = [year, this.month, this.day];
      this.formatData(new Date(year+'/'+this.month+'/'+this.day));
    }

    /**
     * 选择月份事件的回调方法
     *
     * @memberof MobCalendarControlBase
     */
    public selectMonth(month: any, year: any) {
      this.selectday(year, month, this.day);
      this.formatData(new Date(year+'/'+month+'/'+this.day));
    }


    /**
     * 初始化当前时间
     *
     * @memberof MobCalendarControlBase
     */
    protected initcurrentTime() {
      let tempTime = new Date();
      this.year = tempTime.getFullYear();
      this.month = tempTime.getMonth();
      this.day = tempTime.getDate();
    }

    /**
     * 格式化数据
     *
     * @memberof MobCalendarControlBase
     */
    protected formatData(curtime:any,data: any = {}) {
        this.currentDate = curtime;
        this.year = curtime.getFullYear();
        this.month = curtime.getMonth();
        this.day = curtime.getDate();
        switch (this.controlInstance.calendarStyle) {
          case 'DAY':
            this.start = (moment as any)(curtime).startOf('day').format('YYYY-MM-DD HH:mm:ss');
            this.end = (moment as any)(curtime).endOf('day').format('YYYY-MM-DD HH:mm:ss');
            break;
          case 'MONTH':
          case 'MONTH_TIMELINE':
            this.start = (moment as any)(curtime).startOf('month').format('YYYY-MM-DD HH:mm:ss');
            this.end = (moment as any)(curtime).endOf('month').format('YYYY-MM-DD HH:mm:ss');
            break;
          case 'WEEK':
          case 'WEEK_TIMELINE':
            this.start = (moment as any)(curtime).startOf('week').format('YYYY-MM-DD HH:mm:ss');
            this.end = (moment as any)(curtime).endOf('week').format('YYYY-MM-DD HH:mm:ss');
            break;
          default:
            break;
        }
        if(this.Environment?.isPreviewMode){
          this.show = true;
          return 
        }
        this.load(Object.assign(data, {
          "start": this.start,
          "end": this.end
        }));
    }

    /**
     * 点击前一天
     * @memberof MobCalendarControlBase
     */
    public prevDate(){ 
        let preDate = new Date(this.currentDate.getTime() - 24*60*60*1000); //前一天
        this.formatData(preDate);
    }

    /**
     * 点击后一天
     * @memberof MobCalendarControlBase
     */
    public nextDate(){
        let nextDate = new Date(this.currentDate.getTime() + 24*60*60*1000); //后一天
        this.formatData(nextDate);
    }

    /**
     * 数据加载
     *
     * @protected
     * @param {*} [opt={}]
     * @returns {Promise<any>}
     * @memberof MobCalendarControlBase
     */
    protected async load(opt: any = {},isSetTileContent:boolean=true): Promise<any> {
        const arg: any = { ...opt };
        this.onControlRequset('load', { ...this.context },  { ...arg });
        const response: any = await this.service.search(this.activeItem, { ...this.context }, { ...arg }, this.showBusyIndicator);
        if (response && response.status === 200) {
            this.onControlResponse('load',response)
            this.calendarItems = response.data;
            isSetTileContent?this.setTileContent():"";
        } else {
            this.onControlResponse('load',response)
            this.$Notice.error(this.$t('app.error.systemErrorRetry'));
        }
        this.show = true;
    }

    /**
     * 设置事件数组
     *
     * @protected
     * @memberof MobCalendarControlBase
     */
    protected setTileContent(){
        const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        let signData:any[] = [];
        calendarItems.forEach((calendaritem:IPSSysCalendarItem)=>{
          const getBeginTimePSAppDEField = calendaritem.getBeginTimePSAppDEField();
          let itemType = calendaritem?.itemType?.toLowerCase();
          let beginTime = getBeginTimePSAppDEField?.name ? getBeginTimePSAppDEField.name?.toLowerCase() : 'start';
          let itemData = this.parsingData(itemType,beginTime)
          signData.push(...itemData);
        })
        this.setSign(signData)
    }

    /**
     * 格式化标志数据
     * 
     * @param any 
     * @memberof MobCalendarControlBase
     */
    public setSign(signData:any){
        let obj: any = {}
        this.sign.length = 0;
        // 格式化数据
        signData.forEach((item:any,index:number) => {
            if(item.time && item.time.length == 10){
              let year = item.time.split('-')[0];
              let month = item.time.split('-')[1];
              let day = item.time.split('-')[2];
              if( month < 10 ){
                month = month.replace('0','')
              }
              if(day < 10){
                day = day.replace('0','')
              }
              item.time = year+'-'+month+'-'+day;
            }
            if(!obj[item.time]){
              Object.assign(obj,{[item.time]:item.evens})
            }else{
              obj[item.time].push(item.evens[0])
            }
        });
        for (const key in obj) {
          this.sign.push({time:key,evens:obj[key]});
        }
    }

    /**
     * 解析日历事件数据
     *
     * @param {string} tag
     * @param {string} mark
     * @returns {Array<any>}
     * @memberof MobCalendarControlBase
     */
    public parsingData(tag: string, mark: string): Array<any> {
        let dataItem: any = [];
        if (this.calendarItems[tag]) {
            this.calendarItems[tag].forEach((item: any) => {
                if (dataItem.length == 0) {
                    dataItem.push({ time: item[mark]?.substring(0, 10), evens: [item] });
                } else {
                    let flag = dataItem.every((currentValue:any)=>{
                        return (currentValue.time !== item[mark]?.substring(0, 10))
                    })
                    if(flag){
                        dataItem.push({ time: item[mark]?.substring(0, 10), evens: [item] });
                    }
                }
            });
        }
        return dataItem;
    }

    /**
     * 日历部件数据选择日期回调
     *
     * @param any 
     * @memberof MobCalendarControlBase
     */
    public clickDay(data: any) {
      if (data) {
        let reTime = data.join('/');
        let temptime = new Date(reTime);
        this.year = temptime.getFullYear();
        this.month = temptime.getMonth();
        this.day = temptime.getDate();
        this.start = (moment as any)(temptime).startOf('day').format('YYYY-MM-DD HH:mm:ss');
        this.end = (moment as any)(temptime).endOf('day').format('YYYY-MM-DD HH:mm:ss');
        this.load(Object.assign(this.viewparams, { "start": this.start, "end": this.end }),false);
      }
    }    

    /**
     * 获取编辑视图信息
     *
     * @param {*} $event 事件信息
     * @memberof MobCalendarControlBase
     */
    protected async getEditView(deName: string) {
        let view: any = {};
        const $view = this.controlInstance.getParentPSModelObject() as IPSAppDEMobCalendarView;
        const viewRefs = $view.getPSAppViewRefs() as IPSAppViewRef[];
        if (viewRefs?.length > 0) {
            for (let i = 0; i < viewRefs.length; i++) {
              const viewRef = viewRefs[i].getRefPSAppView();
              await viewRef?.fill();
            }
            const editviewRef = viewRefs.find((viewRef:IPSAppViewRef)=>{
              return deName == viewRef?.getRefPSAppView()?.getPSAppDataEntity()?.codeName.toLowerCase();
            })
            const editview = editviewRef?.getRefPSAppView();
            if (!editview) {
              return
            }
            const tempView:any = {};
            tempView.viewname = Util.srfFilePath2(editview.codeName);
            tempView.placement = editview.openMode ? editview.openMode : '';
            tempView.deResParameters = Util.formatAppDERSPath(this.context,(editview as IPSAppDEView).getPSAppDERSPaths());
            if (editview.getPSAppDataEntity) {
              const appDataEntity = editview.getPSAppDataEntity() as IPSAppDataEntity;
              tempView.parameters= [
                  { pathName: Util.srfpluralize(appDataEntity?.codeName).toLowerCase(), parameterName: appDataEntity?.codeName.toLowerCase() },
                  { pathName: (editview as IPSAppDEView).getPSDEViewCodeName()?.toLowerCase(), parameterName: (editview as IPSAppDEView).getPSDEViewCodeName()?.toLowerCase() },
              ]
            } else {
              tempView.parameters = [
                  { pathName: editview?.codeName.toLowerCase(), parameterName: editview?.codeName.toLowerCase() },
              ];
            }
            view = tempView;
        }
        return view;
    }

    /**
     * 日程点击事件
     *
     * @param {*} $event 事件信息
     * @memberof MobCalendarControlBase
     */
    public async onEventClick($event: any): Promise<any> {
        const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        let itemType = $event.itemType.toLowerCase();
        const calendarItem = calendarItems.find((item:IPSSysCalendarItem)=>{return itemType == item.itemType.toLowerCase()});
        if (!calendarItem) {
          return
        }
        let appde = calendarItem.getPSAppDataEntity()?.codeName.toLowerCase() as string;
        this.context[appde] = $event[appde];
        const view = await this.getEditView(appde);
        if (Object.is(view.placement, 'INDEXVIEWTAB') || Object.is(view.placement, '')) {
            const path: string = ViewTool.buildUpRoutePath(this.$route, {}, view.deResParameters, view.parameters, [$event], this.viewparams);
            if (Object.is(this.$route.fullPath, path)) {
                return;
            }
            this.$nextTick(() => {
                this.$router.push(path);
            })

        } else {
            let response: any;
            if (Object.is(view.placement, 'POPUPMODAL')) {
                response = await this.$appmodal.openModal(view,  this.context,  { ...this.viewparams });
            } else if (view.placement && view.placement.startsWith('DRAWER')) {
                response = await this.$appdrawer.openDrawer(view,  this.context,  { ...this.viewparams });
            }
            if (response && Object.is(response.ret, 'OK')) {
                // 刷新日历
                this.load(Object.assign(this.viewparams, { "start": this.start, "end": this.end }),false);
            }
        }
    }

    /**
     * 根据周下标计算事件
     *
     * @param {*} $event 事件信息
     * @memberof MobCalendarControlBase
     */
    public countWeeks(year: any, month: any, week: any) {
      let date = new Date(year + '/' + month + '/' + 1);
      let weekline = date.getDay();
      if(weekline == 0){
        this.formatData(new Date(year + '/' + month + '/' + (week*7 +1)));
      }else{
        this.formatData(new Date(year + '/' + month + '/' + (week*7 - weekline + 1)));
      }
  }

   /**
     * 是否展示多选
     *
     * @memberof MobCalendarControlBase
     */
    public isChoose: boolean = false;

  
    /**
    * 选中数组
    *
    * @param {Array<any>}
    * @memberof MobCalendarControlBase
    */
    public selectedArray:Array<any> = [];
    
    /**
     * 选中或取消事件
     *
     * @memberof MobCalendarControlBase
     */
    public checkboxSelect(item:any){
        let count = this.selectedArray.findIndex((i) => {
            return i.mobile_entity1id == item.mobile_entity1id;
        });
        if(count == -1){
            this.selectedArray.push(item);
        }else{
            this.selectedArray.splice(count,1);
        }
    }

    /**
     * 开始拖动位置
     *
     * @memberof MobCalendarControlBase
     */
    public StarttouchLength = 0;

    /**
     * 开始滑动
     *
     * @memberof MobCalendarControlBase
     */
    public gotouchstart(e:any){
        let _this = this;
        let touch :any=e.touches[0];   
        let startY = touch.pageY; 
        this.StarttouchLength = startY;
    }

    /**
     * touchmove
     *  
     *
     * @memberof MobCalendarControlBase
     */
    public gotouchmove(e:any) {
        let touch :any=e.touches[0];   
        let startY = touch.pageY; 
        let calendar:any = this.$refs.calendar2;
        if(calendar){
            if(startY-this.StarttouchLength<0){
                calendar.changeStyle2(false);
            }else{
                calendar.changeStyle2(true);
            }
        }
    }    


    /**
     * 部件挂载
     *
     * @memberof MobCalendarControlBase
     */
    public ctrlMounted(){
        super.ctrlMounted();
        let appCalendar: any = this.$refs[this.controlInstance.codeName];
        if(appCalendar){
            let api = appCalendar.getApi();
            api.updateSize()
        }
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof MobCalendarControlBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if(action == 'contextMenuItemClick'){
            AppViewLogicService.getInstance().executeViewLogic(`${controlname}_${data}_click`, undefined, this, undefined, this.controlInstance.getPSAppViewLogics());
        }else{
            this.ctrlEvent({ controlname, action, data });
        }
    }


}
