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

    contextArr: any = [];
    viewparamsArr: any = [];
    foldContext: boolean = false;
    foldViewparam: boolean = false;

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
        this.renderObjectInfo(this.context, this.contextArr);
        this.renderObjectInfo(this.viewparams, this.viewparamsArr);
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

    configView(): void {
        this.sdc.openStudioConfigView(this.viewInstance);
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

    copyValue(value: any) {
        if (textCopy.copy(value)) {
            this.$message.success('拷贝成功!');
        }
    }

    renderObjectInfo(items: any, arr: any) {
        for (let key of Object.keys(items)) {
            if (items[key]) {
                arr.push({ key, value: items[key].toString() });
            }
        }
        arr = arr.sort(function(a: any, b: any) {
            return (a.key + '').localeCompare(b.key + '');
        });
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
                    <poptip placement='left-start' trigger='hover' transfer popper-class='app-studioaction'>
                        <div
                            class='app-studio-debug-bar'
                            on-mouseenter={this.mouseenter}
                            on-mouseleave={this.mouseleave}
                        >
                            <div class='app-studio-debug-info' title='点击拷贝视图名称' on-click={() => this.copy()}>
                                {this.viewTitle}（{v.name}）
                            </div>
                            <div class='app-studio-debug-actions'>
                                <i-button
                                    type='text'
                                    title={this.$t('components.appstudioaction.configtitle')}
                                    ghost
                                    size='small'
                                    on-click={this.configView}
                                >
                                    {this.$t('components.appstudioaction.configbutton')}
                                </i-button>
                                <i-button type='text' ghost size='small' on-click={this.openPreview}>
                                    {this.$t('components.appstudioaction.view')}
                                </i-button>
                            </div>
                        </div>
                        <div slot='content'>
                            {
                                <div class='app-studio-debug-detail-wrapper'>
                                    <div class='app-studio-debug-detail'>
                                        <div class='detail-item-title'>
                                            <span
                                                class={{ tag: true, isfold: this.foldContext }}
                                                on-click={() => (this.foldContext = !this.foldContext)}
                                            >
                                                应用上下文 <icon type='ios-arrow-down' />
                                            </span>
                                        </div>
                                        <div class={{ 'detail-item-wrapper': true, isfold: this.foldContext }}>
                                            {this.contextArr && this.contextArr.length > 1
                                                ? this.contextArr.map((item: any) => (
                                                      <div class='detail-item'>
                                                          <span class='key-wrapper'>
                                                              <span
                                                                  title={item.key}
                                                                  class='key'
                                                                  on-click={() => this.copyValue(item.key)}
                                                              >
                                                                  {item.key}
                                                              </span>
                                                          </span>
                                                          <span class='value-wrapper'>
                                                              ：
                                                              <span
                                                                  title={item.value}
                                                                  class='value'
                                                                  on-click={() => this.copyValue(item.value)}
                                                              >
                                                                  {item.value}
                                                              </span>
                                                          </span>
                                                      </div>
                                                  ))
                                                : null}
                                        </div>
                                        <div class='detail-item-title'>
                                            <span
                                                class={{ tag: true, isfold: this.foldViewparam }}
                                                on-click={() => (this.foldViewparam = !this.foldViewparam)}
                                            >
                                                视图参数 <icon type='ios-arrow-down' />
                                            </span>
                                        </div>
                                        <div class={{ 'detail-item-wrapper': true, isfold: this.foldViewparam }}>
                                            {this.viewparamsArr && this.viewparamsArr.length > 1
                                                ? this.viewparamsArr.map((item: any) => (
                                                      <div class='detail-item'>
                                                          <span class='key-wrapper'>
                                                              <span
                                                                  title={item.key}
                                                                  class='key'
                                                                  on-click={() => this.copyValue(item.key)}
                                                              >
                                                                  {item.key}
                                                              </span>
                                                          </span>
                                                          <span class='value-wrapper'>
                                                              ：
                                                              <span
                                                                  title={item.value}
                                                                  class='value'
                                                                  on-click={() => this.copyValue(item.value)}
                                                              >
                                                                  {item.value}
                                                              </span>
                                                          </span>
                                                      </div>
                                                  ))
                                                : null}
                                        </div>
                                    </div>
                                </div>
                            }
                        </div>
                    </poptip>
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
