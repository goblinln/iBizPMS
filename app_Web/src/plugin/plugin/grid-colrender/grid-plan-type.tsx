



/**
 * 表列名（描述计划类型如父子）插件类
 *
 * @export
 * @class GridPlanType
 * @extends {Vue}
 */
export class GridPlanType {

/**
     * 绘制部件项内容
     * @param h createElement函数
     * @param ctrlItemModel 部件项模型数据
     * @param parentContainer  父容器对象
     * @param data  当前项数据
     * 
     * @memberof GridPlanType
     */
    public renderCtrlItem(h: any, ctrlItemModel: any, parentContainer: any, data: any) {
        const { name, caption, align, width, widthUnit } = ctrlItemModel;
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
        const view = {
            viewname: 'product-plan-main-data-edit-view', height: 0, width: 0, title: parentContainer.$t('entities.productplan.views.maindataeditview.title'), placement: 'DRAWER_TOP', isRedirectView: false, deResParameters: [
                { pathName: 'products', parameterName: 'product' },
            ]
            , parameters: [
                { pathName: 'productplans', parameterName: 'productplan' },
                { pathName: 'maindataeditview', parameterName: 'maindataeditview' }
            ]
        }
        return parentContainer.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return <app-column-link
                        deKeyField='productplan'
                        context={JSON.parse(JSON.stringify(parentContainer.context))}
                        viewparams={JSON.parse(JSON.stringify(parentContainer.viewparams))}
                        linkview={view}
                        valueitem="srfkey"
                        data={scope.row}
                    >
                        {
                            scope?.row?.parent === '-1' ? <span><span title="父计划" style="color: #3c4353;background-color: #ddd;border-radius: 9px;padding: 3px 5px;display: inline-block;line-height: 1;vertical-align: middle">父</span>
                                <span>{scope.row.title}</span>
                            </span> : scope?.row?.parent === '0' ? <span>{scope?.row?.title}</span> : <span>
                                <span title="子计划" style="color: #3c4353;background-color: #ddd;border-radius: 9px;padding: 3px 5px;display: inline-block;line-height: 1;vertical-align: middle">子</span>
                                <span> {scope?.row?.title}</span>
                            </span>

                        }
                    </app-column-link>
                },
                header: () => {
                    return <span class="column-header">{ctrlItemModel.caption}</span>
                }
            }
        });
    }

}
