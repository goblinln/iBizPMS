<template>
    <div class="app-studioaction" v-if="isDevMode">
        <template v-if="sdc.isShowTool">
            <div class="studio-config-preview">
                <div class="preview-container">
                    <div
                        :class="{ title: true, 'is-expand': isExpand }"
                        v-popover="`popover-${viewInstance.codeName}`"
                        @click="
                            () => {
                                this.showDetails = !this.showDetails;
                            }
                        "
                    >
                        <span>{{ viewTitle }}</span>
                        <i class="el-icon-arrow-right" />
                    </div>
                    <div class="split"></div>
                    <div class="actions">
                        <div class="action-item" title="进入当前视图配置界面">
                            <i-button type="text" ghost @click="configView()">配置</i-button>
                        </div>
                        <div class="action-item" title="动态应用市场模型模板">
                            <i-button type="text" ghost @click="showViewModel()">应用市场模型模板</i-button>
                        </div>
                    </div>
                </div>
            </div>
            <template>
                <el-popover
                    :ref="`popover-${viewInstance.codeName}`"
                    placement="left"
                    v-model="isExpand"
                    popper-class="studio-config-details"
                >
                    <div class="details-container" @dblclick="onDBClick">
                        <div class="view-details">
                            <div class="message view-message"><span class="info">视图信息</span></div>
                            <div class="title view-title">
                                标题：<span class="info copy-info">{{ viewInstance.caption }}</span>
                            </div>
                            <div class="codename view-codename">
                                标识：<span class="info copy-info">{{ viewInstance.codeName }}</span>
                            </div>
                        </div>
                        <div v-if="viewEntity && viewEntity.codeName" class="entity-deitals">
                            <div class="message entity-message"><span class="word3">实体信息</span></div>
                            <div class="title entity-title">
                                标题：<span class="info copy-info">{{ viewEntity.logicName }}</span>
                            </div>
                            <div class="codename entity-codename">
                                标识：<span class="info copy-info">{{ viewEntity.codeName }}</span>
                            </div>
                        </div>
                        <div class="control-details">
                            <div
                                class="message control-message"
                                @click="
                                    () => {
                                        if (this.viewControls && this.viewControls.length > 0)
                                            this.showControlMessage = !this.showControlMessage;
                                    }
                                "
                            >
                                <span class="info">部件信息</span>
                                <i :class="showControlMessage ? 'el-icon-arrow-down' : 'el-icon-arrow-right'" />
                            </div>
                            <template v-if="showControlMessage">
                                <template v-for="(control, index) in viewControls">
                                    <div :key="index + 'title'" class="title control-title">
                                        标题：<span class="info copy-info">{{ control.logicName }}</span>
                                    </div>
                                    <div :key="index + 'key'" class="codename conctrol-codename">
                                        标识：<span class="info copy-info">{{ control.codeName }}</span>
                                    </div>
                                    <div :key="index + 'type'" class="control-type">
                                        类型：<span class="info copy-info">{{ control.controlType }}</span>
                                    </div>
                                    <button :key="index + 'view'" class="open-config" @click="gotoCtrlModel(control)">
                                        <span class="info">查看配置</span>
                                    </button>
                                    <div
                                        :key="index"
                                        v-if="index != viewControls.length - 1"
                                        class="control-split"
                                    ></div>
                                </template>
                            </template>
                        </div>
                        <div class="view-context">
                            <div
                                class="message"
                                @click="
                                    () => {
                                        if (Object.keys(this.context).length > 0) this.showContext = !this.showContext;
                                    }
                                "
                            >
                                <span class="info">应用上下文</span>
                                <i
                                    v-if="Object.keys(context).length > 0"
                                    :class="showContext ? 'el-icon-arrow-down' : 'el-icon-arrow-right'"
                                />
                            </div>
                            <div v-if="showContext" class="content">
                                <div class="context-item" v-for="(key, index) in Object.keys(context)" :key="index">
                                    <span class="copy-info" title="双击复制">{{ key }}</span> :
                                    <span class="copy-info" title="双击复制">{{ context[key] }}</span>
                                </div>
                            </div>
                        </div>
                        <div class="view-viewparams">
                            <div
                                class="message"
                                @click="
                                    () => {
                                        if (Object.keys(this.viewparams).length > 0)
                                            this.showViewprams = !this.showViewprams;
                                    }
                                "
                            >
                                <span class="info">视图参数</span>
                                <i
                                    v-if="Object.keys(viewparams).length > 0"
                                    :class="showViewprams ? 'el-icon-arrow-down' : 'el-icon-arrow-right'"
                                />
                            </div>
                            <div v-if="showViewprams" class="content">
                                <div
                                    class="viewparams-item"
                                    v-for="(key, index) in Object.keys(viewparams)"
                                    :key="index"
                                >
                                    <span class="copy-info" title="双击复制">{{ key }}</span> :
                                    <span class="copy-info" title="双击复制">{{ viewparams[key] }}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </el-popover>
            </template>
        </template>
    </div>
</template>
<script lang="ts">
import { Vue, Component, Inject, Prop } from 'vue-property-decorator';
import { AppModelService, AppServiceBase, GetModelService, StudioActionUtil } from 'ibiz-core';
import { AppDesign, AppDrawer } from 'ibiz-vue';

@Component({})
export default class AppStudioAction extends Vue {
    /**
     * 视图标题
     *
     * @type {string}
     * @memberof AppStudioAction
     */
    @Prop() public viewTitle!: string;

    /**
     * 视图名称
     *
     * @type {string}
     * @memberof AppStudioAction
     */
    @Prop() public viewName!: string;

    /**
     * 视图实例
     *
     * @type {any}
     * @memberof AppStudioAction
     */
    @Prop() public viewInstance!: any;

    /**
     * 应用上下文
     *
     * @type {any}
     * @memberof AppStudioAction
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {any}
     * @memberof AppStudioAction
     */
    @Prop() public viewparams!: any;

    /**
     * 是否展示详情页
     *
     * @type {any}
     * @memberof AppStudioAction
     */
    public showDetails: boolean = false;

    /**
     * 视图实体
     *
     * @type {any}
     * @memberof AppStudioAction
     */
    public viewEntity: any;

    /**
     * 视图部件
     *
     * @type {any}
     * @memberof AppStudioAction
     */
    public viewControls: any[] = [];

    /**
     * 详情是否展开
     *
     * @type {any}
     * @memberof AppStudioAction
     */
    public isExpand: boolean = false;

    /**
     * 是否显示部件信息栏
     *
     * @type {boolean}
     * @memberof AppStudioAction
     */

    public showControlMessage: boolean = false;

    /**
     * 是否显示视图参数信息栏
     *
     * @type {boolean}
     * @memberof AppStudioAction
     */

    public showViewprams: boolean = false;

    /**
     * 是否显示应用上下文信息栏
     *
     * @type {boolean}
     * @memberof AppStudioAction
     */

    public showContext: boolean = false;

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
     * @type {StudioActionController}
     * @memberof AppStudioAction
     */
    public sdc: StudioActionUtil = StudioActionUtil.getInstance();

    /**
     * 设计服务
     *
     * @type {AppDesign}
     * @memberof AppStudioAction
     */
    public appDesign: AppDesign = AppDesign.getInstance();

    /**
     * 触发配置
     *
     * @protected
     * @memberof AppStudioAction
     */
    protected configView(): void {
        // this.sdc.openStudioConfigView(this.viewName);
    }

    /**
     * 展示视图模型
     *
     * @protected
     * @memberof AppStudioAction
     */
    protected showViewModel(): void {
        // GetModelService(this.context).then((modelService: AppModelService) => {
        //     if (modelService) {
        //         let dynamicProps: any = {};
        //         let dynamicInstanceConfig: DynamicInstanceConfig = modelService.getDynaInsConfig();
        //         Object.assign(dynamicProps, { srfdynainstid: dynamicInstanceConfig?.id });
        //         Object.assign(dynamicProps, { objectid: this.viewInstance.dynaModelFilePath });
        //         this.appDesign.openDrawer({ dynamicProps: dynamicProps }).subscribe((result: any) => {
        //             console.log(result);
        //         });
        //     }
        // });
    }

    /**
     * 查看部件模型配置
     *
     * @protected
     * @memberof AppStudioAction
     */
    protected gotoCtrlModel(data: any): void {
        GetModelService(this.context).then((modelService: AppModelService) => {
            if (modelService) {
                let dynamicInstanceId: string = modelService.app.M.getPSDynaInstId;
                if (dynamicInstanceId) {
                    const appEnvironment: any = AppServiceBase.getInstance().getAppEnvironment();
                    window.open(
                        `${appEnvironment.configDynaPath}?tooltype=${data?.controlType}&dynainst=${dynamicInstanceId}&id=${data.M?.modelid}`,
                    );
                } else {
                    this.$Notice.info('线下模式不支持跳转动态设计工具');
                }
            }
        });
    }

    /**
     *  Vue生命周期 -- Created
     *
     * @public
     * @memberof AppStudioAction
     */
    public created() {
        let Environment: any = AppServiceBase.getInstance().getAppEnvironment();
        if (Environment) {
            this.isDevMode = Environment.devMode;
        }
        this.viewEntity = this.viewInstance.getPSAppDataEntity?.();
        this.viewControls = this.viewInstance.getPSControls?.() || [];
    }

    /**
     * 双击复制事件
     *
     * @memberof AppStudioAction
     */
    public onDBClick(event: any) {
        if (
            event &&
            event.target &&
            event.target.nodeName == 'SPAN' &&
            event.target.className.indexOf('copy-info') !== -1
        ) {
            const value: any = event.target.innerHTML;
            if (value) {
                try {
                    document.execCommand('copy');
                    this.$message({
                        type: 'success',
                        message: `复制成功，内容为 ${value}`,
                    });
                } catch (error) {}
            }
        }
    }
}
</script>

<style lang="less">
@import './app-studioaction.less';
</style>
