



/**
 * 表格列（任务延期时截止时间显示红色）插件类
 *
 * @export
 * @class RedCloumnPlugin
 * @extends {Vue}
 */
export class RedCloumnPlugin {

    /**
     * 绘制部件项内容
     * @param h createElement函数
     * @param ctrlItemModel 部件项模型数据
     * @param parentContainer  父容器对象
     * @param data  当前项数据
     * 
     * @memberof RedCloumnPlugin
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

        return parentContainer.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return scope.row.delay ?
                        <span style="color:red"><app-format-data format="YYYY-MM-DD" data={scope.row.deadline}></app-format-data>
                            <span>{scope.row.delay}</span>
                        </span> : <span >
                            <app-format-data format="YYYY-MM-DD" data={scope.row.deadline}></app-format-data>
                        </span>
                },
                header: () => {
                    return <span class="column-header">{ctrlItemModel.caption}</span>
                }
            }
        });
    }


}
