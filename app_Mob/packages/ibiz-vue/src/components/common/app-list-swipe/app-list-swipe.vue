<template>
    <van-swipe :autoplay="3000" class="app-list-swipe">
        <van-swipe-item v-for="(item,index) in items" :key="index">{{item.srfmajortext}}</van-swipe-item>
    </van-swipe>
</template>
<script lang="ts">
import {
  Vue,
  Component,
  Prop,
  Provide,
  Emit,
  Watch
} from "vue-property-decorator";

@Component({
  components: {}
})
export default class AppListSwipe extends Vue {
  /**
   * 传入item
   *
   * @type {any}
   * @memberof AppCardList
   */
  @Prop() public items?: any;



}
</script>
<style lang="less">
@import "./app-list-swipe.less";
</style>