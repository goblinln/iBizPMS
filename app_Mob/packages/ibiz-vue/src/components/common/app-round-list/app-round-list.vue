<template>
  <div class="app-round-list">
    <div class="list-list-img">{{item.srfmajortext.substr(0,1)}}</div>
    <div class="list-list-text">
      <div class="list-list-info">
        <p class="list-title" v-if="item.srfmajortext">{{item.srfmajortext}}</p>
        <p class="list-info" v-if="item.subtitle && item.content">{{item.subtitle+":"+item.content}}</p>
      </div>
      <div class="list-time-box">
        <p class="list-time" v-if="item.time">{{item.time.substr(11,12).substr(0,5)}}</p>
        <div class="list-num" v-if="item.num">{{item.num}}</div>
      </div>
    </div>
  </div>
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
export default class AppRoundList extends Vue{

  /**
   * 传入item
   * @type {any}
   * @memberof AppRoundList
   */
  @Prop() public item?:any;

};
</script>
<style lang = "less">
@import "./app-round-list.less";
</style>