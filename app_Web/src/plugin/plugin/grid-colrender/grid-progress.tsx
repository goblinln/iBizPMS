



/**
 * 表格列进度条插件插件类
 *
 * @export
 * @class GridProgress
 * @extends {Vue}
 */
export class GridProgress {

/**
     * 绘制部件项内容
     * @param h createElement函数
     * @param ctrlItemModel 部件项模型数据
     * @param parentContainer  父容器对象
     * @param data  当前项数据
     * 
     * @memberof GridProgress
     */
    public renderCtrlItem(h: any, ctrlItemModel: any, parentContainer: any, data: any) {
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
        return parentContainer.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return <over-progress data={scope.row.totalconsumed && scope.row.totalwh && scope.row.totalconsumed != 0 ? scope.row.totalconsumed / scope.row.totalwh : ''}></over-progress>
                },
                header: () => {
                    return <span class="column-header">{ctrlItemModel.caption}</span>
                }
            }
        });
    }


}
