<template>
  <div class="app-studioaction" v-if="isDevMode">
        <div v-show="sdc.isShowTool" class="studio-config-container">
            <div class="title">
                <el-popover trigger="click" popper-class="view-config" placement="left">
                    <div class="view-config-message" @dblclick="onDBClick">
                        <div class="view-message">
                            <div class="title">
                                <div class="icon" />
                                <strong>视图信息</strong>
                            </div>
                            <div class="content">
                                <div>标题 : <span title="双击复制">{{ viewInstance.caption }}</span></div>
                                <div>标识 : <span title="双击复制">{{ viewInstance.codeName }}</span></div>
                            </div>
                        </div>
                        <div class="entity-config" v-if="viewInstance.appDataEntity">
                            <el-divider></el-divider>
                            <div class="title">
                                <div class="icon" />
                                <strong>实体信息</strong>
                            </div>
                            <div class="content">
                                <div>名称 : <span title="双击复制">{{ viewInstance.appDataEntity.logicName }}</span></div>
                                <div>标识 : <span title="双击复制">{{ viewInstance.appDataEntity.codeName }}</span></div>
                            </div>
                        </div>
                        <template v-if="viewInstance.controls && viewInstance.controls.length>0">
                            <el-divider></el-divider>
                            <div :class="{ 'control-config': true, 'isExpand': showControlMessage }">
                                <div class="title" @click="() => { this.showControlMessage = !this.showControlMessage; }">
                                    <span title="双击复制">
                                        <div class="icon" />
                                        <strong>部件信息</strong>
                                    </span>
                                    <Icon :size="16" :type="showControlMessage ? 'ios-arrow-up' : 'ios-arrow-down'" />
                                </div>
                                <template v-if="showControlMessage">
                                    <template v-for="(control, index) in viewInstance.controls">
                                        <el-divider v-if="index != 0" :key="index"></el-divider>
                                        <div :class="`content control-${index}`" :key="control.codeName">
                                            <div>标题 : <span title="双击复制">{{ control.logicName }}</span></div>
                                            <div>标识 : <span title="双击复制">{{ control.codeName }}</span></div>
                                            <div>类型 : <span title="双击复制">{{ control.controlType }}</span></div>
                                        </div>
                                    </template>
                                </template>
                            </div>
                        </template>
                        <div :class="{ 'view-context': true, 'isExpand': showContext }">
                            <el-divider></el-divider>
                            <div class="title" @click="() => { if (Object.keys(context).length>0) this.showContext = !this.showContext; }">
                                <div class="icon"/>
                                <strong>应用上下文</strong>
                                <Icon v-if="Object.keys(context).length>0" :size="16" :type="showContext ? 'ios-arrow-up' : 'ios-arrow-down'" />
                            </div>
                            <div v-if="showContext" class="content">
                                <div class="context-item" v-for="(key, index) in Object.keys(context)" :key="index">
                                    <span title="双击复制">{{ key }}</span> : <span title="双击复制">{{ context[key] }}</span>
                                </div>
                            </div>
                        </div>
                        <div :class="{ 'view-viewparams': true, 'isExpand': showViewprams }">
                            <el-divider></el-divider>
                            <div class="title" @click="() => { if (Object.keys(viewparams).length>0) this.showViewprams = !this.showViewprams; }">
                                <div class="icon"/>
                                <strong>视图参数</strong>
                                <Icon v-if="Object.keys(viewparams).length>0" :size="16" :type="showViewprams ? 'ios-arrow-up' : 'ios-arrow-down'" />
                            </div>
                            <div v-if="showViewprams" class="content">
                                <div class="viewparams-item" v-for="(key, index) in Object.keys(viewparams)" :key="index">
                                    <span title="双击复制">{{ key }}</span> : <span title="双击复制">{{ viewparams[key] }}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <span slot="reference">{{viewTitle}}</span>
                </el-popover>
            </div>
            <div class="actions">
                <div class="action-item" title="进入当前视图配置界面">
                    <i-button type="text" ghost @click="configView()">配置</i-button>
                </div>
                <div class="action-item" title="建立当前界面的issues">
                    <i-button type="text" ghost @click="createIssues()">新建issues</i-button>
                </div>
            </div>
        </div>
  </div>
</template>
<script lang = 'ts'>
import { Vue, Component, Inject, Prop } from "vue-property-decorator";
import { AppServiceBase, StudioActionUtil } from 'ibiz-core';

@Component({
})
export default class AppStudioAction extends Vue {

    /**
     * 视图标题
     *
     * @type {string}
     * @memberof AppStudioAction
     */ 
    @Prop() public viewTitle!:string;

    /**
     * 视图名称
     *
     * @type {string}
     * @memberof AppStudioAction
     */ 
    @Prop() public viewName!:string;

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
    public isDevMode:boolean = false;

    /**
     * 配置平台操作控制器
     *
     * @type {StudioActionController}
     * @memberof AppStudioAction
     */
    public sdc: StudioActionUtil = StudioActionUtil.getInstance();
    
    /**
     * 触发配置
     *
     * @protected
     * @memberof AppStudioAction
     */
    protected configView(): void {
        this.sdc.openStudioConfigView(this.viewName);
    }

    /**
     * 新建issues
     *
     * @protected
     * @memberof AppStudioAction
     */
    protected createIssues(): void {
        this.sdc.createdIssues(this.viewName);
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
    }

    /**
     * 双击复制事件
     * 
     * @memberof AppStudioAction
     */
    public onDBClick(event: any) {
        if (event && event.target && event.target.nodeName == 'SPAN') {
            const value: any = event.target.innerHTML;
            if (value) {
                try {
                    document.execCommand('copy');
                    this.$message({
                        type: "success",
                        message: `复制成功，内容为 ${value}`
                    })
                } catch (error: any) {

                }
            }
        }
    }

}
</script>

<style lang="less">
@import "./app-studioaction.less";
</style>