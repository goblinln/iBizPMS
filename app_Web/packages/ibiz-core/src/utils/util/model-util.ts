/**
 * 模型工具类
 *
 * @export
 * @class ModelUtil
 */
 export class ModelUtil {

    /**
     * 模型默认值
     *
     * @class ModelUtil
     */
    private static modelMap: Map<string, any> = new Map();

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {ModelUtil}
     * @memberof ModelUtil
     */
    private static ModelUtil: ModelUtil;

    /**
     * 私有构造，拒绝通过 new 创建对象
     *
     * @memberof ModelUtil
     */
    private constructor() {
        this.registerDefaultValue();
    }

    /**
     * 获取 ModelUtil 单例对象
     *
     * @static
     * @returns {ModelUtil}
     * @memberof ModelUtil
     */
    public static getInstance(): ModelUtil {
        if (!ModelUtil.ModelUtil) {
            ModelUtil.ModelUtil = new ModelUtil();
        }
        return this.ModelUtil;
    }

    /**
     * 注册默认值
     *
     * @class ModelUtil
     */
    private registerDefaultValue() {
        this.registerViewDefaultValue();
        this.registerCtrlDefaultValue();
        this.registerCtrlItemDefaultValue();
        this.registerEditorDefaultValue();
        this.registerOtherDefaultValue();
    }

    /**
     * 注册视图默认值
     *
     * @class ModelUtil
     */
    private registerViewDefaultValue() {
        ModelUtil.modelMap.set("DECALENDARVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("APPINDEXVIEW", { "defaultPage": false, "redirectView": false, "width": 0, "height": 0 });
        ModelUtil.modelMap.set("DECALENDAREXPVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DECALENDARVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DECHARTVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DECUSTOMVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEDATAVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false })
        ModelUtil.modelMap.set("DEDATAVIEWEXPVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEPORTALVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEEDITVIEW", { "hideEditForm": false, "readOnly": false, "loadDefault": true, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEFORMPICKUPDATAVIEW", { "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEGANTTVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEGRIDEXPVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEGRIDVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableSearch": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEHTMLVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEINDEXPICKUPDATAVIEW", { "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEKANBANVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DELISTEXPVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DELISTVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMEDITVIEW9", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMPICKUPVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEPICKUPGRIDVIEW", { "enableQuickSearch": false, "enableSearch": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEPICKUPTREEVIEW", { "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEPICKUPVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("APPPORTALVIEW", { "defaultPage": false, "redirectView": false, "width": 0, "height": 0 });
        ModelUtil.modelMap.set("DEREDIRECTVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0 });
        ModelUtil.modelMap.set("DETABEXPVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DETREEEXPVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DETREEGRIDEXVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DETREEVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEWFDYNAACTIONVIEW", { "hideEditForm": false, "readOnly": false, "loadDefault": true, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEWFDYNAEXPGRIDVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableSearch": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEWFDYNASTARTVIEW", { "hideEditForm": false, "readOnly": false, "loadDefault": true, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEWFDYNAEDITVIEW", { "hideEditForm": false, "readOnly": false, "loadDefault": true, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEWIZARDVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
    }

    /**
     * 注册部件默认值
     *
     * @class ModelUtil
     */
    private registerCtrlDefaultValue() {
        ModelUtil.modelMap.set("APPMENU", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("APPMENU", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("CALENDAREXPBAR", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("CALENDAR", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("CHART", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("DATAVIEW", { "cardHeight": 0, "cardColMD": -1, "groupColXS": -1, "cardColSM": -1, "cardColXS": -1, "groupColSM": -1, "cardWidth": 0, "groupColLG": -1, "groupColMD": -1, "groupWidth": 0, "cardColLG": -1, "groupHeight": 0, "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("DATAVIEWEXPBAR", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("DASHBOARD", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("FORM", { "formWidth": 0.0, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("GANTT", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("GRIDEXPBAR", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("GRID", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("KANBAN", { "cardHeight": 0, "cardColMD": -1, "groupColXS": -1, "cardColSM": -1, "cardColXS": -1, "groupColSM": -1, "cardWidth": 0, "groupColLG": -1, "groupColMD": -1, "groupWidth": 0, "cardColLG": -1, "groupHeight": 0, "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("LISTEXPBAR", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("LIST", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("MULTIEDITVIEWPANEL", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("PICKUPVIEWPANEL", { "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("TREEVIEW", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("TABEXPPANEL", { "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("TABVIEWPANEL", { "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("TREEEXPBAR", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("TREEGRIDEX", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("WIZARDPANEL", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("STATEWIZARDPANEL", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
    }

    /**
     * 注册部件项默认值
     *
     * @class ModelUtil
     */
    private registerCtrlItemDefaultValue() { }

    /**
     * 注册编辑器默认值
     *
     * @class ModelUtil
     */
    private registerEditorDefaultValue() {
        ModelUtil.modelMap.set("TEXTBOX", { "minLength": 0, "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PASSWORD", { "minLength": 0, "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("TEXTAREA", { "minLength": 0, "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("TEXTAREA_10", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("NUMBER", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("SLIDER", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("RATING", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("STEPPER", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("SWITCH", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("SPANEX", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("SPAN", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("AC", { "minLength": 0, "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("HTMLEDITOR", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("FILEUPLOADER", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICTURE", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICTURE_ONE", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("RADIOBUTTONLIST", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("CHECKBOX", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("CHECKBOXLIST", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MDROPDOWNLIST", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("DROPDOWNLIST", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("DROPDOWNLIST_100", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("DATEPICKER", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("DATEPICKEREX", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("DATEPICKEREX_NOTIME", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("DATEPICKEREX_HOUR", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("DATEPICKEREX_MINUTE", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("DATEPICKEREX_SECOND", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("DATEPICKEREX_NODAY", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("DATEPICKEREX_NODAY_NOSECOND", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKEREX_DROPDOWNVIEW", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKEREX_DROPDOWNVIEW_LINK", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKUPVIEW", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("ADDRESSPICKUP", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("ADDRESSPICKUP_AC", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKEREX_LINKONLY", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKER", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKER_ALLEMPSELECT", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKEREX_NOBUTTON", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKEREX_NOAC_LINK", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKEREX_TRIGGER_LINK", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKEREX_TRIGGER", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKEREX_NOAC", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("PICKEREX_LINK", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("IPADDRESSTEXTBOX", { "editable": true, "editorHeight": 0.0, "editorWidth": 0.0 });
    }

    /**
     * 注册其他默认值
     *
     * @class ModelUtil
     */
    private registerOtherDefaultValue() { }

    /**
     * 通过标识获取默认值
     *
     * @class ModelUtil
     */
    public getDefaultValueByTag(tag: string) {
        return ModelUtil.modelMap.get(tag) ? ModelUtil.modelMap.get(tag) : {};
    }

}