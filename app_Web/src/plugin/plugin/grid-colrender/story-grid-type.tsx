



/**
 * 表列名（描述需求类型如父子）插件类
 *
 * @export
 * @class StoryGridType
 * @extends {Vue}
 */
export class StoryGridType {

 /**
     * 绘制部件项内容
     * @param h createElement函数
     * @param ctrlItemModel 部件项模型数据
     * @param parentContainer  父容器对象
     * @param data  当前项数据
     * 
     * @memberof StoryGridType
     */
    public renderCtrlItem(h:any,ctrlItemModel:any,parentContainer:any,data:any){
        const { name, caption, align, $linkView, width, widthUnit } = ctrlItemModel;
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
            viewname: 'story-main-view',
            height: $linkView.height,
            width: $linkView.width,
            title: $linkView.title,
            placement: $linkView.openMode,
            deResParameters: [
                { pathName: 'products', parameterName: 'product' },
            ], parameters: [
                { pathName: 'stories', parameterName: 'story' },
                { pathName: 'mainview', parameterName: 'mainview' }
            ]
        }
        const refresh = parentContainer.refresh ? parentContainer.refresh : () => { };
        return parentContainer.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return <app-column-link
                        deKeyField='story'
                        context={JSON.parse(JSON.stringify(parentContainer.context))}
                        viewparams={JSON.parse(JSON.stringify(parentContainer.viewparams))}
                        linkview={view}
                        data={scope.row}
                        valueitem="srfkey"
                        on-refresh={refresh}
                    >
                        {
                            scope.row.parent === '-1' ?
                                <span>
                                    <span title="父需求" style="color: #3c4353;background-color: #ddd;border-radius: 9px;padding: 3px 5px;display: inline-block;line-height: 1;vertical-align: middle">父</span><span style={scope.row.color ? { color: scope.row.color } : null}> {scope.row.title}
                                    </span> </span> :
                                scope.row.parent === '0' ? <span style={scope.row.color ? { color: scope.row.color } : null}>{scope.row.title}</span> :
                                    <span>
                                        <span title="子需求" style="color: #3c4353;background-color: #ddd;border-radius: 9px;padding: 3px 5px;display: inline-block;line-height: 1;vertical-align: middle">子</span>
                                        <span style={scope.row.color ? { color: scope.row.color } : null}>{scope.row.title}</span>
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
