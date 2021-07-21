<script lang="tsx">
import { Vue, Component, Prop } from 'vue-property-decorator';
import { IPSAppView } from '@ibiz/dynamic-model-api';
import { modelTransferC } from '@ibiz/model-location';
import { AppServiceBase, StudioActionUtil, textCopy } from 'ibiz-core';
import interact from 'interactjs';

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
     * 气泡框显示状态
     *
     * @type {boolean}
     * @memberof AppStudioAction
     */
    poptipValue: boolean = false;

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
     * 拖拽按钮移入事件
     *
     * @memberof AppStudioAction
     */
    dragHandleMove($event: any) {
        $event.stopPropagation();
        this.poptipValue = false;
    }

    public mounted(): void {
        if (this.$refs.dragHandle) {
            interact(this.$refs.dragHandle as any).draggable({
                inertia: true,
                modifiers: [
                    interact.modifiers.restrictRect({
                        restriction: this.$refs.debugBar as any,
                        endOnly: true,
                    }),
                ],
                autoScroll: true,
                listeners: {
                    move: (event: any) => {
                        const target = event.target;
                        const x = (parseFloat(target.getAttribute('data-x')) || 0) + event.dx;
                        const y = (parseFloat(target.getAttribute('data-y')) || 0) + event.dy;
                        const container: any = this.$refs.container;
                        container.style.webkitTransform = container.style.transform =
                            'translate(' + x + 'px, ' + y + 'px)';
                        target.setAttribute('data-x', x);
                        target.setAttribute('data-y', y);
                    },
                },
            });
        }
    }

    /**
     * 绘制
     *
     * @memberof AppStudioAction
     */
    render(): any {
        const v = this.viewInstance;
        if (this.isDevMode) {
            return (
                <div
                    class='app-studio-debug-bar-container'
                    ref='debugBar'
                    style={{ display: this.sdc.isShowTool ? 'block' : 'none' }}
                >
                    <div ref='border' class='app-studio-debug-bar-border'></div>
                    <div ref='container' class='debug-container'>
                        <poptip
                            v-model={this.poptipValue}
                            placement='left-start'
                            trigger='hover'
                            transfer
                            popper-class='app-studioaction'
                        >
                            <div
                                class='app-studio-debug-bar'
                                on-mouseenter={this.mouseenter}
                                on-mouseleave={this.mouseleave}
                            >
                                <div
                                    class='app-studio-debug-info'
                                    title='点击拷贝视图名称'
                                    on-click={() => this.copy()}
                                >
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
                                <div
                                    class='drag-handle'
                                    ref='dragHandle'
                                    on-mousemove={($event: any) => this.dragHandleMove($event)}
                                >
                                    <div class='drag-btn'>
                                        <svg
                                            viewBox='64 64 896 896'
                                            data-icon='drag'
                                            width='18px'
                                            height='18px'
                                            fill='currentColor'
                                            aria-hidden='true'
                                            focusable='false'
                                            class=''
                                        >
                                            <path d='M909.3 506.3L781.7 405.6a7.23 7.23 0 0 0-11.7 5.7V476H548V254h64.8c6 0 9.4-7 5.7-11.7L517.7 114.7a7.14 7.14 0 0 0-11.3 0L405.6 242.3a7.23 7.23 0 0 0 5.7 11.7H476v222H254v-64.8c0-6-7-9.4-11.7-5.7L114.7 506.3a7.14 7.14 0 0 0 0 11.3l127.5 100.8c4.7 3.7 11.7.4 11.7-5.7V548h222v222h-64.8c-6 0-9.4 7-5.7 11.7l100.8 127.5c2.9 3.7 8.5 3.7 11.3 0l100.8-127.5c3.7-4.7.4-11.7-5.7-11.7H548V548h222v64.8c0 6 7 9.4 11.7 5.7l127.5-100.8a7.3 7.3 0 0 0 .1-11.4z'></path>
                                        </svg>
                                    </div>
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
