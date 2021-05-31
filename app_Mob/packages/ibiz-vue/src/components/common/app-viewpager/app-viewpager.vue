<template>
    <div class="app-viewpager">
        <van-swipe :autoplay="3000" indicator-color="white" >
        <van-swipe-item v-for="(items,index) in groupMenus"  v-bind:key ="index">
            <img width="50%" v-lazy="items.img" v-if="items.img" />
            <div style="background: #39a9ed;height: 155px;width:100%" v-if="!items.img">{{items.srfkey}}</div>
        </van-swipe-item>
    </van-swipe>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';

@Component({
    components: {
    }
})
export default class AppViewpager extends Vue{
    /**
         * 菜单数据
         *
         * @type {Array<any>}
         * @memberof AppViewpager
         */
        @Prop() public groupMenus?:Array<any>;
};
</script>
<style lang = "less">
@import "./app-viewpager.less";
</style>