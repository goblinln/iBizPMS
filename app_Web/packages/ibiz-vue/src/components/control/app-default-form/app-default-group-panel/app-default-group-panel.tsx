import { IPSDEFormGroupPanel, IPSUIActionGroupDetail, IPSLanguageRes } from '@ibiz/dynamic-model-api';
import { throttle } from 'ibiz-core';
import { Component, Prop } from 'vue-property-decorator';
import { AppDefaultFormDetail } from '../app-default-form-detail/app-default-form-detail';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultGroupPanel
 * @extends {Vue}
 */
@Component({})
export class AppDefaultGroupPanel extends AppDefaultFormDetail {
    /**
     * 表单成员实例对象
     *
     * @type {*}
     * @memberof AppDefaultFormDetail
     */
     @Prop() public detailsInstance!: IPSDEFormGroupPanel;

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
        let detail = this.detailsInstance?.getPSUIActionGroup()?.getPSUIActionGroupDetails()?.find((groupDetail: IPSUIActionGroupDetail)=>{
            return item.name == `${this.detailsInstance.name}_${groupDetail.name}`;
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
        let { codeName, caption, titleBarCloseMode, showCaption, infoGroupMode, detailStyle } = this.detailsInstance;
        let layout = this.detailsInstance.getPSLayout();
        let iconInfo = this.detailsInstance.getPSSysImage();
        // 设置默认值
        let layoutMode = layout?.layout || 'TABLE_24COL';
        titleBarCloseMode = Number(titleBarCloseMode) || 0;
        let labelCaption: any = this.detailsInstance.captionItemName ? this.data[this.detailsInstance.captionItemName.toLowerCase()] : this.$tl((this.detailsInstance.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, caption);
        return (
            <app-form-group
                layoutType={layoutMode}
                caption={labelCaption}
                isShowCaption={showCaption && detailStyle != "STYLE2"}
                uiStyle='DEFAULT'
                titleBarCloseMode={titleBarCloseMode}
                titleStyle={this.detailsInstance.getLabelPSSysCss()?.cssName || ''}
                isInfoGroupMode={infoGroupMode}
                key={codeName}
                iconInfo={iconInfo}
                manageContainerStatus={this.runtimeModel?.manageContainerStatus}
                isManageContainer={this.runtimeModel?.isManageContainer}
                uiActionGroup={this.runtimeModel?.uiActionGroup}
                class={{...detailClassNames, [`form-group-${detailStyle.toLowerCase()}`]: true}}
                on-groupuiactionclick={(e: any)=>{throttle(this.groupUIActionClick,[e],this)}}
                on-managecontainerclick={() => { throttle(this.$emit,['managecontainerclick', this.runtimeModel.name],this) }}
                style={this.runtimeModel?.visible ? '' : 'display: none;'}
                scopedSlots={{
                    default: () => {
                        return this.$slots.default;
                    },
                    "dataInfoPanel": () => {
                        return <app-form-group-data-panel
                            detailModel={this.runtimeModel}
                            data={this.data}
                            context={this.context}
                            viewparams={this.viewparams}
                            on-input={()=>{}}
                        />
                    }
                }}
            >
            </app-form-group>
        );
    }
}
