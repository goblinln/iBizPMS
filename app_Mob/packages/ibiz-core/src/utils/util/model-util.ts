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
        ModelUtil.modelMap.set("APPINDEXVIEW", { "defaultPage": false, "redirectView": false, "width": 0, "height": 0 });
        ModelUtil.modelMap.set("DEMOBWFDYNAEDITVIEW", { "hideEditForm": false, "readOnly": false, "loadDefault": true, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBWFDYNAACTIONVIEW", { "hideEditForm": false, "readOnly": false, "loadDefault": true, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBWFDYNAEXPMDVIEW", { "hideEditForm": false, "readOnly": false, "loadDefault": true, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("APPPORTALVIEW", { "defaultPage": false, "redirectView": false, "width": 0, "height": 0 });
        ModelUtil.modelMap.set("DEMOBCALENDARVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBCHARTVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBCUSTOMVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBPORTALVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBDATAVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBEDITVIEW", { "hideEditForm": false, "readOnly": false, "loadDefault": true, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBMDVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBMEDITVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBMPICKUPVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBPICKUPMDVIEW", { "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBPICKUPTREEVIEW", { "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBPICKUPVIEW", { "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });
        ModelUtil.modelMap.set("DEMOBTREEVIEW", { "pickupMode": false, "enableQuickSearch": false, "enableExport": false, "enableSearch": false, "enableImport": false, "enableQuickGroup": false, "expandSearchForm": false, "readOnly": false, "loadDefault": true, "enableWF": false, "tempMode": 0, "width": 0, "height": 0, "redirectView": false });

    }

    /**
     * 注册部件默认值
     *
     * @class ModelUtil
     */
    private registerCtrlDefaultValue() {
        ModelUtil.modelMap.set("MOBMDCTRL", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("APPMENU", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("CHART", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("DATAVIEW", { "cardHeight": 0, "cardColMD": -1, "groupColXS": -1, "cardColSM": -1, "cardColXS": -1, "groupColSM": -1, "cardWidth": 0, "groupColLG": -1, "groupColMD": -1, "groupWidth": 0, "cardColLG": -1, "groupHeight": 0, "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("DASHBOARD", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("FORM", { "formWidth": 0.0, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("PICKUPVIEWPANEL", { "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("TREEVIEW", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("TABEXPPANEL", { "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("TABVIEWPANEL", { "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("MOBMDCTRL", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("CALENDAR", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("LIST", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("LISTEXPBAR", { "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });
        ModelUtil.modelMap.set("MULTIEDITVIEWPANEL", { "readOnly": false, "showBusyIndicator": true, "enableItemPrivilege": false, "autoLoad": true, "width": 0.0, "height": 0.0 });

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
        ModelUtil.modelMap.set("MOBTEXT", { "minLength": 0, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBPASSWORD", { "minLength": 0, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBTEXTAREA", { "minLength": 0, "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBNUMBER", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBSLIDER", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBRATING", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBRADIOLIST", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBDATE", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBDROPDOWNLIST", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBPICKER", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBMPICKER", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBPICKER_DROPDOWNVIEW", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBCHECKLIST", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBSTEPPER", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBSWITCH", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("SPAN", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBHTMLTEXT", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBSINGLEFILEUPLOAD", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBMULTIFILEUPLOAD", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBPICTURE", { "editorHeight": 0.0, "editorWidth": 0.0 });
        ModelUtil.modelMap.set("MOBPICTURELIST", { "editorHeight": 0.0, "editorWidth": 0.0 });

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