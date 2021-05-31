<template>
    <div class="timetitle">
        <div class="title">
            <div class="fisttname">{{title}}</div>
        <div class="titleright">
            <van-count-down :time="time" />
        </div>
        </div>
        <van-grid :border="false" :column-num="2">
            <van-grid-item v-for="(item,index) in groupMenus" :icon="item.img?item.icon:'photo-o'"  v-bind:key ="index"></van-grid-item>
        </van-grid>
    </div>
</template>
<script lang="ts">
import {Vue, Component, Prop, Provide, Emit, Watch} from "vue-property-decorator";

@Component({
  components: {}
})
export default class AppTwoTitle extends Vue{

    /**
     * 名称
     *
     * @type {string}
     * @memberof AppTwoTitle
     */
    @Prop() public title?: string;

    /**
     * 菜单数据
     *
     * @type {Array<any>}
     * @memberof AppTwoTitle
     */
    @Prop() public groupMenus?:Array<any>;

    /**
     * 时间
     *
     * @type {string}
     * @memberof AppTwoTitle
     */
    @Prop() public time?: string;

    

}
</script>
<style lang = "less">
@import "./app-time-list.less";
</style>