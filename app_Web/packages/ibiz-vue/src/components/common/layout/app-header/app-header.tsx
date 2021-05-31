import { Vue, Component } from 'vue-property-decorator';
import { VNode } from 'vue';
import './app-header.less';

/**
 * 应用头部
 *
 * @export
 * @class AppHeader
 * @extends {Vue}
 */
@Component({})
export class AppHeader extends Vue {
    /**
     * 绘制内容
     *
     * @returns {VNode}
     * @memberof AppHeader
     */
    public render(h: any): VNode {
        return (
            <div class="app-header">
                <div class="app-header-left">
                    {this.$slots.header_left}
                    {Object.is(this.$uiState.layoutState.styleMode, 'STYLE2') ? (
                        <app-breadcrumb></app-breadcrumb>
                    ) : null}
                </div>
                <div class="app-header-center">{this.$slots.header_content}</div>
                <div class="app-header-right">{this.$slots.header_right}</div>
            </div>
        );
    }
}
