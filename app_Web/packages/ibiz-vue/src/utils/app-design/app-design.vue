<template>
    <div class="app-model-setting" v-if="sdc.isShowTool">
        <div class="app-design-button" @click="handleButtonClick">
            <icon type="md-settings" />
        </div>
        <drawer :closable="false" class-name="app-design-drawer" :width="800" v-model="isShowDrawer">
            <div class="app-model-setting-button" @click="handleButtonClick">
                <icon type="md-close" />
            </div>
            <iframe class="app-modle-setting-content" height="100%" width="100%" scrolling="true" :src="iframeUrl" />
        </drawer>
    </div>
</template>

<script lang="ts">
import { AppServiceBase, StudioActionUtil, Util } from 'ibiz-core';
import { Subject } from 'rxjs';
import { Component, Vue, Prop, Watch } from 'vue-property-decorator';

@Component({})
export default class AppModelSetting extends Vue {
    /**
     * 动态参数
     *
     * @memberof AppModelSetting
     */
    @Prop() public dynamicProps!: any;

    /**
     * 是否显示抽屉
     *
     * @memberof AppModelSetting
     */
    public isShowDrawer: boolean = false;

    /**
     * 数据传递对象
     *
     * @type {*}
     * @memberof AppModelSetting
     */

    public subject: Subject<any> = new Subject<any>();

    /**
     * iframe路径
     *
     * @memberof AppModelSetting
     */
    public iframeUrl: string = '';

    /**
     * 配置平台操作控制器
     *
     * @type {StudioActionUtil}
     * @memberof AppModelSetting
     */
    public sdc: StudioActionUtil = StudioActionUtil.getInstance();

    /**
     * 获取数据传递对象
     *
     * @memberof AppModelSetting
     */
    public getSubject() {
        return this.subject;
    }

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppModelSetting
     */
    @Watch('dynamicProps', {
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            this.setIframeUrl(newVal);
        }
    }

    /**
     * Vue声明周期
     *
     * @memberof AppModelSetting
     */
    public created() {
        window.addEventListener('message', async (e: MessageEvent) => {
            const appEnvironment: any = AppServiceBase.getInstance().getAppEnvironment();
            if (e.origin !== appEnvironment.previewDynaPath) {
                return;
            }
            this.isShowDrawer = !this.isShowDrawer;
            this.subject.next({ tag: 'message', action: 'close', data: e?.data });
        });
    }

    /**
     * 处理按钮点击
     *
     * @memberof AppModelSetting
     */
    public handleButtonClick() {
        this.isShowDrawer = !this.isShowDrawer;
    }

    /**
     * 设置iframe路径
     *
     * @memberof AppModelSetting
     */
    public setIframeUrl(data: any) {
        const appEnvironment: any = AppServiceBase.getInstance().getAppEnvironment();
        this.iframeUrl = `${appEnvironment.previewDynaPath}/dynamictool/modelresmarket/#/modelresmarket/${data.srfdynainstid}/psuwprojects/applymodelresview?objectid=${data.objectid}`;
    }
}
</script>
<style lang="less">
.app-model-setting {
    width: 100%;
    height: 100%;
    .app-design-button {
        position: fixed;
        top: 300px;
        right: 4px;
        font-size: 48px;
        color: #fff;
        background: #1890ff;
        width: 56px;
        height: 56px;
        text-align: center;
        line-height: 56px;
        border-radius: 6px;
        cursor: pointer;
        .ivu-icon {
            vertical-align: 0;
        }
    }
}
.app-design-drawer {
    .ivu-drawer {
        .ivu-drawer-content {
            background-color: rgba(55, 55, 55, 0);
            box-shadow: none;
            .ivu-drawer-body {
                padding: 0px !important;
                .app-model-setting-button {
                    position: absolute;
                    font-size: 48px;
                    color: #fff;
                    background: #1890ff;
                    width: 56px;
                    height: 56px;
                    text-align: center;
                    line-height: 56px;
                    border-radius: 6px;
                    top: 300px;
                    left: 0px;
                    cursor: pointer;
                    .ivu-icon {
                        vertical-align: 0;
                    }
                }
                .app-modle-setting-content {
                    background-color: #fff;
                    width: calc(100% - 56px);
                    height: calc(100% - 8px);
                    margin-left: 56px;
                    box-shadow: 0 4px 12px rgb(0 0 0 / 15%);
                }
            }
        }
    }
}
</style>
