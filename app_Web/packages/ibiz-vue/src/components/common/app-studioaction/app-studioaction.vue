<script lang="tsx">
import { Vue, Component, Prop } from 'vue-property-decorator';
import { IPSAppView } from '@ibiz/dynamic-model-api';
import { modelTransferC } from '@ibiz/model-location';
import { AppServiceBase, StudioActionUtil, textCopy } from 'ibiz-core';

@Component({})
export default class AppStudioAction extends Vue {
    /**
     * 视图实例
     *
     * @type {IPSAppView}
     * @memberof AppStudioAction
     */
    @Prop()
    viewInstance!: IPSAppView;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppStudioAction
     */
    @Prop()
    context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppStudioAction
     */
    @Prop()
    viewparams!: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppStudioAction
     */
    @Prop()
    viewTitle!: string;

    /**
     * 是否为开发环境
     *
     * @type {boolean}
     * @memberof AppStudioAction
     */
    isDevMode: boolean = false;

    /**
     * 配置平台操作控制器
     *
     * @type {StudioActionUtil}
     * @memberof AppStudioAction
     */
    sdc: StudioActionUtil = StudioActionUtil.getInstance();
    // 是否悬浮在此Debug栏
    isSuspension: boolean = false;

    /**
     * 组件初始化
     *
     * @memberof AppStudioAction
     */
    created(): void {
        this.openPreview = this.openPreview.bind(this);
        this.mouseenter = this.mouseenter.bind(this);
        this.mouseleave = this.mouseleave.bind(this);
        let Environment: any = AppServiceBase.getInstance().getAppEnvironment();
        if (Environment) {
            this.isDevMode = Environment.devMode;
        }
    }

    /**
     * 打开视图
     *
     * @memberof AppStudioAction
     */
    async openPreview(e: MouseEvent): Promise<void> {
        await modelTransferC.open(
            e,
            this.viewInstance.getPSModelService(),
            this.viewInstance,
            this.context,
            this.viewparams,
        );
    }

    /**
     * 拷贝
     *
     * @memberof AppStudioAction
     */
    copy() {
        if (textCopy.copy(this.viewInstance.name)) {
            this.$message.success('拷贝成功!');
        }
    }

    mouseenter(e: MouseEvent): void {
        e.stopPropagation();
        this.isSuspension = true;
        this.showBorder();
    }

    mouseleave(e: MouseEvent): void {
        e.stopPropagation();
        this.isSuspension = false;
        this.hiddenBorder();
    }

    showBorder(): void {
        const ref = this.$refs.border as HTMLDivElement;
        ref.style.display = 'block';
    }

    hiddenBorder(): void {
        const ref = this.$refs.border as HTMLDivElement;
        ref.style.display = 'none';
    }

    /**
     * 绘制
     *
     * @memberof AppStudioAction
     */
    render(): any {
        const v = this.viewInstance;
        if (this.sdc.isShowTool && this.isDevMode) {
            return (
                <div class='app-studio-debug-bar-container'>
                    <div ref='border' class='app-studio-debug-bar-border'></div>
                    <div class='app-studio-debug-bar' on-mouseenter={this.mouseenter} on-mouseleave={this.mouseleave}>
                        <div class='app-studio-debug-info' title='点击拷贝视图名称' on-click={() => this.copy()}>
                            {this.viewTitle}（{v.name}）
                        </div>
                        <div class='app-studio-debug-actions'>
                            <i-button type='text' ghost size='small' on-click={this.openPreview}>
                                {this.$t('components.appstudioaction.view')}
                            </i-button>
                        </div>
                    </div>
                </div>
            );
        } else {
            return null;
        }
    }
}
</script>

<style lang="less">
@import './app-studioaction.less';
</style>
