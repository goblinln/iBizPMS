import { Component } from 'vue-property-decorator';
import { IPSLanguageRes } from '@ibiz/dynamic-model-api';
import { AppDefaultSearchFormDetail } from '../app-default-searchform-detail/app-default-searchform-detail';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultSearchFormPage
 * @extends {Vue}
 */
@Component({})
export class AppDefaultSearchFormPage extends AppDefaultSearchFormDetail {
    /**
     * 绘制表单分页标题
     *
     * @param {*} viewPanel
     * @returns
     * @memberof AppDefaultSearchFormPage
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
     * @memberof AppDefaultSearchFormPage
     */
    public render(): any {
        const { detailClassNames } = this.renderOptions;
        const { codeName } = this.detailsInstance;
        const { appDataEntity, codeName: formCodeName, controlType } = this.controlInstance;
        const tabsName = `${appDataEntity?.codeName?.toLowerCase()}_${controlType?.toLowerCase()}_${formCodeName?.toLowerCase()}`;
        return  <tab-pane label={this.renderLabel} name={codeName} index={this.index} tab={tabsName} class={detailClassNames} style={this.runtimeModel.visible ? '' : 'display: none;'}>
            {this.$slots.default}
        </tab-pane>
    }
}
