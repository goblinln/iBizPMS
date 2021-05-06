
/**
 * 应用组件服务
 * 
 * @memberof AppComponentService
 */
export class AppComponentService {

    /**
     * 视图组件Map
     * 
     * @memberof AppComponentService
     */
    protected static viewMap: Map<string, any> = new Map();

    /**
     * 部件组件Map
     * 
     * @memberof AppComponentService
     */
    protected static controlMap: Map<string, any> = new Map();

    /**
     * 控件组件Map
     * 
     * @memberof AppComponentService
     */
    protected static editorMap: Map<string, any> = new Map();

    /**
     * 注册应用组件

     * 
     * @memberof AppComponentService
     */
    public static registerAppComponents() {
        this.registerViewComponents();
        this.registerControlComponents();
        this.registerEditorComponents();
    }

    /**
     * 注册视图组件
     * 
     * @memberof AppComponentService
     */
    protected static registerViewComponents() {
        this.viewMap.set("APPINDEXVIEW_DEFAULT", 'app-default-mob-indexview');
        this.viewMap.set("DEMOBMDVIEW_DEFAULT", 'app-default-mob-mdview');
        this.viewMap.set("DEMOBEDITVIEW_DEFAULT", 'app-default-mob-editview');
        this.viewMap.set("APPPORTALVIEW_DEFAULT", 'app-default-mob-portalview');
        this.viewMap.set("DEMOBCALENDARVIEW_DEFAULT", 'app-default-mob-calendarview');
        this.viewMap.set("DEMOBCHARTVIEW_DEFAULT", 'app-default-mob-chartview');
        this.viewMap.set("DEMOBTABEXPVIEW_DEFAULT", 'app-default-mob-tabexpview');
        this.viewMap.set("DEMOBLISTEXPVIEW_DEFAULT", 'app-default-mob-listexpview');
        this.viewMap.set("DEMOBTREEVIEW_DEFAULT", 'app-default-mob-treeview');
        this.viewMap.set("DEMOBOPTVIEW_DEFAULT", 'app-default-mob-optview');
        this.viewMap.set("DEMOBPICKUPMDVIEW_DEFAULT", 'app-default-mob-pickmdview');
        this.viewMap.set("DEMOBPICKUPVIEW_DEFAULT", 'app-default-mob-pickview');
        this.viewMap.set("DEMOBMPICKUPVIEW_DEFAULT", 'app-default-mob-mpickview');
        this.viewMap.set("DEMOBPORTALVIEW_DEFAULT", 'app-default-mob-deportalview');
        this.viewMap.set("DEMOBEDITVIEW9_DEFAULT", 'app-default-mob-editview');
        this.viewMap.set("DEMOBMEDITVIEW9_DEFAULT", 'app-default-mob-meditview');
        this.viewMap.set("DEMOBMDVIEW9_DEFAULT", 'app-default-mob-mdview');
        this.viewMap.set("DEMOBPICKUPTREEVIEW_DEFAULT", 'app-default-mob-pickuptreeview');
        this.viewMap.set("DEMOBWFDYNAEDITVIEW_DEFAULT", 'app-default-mob-wfdynaeditview');
        this.viewMap.set("DEMOBWFDYNAACTIONVIEW_DEFAULT", 'app-default-mob-wfdynaactionview');
        this.viewMap.set("DEMOBWFDYNAEXPMDVIEW_DEFAULT", 'app-default-mob-wfdynaexpmdview');
        // 注册视图插件
    }

    /**
     * 获取视图组件
     * 
     * @memberof AppComponentService
     */
    public static getViewComponents(viewType: string, viewStyle: string, pluginCode?: string) {
        let componentName = 'app-default-notsupportedview';
        if(pluginCode){
            componentName = this.viewMap.get(`${pluginCode}`);
        }else{
            componentName = this.viewMap.get(`${viewType}_${viewStyle}`);
        }
        return componentName || 'app-default-notsupportedview';
    }

    /**
     * 注册部件组件

     * 
     * @memberof AppComponentService
     */
    protected static registerControlComponents() {
        this.controlMap.set("APPMENU_DEFAULT", 'app-default-mob-appmenu');
        this.controlMap.set("MOBMDCTRL_LISTVIEW", 'app-default-mob-mdctrl');
        this.controlMap.set("FORM_DEFAULT", 'app-default-mob-form');
        this.controlMap.set("APPMENU_ICONVIEW", 'app-default-mob-appmenu');
        this.controlMap.set("DASHBOARD_DEFAULT", 'app-default-mob-dashboard');
        this.controlMap.set("PORTLET_DEFAULT", 'app-default-mob-portlet');
        this.controlMap.set("CHART_DEFAULT", 'app-default-mob-chart');
        this.controlMap.set("CHART_NEW", 'app-default-mob-chart');
        this.controlMap.set("CALENDAR_DEFAULT", 'app-default-mob-calendar');
        this.controlMap.set("CALENDAR_TIMELINE", 'app-default-mob-calendar');
        this.controlMap.set("TABEXPPANEL_DEFAULT", 'app-default-mob-tabexppanel');
        this.controlMap.set("TABVIEWPANEL_DEFAULT", 'app-default-mob-tabviewpanel');
        this.controlMap.set("LIST_DEFAULT", 'app-default-mob-mdctrl');
        this.controlMap.set("LISTEXPBAR_DEFAULT", 'app-default-mob-listexpbar');
        this.controlMap.set("TREEVIEW_DEFAULT", 'app-default-mob-tree');
        this.controlMap.set("MULTIEDITVIEWPANEL_DEFAULT", 'app-default-mob-meditviewpanel');
        this.controlMap.set("CONTEXTMENU_DEFAULT", 'app-default-mob-contextmenu');
        this.controlMap.set("PICKUPVIEWPANEL_DEFAULT", 'app-default-mob-pickupviewpanel');
        this.controlMap.set("SEARCHFORM_DEFAULT", 'app-default-mob-searchform');
        this.controlMap.set("PANEL_DEFAULT", 'app-default-mob-panel');
        // 临时
        this.controlMap.set("PFPlugin", 'app-default-mob-mdctrl');
        // 注册部件插件标识
        this.controlMap.set("MobUpdateLogInfo", 'app-list-render-mob-update-log-info');
        this.controlMap.set("mobBugItemList", 'app-list-itemrender-mob-bug-item-list');
        this.controlMap.set("mobFileList3", 'app-list-render-mob-file-list3');
        this.controlMap.set("mobItemList", 'app-list-itemrender-mob-item-list');
        this.controlMap.set("NEW", 'app-chart-render-new');
        this.controlMap.set("mobReportList2", 'app-list-render-mob-report-list2');
        this.controlMap.set("", 'app-list-render-list-renderc8da12e867');
        this.controlMap.set("mobUpdateLogList", 'app-list-render-mob-update-log-list');
        this.controlMap.set("mobDemandList2", 'app-list-itemrender-mob-demand-list2');
        this.controlMap.set("mobFileTree2", 'app-tree-render-mob-file-tree2');
        this.controlMap.set("mobAllDynamicList2", 'app-list-render-mob-all-dynamic-list2');
        this.controlMap.set("mobHistoryList2", 'app-list-render-mob-history-list2');
        this.controlMap.set("mobProjectTeamItemList", 'app-list-itemrender-mob-project-team-item-list');
        this.controlMap.set("mobTaskTeam", 'app-list-itemrender-mob-task-team');
        this.controlMap.set("mobTestList2", 'app-list-itemrender-mob-test-list2');
        this.controlMap.set("mobTaskItemList2", 'app-list-itemrender-mob-task-item-list2');
        this.controlMap.set("NEW", 'app-default-mob-chart');
    }

    /**
     * 获取部件组件
     * 
     * @memberof AppComponentService
     */
    public static getControlComponents(ctrlType: string, ctrlStyle: string, pluginCode?: string) {
        let componentName = 'app-default-notsupportedcontrol';
        if(pluginCode){
            componentName = this.controlMap.get(`${pluginCode}`);
        }else{
            componentName = this.controlMap.get(`${ctrlType}_${ctrlStyle}`);
        }
        return componentName || 'app-default-notsupportedcontrol';
    }

    /**
     * 注册编辑器组件

     * 
     * @memberof AppComponentService
     */
    protected static registerEditorComponents() {
        this.editorMap.set('MOBTEXT_DEFAULT','app-mob-input');
        this.editorMap.set('MOBPASSWORD_DEFAULT','app-mob-input')
        this.editorMap.set('MOBNUMBER_DEFAULT','app-mob-input')
        this.editorMap.set('MOBSTEPPER_DEFAULT','app-mob-stepper')
        this.editorMap.set('MOBRADIOLIST_DEFAULT','app-mob-radio-list')
        this.editorMap.set('MOBTEXTAREA_DEFAULT','app-mob-textarea')
        this.editorMap.set('MOBSLIDER_DEFAULT','app-mob-slider')
        this.editorMap.set('MOBSWITCH_DEFAULT','app-mob-switch')
        this.editorMap.set('MOBRATING_DEFAULT','app-mob-rate')
        this.editorMap.set('MOBDATE_DEFAULT','app-mob-datetime-picker')
        this.editorMap.set('MOBHTMLTEXT_DEFAULT','app-mob-rich-text-editor')
        this.editorMap.set('MOBDROPDOWNLIST_DEFAULT','app-mob-select')
        this.editorMap.set('MOBCHECKLIST_DEFAULT','app-mob-check-list')
        this.editorMap.set('MOBPICKER_DEFAULT','app-mob-picker')
        this.editorMap.set('MOBMPICKER_DEFAULT','app-mob-mpicker')
        this.editorMap.set('MOBPICKER_DROPDOWNVIEW_DEFAULT','app-mob-select-drop-down')
        this.editorMap.set('MOBSINGLEFILEUPLOAD_DEFAULT','app-mob-file-upload')
        this.editorMap.set('MOBMULTIFILEUPLOAD_DEFAULT','app-mob-file-upload')
        this.editorMap.set('MOBPICTURE_DEFAULT','app-mob-picture')
        this.editorMap.set('MOBPICTURELIST_DEFAULT','app-mob-picture')
        this.editorMap.set('SPAN_DEFAULT','app-mob-span')
        // 预置扩展编辑器
        this.editorMap.set("MOBDATE_day", "app-mob-datetime-picker");
        // 注册编辑器
        this.editorMap.set("MOBHTMLTEXT_MOBHTMLOFPMS", "app-mobhtmltext-mobhtmlofpms");
        this.editorMap.set("MOBTEXT_MOBCOLORPICKER", "app-mobtext-mobcolorpicker");
        this.editorMap.set("MOBPICTURE_defaluturl", "app-mobpicture-defaluturl");
        this.editorMap.set("MOBHTMLTEXT_MOBHTMLOFPMS", "app-mobhtmltext-mobhtmlofpms");
  


    }

    /**
     * 获取编辑器组件
     * 
     * @memberof AppComponentService
     */
    public static getEditorComponents(editorType: string, editorStyle: string) {
        let componentName = editorStyle ? this.editorMap.get(`${editorType}_${editorStyle}`) : this.editorMap.get(`${editorType}_DEFAULT`);
        return componentName || 'app-not-supported-editor';
    }

}