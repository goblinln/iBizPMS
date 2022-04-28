import { Vue, Component, Prop } from 'vue-property-decorator';
import { DataTypes, ModelTool, Util } from 'ibiz-core';
import { IPSAppCodeList, IPSAppDataEntity, IPSAppDEField, IPSAppDEView, IPSAppView, IPSDEGrid, IPSDEGridColumn, IPSDEGridDataItem, IPSDEGridEditItem, IPSDEGridFieldColumn, IPSDEUIAction, IPSEditor } from '@ibiz/dynamic-model-api';

@Component({})
export class AppDefaultGridColumn extends Vue {

    /**
     * 表格列实例
     * 
     * @type {IBizGridColumnModel}
     * @memberof AppDefaultGridColumn
     */
    @Prop() public columnInstance!: IPSDEGridColumn;

    /**
     * 表格实例
     * 
     * @type {IBizGridColumnModel}
     * @memberof AppDefaultGridColumn
     */
    @Prop() public gridInstance!: IPSDEGrid;

    /**
     * 当前行
     * 
     * @type {*}
     * @memberof AppDefaultGridColumn
     */
    @Prop() public row!: any;

    /**
     * 当前行下标
     * 
     * @type {number}
     * @memberof AppDefaultGridColumn
     */
    @Prop() public index!: number;

    /**
     * 应用上下文
     * 
     * @type {*}
     * @memberof AppDefaultGridColumn
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     * 
     * @type {*}
     * @memberof AppDefaultGridColumn
     */
    @Prop() public viewparams!: any;

    /**
     * 界面UI服务对象
     * 
     * @type {*}
     * @memberof AppDefaultGridColumn
     */
    @Prop() public appUIService!: any;

    /**
    * 模型服务对象
    * 
    * @memberof AppStyle2DefaultLayout
    */
    @Prop() public modelService!: any;

    /**
     * 绘制
     * 
     * @memberof AppDefaultGridColumn
     */
    public render() {
        const uiAction: IPSDEUIAction | null = (this.columnInstance as IPSDEGridFieldColumn).getPSDEUIAction();
        if (uiAction && uiAction.uIActionTag) {
            return (
                <a
                    v-show={this.row[uiAction.uIActionTag] && this.row[uiAction.uIActionTag].visabled === false ? false : true}
                    disabled={this.row[uiAction.uIActionTag]?.disabled}
                    style="{'display': 'block'}"
                    on-click={($event: any) => { this.onClick($event) }}>
                    {this.renderGridColumn()}
                </a>)
        } else {
            return this.renderGridColumn()
        }
    }

    /**
     * 绘制表格列
     * 
     * @memberof AppDefaultGridColumn
     */
    public renderGridColumn() {
        const columnModel: IPSDEGridFieldColumn = this.columnInstance as IPSDEGridFieldColumn;
        const { cLConvertMode, enableLinkView,  dataItemName } = columnModel;
        const codeList: IPSAppCodeList | null = columnModel.getPSAppCodeList();
        if (codeList && cLConvertMode == "FRONT") {   //  代码表
            return (
                <codelist
                    value={this.row[dataItemName]}
                    codeList={codeList}
                >
                </codelist>
            )
        } else if (enableLinkView && columnModel.getLinkPSAppView()) {   //  链接视图
            const linkView: IPSAppView = columnModel.getLinkPSAppView() as IPSAppView;
            let view: any = {
                viewname: 'app-view-shell',
                height: linkView.height,
                width: linkView.width,
                title: this.$tl(linkView.getCapPSLanguageRes()?.lanResTag, linkView.title),
                isRedirectView: linkView.redirectView ? true : false,
                placement: linkView.openMode ? linkView.openMode : '',
                viewpath: linkView.modelFilePath
            }
            Object.defineProperty(view, 'viewModel', { enumerable: false, writable: true, value: linkView });
            this.handleLinkViewParams(linkView, view);
            return this.renderLinkView(linkView, view);
        } else {    //常规显示
            return this.renderDefault();
        }
    }


    /**
     * 计算路由参数
     * 
     * @param linkView 链接视图
     * @param view 模型
     * @param entity 链接视图实体
     */
    public handleLinkViewParams(linkView: IPSAppView, view: any) {
        const entity: IPSAppDataEntity = linkView.getPSAppDataEntity() as IPSAppDataEntity;
        //获取父关系路由参数
        let tempDeResParameters: any[] = [];
        //视图本身路由参数
        let tempParameters: any[] = [];
        if (entity && entity.codeName) {
            tempDeResParameters = Util.formatAppDERSPath(this.context, (linkView as IPSAppDEView).getPSAppDERSPaths());
            tempParameters.push({
                pathName: Util.srfpluralize(entity.codeName).toLowerCase(),
                parameterName: entity.codeName.toLowerCase()
            });
            tempParameters.push({
                pathName: (linkView as IPSAppDEView).getPSDEViewCodeName()?.toLowerCase(),
                parameterName: (linkView as IPSAppDEView).getPSDEViewCodeName()?.toLowerCase()
            });
        } else {
            tempParameters.push({
                pathName: linkView.codeName?.toLowerCase(),
                parameterName: linkView.codeName?.toLowerCase()
            });
        }
        Object.assign(view, {
            parameters: tempParameters,
            deResParameters: tempDeResParameters
        })
    }

    /**
     * 绘制常规显示内容
     * 
     * @memberof AppDefaultGridColumn
     */
    public renderDefault() {
        const editItem: IPSDEGridEditItem = ModelTool.getGridItemByCodeName(this.columnInstance.codeName, this.gridInstance) as IPSDEGridEditItem;
        const editor: IPSEditor | null = editItem?.getPSEditor();
        const appDEField: IPSAppDEField = (editItem ? editItem.getPSAppDEField() : (this.columnInstance as IPSDEGridFieldColumn).getPSAppDEField()) as IPSAppDEField;
        let { stdDataType, precision, valueFormat } = appDEField;
        valueFormat = (this.columnInstance as IPSDEGridFieldColumn).valueFormat ? (this.columnInstance as IPSDEGridFieldColumn).valueFormat : valueFormat;
        const name: any = editor?.name ? editor.name : this.columnInstance.name;
        const dataItemName:any = this.columnInstance.dataItemName;
        if ((editor && editor.editorType) || (valueFormat && valueFormat != '%1$s')) {
          return (
            <app-span
                name={name}
                editorType={editor?.editorType}
                value={dataItemName ? this.row[dataItemName] : ""}
                dataType={stdDataType ? DataTypes.toString(stdDataType) : "text"}
                valueFormat={valueFormat}
                precision={precision || 0}>
            </app-span>
          )
        } else if (stdDataType && DataTypes.toString(stdDataType) && (DataTypes.toString(stdDataType) == "FLOAT" || DataTypes.toString(stdDataType) == "CURRENCY" || DataTypes.toString(stdDataType) == "DECIMAL")) {
            return (<app-format-data
                dataType={DataTypes.toString(stdDataType)}
                precision={precision ? precision : 0}
                data={this.row[dataItemName]}>
            </app-format-data>)
        } else {
            const dataItem: IPSDEGridDataItem | undefined = this.gridInstance.getPSDEGridDataItems()?.find((item: IPSDEGridDataItem) => { return item.name === this.columnInstance.dataItemName; });
            if (dataItem?.customCode) {
                return (
                    this.$createElement('div', {
                        domProps: {
                            innerHTML: this.row[dataItemName],
                        },
                    })
                )
            } else {
                if (Object.is('ADDRESSPICKUP', this.columnInstance?.userTag)) {
                  return (
                    <app-span
                        name={name}
                        editorType={'ADDRESSPICKUP'}
                        value={dataItemName ? this.row[dataItemName] : ""}
                        dataType={stdDataType ? DataTypes.toString(stdDataType) : "text"}
                        valueFormat={valueFormat}
                        precision={precision || 0}>
                    </app-span>
                  )
                }
                return (
                    <span>{this.row[dataItemName]}</span>
                )
            }

        }
    }

    /**
     * 绘制链接视图内容
     * 
     * @memberof AppDefaultGridColumn
     */
    public renderLinkView(linkView: IPSAppView, view: any) {
        const linkViewEntity: IPSAppDataEntity | null = this.gridInstance.getPSAppDataEntity();
        const editInstance: IPSEditor = (ModelTool.getGridItemByCodeName(this.columnInstance.codeName, this.gridInstance) as IPSDEGridEditItem)?.getPSEditor() as IPSEditor;
        const name = this.columnInstance.name.toLowerCase();
        const dataItemName = this.columnInstance.dataItemName;
        let tempContext: any = Util.deepCopy(this.context);
        if (view.viewpath) {
            Object.assign(tempContext, { viewpath: view.viewpath });
        }
        let tempViewParam: any = Util.deepCopy(this.viewparams);
        let localContext: any = {}
        let localParam: any = {}
        if(editInstance){
          localContext = ModelTool.getNavigateContext(editInstance)
          localParam = ModelTool.getNavigateParams(editInstance)
        }
        return (
            <app-column-link
                deKeyField={linkViewEntity && linkViewEntity.codeName ? linkViewEntity.codeName?.toLowerCase() : ""}
                context={tempContext}
                viewparams={tempViewParam}
                modelService={this.modelService}
                data={this.row}
                linkview={view}
                appUIService={this.appUIService}
                localContext={localContext}
                localParam={localParam}
                valueitem={(this.columnInstance as IPSDEGridFieldColumn).linkValueItem}
            >
                {editInstance && editInstance.editorType ?
                    <app-span
                        name={name}
                        editorType={editInstance.editorType}
                        value={this.row[dataItemName]}>
                    </app-span> :
                    <span>
                        {this.row[dataItemName]}
                    </span>}
            </app-column-link>
        )
    }

    /**
     * 链接点击
     * 
     * @param {*} row 当前行数据
     * @param {*} tag 标识
     * @param {*} $event 点击Event
     * @memberof AppDefaultGridColumn
     */
    public onClick($event: any) {
        this.$emit("uiAction", $event);
    }
}