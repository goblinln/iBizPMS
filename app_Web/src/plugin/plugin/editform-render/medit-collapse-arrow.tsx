



/**
 *  多编辑表单收缩箭头插件类
 *
 * @export
 * @class MeditCollapseArrow
 * @extends {Vue}
 */
export class MeditCollapseArrow {

    /**
     * 绘制表单收缩箭头
     *
     * @param {*} h
     * @param {*} ctrlItemModel
     * @param {*} parentContainer
     * @param {*} data
     * @returns
     * @memberof MeditCollapseArrow
     */
    renderCtrlItem(h: any, ctrlItemModel: any, parentContainer: any, data: any) {
        let _data = parentContainer.data;
        let { getPSSysCss, rawItemHeight, rawItemWidth, contentType, getPSSysImage, htmlContent, rawContent } = ctrlItemModel;
        let sizeStyle = rawItemHeight > 0 && rawItemWidth > 0 ? { width: rawItemWidth, height: rawItemHeight } : '';
        const tempNode = <div>
            <i class="ivu-icon ivu-icon-ios-arrow-down" style={_data.formitem == '1' ? "" : "display: none;"} on-click={() => parentContainer.onFormItemValueChange({name: 'formitem', value: '0'})}></i>
            <i class="ivu-icon ivu-icon-ios-arrow-back" style={!_data.formitem || _data.formitem == '0' ? "" : "display: none;"} on-click={() => parentContainer.onFormItemValueChange({name: 'formitem', value: '1'}) }></i>
        </div>
        return (
            <app-rawitem
                viewparams={parentContainer.viewparams}
                context={parentContainer.context}
                contentStyle={getPSSysCss?.cssName}
                sizeStyle={sizeStyle}
                contentType={contentType}
                imageClass={getPSSysImage?.cssClass}
                htmlContent={htmlContent}
            >
                { Object.is(contentType, 'RAW') ? tempNode : null}
            </app-rawitem>
        );
    }

}
