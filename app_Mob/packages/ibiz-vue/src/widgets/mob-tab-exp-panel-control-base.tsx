import { MobTabExpPanelControlInterface } from "ibiz-core";
import { MainControlBase } from "./main-control-base";

/**
 * 分页面板部件基类
 *
 * @export
 * @class MobTabExpPanelControlBase
 * @extends {MainControlBase}
 */
export class MobTabExpPanelControlBase extends MainControlBase implements MobTabExpPanelControlInterface{

    /**
     * 被激活的分页面板
     *
     * @type {string}
     * @memberof MobTabExpPanelControlBase
     */
    protected activiedTabViewPanel?: string;


    /**
     * 行为参数
     *
     * @protected
     * @type {*}
     * @memberof MobTabExpPanelControlBase
     */
    protected action: any = '';

    /**
     * 部件初始化
     *
     * @param {*} [args]
     * @memberof MainControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: { tag: string, action: string, data: any }) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                this.action = action;
                this.activiedTabViewPanel = data.activeItem;
                if (data.activeItem) {
                    this.tabPanelClick(data.activeItem);
                } else {
                    this.$nextTick(() => {
                        let panel: any = this.activiedTabViewPanel;
                        if (panel) {
                            this.viewState.next({ tag: panel, action: this.action, data: {} });
                        }
                    });
                }
            });
        }
    }

    /**
     * 分页面板选中
     *
     * @param {*} $event
     * @returns
     * @memberof MobTabExpPanelControlBase
     */
    public tabPanelClick($event: any) {
        if (!$event) {
            return;
        }
        if (!this.viewState) {
            return;
        }
        this.$emit('ctrl-event', { controlname: this.controlInstance.name, action: "changepanel", data: $event });
        this.$nextTick(() => {
            let panel: any = this.activiedTabViewPanel
            if (panel) {
                this.viewState.next({ tag: panel, action: this.action, data: {} });
            }
        });
    }

    /**
     * 参数处理
     */
    public initRenderOptions() {
        super.initRenderOptions();
        this.activiedTabViewPanel = this.dynamicProps.activiedTabViewPanel;
    }
}