<template>
    <div class="list02-box">
      <div class="list02-text">
        <van-icon class="list02-icon" name="bars" />
        <label class="text_list02">{{item.srfmajortext}}</label>
      </div>
      <div>
        <van-icon class="list02-icon" name="arrow" />
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
export default class AppIconList extends Vue{

  /**
   * 传入item
   *
   * @type {any}
   * @memberof AppIconList
   */
  @Prop() public item?: any;

}
</script>
<style>
@import "./app-icon-list.less";
</style>