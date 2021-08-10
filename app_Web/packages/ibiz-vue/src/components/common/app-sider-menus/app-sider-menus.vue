<template>
  <div class="app-sider-menus">
      <template v-for="(item,index) in menus">
          <template v-if="item.items && Array.isArray(item.items) && item.items.length > 0">
              <el-submenu :key="index" :index="item.id" :disabled="item.disabled">
                  <template slot='title'>
                      <img v-if="item.icon && !Object.is(item.icon, '')" :src="item.icon" class='app-menu-icon'/>
                      <i v-else-if="item.iconcls && !Object.is(item.iconcls, '')" :class="item.iconcls + ' app-menu-icon'"></i>
                      <template v-else></template>
                      <span>{{item.text}}</span>
                  </template>
                  <app-sider-menus :menus="item.items"></app-sider-menus>
              </el-submenu>
          </template>
          <template v-else>
              <el-menu-item :key="index" :index="item.id" :disabled="item.disabled">
                  <img v-if="item.icon && !Object.is(item.icon, '')" :src="item.icon" class='app-menu-icon'/>
                  <i v-else-if="item.iconcls && !Object.is(item.iconcls, '')" :class="item.iconcls + ' app-menu-icon'"></i>
                  <template v-else></template>
                  <el-popover
                    :content="$t('components.appformdruipart.blockuitipinfo')"
                    width="150"
                    popper-class="app-tooltip"
                    :disabled="!item.disabled"
                    trigger="hover">
                    <span slot="reference">{{item.text}}</span>
                </el-popover>
                <span v-if="item.counter && (item.counter.count || item.counter.count == 0)" v-badge="item.counter" class="right-badge"/>
              </el-menu-item>
          </template>
      </template>
  </div>
</template>

<script lang='ts'>
import { Component, Vue, Prop } from "vue-property-decorator";

@Component({})
export default class AppSiderMenus extends Vue {
  /**
   * 菜单数据
   *
   * @type {*}
   * @memberof AppSiderMenus
   */
  @Prop({ default: [] }) public menus!: any;
}
</script>

<style lang='less'>
@import "./app-sider-menus.less";
</style>