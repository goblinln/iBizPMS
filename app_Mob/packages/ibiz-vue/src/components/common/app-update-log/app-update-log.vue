<template>
  <van-overlay v-if="useUpdateLog" class="app-update-log" :show="show" @click="onClick">
    <div class="wrapper" @click.stop>
        <div class="version">
           {{updateLog.version}}
        </div>
        <div class="title">{{updateLog.title}}</div>
        <div class="content">
            <ol>
                <li v-for="(item,index) in updateLog.updateList" :key="index">{{index+1}}.{{item}}</li>
            </ol>
        </div>
    </div>
  </van-overlay>
</template>
<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from "vue-property-decorator";
import { updateLog } from './app-update-log';
import { Environment } from '@/environments/environment';
@Component({
    components: {},
})
export default class AppUpdateLog extends Vue {

    /**
     * 显示
     *
     * @type {boolean}
     * @memberof AppUpdateLog
     */
    public show: boolean = false;

    /**
     * 启用更新日志
     *
     * @type {*}
     * @memberof AppMobMenuDefaultView
     */
    public useUpdateLog:boolean = Environment.useUpdateLog;

    /**
     * 蒙层点击
     *
     * @memberof AppUpdateLog
     */
    public onClick() {
        this.show = false;
        localStorage.setItem("updateLogStatus", 'true');
    }

    /**
     * 数据
     *
     * @memberof AppUpdateLog
     */
    public updateLog = updateLog;


    /**
     * 生命周期
     *
     * @memberof AppUpdateLog
     */
    created() {
        setTimeout(() => {
            this.show = true;
        }, 1500);
    }

}
</script>
<style lang = "less">
@import "./app-update-log.less";
</style>