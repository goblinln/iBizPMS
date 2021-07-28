import { MDViewBase } from './mdview-base';
import { ModelTool, CalendarViewInterface } from 'ibiz-core';
import { IPSAppDECalendarView, IPSDECalendar } from '@ibiz/dynamic-model-api';

/**
 * 日历视图基类
 *
 * @export
 * @class CalendarViewBase
 * @extends {MDViewBase}
 * @implements {CalendarViewInterface}
 */
export class CalendarViewBase extends MDViewBase implements CalendarViewInterface {

    /**
     * 视图实例
     * 
     * @memberof CalendarViewBase
     */
    public viewInstance!: IPSAppDECalendarView;

    /**
     * 日历实例
     * 
     * @memberof CalendarViewBase
     */
    public calendarInstance!: IPSDECalendar;

    /**
     * 引擎初始化（日历视图暂无引擎）
     * 
     * @memberof CalendarViewBase
     */
    public engineInit() { }

    /**
     * 初始化日历视图实例
     * 
     * @param opts 
     * @memberof CalendarViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDECalendarView;
        await super.viewModelInit();
        this.calendarInstance = ModelTool.findPSControlByType("CALENDAR",this.viewInstance.getPSControls());
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof CalendarViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.calendarInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.calendarInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 快速搜索
     *
     * @param {*} $event
     * @memberof CalendarViewBase
     */
    public onSearch($event: any): void {
        const refs: any = this.$refs;
        if (refs[this.calendarInstance?.name] && refs[this.calendarInstance?.name].ctrl) {
            refs[this.calendarInstance?.name].ctrl.refresh();
        }
    }

    /**
     * calendar 的 beforeload 事件
     *
     * @param {*} arg
     * @memberof CalendarViewBase
     */
    public onBeforeLoad(arg: any) {
        let _this: any = this;
        if (_this.viewparams && Object.keys(_this.viewparams).length > 0) {
            Object.assign(arg, _this.viewparams);
        }
        if(this.searchFormInstance?.name){
            const searchFrom: any = _this.$refs[this.searchFormInstance.name]?.ctrl;
            if (searchFrom && _this.isExpandSearchForm) {
                Object.assign(arg, searchFrom.getData());
            }
        }
        if (_this && !_this.isExpandSearchForm) {
            Object.assign(arg, { query: _this.query });
        }
        // 快速分组和快速搜索栏
        let otherQueryParam: any = {};
        if (_this && (_this.quickGroupData as any)) {
            Object.assign(otherQueryParam, _this.quickGroupData);
        }
        if (_this && _this.quickFormData) {
            Object.assign(otherQueryParam, _this.quickFormData);
        }
        // 自定义查询条件
        //TODO YY 日历部件自定义查询条件
        // if (this.calendarInstance && this.calendarInstance.customCond) {
        //     Object.assign(otherQueryParam, { srfdsscope: this.calendarInstance.customCond });
        // }
        Object.assign(arg, { viewparams: otherQueryParam });
    }

    /**
     * searchform 部件 search 事件
     *
     * @param {*} $event
     * @memberof CalendarViewBase
     */
    public searchform_search($event: any) {
        this.onSearch($event);
    }

    /**
     * searchform 部件 load 事件
     *
     * @param {*} $event
     * @memberof CalendarViewBase
     */
    public searchform_load($event: any) {
        this.onSearch($event);
    }

    /**
     * 部件事件处理
     *
     * @param {*} $event
     * @memberof CalendarViewBase
     */
    public onCtrlEvent(ctrlName: any, action: any, data: any) {
        if (!action) {
            return;
        }
        if (Object.is(ctrlName, this.calendarInstance.name)) {
            if (Object.is(action,"beforeload")) {
                this.onBeforeLoad(data);
            }
        } else if(Object.is(ctrlName,this.searchFormInstance?.name)) {
            switch (action) {
                case "search":
                    this.searchform_search(data);
                    break;
                case "load":
                    this.searchform_load(data);
                    break;
            }
        }
        super.onCtrlEvent(ctrlName, action, data);
    }
}