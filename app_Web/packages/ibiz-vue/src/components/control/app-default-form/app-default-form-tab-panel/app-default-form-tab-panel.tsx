import { IPSDEFormTabPanel } from '@ibiz/dynamic-model-api';
import { debounce } from 'ibiz-core';
import { Vue, Component, Prop, Inject, Watch } from 'vue-property-decorator';
import { AppDefaultFormDetail } from '../app-default-form-detail/app-default-form-detail';

/**
 * 表单UI组件
 *
 * @export
 * @class AppDefaultFormTabPanel
 * @extends {Vue}
 */
@Component({})
export class AppDefaultFormTabPanel extends AppDefaultFormDetail {
    /**
     * 表单成员实例对象
     *
     * @type {*}
     * @memberof AppDefaultFormDetail
     */
     @Prop() public detailsInstance!: IPSDEFormTabPanel;

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppDefaultFormTabPanel
     */
    public render(): any {
        const { detailClassNames } = this.renderOptions;
        let { codeName, caption, contentHeight, contentWidth } = this.detailsInstance;
        let style = { 
            height: contentHeight ? contentHeight + 'px' : false,
            width: contentWidth ? contentWidth + 'px' : false,
        };
        // 设置默认值
        let layoutmode = this.detailsInstance.getPSLayout()?.layout || 'TABLE_24COL';
        caption = caption || codeName;
        return (
            <tabs
                v-show={this.runtimeModel.visible}
                style={style}
                animated={false}
                name={codeName.toLowerCase()}
                class={{'app-tabpanel-flex': layoutmode == 'FLEX',...detailClassNames}}
                value={this.runtimeModel.activatedPage}
                on-on-click={(e: any) => {
                  debounce(this.runtimeModel?.clickPage,[e],this);
                }}
            >
                {this.$slots.default}
            </tabs>
        );
    }
}
