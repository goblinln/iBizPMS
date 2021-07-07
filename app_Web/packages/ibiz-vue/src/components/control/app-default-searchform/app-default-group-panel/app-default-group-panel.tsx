import { debounce } from 'ibiz-core';
import { Component, Prop } from 'vue-property-decorator';
import { IPSLanguageRes } from '@ibiz/dynamic-model-api';
import { AppDefaultSearchFormDetail } from '../app-default-searchform-detail/app-default-searchform-detail';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultGroupPanel
 * @extends {Vue}
 */
@Component({})
export class AppDefaultGroupPanel extends AppDefaultSearchFormDetail {

    /**
     * 表单数据
     * 
     * @type {*}
     * @memberof AppDefaultGroupPanel
     */
    @Prop() public data: any;

    /**
     * 分组面板界面行为点击事件回调
     *
     * @param {*} { item , event}
     * @memberof AppDefaultGroupPanel
     */
    public groupUIActionClick({ item , event }: any): void {
        let detail = this.detailsInstance?.uiActionGroup?.getPSUIActionGroupDetails?.find((groupDetail: any)=>{
            return item.name == `${this.detailsInstance?.name}_${groupDetail?.name}`;
        })
        if(detail){
            this.$emit('groupUIActionClick',event, this.detailsInstance, detail);
        }
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppDefaultGroupPanel
     */
    public render(): any {
        const { detailClassNames } = this.renderOptions;
        let { codeName, caption, getLayout, titleBarCloseMode, showCaption, infoGroupMode, getLabelPSSysCss } = this.detailsInstance;
        // 设置默认值
        let layoutMode = getLayout?.layout || 'TABLE_24COL';
        titleBarCloseMode = Number(titleBarCloseMode) || 0;
        let labelCaption: any = this.detailsInstance.captionItemName ? this.data[this.detailsInstance.captionItemName] : this.$tl((this.detailsInstance.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, caption);
        return (
            <app-form-group
                layoutType={layoutMode}
                caption={labelCaption}
                isShowCaption={showCaption}
                uiStyle='DEFAULT'
                titleBarCloseMode={titleBarCloseMode}
                titleStyle={getLabelPSSysCss?.cssName || ''}
                isInfoGroupMode={infoGroupMode}
                key={codeName}
                manageContainerStatus={this.runtimeModel?.manageContainerStatus}
                isManageContainer={this.runtimeModel?.isManageContainer}
                uiActionGroup={this.runtimeModel?.uiActionGroup}
                class={detailClassNames}
                on-groupuiactionclick={(e: any)=>{debounce(this.groupUIActionClick,[e],this)}}
                style={this.runtimeModel?.visible ? '' : 'display: none;'}
            >
                {this.$slots.default}
            </app-form-group>
        );
    }
}
