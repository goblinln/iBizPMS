

import { IPSAppDataEntity, IPSAppDEView, IPSDEGridFieldColumn } from '@ibiz/dynamic-model-api';
import { Util } from 'ibiz-core';

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
        const { name, caption, align, width, widthUnit, linkValueItem } = ctrlItemModel as IPSDEGridFieldColumn;
        const linkView = ctrlItemModel.getLinkPSAppView() as IPSAppDEView;
        const entity = linkView?.getPSAppDataEntity() as IPSAppDataEntity;
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
        let view: any = {
            viewname: 'app-view-shell',
            height: linkView.height ? linkView.height : 0,
            width: linkView.width,
            title: linkView.title,
            isRedirectView: linkView.redirectView ? true : false,
            placement: linkView.openMode ? linkView.openMode : '',
            viewpath: linkView.modelFilePath
        }
        this.handleLinkViewParams(linkView, view, entity, parentContainer.context);
        let tempContext: any = Util.deepCopy(parentContainer.context);
        if (linkView && linkView.modelFilePath) {
            Object.assign(tempContext, { viewpath: linkView.modelFilePath });
        }
        let tempViewParam: any = Util.deepCopy(parentContainer.viewparams);
        const deKeyField = entity ? entity.codeName.toLowerCase() : "";
        return parentContainer.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return <app-column-link
                        deKeyField={deKeyField}
                        context={tempContext}
                        viewparams={tempViewParam}
                        linkview={view}
                        valueitem={linkValueItem}
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

    /**
     * 计算路由参数
     * 
     * @param linkView 链接视图
     * @param view 模型
     * @param entity 链接视图实体
     */
    public handleLinkViewParams(linkView: IPSAppDEView, view: any, entity?: any, context?: any) {
        //获取父关系路由参数
        let tempDeResParameters: any[] = linkView.getPSAppDERSPaths() ? Util.formatAppDERSPath(context, linkView.getPSAppDERSPaths()) : [];
        //视图本身路由参数
        let tempParameters: any[] = [];
        if (entity) {
            tempParameters.push({
                pathName: Util.srfpluralize(entity.codeName).toLowerCase(),
                parameterName: entity.codeName.toLowerCase()
            });
            tempParameters.push({
                pathName: 'views',
                parameterName: linkView.getPSDEViewCodeName()?.toLowerCase()
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

}
