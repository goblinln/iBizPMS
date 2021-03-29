import { mergeDeepLeft } from 'ramda';
import { DataTypes } from '../../utils/util/data-types';
/**
 * 实体模型对象
 */
export class IBizEntityModel {

    /**
     * 默认模型数据
     * 
     * @memberof IBizEntityModel
     */
    protected defaultOption: any = {};

    /**
     * 应用上下文
     * 
     * @memberof IBizEntityModel
     */
    protected context: any = {};

    /**
     * 实体模型数据
     * 
     * @memberof IBizEntityModel
     */
    protected entityModelData: any;

    /**
     * 实体主键属性
     * 
     * @memberof IBizEntityModel
     */
    protected $keyField!: any;

    /**
     * 实体主信息属性
     * 
     * @memberof IBizEntityModel
     */
    protected $majorField!: any;

    /**
     * 所有实体属性集合
     * 
     * @memberof IBizEntityModel
     */
    protected appDeFieldsMap: Map<string, any> = new Map();

    /**
     * 支持快速搜索的实体属性集合
     * 
     * @memberof IBizEntityModel
     */
    protected searchFieldsMap: Map<string, any> = new Map();

    /**
     * 初始化IBizEntityModel对象
     * @param opts 额外参数
     * 
     * @memberof IBizEntityModel
     */
    constructor(opts: any, context?: any) {
        this.entityModelData = mergeDeepLeft(opts, this.defaultOption);
        this.context = context ? context : {};
        this.initAppDeFields();
    }

    /**
     * 初始化应用实体属性
     *
     * @private
     * @memberof IBizEntityModel
     */
    private initAppDeFields() {
        if (this.entityModelData?.getAllPSAppDEFields?.length > 0) {
            for (const field of this.entityModelData.getAllPSAppDEFields) {
                let copyField: any = JSON.parse(JSON.stringify(field));
                copyField.dataType = DataTypes.toString(field.stdDataType);
                copyField.isNumber = DataTypes.isNumber(copyField.dataType);
                this.appDeFieldsMap.set(field.codeName, copyField);
                if (field.keyField) {
                    this.$keyField = copyField;
                } else if (field.majorField) {
                    this.$majorField = copyField;
                }
                if (field.enableQuickSearch) {
                    this.searchFieldsMap.set(field.name, copyField);
                }
            }
        }
    }

    /**
     * 通过code获取实体属性
     *
     * @param {string} codeName
     * @returns
     * @memberof IBizEntityModel
     */
    public getFieldByCodeName(codeName: string) {
        return this.appDeFieldsMap.get(codeName);
    }

    /**
     * 获取所有应用实体属性字段集合
     *
     * @readonly
     * @memberof IBizEntityModel
     */
    public getAllAppDeFields() {
        return [...this.appDeFieldsMap.values()]
    }

    /**
     * 支持快速搜索的属性
     *
     * @readonly
     * @memberof IBizEntityModel
     */
    get searchFileds() {
        return [... this.searchFieldsMap.values()];
    }

    /**
     * 获取主键属性
     *
     * @readonly
     * @memberof IBizEntityModel
     */
    get keyField() {
        return this.$keyField;
    }

    /**
     * 获取主信息属性
     *
     * @readonly
     * @memberof IBizEntityModel
     */
    get majorField() {
        return this.$majorField;
    }

    /**
     * 获取codeName
     * 
     * @memberof IBizEntityModel
     */
    get codeName() {
        return this.entityModelData.codeName;
    }

    /**
     * 实体访问控制体系
     * 
     * @memberof IBizEntityModel
     */
    get dataAccCtrlArch() {
        return this.entityModelData.dataAccCtrlArch;
    }

    /**
     * 实体数据访问控制方式
     * 
     * @memberof IBizEntityModel
     */
    get dataAccCtrlMode() {
        return this.entityModelData.dataAccCtrlMode;
    }

    /**
     * 实体默认
     * 
     * @memberof IBizEntityModel
     */
    get defaultMode() {
        return this.entityModelData.defaultMode;
    }

    /**
     * 动态模型文件路径
     * 
     * @memberof IBizEntityModel
     */
    get dynaModelFilePath() {
        return this.entityModelData.dynaModelFilePath;
    }

    /**
     * 提供过滤器相关行为
     * 
     * @memberof IBizEntityModel
     */
    get enableFilterActions() {
        return this.entityModelData.enableFilterActions;
    }

    /**
     * 支持临时数据模式
     * 
     * @memberof IBizEntityModel
     */
    get enableTempData() {
        return this.entityModelData.enableTempData;
    }

    /**
     * 实体支持界面行为
     * 
     * @memberof IBizEntityModel
     */
    get enableUIActions() {
        return this.entityModelData.enableUIActions;
    }

    /**
     * 支持界面建立
     * 
     * @memberof IBizEntityModel
     */
    get enableUICreate() {
        return this.entityModelData.enableUICreate;
    }

    /**
     * 支持界面修改
     * 
     * @memberof IBizEntityModel
     */
    get enableUIModify() {
        return this.entityModelData.enableUIModify;
    }

    /**
     * 支持界面删除
     * 
     * @memberof IBizEntityModel
     */
    get enableUIRemove() {
        return this.entityModelData.enableUIRemove;
    }

    /**
     * 提供工作流相关行为
     * 
     * @memberof IBizEntityModel
     */
    get enableWFActions() {
        return this.entityModelData.enableWFActions;
    }

    /**
     * 应用实体属性集合
     * 
     * @memberof IBizEntityModel
     */
    get getAllPSAppDEFields() {
        return this.entityModelData.getAllPSAppDEFields;
    }

    /**
     * 应用实体方法集合
     * 
     * @memberof IBizEntityModel
     */
    get getAllPSAppDEMethods() {
        return this.entityModelData.getAllPSAppDEMethods;
    }

    /**
     * 逻辑名称
     * 
     * @memberof IBizEntityModel
     */
    get logicName() {
        return this.entityModelData.logicName;
    }

    /**
     * 主实体
     * 
     * @memberof IBizEntityModel
     */
    get major() {
        return this.entityModelData.major;
    }

    /**
     * 名称
     * 
     * @memberof IBizEntityModel
     */
    get name() {
        return this.entityModelData.name;
    }

    /**
     * 本地存储模式
     * 
     * @memberof IBizEntityModel
     */
    get storageMode() {
        return this.entityModelData.storageMode;
    }

    /**
     * 应用实体从关系集合
     * 
     * @memberof IBizEntityModel
     */
    get getMinorPSAppDERSs() {
        return this.entityModelData.getMinorPSAppDERSs;
    }

    /**
     * 实体界面行为集合
     * 
     * @memberof IBizEntityModel
     */
    get getAllPSAppDEUIActions() {
        return this.entityModelData.getAllPSAppDEUIActions;
    }

    /**
     * 主状态集合
     * 
     * @memberof IBizEntityModel
     */
    get getAllPSDEMainStates(){
        return this.entityModelData.getAllPSDEMainStates;
    }

    /**
     * 实体操作标识集合
     * 
     * @memberof IBizEntityModel
     */
    get getAllPSDEOPPrivs(){
        return this.entityModelData.getAllPSDEOPPrivs;
    }

    /**
     * 动态模型路径
     *
     * @readonly
     * @memberof IBizEntityModel
     */
    get path(){
        return this.entityModelData.path;
    }

    /**
     * 获取所有自填模式
     *
     * @readonly
     * @memberof IBizEntityModel
     */
    get getAllPSAppDEACModes(){
        return this.entityModelData.getAllPSAppDEACModes;
    }
    
    /**
     * 根据name查找自填模式
     * 
     * @param {string} name
     * @returns
     * @memberof IBizEntityModel
     */
    public getACModeByName(name: string) {
        if (this.getAllPSAppDEACModes) {
            return this.getAllPSAppDEACModes.find((element: any) => {
                return element.name === name;
            });
        } else {
            return null;
        }
    }

    /**
     * 导出模型数据
     *
     * @readonly
     * @memberof IBizEntityModel
     */
    get getAllPSAppDEDataExports() {
        return this.entityModelData.getAllPSAppDEDataExports;
    }

    /**
     * 根据界面行为tag查找实体界面行为
     * 
     * @memberof IBizEntityModel
     */
    public getAppDEUIActionByTag(tag: string) {
        if (this.entityModelData.getAllPSAppDEUIActions) {
            return this.entityModelData.getAllPSAppDEUIActions.find((element: any) => {
                return element.id === tag;
            });
        } else {
            return null;
        }
    }

    /**
     * 获取快速搜索输入提示字符串
     *
     * @memberof IBizEntityModel
     */
    public getQuickSearchPlaceholder() {
        if (this.searchFieldsMap.size > 0) {
            let strArr: string[] = [];
            this.searchFieldsMap.forEach((field: any) => {
                strArr.push((field.logicName || field.name).toLowerCase());
            })
            return strArr.join(',');
        } else {
            return '请输入'
        }
    }

}