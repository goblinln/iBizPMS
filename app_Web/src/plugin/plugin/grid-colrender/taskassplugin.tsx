

import { AppViewLogicService } from "ibiz-vue";

/**
 * 任务指派和转交跳转视图的插件插件类
 *
 * @export
 * @class TASKASSPlugin
 * @extends {Vue}
 */
export class TASKASSPlugin {

    /**
     * 绘制部件项内容
     * @param h createElement函数
     * @param ctrlItemModel 部件项模型数据
     * @param parentContainer  父容器对象
     * @param data  当前项数据
     * 
     * @memberof TASKASSPlugin
     */
    public renderCtrlItem(h:any,ctrlItemModel:any,parentContainer:any,data:any){
        const { name, caption, align, width, widthUnit, codeList } = ctrlItemModel;
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
        return parentContainer.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return scope.row.tasktype != '10' ? <a 
                    // v-show={scope.row?.AssignTask?.visible} todo主状态
                     disabled={scope.row?.AssignTask?.disabled} style="{'display': 'block'}" on-click={($event: any) => this.handleActionClick(scope.row, $event, "tasktype", parentContainer)}>
                        <i class="fa fa-hand-o-right" title="指派" style="float: left;padding-top: 5px;padding-right: 5px;"></i>
                        <app-user-span
                            value={scope.row.assignedto}
                            context={JSON.parse(JSON.stringify(parentContainer.context))}
                            codeList={codeList}
                        >
                        </app-user-span>
                    </a> : <a  
                    // v-show={scope.row?.CheckForward?.visible}  todo主状态
                    disabled={scope.row?.CheckForward?.disabled} style="{'display': 'block'}" on-click={($event: any) => this.handleActionClick(scope.row, $event, "assignedtozj", parentContainer)}>
                            <i class="fa fa-mail-forward" title="转交" style="float: left;padding-top: 5px;padding-right: 5px;"></i>
                            <app-user-span
                                value={scope.row.assignedto}
                                context={JSON.parse(JSON.stringify(parentContainer.context))}
                                codeList={codeList}
                            >
                            </app-user-span></a>
                },
                header: () => {
                    return <span class="column-header">{ctrlItemModel.caption}</span>
                }
            }
        });
    }

    /**
     * 处理操作列点击
     * 
     * @memberof GridControlBase
     */
    public handleActionClick (data: any, event: any, column: any, parentContainer: any) {
        AppViewLogicService.getInstance().executeViewLogic(`${parentContainer.controlInstance.controlType.toLowerCase()}_${column}_click`,event,parentContainer,data,parentContainer.controlInstance.getPSAppViewLogics() || []);
    }

}
