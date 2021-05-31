import { IPSDEFormGroupPanel, IPSUIActionGroupDetail } from '@ibiz/dynamic-model-api';
import { debounce } from 'ibiz-core';
import { Vue, Component, Prop, Inject, Watch } from 'vue-property-decorator';
import { AppViewLogicService } from '../../../../app-service';
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
        let { codeName, caption, titleBarCloseMode, showCaption, infoGroupMode } = this.detailsInstance;
        let layout = this.detailsInstance.getPSLayout();
        // 设置默认值
        let layoutMode = layout?.layout || 'TABLE_24COL';
        titleBarCloseMode = Number(titleBarCloseMode) || 0;
        caption = caption || codeName;
        return (
            <app-form-group
                layoutType={layoutMode}
                caption={caption}
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
                on-groupuiactionclick={(e: any)=>{debounce(this.groupUIActionClick,[e],this)}}
                on-managecontainerclick={() => { debounce(this.$emit,['managecontainerclick', this.runtimeModel.name],this) }}
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
