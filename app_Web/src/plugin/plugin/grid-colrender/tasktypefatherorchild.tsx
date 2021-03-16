



/**
 * 表列名（描述任务类型如多人和父子）插件类
 *
 * @export
 * @class TASKTYPEFATHERORCHILD
 * @extends {Vue}
 */
export class TASKTYPEFATHERORCHILD {

/**
     * 绘制部件项内容
     * @param h createElement函数
     * @param ctrlItemModel 部件项模型数据
     * @param parentContainer  父容器对象
     * @param data  当前项数据
     * 
     * @memberof TASKTYPEFATHERORCHILD
     */
    public renderCtrlItem(h:any,ctrlItemModel:any,parentContainer:any,data:any){
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
            viewname: 'task-main-dashboard-view', height: 0, width: 1360, title: parentContainer.$t('entities.task.views.maindashboardview.title'), placement: 'DRAWER_TOP', isRedirectView: false, deResParameters: [
                { pathName: 'stories', parameterName: 'story' },
            ]
            , parameters: [
                { pathName: 'tasks', parameterName: 'task' },
                { pathName: 'maindashboardview', parameterName: 'maindashboardview' }
            ]
        }
        const refresh = parentContainer.refresh ? parentContainer.refresh : () => { };
        return parentContainer.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return <app-column-link
                        deKeyField='task'
                        context={JSON.parse(JSON.stringify(parentContainer.context))}
                        viewparams={JSON.parse(JSON.stringify(parentContainer.viewparams))}
                        linkview={view}
                        data={scope.row}
                        valueitem="srfkey"
                        on-refresh={refresh}
                    >
                        {
                            scope.row.tasktype === '10' ? <span><span title="多人任务" style="color: #3c4353;background-color: #ddd;border-radius: 9px;padding: 3px 5px;display: inline-block;line-height: 1;vertical-align: middle">多人</span>
                                <span style={scope.row.color ? { color: scope.row.color } : null}> {scope.row.name}</span></span> : scope.row.tasktype === '20' ? <span><span title="父任务" style="color: #3c4353;background-color: #ddd;border-radius: 9px;padding: 3px 5px;display: inline-block;line-height: 1;vertical-align: middle">父</span>
                                    <span style={scope.row.color ? { color: scope.row.color } : null}> {scope.row.name}</span></span> : scope.row.tasktype === '30' ? <span><span style={scope.row.color ? { color: scope.row.color } : null}>{scope.row.name}</span></span> :
                                        <span><span title="子任务" style="color: #3c4353;background-color: #ddd;border-radius: 9px;padding: 3px 5px;display: inline-block;line-height: 1;vertical-align: middle">子</span>
                                            <span style={scope.row.color ? { color: scope.row.color } : null}> {scope.row.name}</span></span>
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
