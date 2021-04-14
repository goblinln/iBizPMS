import { Vue, Component } from 'vue-property-decorator';
import { VNode } from 'vue';
import './app-layout.less';

/**
 * 应用布局容器
 *
 * @export
 * @class AppLayout
 * @extends {Vue}
 */
@Component({})
export class AppLayout extends Vue {

    /**
     * 绘制内容
     *
     * @returns {VNode}
     * @memberof AppLayout
     */
    public render(): VNode {
        return (
            <div
                class={{
                    'app-layout': true,
                    [this.$uiState.layoutState.styleMode.toLowerCase()]:
                    this.$uiState.layoutState.styleMode !== 'DEFAULT',
                }}
            >
                <div class="app-layout-header-warp">{this.$slots.header}</div>
                <div class="app-layout-content-warp">{this.$slots.default}</div>
                <div class="app-layout-footer-warp">
                    {this.$slots.footer}
                </div>
            </div>
        );
    }
}
