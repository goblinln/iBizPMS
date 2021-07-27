import { IPSAppDataEntity, IPSAppDEACMode, IPSAppDEDataSet, IPSAppDEField, IPSAppView, IPSEditor, IPSFlexLayout, IPSFlexLayoutPos, IPSGridLayoutPos, IPSLayout, IPSLayoutPos, IPSNumberEditor, IPSDEGrid, IPSDEGridColumn, IPSDEGridEditItem, IPSDEGridDataItem, IPSAppCodeList } from "@ibiz/dynamic-model-api";
import { DataTypes } from "./data-types";
import { Util } from "./util";

/**
 * 模型辅助类
 *
 * @export
 * @class ModelTool
 */
export class ModelTool {

    /**
     * 通过类型查找指定部件
     * 
     * @memberof ModelTool
     */
    public static findPSControlByType(type: string, controls: Array<any> | null) {
        if (!type || !controls) {
            return null;
        }
        return controls.find((item: any) => {
            return item.controlType === type;
        })
    }

    /**
     * 通过name查找指定部件
     * 
     * @memberof ModelTool
     */
    public static findPSControlByName(name: string, controls: Array<any> | null) {
        if (!name || !controls) {
            return null;
        }
        return controls.find((item: any) => {
            return item.name === name;
        })
    }

    /**
     * 通过logicCat获取动态逻辑
     *
     * @param {string} logicCat
     * @memberof IBizFormDetailModel
     */
     public static findGroupLogicByLogicCat(logicCat: string, groupLogics: Array<any> | null){
        if (!logicCat || !groupLogics) {
            return null;
        }
        return groupLogics.find((item: any) => {
            return item?.logicCat === logicCat;
        })
    }

    /**
     * 获取视图应用实体codeName
     * 
     * @memberof ModelTool
     */
    public static getViewAppEntityCodeName(viewInstance: IPSAppView) {
        return viewInstance?.getPSAppDataEntity()?.codeName || '';
    }

    /**
     * 获取指定应用实体主键属性
     * 
     * @memberof ModelTool
     */
    public static getAppEntityKeyField(appEntity: IPSAppDataEntity | null){
        if(!appEntity || !appEntity.getAllPSAppDEFields()){
            return null;
        }
        return appEntity.getAllPSAppDEFields()?.find((appField:IPSAppDEField) =>{
            return (appField?.keyField === true);
        })
    }

    /**
     * 获取指定应用实体主信息属性
     * 
     * @memberof ModelTool
     */
    public static getAppEntityMajorField(appEntity:IPSAppDataEntity | null){
        if(!appEntity || !appEntity.getAllPSAppDEFields()){
            return null;
        }
        return appEntity.getAllPSAppDEFields()?.find((appField:IPSAppDEField) =>{
            return (appField?.majorField === true);
        })
    }

    /**
     * 获取表单所有的表单项成员
     *
     * @static
     * @param {*} form
     * @returns
     * @memberof ModelTool
     */
    public static getAllFormItems(form: any){
        let arr: any[] = [];
        this.parseForm(form, (formDetail: any)=>{
            if(formDetail?.detailType == 'FORMITEM'){
                arr.push(formDetail);
            }
            if (formDetail?.compositeItem && formDetail.getPSDEFormItems?.()?.length > 0) {
                (formDetail.getPSDEFormItems() || []).forEach((compositeItem: any) => {
                    arr.push(compositeItem);
                })
            }
        })
        return arr;
    }

    /**
     * 通过name获取表单项成员
     *
     * @static
     * @param {*} form 表单
     * @param {string} name 表单成员name
     * @returns
     * @memberof ModelTool
     */
    public static getFormDetailByName(form: any, name: string){
        let arr: any[] = [];
        this.parseForm(form, (formDetail: any)=>{
            if(formDetail){
                arr.push(formDetail);
            }
        })
        return arr.find((item: any)=> item.name == name);
    }

    /**
     * 获取表单所有的表单项成员
     *
     * @static
     * @param {*} form
     * @returns
     * @memberof ModelTool
     */
    public static getAllFormDetails(form: any){
        let arr: any[] = [];
        this.parseForm(form, (formDetail: any)=>{
            if(formDetail){
                arr.push(formDetail);
            }
        })
        return arr;
    }

    /**
     * 解析表单，每一个表单项成员调用一次callback回调
     *
     * @static
     * @param {*} form
     * @param {(item: any) => void} callback
     * @memberof ModelTool
     */
    public static parseForm(form: any, callback: (item: any) => void ){
        form?.getPSDEFormPages?.().forEach((formDetail: any)=>{
            this.parserFormDetail(formDetail, callback);
        })
    }
    
    /**
     * 递归调用表单项成员
     *
     * @static
     * @param {*} formDetail
     * @param {(item: any) => void} callback
     * @memberof ModelTool
     */
    public static parserFormDetail(formDetail: any, callback: (item: any) => void ){
        if(!formDetail){
            return;
        }
        // 每个表单成员调用一次回调。
        callback(formDetail);
        let childDetails: any[] = [];
        if(formDetail.detailType == 'TABPANEL'){
            childDetails = formDetail?.getPSDEFormTabPages?.();
        }else{
            childDetails = formDetail?.getPSDEFormDetails?.();
        }
        if(childDetails?.length > 0){
            childDetails.forEach((formDetail: any)=>{
                this.parserFormDetail(formDetail, callback);
            })
        }
    }
    
    /**
     * 获取Ac参数
     *
     * @static
     * @param {IPSEditor} editor 编辑器
     * @memberof ModelTool
     */
    public static getAcParams(editor: any){
        let appDe: IPSAppDataEntity = editor?.getPSAppDataEntity?.();
        let dataSet: IPSAppDEDataSet = editor?.getPSAppDEDataSet?.();
        if(appDe && dataSet){
            return {
                serviceName: appDe.codeName,
                interfaceName: dataSet.codeName,
            }
        }
    }
    
    /**
     * 获取自填模式sort排序
     *
     * @static
     * @param {IPSEditor} editor 编辑器
     * @memberof ModelTool
     */
    public static getAcSort(editor: any){
        if(editor?.getPSAppDEACMode?.()){
            let acMode: IPSAppDEACMode = editor.getPSAppDEACMode();
            if(acMode?.getMinorSortPSAppDEField() && acMode.minorSortDir){
                return `${acMode.getMinorSortPSAppDEField()?.codeName.toLowerCase()},${acMode.minorSortDir.toLowerCase()}`
            }
        }
        return undefined;
    }

    /**
     * 获取编辑器的主键名称
     *
     * @param {IPSEditor} editor
     * @memberof ModelTool
     */
    public static getEditorKeyName(editor: any){
        let appDe: IPSAppDataEntity =editor?.getPSAppDataEntity?.();
        const appDeKeyCodeName = ModelTool.getAppEntityKeyField(appDe)?.codeName;
        if(editor?.getPSAppDEACMode?.()){
            let acMode: IPSAppDEACMode = editor.getPSAppDEACMode();
            let keyName = acMode?.M?.getValuePSAppDEField?.codeName;
            if(!keyName || Object.is(keyName, appDeKeyCodeName)){
                return appDe?.codeName?.toLowerCase() || undefined;
            } else {
                return keyName?.toLowerCase() || undefined;
            }
        }
        return appDe?.codeName?.toLowerCase() || undefined;
    }

    /**
     * 获取编辑器的主信息名称
     *
     * @param {IPSEditor} editor
     * @memberof ModelTool
     */
    public static getEditorMajorName(editor: any){
        if(editor?.getPSAppDEACMode?.()){
            let acMode: IPSAppDEACMode = editor.getPSAppDEACMode();
            let majorName = acMode?.getTextPSAppDEField()?.codeName;
            if(majorName){
                return majorName.toLowerCase();
            }
        }
        let appDe: IPSAppDataEntity =editor?.getPSAppDataEntity?.();
        return ModelTool.getAppEntityMajorField(appDe)?.codeName.toLowerCase() || undefined;
    }
    
    /**
     * 是否是数值属性
     *
     * @static
     * @param {IPSAppDEField} appDeField 应用实体属性
     * @returns
     * @memberof ModelTool
     */
    public static isNumberField(appDeField: IPSAppDEField | null){
        if(appDeField?.stdDataType){
            return DataTypes.isNumber(DataTypes.toString(appDeField.stdDataType))
        }else{
            return false;
        }
    }

    /**
     * 获取精度
     *
     * @param {*} editor 编辑器
     * @param {IPSAppDEField} appDeField 实体属性
     * @memberof ModelTool
     */
    public static getPrecision(editor: IPSEditor, appDeField: IPSAppDEField){
        if ((editor as IPSNumberEditor)?.precision) {
            return (editor as IPSNumberEditor).precision;
        } else if (editor?.editorParams?.['precision']) {
            return editor.editorParams['precision']
        } else if (DataTypes.toString(appDeField?.stdDataType) == 'FLOAT') {
            let fieldPrecision = appDeField.precision;
            return Util.isEmpty(fieldPrecision) ? 2 : fieldPrecision;
        } else {
            return undefined;
        }
    }

    /**
     * 获取导航上下文
     *
     * @static
     * @param {*} currentItem 当前模型对象
     * @memberof ParserTool
     */
    public static getNavigateContext(currentItem: any) {
        const result: any = {};
        if (currentItem?.getPSNavigateContexts?.()?.length > 0) {
            currentItem.getPSNavigateContexts().forEach((navContext: any) => {
                result[navContext?.key] = navContext.rawValue ? navContext.value : `%${navContext.value}%`;
            });
        }
        return result;
    }

    /**
     * 获取导航参数
     *
     * @static
     * @param {*} currentItem 当前模型对象
     * @memberof ParserTool
     */
    public static getNavigateParams(currentItem: any) {
        const result: any = {};
        if (currentItem?.getPSNavigateParams?.()?.length > 0) {
            currentItem.getPSNavigateParams().forEach((navParam: any) => {
                result[navParam?.key] = navParam.rawValue ? navParam.value : `%${navParam.value}%`;
            });
        }
        return result;
    }

    /**
     * 根据codeName获取表格列
     *
     * @static
     * @param {(IPSLayoutPos | null)} layoutPos
     * @returns
     * @memberof ModelTool
     */
    public static getGridItemByCodeName(codeName: string, gridInstance: IPSDEGrid, mode: string = 'EDITITEM'): IPSDEGridColumn | IPSDEGridEditItem | IPSDEGridDataItem | null {
        if (!codeName) {
            return null;
        }
        switch (mode) {
            case 'GRIDCOLUMN':
                return (gridInstance.getPSDEGridColumns() || []).find((item: IPSDEGridColumn) => { return item.codeName === codeName; }) as IPSDEGridColumn;
            case 'EDITITEM':
                return (gridInstance.getPSDEGridEditItems() || []).find((item: IPSDEGridEditItem) => { return item.codeName === codeName; }) as IPSDEGridEditItem;
            case 'DATAITEM':
                return (gridInstance.getPSDEGridDataItems() || []).find((item: IPSDEGridDataItem) => { return item.codeName === codeName; }) as IPSDEGridDataItem;
            default:
                return null;
        }
    }

    /**
     * 获取关系视图数据
     *
     * @static
     * @param {IPSAppView} view
     * @memberof ModelTool
     */
    public static async loadedAppViewRef(view: IPSAppView){
        let targetAppViewRefs: Array<any> = [];
        if (view.getPSAppViewRefs?.()?.length) {
            for (const appViewRef of view.getPSAppViewRefs() || []) {
                if (appViewRef && (appViewRef.name !== "NEWDATA" && appViewRef.name !== "EDITDATA")) {
                    await appViewRef.fill();
                    let targetView = Util.deepCopy(appViewRef.M);
                    Object.assign(targetView, { name: appViewRef.name });
                    targetAppViewRefs.push(targetView);
                }
            }
        }
        return targetAppViewRefs;
    }

    /**
     * 获取所有门户部件
     *
     * @static
     * @param {*} container
     * @memberof ModelTool
     */
    public static getAllPortlets(container: any){
        let childs: any[] = [];
        if(container.controlType == 'DASHBOARD' || container?.portletType == 'CONTAINER'){
            childs = [... container?.getPSControls?.() ];
            container?.getPSControls?.()?.forEach((item: any)=>{
                if(item.controlType == 'DASHBOARD' || item?.portletType == 'CONTAINER'){
                    childs.push(...this.getAllPortlets(item))
                }
            })
        }
        return childs;
    }

    /**
     * 解析代码表和vlaue
     *
     * @param {any[]} items 代码表项数据
     * @param {*} value 值
     * @param {IPSAppCodeList} codelist 代码表模型
     * @param {*} _this 引用
     * @return {*} 
     * @memberof ModelTool
     */
     public static getCodelistValue(items: any[], value: any, codelist: IPSAppCodeList, _this: any) {
        if (!value && value !== 0 && value !== false) {
            return '';
        }
        if (items) {
            let result: any = [];
            if (Object.is(codelist.orMode, "NUM")) {
                items.map((_item: any, index: number) => {
                    const nValue = parseInt((value as any), 10);
                    const codevalue = _item.value;
                    if ((parseInt(codevalue, 10) & nValue) > 0) {
                        result.push(_item);
                    }
                });
            } else if (Object.is(codelist.orMode, "STR")) {
                const arrayValue: Array<any> = (value as any).split(codelist.valueSeparator);
                arrayValue.map((value: any, index: number) => {
                    result.push([]);
                    let values: any[] = Object.is(Util.typeOf(value), 'number') ? [value] : [...(value as any).split(codelist.valueSeparator)];
                    values.map((val: any, num: number) => {
                        const item = this.getItem(items, val, codelist, _this);
                        if (item) {
                            result[index].push(item);
                        }
                    });
                });
            } else {
                let values: any[] = Object.is(Util.typeOf(value), 'number') ? [value] : [...(value as any).split(codelist.valueSeparator || ',')];
                values.map((value: any, index: number) => {
                    const item = this.getItem(items, value, codelist, _this);
                    if (item) {
                        result.push(item);
                    }
                });
            }
            // 设置items
            if (result.length != 0) {
                return result.join(codelist.valueSeparator || ',');
            } else {
                return value;
            }
        }
    }

    /**
     * 获取代码项
     *
     * @param {any[]} items 代码表项集合
     * @param {*} value 值
     * @param {IPSAppCodeList} codelist 代码表对象
     * @param {*} _this 引用
     * @return {*}  {*}
     * @memberof GridControlBase
     */
    public static getItem(items: any[], value: any, codelist: IPSAppCodeList, _this: any): any {
        const arr: Array<any> = items.filter(item => { return item.value == value });
        if (arr.length !== 1) {
            return undefined;
        }
        return arr[0].text;
    }

    /**
     * 获取部件多语言公共部分
     *
     * @static
     * @param {*} control 部件模型对象
     * @return {*} 
     * @memberof ModelTool
     */
    public static getCtrlLangBase(control: any){
        if(!control){
            return '';
        }
        const { codeName, controlType } = control;
        const appDataEntity = control.getPSAppDataEntity();
        if (appDataEntity) {
            return `entities.${appDataEntity.codeName?.toLowerCase()}.${codeName?.toLowerCase()}_${controlType?.toLowerCase()}`;
        }
        return ''
    }
}