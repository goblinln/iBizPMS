

import { Util } from "ibiz-core";

/**
 * 表格列链接绘制（传参）插件类
 *
 * @export
 * @class GirdCoumLJ
 * @extends {Vue}
 */
export class GirdCoumLJ {

/**
     * 绘制部件项内容
     * @param h createElement函数
     * @param ctrlItemModel 部件项模型数据
     * @param parentContainer  父容器对象
     * @param data  当前项数据
     * 
     * @memberof GirdCoumLJ
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
            viewname: Util.srfFilePath2($linkView.codeName),
            height: $linkView.height,
            width: $linkView.width,
            title: $linkView.title,
            placement: $linkView.openMode
        }
        const deKeyField = $linkView.$appDataEntity ? $linkView.$appDataEntity.codeName.toLowerCase() : ""
        return parentContainer.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return <app-column-link
                        deKeyField={deKeyField}
                        context={JSON.parse(JSON.stringify(parentContainer.context))}
                        viewparams={JSON.parse(JSON.stringify(parentContainer.viewparams))}
                        linkview={view}
                        localParam="{product: '%product%'}"
                        valueitem={ctrlItemModel.linkValueItem}
                        data={scope.row}
                    >
                        <span>{scope.row[name]}</span>
                    </app-column-link>
                },
                header: () => {
                    return <span class="column-header">{ctrlItemModel.caption}</span>
                }
            }
        });
    }

}
