<template>
<van-panel :title="title">
  <ul>
    <li
      v-for="value in menus"
      :key="value.id"
      :id="value.id"
      @click="menuClick(value.name)"
    >
      <div style="display:flex;">
        <div class="divnew1">
          <van-image v-bind:src="value.img" style="height: 80px;" />
        </div>
        <div>
          <div style="margin-left: 10px;">
            <span>{{value.text}}</span>
          </div>
          <div class="divnew2">
            <span class="colorname">￥{{value.price}}</span>
          </div>
        </div>
      </div>
    </li>
  </ul>
</van-panel>
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
export default class AppPicrightMenu extends Vue {
  /**
   * 名称
   *
   * @type {string}
   * @memberof AppPicrightMenu
   */
  @Prop() public title?: string;

  /**
   * 菜单数据
   *
   * @type {Array<any>}
   * @memberof AppPicrightMenu
   */
  @Prop() public menus?: Array<any>;

  /**
   *存储后的菜单
   *@memberof AppPicrightMenu
   */
  public groupMenus: Array<any> = [];

  /**
   * 监听菜单数据
   * @memberof AppPicrightMenu
   */
  @Watch("menus", { immediate: true, deep: true })
  onMenusChanged(val: any, oldVal: any) {
    if (val) {
      if (val.length >= 8) {
        let tempArray:Array<any> = [];
        val.forEach((item: any, index: any) => {
          if (index % 8 == 0) {
            if (index != 0) {
              this.groupMenus.push(tempArray);
            }
            tempArray = [];
          }
          tempArray.push(item);
        });
        if (tempArray.length > 0) {
          this.groupMenus.push(tempArray);
        }
      } else {
        this.groupMenus = val;
      }
    }
  }

  /**
   * 菜单点击事件
   *
   * @memberof AppPicrightMenu
   */
  public menuClick($event: any) {
    this.$emit("menuClick", $event);
  }
}
</script>
<style lang="less">
@import "./app-picright-menu.less";
</style>