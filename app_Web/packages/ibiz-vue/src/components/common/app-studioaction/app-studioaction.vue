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
     * 是否为开发环境
     *
     * @type {boolean}
     * @memberof AppStudioAction
     */
    public isDevMode: boolean = false;

    /**
     * 配置平台操作控制器
     *
     * @type {StudioActionUtil}
     * @memberof AppStudioAction
     */
    public sdc: StudioActionUtil = StudioActionUtil.getInstance();

    /**
     * 组件初始化
     *
     * @memberof AppStudioAction
     */
    created(): void {
        this.openPreview = this.openPreview.bind(this);
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
    async openPreview(): Promise<void> {
        await modelTransferC.open(
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

    /**
     * 绘制
     *
     * @memberof AppStudioAction
     */
    render(): any {
        const v = this.viewInstance;
        if (this.sdc.isShowTool && this.isDevMode) {
            return (
                <div class='app-studio-debug-bar'>
                    <div class='app-studio-debug-info' title='点击拷贝视图名称' on-click={() => this.copy()}>
                        {v.title}（{v.name}）
                    </div>
                    <div class='app-studio-debug-actions'>
                        <i-button type='text' ghost size='small' on-click={this.openPreview}>
                            查看
                        </i-button>
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
