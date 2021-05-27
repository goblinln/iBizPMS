import { debounce } from 'ibiz-core';
import { Vue, Component, Prop, Inject, Watch } from 'vue-property-decorator';
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
        caption = caption || codeName;
        return (
            <app-form-group
                layoutType={layoutMode}
                caption={caption}
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
