<template>
    <div class="app-notice-bar">
        <div class="bar-title">{{title}}：</div>
        <van-swipe :autoplay="3000" :show-indicators="false" style="height: 30px;text-align:left" vertical>
            <van-swipe-item v-for="(item,index) in menus"  v-bind:key ="index">{{item.srfmajortext}}</van-swipe-item>
        </van-swipe>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';
// import { Style } from 'cube-ui';

@Component({
    components: {
    }
})
export default class APPNoticeBar extends Vue{
    /**
     * 名称
     *
     * @type {string}
     * @memberof APPNoticeBar
     */
    @Prop() public title?: string;

    /**
     * 菜单数据
     *
     * @type {Array<any>}
     * @memberof APPNoticeBar
     */
    @Prop() public menus?:Array<any>;
};
</script>
<style>
@import "./app-notice-bar.less";
</style>