import { IPSDEFormPage, IPSLanguageRes } from '@ibiz/dynamic-model-api';
import { Component, Prop } from 'vue-property-decorator';
import { AppDefaultFormDetail } from '../app-default-form-detail/app-default-form-detail';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultFormPage
 * @extends {Vue}
 */
@Component({})
export class AppDefaultFormPage extends AppDefaultFormDetail {
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
     * @memberof AppDefaultFormPage
     */
    public renderLabel() {
        const { caption } = this.detailsInstance;
        const sysCss = this.detailsInstance.getLabelPSSysCss();
        const sysImg = this.detailsInstance.getPSSysImage();
        let labelClass = sysCss?.cssName ? 'caption ' + sysCss.cssName : 'caption';
        let labelCaption: any = this.$tl((this.detailsInstance.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, caption);
        let labelIcon: any;
        if(sysImg){
            if(sysImg?.imagePath){
                labelIcon = <img src={sysImg?.imagePath} style={{'margin-right' : '2px'}}></img>
            }else{
                labelIcon = <i class={sysImg?.cssClass} style={{'margin-right' : '2px'}}></i>
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
     * @memberof AppDefaultFormPage
     */
    public render(): any {
        const { detailClassNames } = this.renderOptions;
        const { codeName } = this.detailsInstance;
        const { codeName: formCodeName, controlType } = this.controlInstance;
        const tabsName = `${this.controlInstance.getPSAppDataEntity()?.codeName?.toLowerCase()}_${controlType?.toLowerCase()}_${formCodeName?.toLowerCase()}`;
        return  <tab-pane label={this.renderLabel} name={codeName} index={this.index} tab={tabsName} class={detailClassNames} style={this.runtimeModel.visible ? '' : 'display: none;'}>
            {this.$slots.default}
        </tab-pane>
    }
}
