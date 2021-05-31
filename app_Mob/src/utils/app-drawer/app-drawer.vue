<template>
<div >
        <div :id="uuid"></div>
        <ibiz-drawer
        :content-id="uuid"
        :ref="this.uuid+'drawer'"
         @dropClick="close"
        :close-view="closeView"
        @on-visible-change="onVisibleChange($event)">
        <component
            :is="viewname"
            class="viewcontainer2"
            viewDefaultUsage="includedView"
            :_context="JSON.stringify(context)" 
            :_viewparams="JSON.stringify(viewparams)" 
            @viewdataschange="dataChange($event)"
            @viewdatasactivated="viewDatasActivated($event)"
            @close="close($event)"
            :ref="viewname">
        </component>
        </ibiz-drawer>
        
</div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from "vue-property-decorator";
import { Subject } from "rxjs";
@Component({
    components: {}
})
export default class AppDrawerCompponent extends Vue {

    /**
     * 视图UI参数
     *
     * @type {any}
     * @memberof AppDrawerCompponent
     */
    @Prop() public view!: any;


    /**
     * 是否关闭view
     *
     * @type {any}
     * @memberof AppDrawerCompponent
     */
    public closeView = false;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppDrawerCompponent
     */
    @Prop({ default: {} }) public context?: any;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppDrawerCompponent
     */
    @Prop() public uuid?: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppDrawerCompponent
     */
    @Prop({ default: {} }) public viewparams?: any;

    /**
     * 数据传递对象
     *
     * @type {(null | Subject<any>)}
     * @memberof AppDrawerCompponent
     */
    public subject: Subject<any> = new Subject<any>();


    /**
     * 抽屉弹出位置
     *
     * @type {string}
     * @memberof AppDrawerCompponent
     */
    public placement: string = '';

    /**
     * 是否显示
     *
     * @type {boolean}
     * @memberof AppDrawerCompponent
     */
    public isShow: boolean = false;

    /**
     * 零时结果
     *
     * @type {*}
     * @memberof AppDrawerCompponent
     */
    public tempResult: any = { ret: '' };

    /**
     * 视图名称
     *
     * @type {string}
     * @memberof AppDrawerCompponent
     */
    public viewname: string = '';

    /**
     * 视图宽度
     *
     * @type {number}
     * @memberof AppDrawerCompponent
     */
    public width: number = 256;

    /**
     * 视图层级
     *
     * @type {any}
     * @memberof AppDrawerCompponent
     */
    public zIndex: any = null;

    /**
     * 获取数据传递对象
     *
     * @returns {(null | Subject<any>)}
     * @memberof AppDrawerCompponent
     */
    public getSubject(): null | Subject<any> {
        return this.subject;
    }

    /**
     * Vue生命周期created
     *
     * @memberof AppDrawerCompponent
     */
    public created() {
        this.viewname = this.view.viewname;
        this.placement = this.view.placement === 'DRAWER_LEFT' ? 'left' : 'right';
        if (this.view.width) {
            if (this.view.width.toString().indexOf('px') > 0) {
                if (!Object.is(this.view.width, '0px')) {
                    this.width = parseInt(this.view.width.toString().slice(0, this.view.width.toString().length - 2));
                } else {
                    this.width = 800;
                }
            } else {
                if (this.view.width !== 0) {
                    this.width = this.view.width;
                } else {
                    this.width = 800;
                }
            }
        } else {
            this.width = 800;
        }
        document.onkeydown = (e) => {
            var keyCode = e.keyCode || e.which || e.charCode;
            if (keyCode == 27) {
                this.isShow = false;
            }
        }
    }

    /**
     * Vue生命周期mounted
     *
     * @memberof AppDrawerCompponent
     */
    public mounted() {
        this.isShow = true;
        this.handleZIndex('ivu-drawer-mask', 'ivu-drawer-wrap');
        let drawer :any = this.$refs[this.uuid+'drawer'];
            if(drawer){
                drawer.open()
        }
    }

    /**
     * Vue生命周期beforeDestroy
     *
     * @memberof AppDrawerCompponent
     */
    public beforeDestroy() {
        if (this.zIndex) {
            const zIndex: any = this.zIndex;
            this.$store.commit('updateZIndex', zIndex - 100);
        }
    }

    /**
     * 处理 z-index
     *
     * @memberof AppDrawerCompponent
     */
    public handleZIndex(mask: string, wrap: string) {
        // const zIndex = this.$store.getters.getZIndex();
        // if (zIndex) {
        //     this.zIndex = zIndex + 100;
        //     this.$store.commit('updateZIndex', this.zIndex);
        // }
        // const element: Element = this.$el;
        // const maskTag: any = element.getElementsByClassName(mask)[0];
        // const warpTag: any = element.getElementsByClassName(wrap)[0];
        // maskTag.style.zIndex = this.zIndex;
        // warpTag.style.zIndex = this.zIndex;
    }

    /**
     * 关闭回调
     *
     * @memberof AppDrawerCompponent
     */
    public close(result: any) {
        if (result && Array.isArray(result) && result.length > 0) {
            Object.assign(this.tempResult, { ret: 'OK' }, { datas: JSON.parse(JSON.stringify(result)) });
        }
        this.isShow = false;
        this.onVisibleChange(true);

    }

    /**
     * 数据变化回调
     *
     * @memberof AppDrawerCompponent
     */
    public dataChange(result: any) {
        this.tempResult = { ret: '' };
        if (result && Array.isArray(result) && result.length > 0) {
            Object.assign(this.tempResult, { ret: 'OK' }, { datas: JSON.parse(JSON.stringify(result)) });
        }
    }

    /**
     * 激活数据回调
     *
     * @memberof AppDrawerCompponent
     */
    public viewDatasActivated(result: any) {
        if (result && Array.isArray(result) && result.length > 0) {
            this.close(result);
        }
    }

    /**
     * 抽屉显示隐藏回调
     *
     * @memberof AppDrawerCompponent
     */
    public async onVisibleChange($event: any) {
        const component: any = this.$refs[this.viewname];
        if (component) {
            const { viewtag: _viewtag } = component;
            const appview = this.$store.getters['viewaction/getAppView'](_viewtag);
            if (appview && appview.viewdatachange) {
                this.isShow = true;
                const title: any = this.$t('app.tabpage.sureclosetip.title');
                const contant: any = this.$t('app.tabpage.sureclosetip.content');
                const result = await this.$Notice.confirm(title, contant);
                if (result) {
                    this.$store.commit('viewaction/setViewDataChange', { viewtag: _viewtag, viewdatachange: false });
                    this.isShow = false;
                    this.handleShowState($event);
                    this.closeView = true;
                } else {
                    this.closeView = false;
                    this.isShow = true;
                    let drawer :any = this.$refs[this.uuid+'drawer'];

                }
            }else{
                this.handleShowState($event);
                this.closeView = true;
            }
        }
    }

    /**
     * 处理显示隐藏状态
     *
     * @memberof AppDrawerCompponent
     */
    public handleShowState($event: any) {
        if (!$event) {
            return;
        }
        if (this.subject && this.subject!=null) {
            if (this.tempResult && Object.is(this.tempResult.ret, 'OK')) {
                
            }
            setTimeout(() => {
            this.subject.next(this.tempResult);
        }, 500)
        }

    }
}
</script>
<style lang="less" scoped>
@import './app-drawer.less';
</style>