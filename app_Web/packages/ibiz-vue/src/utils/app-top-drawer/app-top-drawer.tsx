import { Vue, Component, Watch } from 'vue-property-decorator';
import { CreateElement } from 'vue/types/umd';
import { on, LogUtil } from 'ibiz-core';
import './app-top-drawer.less';

/**
 * 模态承载组件
 *
 * @export
 * @class AppTopDrawer
 * @extends {Vue}
 */
@Component({})
export class AppTopDrawer extends Vue {
    /**
     * 已呈现视图列表
     *
     * @protected
     * @type {any[]}
     * @memberof AppTopDrawer
     */
    protected viewList: any[] = [];

    /**
     * 为了视觉效果，当前列表为显示的视图
     *
     * @protected
     * @type {any[]}
     * @memberof AppTopDrawer
     */
    protected showViewList: any[] = [];

    /**
     * 关闭模态数据传递
     *
     * @protected
     * @type {*}
     * @memberof AppTopDrawer
     */
    protected closeModalData: any;

    /**
     * 待关闭视图
     *
     * @protected
     * @type {any[]}
     * @memberof AppTopDrawer
     */
    protected toBeClosedViews: any[] = [];

    /**
     * 是否展示模态
     *
     * @protected
     * @type {boolean}
     * @memberof AppTopDrawer
     */
    protected isShow: boolean = false;


    /**
     * 是否显示蒙层
     *
     * @memberof AppTopDrawer
     */
    public isShowDialogContainer: boolean = false;

    /**
     * 监控模态展示状态变更
     *
     * @memberof AppTopDrawer
     */
    @Watch('isShow')
    public isShowWatch(newVal: boolean, oldVal: boolean): void {
        if (newVal !== oldVal && newVal === false) {
            this.zIndex -= 1;
            this.$store.commit('updateZIndex', this.zIndex);
        }else{
             // 滑动动画时间结束后关闭蒙层
            setTimeout(() => {
                this.isShowDialogContainer = false;
            }, 550);
        }
    }

    /**
     * 视图层级
     *
     * @protected
     * @type {number}
     * @memberof AppTopDrawer
     */
    protected zIndex: number = 100;

    /**
     * 当前激活项下标
     *
     * @protected
     * @type {number}
     * @memberof AppTopDrawer
     */
    protected activeIndex: number = 0;

    /**
     * 组件创建完毕
     *
     * @protected
     * @memberof AppTopDrawer
     */
    protected created(): void {
        on(document, 'keydown', ($event: KeyboardEvent) => {
            if ($event && $event.keyCode === 27 && this.viewList.length > 0) {
                const zIndex = this.$store.getters.getZIndex();
                if (zIndex !== this.zIndex) {
                    return;
                }
                this.refCloseView(this.viewList[this.viewList.length - 1], this.viewList.length - 1);
            }
        });
    }

    /**
     * 使用实例方法关闭视图
     *
     * @protected
     * @param {*} view
     * @param {number} i
     * @returns {*}
     * @memberof AppTopDrawer
     */
    protected refCloseView(view: any, i: number): any {
        const ref: any = this.$refs[view.viewname + i];
        if (ref) {
            if (view.dynamicProps) {
                this.closeModalData = [];
            }
            ref.$listeners.close();
        }
    }

    /**
     * 打开模态视图
     *
     * @param {*} [param={}]
     * @returns {Promise<any>}
     * @memberof AppTopDrawer
     */
    public openModal(param: any = {}): Promise<any> {
        if(this.viewList.length === 0){
            this.isShowDialogContainer = true;
        }
        return new Promise((resolve: (res: any) => void) => {
            if (!this.isShow) {
                const zIndex: number = this.$store.getters.getZIndex();
                if (zIndex) {
                    this.zIndex = zIndex + 1;
                    this.$store.commit('updateZIndex', this.zIndex);
                }
                setTimeout(() => (this.isShow = true), 50);
            }
            this.viewList.push(Object.assign(param, { resolve }));
            this.showViewList.push(this.viewList[this.viewList.length - 1]);
            this.activeIndex = this.viewList.length - 1;
        });
    }

    /**
     * 关闭模态视图
     *
     * @memberof AppTopDrawer
     */
    public closeView(item: any): void {
        this.isShowDialogContainer = false;
        if (this.closeModalData && this.closeModalData.length > 0 && this.closeModalData[0] !== undefined) {
            item.resolve({ ret: 'OK', datas: this.closeModalData });
        } else {
            item.resolve({ ret: '', datas: [] });
        }
        this.viewList.pop();
        this.activeIndex = this.viewList.length - 1;
        setTimeout(() => {
            this.showViewList.pop();
        }, 300);
        if (this.toBeClosedViews.length > 0) {
            const view: any = this.toBeClosedViews[this.toBeClosedViews.length - 1];
            const viewname: string = view.viewname;
            const i: number = view.index;
            this.toBeClosedViews.pop();
            this.refCloseView({ viewname }, i);
        }
    }

    /**
     * 关闭指定下标之前的所有页面
     *
     * @protected
     * @memberof AppTopDrawer
     */
    protected closeByIndex(i: number): void {
        this.toBeClosedViews = [];
        for (let index = i + 1; index < this.viewList.length - 1; index++) {
            this.toBeClosedViews.push({
                viewname: this.viewList[index].viewname,
                index: index,
            });
        }
        this.refCloseView(this.viewList[this.viewList.length - 1], this.viewList.length - 1);
    }

    /**
     * 绘制标题
     *
     * @protected
     * @returns {*}
     * @memberof AppTopDrawer
     */
    protected renderHeader(): any {
        return (
            <div class="studio-drawer-header">
                <div class="studio-drawer-breadcrumb">
                    <div class="studio-drawer-back" on-click={() => this.closeByIndex(this.viewList.length - 1)}>
                        <icon type="ios-arrow-back" />
                        返回
                    </div>
                    {this.showViewList.map((item, i) => {
                        const ref: any = this.$refs[item.viewname + i];
                        if (!ref || !ref.$children || ref.$children.length <= 0) {
                            setTimeout(() => {
                                this.$forceUpdate();
                            }, 300);
                            return;
                        }
                        const title = ref.$children[0]?.model?.dataInfo ? ref.$children[0]?.model?.dataInfo : item.title;
                        return (
                            <span key={i}>
                                {i !== 0 ? <span class="studio-drawer-breadcrumb-item-separator">&gt;</span> : null}
                                <span
                                    class={{ text: true, active: i === this.showViewList.length - 1 }}
                                    on-click={() => {
                                        if (this.showViewList.length === i + 1) {
                                            return;
                                        }
                                        this.closeByIndex(i);
                                    }}
                                >
                                    {title}
                                </span>
                            </span>
                        );
                    })}
                </div>
                {this.viewList.length > 1 ? (
                    <poptip
                        class="close"
                        confirm
                        placement="left-start"
                        title="确认关闭所有界面?"
                        on-on-ok={() => this.closeByIndex(-1)}
                    >
                        <icon title="关闭所有视图" type="md-close" />
                    </poptip>
                ) : (
                    <div class="close" on-click={() => this.closeByIndex(-1)}>
                        <icon title="关闭所有视图" type="md-close" />
                    </div>
                )}
            </div>
        );
    }

    /**
     * 绘制视图
     *
     * @protected
     * @param {CreateElement} h
     * @returns {*}
     * @memberof AppTopDrawer
     */
    protected renderViews(h: CreateElement): any {
        return this.showViewList.map((view: any, i: number) => {
            try {
                const props: any = {
                    staticProps: { ...view.staticProps, viewDefaultUsage: false, noViewCaption: true },
                    dynamicProps: { ...view.dynamicProps }
                };
                const style: any = { 'z-index': i + 1, 'height': '100%' };
                return (
                    <div class={{ 'studio-drawer-item': true, active: this.activeIndex === i }}>
                        {h(view.viewname, {
                            key: view.viewname + i,
                            ref: view.viewname + i,
                            style,
                            props,
                            on: {
                                viewdataschange: (data: any) => {
                                    this.closeModalData = data;
                                },
                                close: () => {
                                    if (this.viewList.length - 1 < i) {
                                        return;
                                    }
                                    if (this.viewList.length === 1) {
                                        this.isShow = false;
                                        setTimeout(() => this.closeView(view), 500);
                                    } else {
                                        this.closeView(view);
                                    }
                                },
                                viewModelChange: () => {
                                    this.$forceUpdate();
                                },
                            },
                        })}
                    </div>
                );
            } catch (err) {
                LogUtil.warn('上飘窗打开视图参数转换异常', err);
            }
        });
    }

    /**
     * 绘制模态内容
     *
     * @returns {*}
     * @memberof AppTopDrawer
     */
    public render(h: CreateElement): any {
        return (
            <div style={{ 'z-index': this.zIndex+1}}>
                <div class='dialogContainer' style={{ 'z-index': this.zIndex+1, 'display': this.isShowDialogContainer ? 'block' : 'none' }}></div>
                <div
                    class='studio-drawer'
                    key="studio-drawer"
                    style={{'z-index': this.zIndex, 'margin-top': this.isShow ? '0px' : '-100vh' }}
                >
                    {this.renderHeader()}
                    <div
                        class="studio-drawer-content"
                        style={`transform: translateX(${this.activeIndex > 0 ? this.activeIndex * -100 : 0
                            }%) translateZ(0px);`}
                    >
                        {this.renderViews(h)}
                    </div>
                </div>
            </div>
        );
    }
}