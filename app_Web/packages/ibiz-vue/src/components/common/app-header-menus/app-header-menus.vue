<template>
  <div class="app-header-menus" v-if="isShow && this.sdc.isShowTool">
    <div v-for="(menu,index) in menus" :key="index" class="app-header-menu-item text" @click="openWindow(menu)">
      <div class="app-header-menu-item-icon">
        <i :class="menu.iconcls" :aria-hidden="true" />
      </div>
      <div class="app-header-menu-item-text">{{$t(menu.title)}}</div>

    </div>
  </div> 
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { Environment } from '@/environments/environment';
import { StudioActionUtil } from "ibiz-core";

@Component({
})
export default class AppHeaderMenus extends Vue {

  /**
   * 配置平台操作控制器
   *
   * @type {StudioActionController}
   * @memberof AppStudioAction
   */
  public sdc: StudioActionUtil = StudioActionUtil.getInstance();

  /**
   * 是否显示
   *
   * @type {boolean}
   * @memberof AppHeaderMenus
   */
  public isShow:boolean = Environment.devMode;
  
  /**
   * 菜单数据
   * 
   * @type {any}
   * @memberof AppHeaderMenus
   */
  public menus:any = [
      {
          name: "ibizlab",
          title: "components.appheadermenus.ibizlab.title",
          url: Environment.ibizlabtUrl,
          iconcls: 'fa fa-home',
      },
      {
          name: "publishProject",
          title: "components.appheadermenus.publishproject.title",
          url: Environment.PublishProjectUrl,
          iconcls: 'fa fa-folder-open-o',
      },
      {
          name: "ibizstudio",
          title: "components.appheadermenus.ibizstudio.title",
          url: `${Environment.StudioUrl}?#/common_mosindex/srfkeys=${Environment.SysId}`,
          iconcls: 'fa fa-wrench',
      },
      {
          name: "ibizbbs",
          title: "components.appheadermenus.ibizbbs.title",
          url: Environment.ibizbbstUrl,
          iconcls: 'fa fa-comments-o',
      },
  ];

  /**
   * 触发界面行为
   * 
   * @memberof AppHeaderMenus
   */
  public openWindow(menu:any){
      window.open(menu.url, '_blank');
  }


}
</script>

<style lang='less'>
@import "./app-header-menus.less";
</style>