import { Vue, Component, Prop, Inject, Watch } from 'vue-property-decorator';
import { AppDefaultFormDetail } from '../app-default-form-detail/app-default-form-detail';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultFormTabPage
 * @extends {Vue}
 */
@Component({})
export class AppDefaultFormTabPage extends AppDefaultFormDetail {
    /**
     * 绘制表单分页标题
     *
     * @param {*} viewPanel
     * @returns
     * @memberof AppDefaultFormTabPage
     */
    public renderLabel() {
        const { getLabelPSSysCss, getPSSysImage, caption } = this.detailsInstance;
        let labelClass = getLabelPSSysCss?.cssName ? 'caption ' + getLabelPSSysCss.cssName : 'caption';
        let labelContent = caption;
        if(getPSSysImage){
            if(getPSSysImage?.imagePath == ''){
                labelContent = <i class={getPSSysImage?.cssClass} style={{'margin-right' : '2px'}}></i>
            }else{
                labelContent = <img src={getPSSysImage?.imagePath} style={{'margin-right' : '2px'}}></img>
            }
        }else{
            // todo 多语言标题
        }
        return <span class={labelClass}>
            {labelContent}
        </span>
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppDefaultFormTabPage
     */
    public render(): any {
        const { detailClassNames } = this.renderOptions;
        const { codeName } = this.detailsInstance;
        const { appDataEntity, codeName: formCodeName, controlType } = this.controlInstance;
        const tabsName = `${appDataEntity.codeName.toLowerCase()}_${controlType?.toLowerCase()}_${formCodeName.toLowerCase()}`;
        return  <tab-pane label={this.renderLabel} name={codeName} index={this.index} tab={tabsName} class={detailClassNames}>
            {this.$slots.default}
        </tab-pane>
    }
}
