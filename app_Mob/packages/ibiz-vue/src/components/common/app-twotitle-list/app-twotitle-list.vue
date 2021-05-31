<template>
    <div class = "twotitle">
        <div class="fisttname">{{title}}</div>
        <div class="secondname">{{subhead}}</div>
        <div style="display:flex">
            <van-grid :border="false" :column-num="2">
                <van-grid-item v-for="(item,index) in groupMenus" :icon="item.img?item.icon:'photo-o'"  v-bind:key ="index"></van-grid-item>
            </van-grid>
        </div>
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
     * 小标题
     *
     * @type {string}
     * @memberof AppTwoTitle
     */
    @Prop() public subhead?: string;
}
</script>
<style lang = "less">
@import "./app-twotitle-list.less";
</style>