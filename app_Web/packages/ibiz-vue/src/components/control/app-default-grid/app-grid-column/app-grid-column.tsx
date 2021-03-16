import { Vue, Component, Prop } from 'vue-property-decorator';
import { IBizGridModel, Util } from 'ibiz-core';
import { AppViewLogicService } from '../../../..';

@Component({})
export class AppDefaultGridColumn extends Vue {

    /**
     * 表格列实例
     * 
     * @type {IBizGridColumnModel}
     * @memberof AppDefaultGridColumn
     */
    @Prop() public columnInstance!: any;

    /**
     * 表格实例
     * 
     * @type {IBizGridColumnModel}
     * @memberof AppDefaultGridColumn
     */
    @Prop() public gridInstance!: IBizGridModel;

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
     * 绘制
     * 
     * @memberof AppDefaultGridColumn
     */
    public render() {
        const uiAction = this.columnInstance?.getPSDEUIAction;
        if (uiAction && uiAction.uIActionTag) {
            return (
                <a
                    v-show={this.row[uiAction.uIActionTag]?.visabled}
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
        const { codeList, cLConvertMode, enableLinkView, $linkView, name } = this.columnInstance;
        if (codeList && cLConvertMode == "FRONT") {   //  代码表
            return (
                <codelist
                    value={this.row[name]}
                    codeList={codeList}
                >
                </codelist>
            )
        } else if (enableLinkView && $linkView && $linkView.codeName) {   //  链接视图
            let view: any = {
                viewname: 'app-view-shell',
                height: $linkView.height,
                width: $linkView.width,
                title: $linkView.title,
                isRedirectView: $linkView.isRedirectView ? true : false,
                placement: $linkView.openMode ? $linkView.openMode : '',
                viewpath: $linkView.dynaModelFilePath
            }
            this.handleLinkViewParams($linkView, view);
            return this.renderLinkView(view);
        } else {    //常规显示
            return this.renderDefault();
        }
    }

    public handleLinkViewParams(linkView: any, view: any) {
        //TODO获取父关系路由参数
        let tempDeResParameters: any[] = [];
        //视图本身路由参数
        let tempParameters: any[] = [];
        const appde = linkView.$appDataEntity;
        if (appde) {
            tempParameters.push({
                pathName: Util.srfpluralize(appde.codeName).toLowerCase(),
                parameterName: appde.codeName.toLowerCase()
            });
            tempParameters.push({
                pathName: 'views',
                parameterName: linkView.getPSDEViewCodeName.toLowerCase()
            });
        } else {
            tempParameters.push({
                pathName: 'views',
                parameterName: linkView.codeName.toLowerCase()
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
        let editInstance: any = this.gridInstance.getEditColumnByName(this.columnInstance.name.toLowerCase());
        //  todo 精度欠缺
        const { dataType, precision, valueFormat, name } = this.columnInstance;
        if (editInstance && editInstance.editorType) {
            return (
                <app-span
                    name={name}
                    editorType={editInstance.editorType}
                    value={this.row[name]}
                    dataType={dataType ? dataType : "text"}
                    precision={precision ? precision : 0}>
                </app-span>
            )
        } else if (valueFormat && valueFormat != '%1$s') {
            return (
                <app-format-data
                    format={valueFormat}
                    data={this.row[name]}>
                </app-format-data>
            )
        } else if (dataType && (dataType == "FLOAT" || dataType == "CURRENCY" || dataType == "DECIMAL")) {
            return (<app-format-data
                dataType={dataType}
                precision={precision ? precision : 0}
                data={this.row[name]}>
            </app-format-data>)
        } else {
            return (
                <span>{this.row[name]}</span>
            )
        }
    }

    /**
     * 绘制链接视图内容
     * 
     * @memberof AppDefaultGridColumn
     */
    public renderLinkView(view: any) {
        const linkView: any = this.columnInstance.$linkView;
        const editInstance = this.gridInstance.getEditColumnByName(this.columnInstance.name.toLowerCase());
        const name = this.columnInstance.name.toLowerCase();
        let tempContext: any = Util.deepCopy(this.context);
        if (view.viewpath) {
            Object.assign(tempContext, { viewpath: view.viewpath });
        }
        let tempViewParam: any = Util.deepCopy(this.viewparams);
        return (
            <app-column-link
                deKeyField={linkView.$appDataEntity ? linkView.$appDataEntity.codeName.toLowerCase() : ""}
                context={tempContext}
                viewparams={tempViewParam}
                data={this.row}
                linkview={view}
                valueitem={this.columnInstance.linkValueItem}
            >
                {editInstance && editInstance.editorType ?
                    <app-span
                        name={name}
                        editorType={editInstance.editorType}
                        value={this.row[name]}>
                    </app-span> :
                    <span>
                        {this.row[name]}
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