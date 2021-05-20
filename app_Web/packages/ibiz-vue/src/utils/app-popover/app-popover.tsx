import Vue, { CreateElement } from 'vue';
import { Subject, Observable } from 'rxjs';
import { createPopper, Instance } from '@popperjs/core/lib/popper-lite.js';
import preventOverflow from '@popperjs/core/lib/modifiers/preventOverflow.js';
import flip from '@popperjs/core/lib/modifiers/flip.js';
import { Placement } from '@popperjs/core/lib/enums';
import { AppServiceBase, on, Util } from 'ibiz-core';
import './app-popover.less';

/**
 * 悬浮窗控制器实例
 *
 * @export
 * @class AppPopover
 */
export class AppPopover {
    /**
     * 实例
     *
     * @private
     * @static
     * @memberof AppPopover
     */
    private static readonly $popover = new AppPopover();

    /**
     * vue实例
     *
     * @private
     * @type {Vue}
     * @memberof AppPopover
     */
    private vueExample!: Vue;

    /**
     * store对象
     *
     * @private
     * @memberof AppPopover
     */
    private store: any;

    /**
     * i18n对象
     *
     * @private
     * @memberof AppPopover
     */
    private i18n: any;

    /**
     * PopperJs实例
     *
     * @private
     * @type {Instance}
     * @memberof AppPopover
     */
    private popperExample?: Instance;

    /**
     * 是否显示悬浮窗
     *
     * @private
     * @type {boolean}
     * @memberof AppPopover
     */
    private showPopper: boolean = false;

    /**
     * 是否在点击空白区域时自动关闭
     *
     * @private
     * @type {boolean}
     * @memberof AppPopover
     */
    private isAutoClose: boolean = true;

    /**
     * 是否在点击空白区域时自动关闭
     *
     * @private
     * @type {boolean}
     * @memberof AppPopover
     */
    private isMoveOutClose: boolean = false;

    /**
     * 当前显示层级
     *
     * @private
     * @type {number}
     * @memberof AppPopover
     */
    private zIndex: number = 0;

    /**
     * Creates an instance of AppPopover.
     * @memberof AppPopover
     */
    constructor() {
        this.initBasicData();
        if (AppPopover.$popover) {
            return AppPopover.$popover;
        }
    }

    /**
     * 初始化基础数据
     * 
     * @memberof AppPopover
     */
    private initBasicData() {
        const appService = AppServiceBase.getInstance();
        this.store = appService.getAppStore();
        this.i18n = appService.getI18n();
    }

    /**
     * 初始化vue实例
     *
     * @private
     * @returns {void}
     * @memberof AppPopover
     */
    private initVueExample(customClass?: any): void {
        const self = this;
        if (!self.store || !self.i18n) {
            self.initBasicData();
        }
        const container = document.createElement('div');
        container.className = 'app-popover-wrapper';
        on(container, 'click', () => {
            if (!this.showPopper || !this.isAutoClose) {
                return;
            }
            this.popperDestroy();
        });
        const div = document.createElement('div');
        container.appendChild(div);
        document.body.appendChild(container);
        this.vueExample = new Vue({
            el: div,
            store: this.store,
            i18n: this.i18n,
            data: { content: null, width: 300, height: 300 },
            methods: {
                click(e: MouseEvent) {
                    e.stopPropagation();
                },
                mouseout(e: MouseEvent) {
                    (this.$apppopover as any).mouseOutDestory()
                }
            },
            render(h: CreateElement) {
                const content: any = this.content;
                container.style.zIndex = (self.zIndex - 1).toString();
                let customStyle: any = {'z-index': self.zIndex};
                if(Util.isNumber(this.width)){
                    customStyle.width = this.width + 'px';
                }
                if(Util.isNumber(this.height)){
                    customStyle.height = this.height + 'px';
                }
                return <div v-show="self.showPopper" style={customStyle} class={`app-popover app-popper${customClass ? ' ' + customClass : ''}`} on-click={this.click} on-mouseleave={this.mouseout}>{(self.showPopper && content) ? content(h) : null}</div>;
            }
        });
    }

    /**
     * 打开悬浮窗
     *
     * @param {MouseEvent} event 事件
     * @param {*} view 视图
     * @param {*} [context={}] 应用上下文参数
     * @param {*} data 行为参数
     * @param {Placement} position 显示位置
     * @param {boolean} isAutoClose 是否可自动关闭
     * @returns {Observable<any>}
     * @memberof AppPopover
     */
    public openPop(event: any, view: any, context: any = {}, data: any, position?: Placement, isAutoClose?: boolean): Observable<any> {
        const subject = new Subject<any>();
        if (!event) {
            console.error("事件触发源无值，强制返回");
            return subject.asObservable();
        }
        if (!view.width) view.width = 300;
        if (!view.height) view.height = 300;
        this.openPopover(event, (h: CreateElement) => {
            return h(view.viewname, {
                props: {
                    staticProps: { viewDefaultUsage: false, noViewCaption: true},
                    dynamicProps: { viewdata: JSON.stringify(context), viewparam: JSON.stringify(data) }
                },
                on: {
                    close: (result: any) => {
                        subject.next({ ret: 'OK', datas: result });
                        subject.complete();
                        subject.unsubscribe();
                    }
                }
            })
        }, position, isAutoClose, view.width, view.height);
        return subject.asObservable();
    }

    /**
     * 打开悬浮窗
     *
     * @param {*} event
     * @param {(h: CreateElement) => any} [content]
     * @param {Placement} [position='left']
     * @param {boolean} [isAutoClose=true]
     * @param {number} [width=300]
     * @param {number} [height=300]
     * @memberof AppPopover
     */
    public openPopover(event: any, content?: (h: CreateElement) => any, position: Placement = 'left-end', isAutoClose: boolean = true, width: number = 300, height: number = 300, customClass?: any): void {
        // 阻止事件冒泡
        event.stopPropagation();
        const element: Element = event.toElement || event.srcElement;
        if (!this.vueExample) {
            this.initVueExample(customClass);
        }
        this.popperDestroy();
        const zIndex = this.vueExample.$store.getters.getZIndex();
        if (zIndex) {
            this.zIndex = zIndex + 100;
            this.vueExample.$store.commit('updateZIndex', this.zIndex);
        }
        // 是否可自动关闭
        this.isAutoClose = isAutoClose;
        // 更新vue实例内容
        this.showPopper = true;
        Object.assign(this.vueExample.$data, { content, width, height, zIndex: this.zIndex });
        const el: any = this.vueExample.$el;
        this.popperExample = createPopper(element, el, {
            placement: position,
            strategy: 'absolute',
            modifiers: [preventOverflow, flip]
        });
        this.vueExample.$forceUpdate();
    }
    
    /**
     * 打开悬浮窗(自定义modefiers)
     *
     * @param {*} event
     * @param {(h: CreateElement) => any} [content]
     * @param {Placement} [position='left-end']
     * @param {boolean} [isAutoClose=true]
     * @param {number} [width]
     * @param {number} [height]
     * @param {*} [customClass]
     * @param {any[]} [modifiers=[]]
     * @memberof AppPopover
     */
     public openPopover2(event: any, content?: (h: CreateElement) => any, position: Placement = 'left-end', isAutoClose: boolean = true, isMoveOutClose: boolean = false, width?: number, height?: number, customClass?: any, modifiers: any[] = []): void {
        // 阻止事件冒泡
        event.stopPropagation();
        const element: Element = event.toElement || event.srcElement;
        if (!this.vueExample) {
            this.initVueExample(customClass);
        }
        this.popperDestroy();
        const zIndex = this.vueExample.$store.getters.getZIndex();
        if (zIndex) {
            this.zIndex = zIndex + 100;
            this.vueExample.$store.commit('updateZIndex', this.zIndex);
        }
        // 是否可自动关闭
        this.isAutoClose = isAutoClose;
        this.isMoveOutClose = isMoveOutClose;
        // 更新vue实例内容
        this.showPopper = true;
        Object.assign(this.vueExample.$data, { content, width: width, height: height, zIndex: this.zIndex });
        const el: any = this.vueExample.$el;
        this.popperExample = createPopper(element, el, {
            placement: position,
            strategy: 'absolute',
            modifiers: [...modifiers]
        });
        this.vueExample.$forceUpdate();
    }

    /**
     * 销毁popper(带回填数据)
     *
     * @memberof AppPopover
     */
    public popperDestroy(): void {
        if (this.popperExample) {
            this.popperExample.destroy();
            if (this.zIndex !== 0) {
                const zIndex: any = this.zIndex;
                this.vueExample.$store.commit('updateZIndex', zIndex - 100);
                this.zIndex = 0;
            }
            this.showPopper = false;
            this.vueExample.$forceUpdate();
        }
    }

    /**
     * 销毁popper(当鼠标移出时)
     *
     * @memberof AppPopover
     */
    public mouseOutDestory(): void {
        if (this.showPopper && this.isMoveOutClose) {
            this.popperDestroy();
        }
    }

    /**
     * 获取实例
     *
     * @static
     * @memberof AppPopover
     */
    public static getInstance() {
        return AppPopover.$popover;
    }

}