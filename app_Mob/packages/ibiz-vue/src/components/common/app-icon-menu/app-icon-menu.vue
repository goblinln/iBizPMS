<template>
  <div class="app-icon-menu">
    <van-panel :title="title" :status="statusValue" @click.native="clickHandler()">
      <div>
        <van-grid :column-num="4">
          <van-grid-item
            v-for="value in beforeMenus"
            :key="value.id"
            :id="value.id"
            :icon="value.icon?value.icon:'photo-o'"
            :text="value.text"
            @click="menuClick(value.name,$event)"
          />
        </van-grid>
      </div>

      <div>
        <van-grid :column-num="4" v-if="isDisplay">
          <van-grid-item
            v-for="value in afterMenus"
            :key="value.id"
            :id="value.id"
            :icon="value.icon?value.icon:'photo-o'"
            :text="value.text"
            @click="menuClick(value.name,$event)"
          />
        </van-grid>
      </div>
    </van-panel>
  </div>
</template>



<script lang="ts">
import {Vue,Component,Prop,Provide,Emit,Watch} from "vue-property-decorator";

@Component({
  components: {}
})
export default class AppIconMenu extends Vue {
  /**
   * 下拉是否展开
   *
   * @type {string}
   * @memberof AppIconMenu
   */
  public isDisplay: boolean = false;

  /**
   * 右侧内容
   *
   * @type {string}
   * @memberof AppIconMenu
   */
  public statusValue: string = "";

  /**
   * 名称
   *
   * @type {string}
   * @memberof AppIconMenu
   */
  @Prop() public title?: string;

  /**
   * 菜单数据
   *
   * @type {Array<any>}
   * @memberof AppIconMenu
   */
  @Prop() public menus?: Array<any>;

  /**
   * 展开后的菜单展示内容
   *
   * @type {Array<any>}
   * @memberof AppIconMenu
   */
  public afterMenus: Array<any> = [];

  /**
   * 展开前的菜单展示内容
   *
   * @type {Array<any>}
   * @memberof AppIconMenu
   */
  public beforeMenus: any = [];

  /**
   * 监听菜单数据
   *
   * @memberof AppIconMenu
   */
  @Watch("menus", { immediate: true, deep: true })
  onMenusChanged(val: any, oldVal: any) {
    if (val) {
      if (val.length > 8) {
        val.forEach((item: any, index: any) => {
          if (index < 8) {
            this.beforeMenus.push(item);
          } else {
            this.afterMenus.push(item);
          }
        });
        this.statusValue = this.$t('app.components.app_icon_menu.statusValue_open') + "";
      } else {
        this.beforeMenus = JSON.parse(JSON.stringify(this.menus));
        this.statusValue = "";
      }
    }
  }

  /**
   * 菜单点击事件
   *
   * @memberof AppIconMenu
   */
  public menuClick($event: any,event:any) {
    this.$emit("menuClick", $event);
    event.stopPropagation();
  }

  /**
   * 下拉菜单点击事件
   *
   * @memberof AppIconMenu
   */
  public clickHandler() {
    if (Object.is(this.statusValue,this.$t('app.components.app_icon_menu.statusValue_open') + "")) {
      this.statusValue = this.$t('app.components.app_icon_menu.statusValue_close') + "";;
      this.isDisplay = true;
    } else {
      this.statusValue = this.$t('app.components.app_icon_menu.statusValue_open') + "";
      this.isDisplay = false;
    }
  }

  public created(){
    this.statusValue = this.$t('app.components.app_icon_menu.statusValue_open') + "";
  }
}
</script>
<style lang="less">
@import "./app-icon-menu.less";
</style>