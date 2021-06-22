import { IPSDEFormGroupPanel, IPSUIActionGroupDetail } from '@ibiz/dynamic-model-api';
import { Component, Prop } from 'vue-property-decorator';
import { AppDefaultMobFormDetail } from '../app-default-mob-form-detail/app-default-mob-form-detail';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultMobGroupPanel
 * @extends {Vue}
 */
@Component({})
export class AppDefaultMobGroupPanel extends AppDefaultMobFormDetail {

    /**
     * 表单成员实例对象
     *
     * @type {*}
     * @memberof AppDefaultFormDetail
     */
    @Prop() public detailsInstance!: IPSDEFormGroupPanel;


    /**
     * 分组面板界面行为点击事件回调
     *
     * @param {*} { item , event}
     * @memberof AppDefaultMobGroupPanel
     */
    public groupUIActionClick({ item, event }: any): void {
        let detail = this.detailsInstance?.getPSUIActionGroup()?.getPSUIActionGroupDetails()?.find((groupDetail: IPSUIActionGroupDetail) => {
            return item.name == `${this.detailsInstance.name}_${groupDetail.name}`;
        })
        if (detail) {
            this.$emit('groupUIActionClick', event, this.detailsInstance, detail);
        }
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppDefaultMobGroupPanel
     */
    public render(): any {
        const { detailClassNames } = this.renderOptions;
        let { codeName, caption, titleBarCloseMode, showCaption, infoGroupMode } = this.detailsInstance;
        let layout = this.detailsInstance.getPSLayout();
        // 设置默认值
        let layoutMode = layout?.layout || 'TABLE_24COL';
        titleBarCloseMode = Number(titleBarCloseMode) || 0;
        caption = caption || codeName;
        let labelCaption: any = this.$tl(this.detailsInstance.getCapPSLanguageRes()?.lanResTag,caption);
        return (
            <app-form-group
                layoutType={layoutMode}
                caption={labelCaption}
                isShowCaption={showCaption}
                uiStyle='DEFAULT'
                titleBarCloseMode={titleBarCloseMode}
                titleStyle={this.detailsInstance.getLabelPSSysCss()?.cssName || ''}
                isInfoGroupMode={infoGroupMode}
                key={codeName}
                manageContainerStatus={this.runtimeModel?.manageContainerStatus}
                isManageContainer={this.runtimeModel?.isManageContainer}
                uiActionGroup={this.runtimeModel?.uiActionGroup}
                class={detailClassNames}
                on-groupuiactionclick={(e: any) => { this.groupUIActionClick(e) }}
                style={this.runtimeModel?.visible ? '' : 'display: none;'}
            >
                {this.$slots.default}
            </app-form-group>
        );
    }
}
