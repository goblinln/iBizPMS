

import { AppViewLogicService } from 'ibiz-vue';

/**
 * 表格列（当前用户名显示红色）插件类
 *
 * @export
 * @class GridUserColorRed
 * @extends {Vue}
 */
export class GridUserColorRed {

/**
     * 绘制部件项内容
     * @param h createElement函数
     * @param ctrlItemModel 部件项模型数据
     * @param parentContainer  父容器对象
     * @param data  当前项数据
     * 
     * @memberof GridUserColorRed
     */
    public renderCtrlItem(h: any, ctrlItemModel: any, parentContainer: any, data: any) {
        const { name, caption, align, width, widthUnit, psappcodelist, psdeuiaction } = ctrlItemModel;
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
                    return (
                        psdeuiaction && psdeuiaction.uIActionTag ?
                        <a 
                            v-show={scope.row[psdeuiaction.uIActionTag]?.visabled} 
                            disabled={scope.row[psdeuiaction.uIActionTag]?.disabled}
                            style="{'display': 'block'}"
                            on-click={($event: any ) => { this.onClick(scope.row, ctrlItemModel, parentContainer, $event) }}>
                            <app-user-span
                                value={scope.row[ctrlItemModel.codeName.toLowerCase()]}
                                data={scope.row}
                                context={JSON.parse(JSON.stringify(parentContainer.context))}
                                codeList={psappcodelist}
                            ></app-user-span>
                        </a> : 
                        <app-user-span
                            value={scope.row[ctrlItemModel.codeName.toLowerCase()]}
                            data={scope.row}
                            context={JSON.parse(JSON.stringify(parentContainer.context))}
                            codeList={psappcodelist}
                        ></app-user-span>
                    )
                },
                header: () => {
                    return <span class="column-header">{ctrlItemModel.caption}</span>
                }
            }
        })
    }

    /**
     * 点击事件
     * @param row 表格行插槽数据
     * @param column 表格列模型
     * @param gridContainer  表格容器对象
     * @param $event 原生event
     * 
     * @memberof GridUserColorRed
     */
    public onClick(row: any, column: any, parentContainer: any, $event: any) {
        const tag = `grid_${column.codeName.toLowerCase()}_click`;
        if(column && parentContainer && parentContainer.controlInstance) {
            AppViewLogicService.getInstance().executeViewLogic(tag, $event, parentContainer, row, parentContainer.controlInstance.getPSAppViewLogics() || []);
        }
    }

}
