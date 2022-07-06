import { IPSDEFormRawItem } from "@ibiz/dynamic-model-api";
import { Component, Prop } from "vue-property-decorator";
import { AppDefaultMobFormDetail } from "../app-default-mob-form-detail/app-default-mob-form-detail";

@Component({})
export class AppDefaultMobFormRawItem extends AppDefaultMobFormDetail {
    /**
     * 表单项实例对象
     *
     * @type {*}
     * @memberof AppDefaultMobFormRawItem
     */
    @Prop() public detailsInstance!: IPSDEFormRawItem;

    /**
     * 表单的模型对象
     *
     * @type {*}
     * @memberof AppDefaultMobFormRawItem
     */
    @Prop() public controlInstance!: any;

    /**
     * 表单数据
     *
     * @type {*}
     * @memberof AppDefaultMobFormRawItem
     */
    @Prop() public data: any;

    /**
     * 表单值规则
     *
     * @type {*}
     * @memberof AppDefaultMobFormRawItem
     */
    @Prop() public rules: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppDefaultMobFormRawItem
     */
    @Prop() context: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppDefaultMobFormRawItem
     */
    @Prop() viewparams: any;

    /**
     * 渲染直接内容
     *
     * @return {*} 
     * @memberof AppDefaultMobFormRawItem
     */
    render() {
        const { detailClassNames } = this.renderOptions;
        const customStyle = this.runtimeModel.visible ? '' : 'display: none';
        const { caption, showCaption, contentType, htmlContent, rawContent } = this.detailsInstance;
        const image = this.detailsInstance.getPSSysImage?.()?.cssClass;
        return (
            <app-rawitem
                class={detailClassNames}
                context={this.context}
                viewparams={this.viewparams}
                contentType={contentType}
                htmlContent={htmlContent}
                content={rawContent}
                imageClass={image}
                caption={showCaption ? caption : ''}
                style={customStyle}
                >
            </app-rawitem>
        )
    }
}