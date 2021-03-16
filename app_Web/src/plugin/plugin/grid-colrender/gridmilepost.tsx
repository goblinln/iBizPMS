



/**
 * 发布里程碑旗帜插件插件类
 *
 * @export
 * @class Gridmilepost
 * @extends {Vue}
 */
export class Gridmilepost {

/**
     * 绘制部件项内容
     * @param h createElement函数
     * @param ctrlItemModel 部件项模型数据
     * @param parentContainer  父容器对象
     * @param data  当前项数据
     * 
     * @memberof Gridmilepost
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
        const view = {
            viewname: 'release-main-tab-exp-view', height: 0, width: 0, title: parentContainer.$t('entities.release.views.maintabexpview.title'), placement: 'DRAWER_TOP', isRedirectView: false, deResParameters: [
                { pathName: 'products', parameterName: 'product' },
            ]
            , parameters: [
                { pathName: 'releases', parameterName: 'release' },
                { pathName: 'maintabexpview', parameterName: 'maintabexpview' }
            ]
        }
        return parentContainer.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return [<app-column-link
                        deKeyField='release'
                        context={JSON.parse(JSON.stringify(parentContainer.context))}
                        viewparams={JSON.parse(JSON.stringify(parentContainer.viewparams))}
                        linkview={view}
                        valueitem='srfkey'
                        data={scope.row}
                    >
                        <span>{scope?.row?.name}</span>
                    </app-column-link>, scope?.row?.marker == '1' ? <span ><i class="fa fa-flag-o" title="里程碑" style="font-size: 18px;"></i></span> : null]
                },
                header: () => {
                    return <span class="column-header">{ctrlItemModel.caption}</span>
                }
            }
        });
    }

}
