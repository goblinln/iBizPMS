import { IPSDEFormPage } from '@ibiz/dynamic-model-api';
import { Component, Prop } from 'vue-property-decorator';
import { AppDefaultMobFormDetail } from '../app-default-mob-form-detail/app-default-mob-form-detail';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultMobFormPage
 * @extends {Vue}
 */
@Component({})
export class AppDefaultMobFormPage extends AppDefaultMobFormDetail {

    /**
     * 表单成员实例对象
     *
     * @type {*}
     * @memberof AppDefaultFormDetail
     */
    @Prop() public detailsInstance!: IPSDEFormPage;

    /**
     * 绘制表单分页标题
     *
     * @param {*} viewPanel
     * @returns
     * @memberof TabExpPanelControlParser
     */
    public renderLabel() {
        const { caption } = this.detailsInstance;
        const sysCss = this.detailsInstance.getLabelPSSysCss();
        const sysImg = this.detailsInstance.getPSSysImage();
        let labelClass = sysCss?.cssName ? 'caption ' + sysCss.cssName : 'caption';
        let labelCaption: any = caption;
        let labelIcon: any;
        if (sysImg) {
            if (sysImg?.imagePath) {
                labelIcon = <img src={sysImg?.imagePath} style={{ 'margin-right': '2px' }}></img>
            } else {
                labelIcon = <i class={sysImg?.cssClass} style={{ 'margin-right': '2px' }}></i>
            }
        }
        return <span class={labelClass}>
            {labelIcon}{labelCaption}
        </span>
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppDefaultMobFormPage
     */
    public render(): any {
        const { detailClassNames } = this.renderOptions;
        return <div class={detailClassNames} style={this.runtimeModel.visible ? '' : 'display: none;'}>{this.$slots.default}</div>
    }
}
