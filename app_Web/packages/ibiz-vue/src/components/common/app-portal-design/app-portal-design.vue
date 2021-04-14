<template>
    <card class="app-portal-design" :dis-hover="true" :padding="0" :bordered="false">
        <p slot="title">
            {{'自定义门户'}}
        </p>
        <div class="design-toolbar" slot="extra">
            <i-button @click="click">{{ '保存' }}</i-button> 
        </div>
        <div class="design-container">
            <app-dashboard-design :viewState="viewState" :context="context" :customModel="customModel" :viewparams="viewparams" :utilServiceName="utilServiceName" @save="onSaved"></app-dashboard-design>
        </div>
    </card>
</template>

<script lang="ts">
import { Vue, Component, Prop, Model, Emit,Watch } from "vue-property-decorator";
import { Subject } from "rxjs";
import AppDashboardDesign from '../app-dashboard-design/app-dashboard-design.vue';
import { Util, ViewState } from 'ibiz-core';

@Component({
    components: {
        AppDashboardDesign
    }
})
export default class AppPortalDesign extends Vue {

      /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppPortalDesign
     */
    protected viewdata!: string;

      /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppPortalDesign
     */
    protected viewparam!: string;

    /**
     * 部件动态参数
     *
     * @memberof AppDefaultDashboard
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppDefaultDashboard
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultDashboard
     */
    @Watch('dynamicProps',{
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
            if(newVal?.viewdata && newVal.viewdata != oldVal?.viewdata){
                this.viewdata = newVal.viewdata;
                this.prepareContext();
            }
            if(newVal?.viewparam && newVal.viewparam != oldVal?.viewparam){
                this.viewparam = newVal.viewparam;
                this.prepareViewparam();
            }
            if(newVal?.customModel && newVal.customModel != oldVal?.customModel){
                console.log(this.customModel);
                this.customModel = newVal.customModel;
            }
        }
    }

    /**
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultDashboard
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
            this.viewDefaultUsage = newVal.viewDefaultUsage !== false;
        }
    }

    /**
     * 视图默认使用
     *
     * @type {boolean}
     * @memberof AppPortalDesign
     */
    protected viewDefaultUsage!: boolean;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppPortalDesign
     */
    protected context:any = {};

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppPortalDesign
     */
    protected viewparams:any = {};

    /**
     * 自定义模型数据
     *
     * @type {*}
     * @memberof AppPortalDesign
     */
    protected customModel: any = [];

    /**
     * modelId
     *
     * @type {*}
     * @memberof AppPortalDesign
     */
    protected modelId:string = "";

    /**
     * 功能服务名称
     *
     * @type {*}
     * @memberof AppPortalDesign
     */
    protected utilServiceName:string = "";

    /**
     * 视图状态订阅对象
     *
     * @private
     * @type {Subject<{action: string, data: any}>}
     * @memberof AppPortalDesign
     */
    protected viewState: Subject<ViewState> = new Subject();

    /**
     * 视图参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPortalDesign
     */
    @Watch('viewparam',{immediate: true, deep: true})
    onParamData(newVal: any, oldVal: any) {
        this.prepareViewparam();
    }

    /**
     * 处理应用上下文变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPortalDesign
     */
    @Watch('viewdata')
    onViewData(newVal: any, oldVal: any) {
        this.prepareContext();
    }

    /**
     * 生命周期
     *
     * @memberof AppPortalDesign
     */
    public created() {
        this.prepareViewparam();
        this.prepareContext();
    }

    /**
     * 准备视图参数
     *
     * @memberof AppPortalDesign
     */
    public prepareViewparam() {
        if(this.viewparam){
            Object.assign(this.viewparams, JSON.parse(this.viewparam));
            if(this.viewparams && this.viewparams.modelid){
                this.modelId = this.viewparams.modelid;
            }
            if(this.viewparams && this.viewparams.utilServiceName){
                this.utilServiceName = this.viewparams.utilServiceName;
            }
        } 
    }

    /**
     * 准备视图上下文参数
     *
     * @memberof AppPortalDesign
     */
    public prepareContext() {
        if (!this.viewDefaultUsage && this.viewdata && !Object.is(this.viewdata, '')) {
            Object.assign(this.context, JSON.parse(this.viewdata));
            return;
        } 
    }

     
    /**
     * 点击保存
     *
     * @memberof AppPortalDesign
     */
    public click() {
        this.viewState.next({ tag: "", action: "save", data: {} })
    }

     /**
     * 保存完成
     *
     * @memberof AppPortalDesign
     */
    public onSaved($event: any) {
        this.$emit("close", $event);
    }

}
</script>

<style lang='less'>
@import "./app-portal-design.less";
</style>