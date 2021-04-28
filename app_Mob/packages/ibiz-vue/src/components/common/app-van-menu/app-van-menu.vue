<template>
<van-panel :title="title">
  <van-grid :column-num="3">
    <van-grid-item
      v-for="value in menus"
      :key="value.id"
      :id="value.id"
      :icon="value.icon?value.icon:'photo-o'"
      :text="value.text"
      @click="menuClick(value.text)"
    />
  </van-grid>
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
export default class AppVanMenu extends Vue {
  /**
   * 名称
   *
   * @type {string}
   * @memberof AppVanMenu
   */
  @Prop() public title?: string;

  /**
   * 菜单数据
   *
   * @type {Array<any>}
   * @memberof AppVanMenu
   */
  @Prop() public menus?: Array<any>;

  /**
   *存储后的菜单
   *@memberof AppVanMenu
   */
  public groupMenus: Array<any> = [];

  /**
   * 监听菜单数据
   * @memberof AppVanMenu
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
   * @memberof AppVanMenu
   */
  public menuClick($event: any) {
    this.$emit("menuClick", $event);
  }
}
</script>
<style lang="less">
</style>