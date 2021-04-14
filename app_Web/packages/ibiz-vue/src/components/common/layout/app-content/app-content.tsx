import { Vue, Component, Watch } from 'vue-property-decorator';
import { on } from '../../../../utils';
import { VNode } from 'vue';
import './app-content.less';

/**
 * 应用头部
 *
 * @export
 * @class AppContent
 * @extends {Vue}
 */
@Component({})
export class AppContent extends Vue {
    /**
     * Creates an instance of AppContent.
     * @memberof AppContent
     */
    constructor() {
        super();
        on(window, 'keydown', (e: KeyboardEvent) => {
            if (e.ctrlKey && e.keyCode === 192) {
                this.changeBottom();
            }
        });
    }

    /**
     * 组件创建完毕
     *
     * @protected
     * @memberof AppContent
     */
    protected created(): void {
        if (this.$slots.content_bottom) {
            this.$footerRenderService.registerRightItem(() => {
                return (
                    <div
                        title="「打开/关闭」底部分页 [ctrl + `]"
                        class="action-item"
                        on-click={() => this.changeBottom()}
                    >
                        <svg
                            t="1562669728550"
                            viewBox="0 0 1024 1024"
                            version="1.1"
                            xmlns="http://www.w3.org/2000/svg"
                            p-id="1118"
                            width="18"
                            height="18"
                            class="icon"
                        >
                            <path
                                d="M170.666667 170.666667h682.666666a85.333333 85.333333 0 0 1 85.333334 85.333333v512a85.333333 85.333333 0 0 1-85.333334 85.333333H170.666667a85.333333 85.333333 0 0 1-85.333334-85.333333V256a85.333333 85.333333 0 0 1 85.333334-85.333333z m0 85.333333v512h682.666666V256H170.666667z m341.333333 341.333333h256v85.333334h-256v-85.333334z m-43.306667-103.637333L317.866667 644.565333l-60.330667-60.373333 90.453333-90.453333-90.453333-90.538667L317.866667 342.869333l150.826666 150.826667z"
                                p-id="1119"
                            ></path>
                        </svg>
                    </div>
                );
            }, 0);
        }
    }

    /**
     * 内容区底部区域，显示变更
     *
     * @protected
     * @param {boolean} [judge]
     * @memberof AppContent
     */
    protected changeBottom(judge?: boolean): void {
        if (judge !== undefined) {
            this.$uiState.changeLayoutState({
                contentBottomShow: judge,
            });
        } else {
            this.$uiState.changeLayoutState({
                contentBottomShow: !this.$uiState.layoutState.contentBottomShow,
            });
        }
    }

    /**
     * 绘制内容
     *
     * @protected
     * @param {boolean} isSlot
     * @returns {*}
     * @memberof AppContent
     */
    protected renderContent(isSlot: boolean): any {
        return (
            <div
                slot={isSlot ? 'right' : null}
                class={{
                    'app-content-right': true,
                    'hidden-bottom': !this.$uiState.layoutState.contentBottomShow || !this.$slots.content_bottom,
                }}
            >
                <split mode="vertical" v-model={this.$uiState.layoutState.contentVerticalSplit} max={0.1}>
                    <div slot="top" class="app-content-exp">
                        {this.$slots.default}
                    </div>
                    {this.$slots.content_bottom ? (
                        <div slot="bottom" class="app-content-bottom">
                            <div class="app-content-bottom-close" on-click={() => this.changeBottom(false)}>
                                <icon type="ios-arrow-down" />
                            </div>
                            {this.$slots.content_bottom}
                        </div>
                    ) : null}
                </split>
            </div>
        );
    }

    /**
     * 绘制内容
     *
     * @returns {VNode}
     * @memberof AppContent
     */
    public render(): VNode {
        let content: any = null;
        if (this.$uiState.layoutState.styleMode === 'STYLE2') {
            content = [<div class="app-content-nav">{this.$slots.content_left}</div>, this.renderContent(false)];
        } else {
            content = this.$slots.content_left ? (
                <split
                    class={{ 'app-content-split': true, 'hidden-left': !this.$uiState.layoutState.leftExpContentShow }}
                    v-model={this.$uiState.layoutState.contentHorizontalSplit}
                    min={0.1}
                    max={0.5}
                >
                    <div slot="left" class="app-content-left">
                        {this.$slots.content_left}
                    </div>
                    {this.renderContent(true)}
                </split>
            ) : (
                this.renderContent(false)
            );
        }
        return <div class="app-content">{content}</div>;
    }
}