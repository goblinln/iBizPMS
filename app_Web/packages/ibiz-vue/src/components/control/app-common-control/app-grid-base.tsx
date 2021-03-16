import { IBizEditorModel, Util } from 'ibiz-core';
import { Prop, Watch, Emit } from 'vue-property-decorator';
import { GridControlBase } from '../../../widgets';

/**
 * 表格部件基类
 *
 * @export
 * @class AppGridBase
 * @extends {GridControlBase}
 */
export class AppGridBase extends GridControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppGridBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppGridBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppGridBase
     */
    @Watch('dynamicProps', {
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onDynamicPropsChange(newVal, oldVal);
        }
    }

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppGridBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onStaticPropsChange(newVal, oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppDefaultForm
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppDefaultTree
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }


    /**
     * 计算表格参数
     * 
     * @memberof AppGridBase
     */
    public computeGridParams() {
        let options: any = {
            "data": this.items,
            "border": true,
            "high-light-current-row": this.controlInstance.singleSelect,
            "show-header": !this.controlInstance.hideHeader,
            "row-class-name": ({ row, rowIndex }: any) => this.getRowClassName({ row, rowIndex }),
            "cell-class-name": ({ row, column, rowIndex, columnIndex }: any) => this.getCellClassName({ row, column, rowIndex, columnIndex }),
            "max-height": this.items.length > 0 ? "calc(100% - 50px)" : "100%",
            "stripe" : true
        };
        const { aggMode } = this.controlInstance;
        //  支持排序
        if (!this.isNoSort) {
            Object.assign(options, {
                "default-sort": {
                    prop: this.minorSortPSDEF,
                    order: Object.is(this.minorSortDir, "ASC") ? "ascending" : Object.is(this.minorSortDir, "DESC") ? "descending" : ""
                }
            });
        }
        //  支持表格聚合
        if (aggMode && aggMode != "NONE") {
            Object.assign(options, {
                "show-summary": this.items.length > 0 ? true : false,
                "summary-method": (param: any) => this.getSummaries(param)
            });
        }
        //  支持分组
        if (this.isEnableGroup) {
            Object.assign(options, {
                "span-method": ({ row, column, rowIndex, columnIndex }: any) => this.arraySpanMethod({ row, column, rowIndex, columnIndex }),
                "tree-props": {
                    children: "children",
                    hasChildren: "children && children.length>0 ? true : false"
                },
                "row-key": "groupById"
            });
        }
        return options;
    }

    /**
     * 计算表格事件
     * 
     * @memberof AppGridBase
     */
    public computeGridEvents() {
        let events: any = {
            "row-click": (row: any) => this.rowClick(row),
            "row-dblclick": (row: any) => this.rowDBLClick(row),
            "select": (selection: any, row: any) => this.select(selection, row),
            "select-all": (selection: any) => this.selectAll(selection),
        }
        //  支持排序
        if (!this.controlInstance.noSort) {
            Object.assign(events, {
                "sort-change": ({ column, prop, order }: any) => this.onSortChange({ column, prop, order })
            })
        }
        return events;
    }


    /**
     * 绘制表格内容
     * 
     * @param {*} h CreateElement对象
     * @memberof AppGridBase
     */
    public renderGridContent(h: any) {
        return this.$createElement('el-table', {
            props: this.computeGridParams(),
            on: this.computeGridEvents(),
            ref: `${this.controlInstance.codeName.toLowerCase()}grid`
        }, [
            !this.isSingleSelect ?
                <el-table-column align="center" type="selection" width="50"></el-table-column> : null,
            this.isEnableGroup ?
                <el-table-column
                    show-overflow-tooltip
                    prop="group"
                    label="分组"
                    min-width="80"
                    scopedSlots={{
                        default: (scope: any) => {
                            return <span>{scope.row.group}</span>
                        }
                    }}>
                </el-table-column> : null,
            this.renderGridColumns(h),
            !this.allColumns.find((column: any) => {
                return column.show && Object.is(column.unit, "STAR");
            }) ? <el-table-column></el-table-column> : null
        ])
    }

    /**
     * 绘制表格列
     * 
     * @param {*} h CreateElement对象
     * @memberof AppGridBase
     */
    public renderGridColumns(h: any) {
        return this.allColumnsInstance.map((item: any) => {
            //隐藏表格项
            if (item.hideDefault || item.hiddenDataItem || !this.getColumnState(item.name.toLowerCase())) {
                return null;
            }
            if(item.getPSSysPFPlugin){
                const pluginInstance:any = this.PluginFactory.getPluginInstance("CONTROLITEM",item.getPSSysPFPlugin.pluginCode);
                if(pluginInstance){
                    return pluginInstance.renderCtrlItem(this.$createElement,item,this,null);
                }
            }else{
                if (item.columnType == "UAGRIDCOLUMN") {
                    //TODO  操作列(缺少界面行为组)
                    return this.renderUAColumn(item);
                } else if (item.columnType == "DEFGRIDCOLUMN") {
                    //  数据列
                    return this.renderGridColumn(h, item);
                }
            }
        })
    }

    /**
     * 绘制操作列
     * 
     * @param {any} column 表格列实例
     * @memberof AppGridBase 
     */
    public renderUAColumn(column: any) {
        const { name, caption, align, width, widthUnit } = column;
        //参数
        let renderParams: any = {
            "column-key": name,
            "label": caption,
            "align": align ? align.toLowerCase() : "center",
        }
        if (widthUnit && widthUnit != "STAR") {
            renderParams["width"] = width;
        } else {
            renderParams["min-width"] = width;
        }
        //绘制
        return this.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return this.renderActionModel(column, scope)
                },
                header: () => {
                    return <span class="column-header">{column.caption}</span>
                }
            }
        });
    }

    /**
     * 绘制操作列内容
     * 
     * @param {any} _column 表格列实例
     * @param {row, column, $index} scope 插槽返回数据
     */
    public renderActionModel(_column: any, scope: any) {
        const { getPSDEUIActionGroup: UIActionGroup } = _column;
        const { row, column, $index } = scope;
        if (UIActionGroup?.getPSUIActionGroupDetails?.length > 0) {
            return (
                <div style="text-align: center;">
                    {UIActionGroup.getPSUIActionGroupDetails.map((uiactionDetail: any, index: number) => {
                        const uiaction = uiactionDetail.getPSUIAction;
                        const actionModel = row[uiaction.uIActionTag];
                        if(Util.isEmpty(actionModel) || actionModel.visabled){
                            if (!uiactionDetail.showCaption) {
                                return (
                                    <tooltip transfer={true} max-width={600}>
                                        <a class={index == 0 ? "grid-first-uiaction" : "grid-uiaction-divider"} disabled={!Util.isEmpty(actionModel) && actionModel.disabled} style={{'pointer-events': row[uiaction.uIActionTag]?.disabled ? 'none' : 'auto', 'color': row[uiaction.uIActionTag]?.disabled ? '#7b7979' : '#wd8cf0'}} on-click={($event: any) => { this.handleActionClick(row, $event, _column, uiactionDetail) }}>
                                            {uiactionDetail.showIcon ? <i class={uiaction?.getPSSysImage?.cssClass ? uiaction.getPSSysImage.cssClass: "fa fa-save"}></i> : ""}
                                        </a>
                                        <div slot="content" >
                                            {uiaction?.caption ? uiaction.caption : ""}
                                        </div>
                                    </tooltip>
                                )
                            } else {
                                return (
                                    <a class={index == 0 ? "grid-first-uiaction" : "grid-uiaction-divider"} disabled={!Util.isEmpty(actionModel) && actionModel.disabled} style={{'pointer-events': row[uiaction.uIActionTag]?.disabled ? 'none' : 'auto', 'color': row[uiaction.uIActionTag]?.disabled ? '#7b7979' : '#wd8cf0'}}on-click={($event: any) => { this.handleActionClick(row, $event, _column, uiactionDetail) }}>
                                        {uiactionDetail.showIcon ? <i class={uiaction?.iconCls}></i> : ""}
                                        {uiactionDetail.showCaption ? uiaction?.caption ? uiaction.caption : "" : ""}
                                    </a>
                                )
                            }
                        }
                    })}
                </div>
            );
        }
    }

    /**
     * 绘制数据列
     * 
     * @param {*} h CreateElement对象
     * @param {any} column 表格列实例
     * @memberof AppGridBase
     */
    public renderGridColumn(h: any, column: any) {
        const { name, enableRowEdit, width, caption, widthUnit, align } = column;
        const editItem = this.controlInstance.getEditColumnByName(name.toLowerCase());
        let renderParams: any = {
            "show-overflow-tooltip": true,
            "label": caption,
            "prop": name,
            "align": align ? align.toLowerCase() : "center",
            "sortable": "custom"
        }
        if (widthUnit && widthUnit != "STAR") {
            renderParams["width"] = width;
        } else {
            renderParams["min-width"] = width;
        }
        return this.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return (this.actualIsOpenEdit && enableRowEdit && editItem) ?
                        this.renderOpenEditItem(column, scope) : this.renderColumn(column, scope)
                },
                header: () => {
                    return <span class="column-header">{column.caption}</span>
                }
            }
        });
    }

    /**
     * 绘制分页栏
     * 
     * @param {*} h CreateElement对象
     * @memberof AppGridBase
     */
    public renderPagingBar(h: any) {
        return (
            this.items.length > 0 ? <row class='grid-pagination'>
                <page class='pull-right' on-on-change={($event: any) => this.pageOnChange($event)}
                    on-on-page-size-change={($event: any) => this.onPageSizeChange($event)}
                    transfer={true}
                    total={this.totalRecord}
                    show-sizer
                    current={this.curPage}
                    page-size={this.limit}
                    page-size-opts={[10, 20, 30, 40, 50, 60, 70, 80, 90, 100]}
                    show-elevator
                    show-total>
                    <poptip transfer placement="top-start" class="page-column">
                        <i-button icon="md-menu">选择列</i-button>
                        <div slot="content">
                            {this.allColumns.map((col: any) => {
                                return <div><el-checkbox key={col.name} v-model={col.show} on-change={this.onColChange.bind(this)}>{col.label}</el-checkbox></div>
                            })}
                        </div>
                    </poptip>
                    <span class="page-button"><i-button icon="md-refresh" title="刷新" on-click={() => this.pageRefresh()}></i-button></span>&nbsp;
                显示&nbsp;
                {this.items.length > 0 ? 1 : (this.curPage - 1) * this.limit + 1}&nbsp;-&nbsp;{this.totalRecord > this.curPage * this.limit ? this.curPage * this.limit : this.totalRecord}&nbsp;
                条，共&nbsp;{this.totalRecord}&nbsp;条
            </page>
            </row> : null
        );
    }

    /**
     * 行编辑绘制
     * 
     * @param {any} item 表格列实例
     * @param {row，column, $index} scope 插槽返回数据
     * @memberof AppGridBase
     */
    public renderOpenEditItem(item: any, scope: any) {
        const editItem: any = this.controlInstance.getEditColumnByName(item.name.toLowerCase());
        let editor: IBizEditorModel = editItem.editorInstance;
        if(!editor) {
            return null;
        }
        const { row, column, $index } = scope;
        return (
            <app-form-item error={this.gridItemsModel[$index][column.property].error}>
                <app-default-editor
                    editorInstance={editor}
                    value={row[editor?.name]}
                    disabled={this.getColumnDisabled(row, editor?.name)}
                    context={this.context}
                    contextData={row}
                    viewparams={this.viewparams}
                    service={this.service}
                    on-change={(value: any) => {
                        this.onGridItemValueChange(row, value, $index);
                    }}
                />
            </app-form-item>
        );
    }

    /**
     * 绘制数据表格列
     * 
     * @memberof AppGridBase
     */
    public renderColumn(item: any, scope: any) {
        return (
            <app-grid-column
                row={scope.row}
                index={scope.$index}
                columnInstance={item}
                gridInstance={this.controlInstance}
                context={this.context}
                viewparams={this.viewparams}
                on-uiAction={($event: any)=>this.columnUIAction(scope.row, $event, item)}>
            </app-grid-column>
        )
    }

    /**
     * 绘制
     * 
     * @param {*} h CreateElement对象
     * @memberof AppGridBase
     */
    public render(h: any) {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        return (<div class={{ ...controlClassNames, 'grid': true }} style="height:100%">
            <i-form style="height:100%;display:flex;flex-direction: column;justify-content: space-between">
                {this.renderGridContent(h)}
                {this.controlInstance.enablePagingBar ? this.renderPagingBar(h) : ""}
            </i-form>
        </div>)
    }
}