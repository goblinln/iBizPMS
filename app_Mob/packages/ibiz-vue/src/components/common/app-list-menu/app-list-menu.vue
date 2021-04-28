<template>
  <div  class="app-list-menu">
    <van-panel :title="title">
      <van-cell v-for="item in menus" v-bind:key="item.id" is-link  @click="menuClick(item.name)">
      <template name="icon">
        <van-icon :name="item.icon?item.icon:'photo-o'" />
      </template>
      <template name="title">
        {{item.text}}
      </template>
      </van-cell>
    </van-panel>
  </div>
</template>



<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';



@Component({
    components: {
      
    }
})
export default class AppListMenu extends Vue {

        /**
         * 名称
         *
         * @type {string}
         * @memberof AppListMenu
         */
        @Prop() public title?: string;

        /**
         * 菜单数据
         *
         * @type {Array<any>}
         * @memberof AppListMenu
         */
        @Prop() public menus?:Array<any>;


        /**
         * 菜单点击事件
         *
         * @memberof AppListMenu
         */
        public menuClick($event:any){
            this.$emit('menuClick',$event);
        }
    
}
</script>
<style lang="less">
@import "./app-list-menu.less";
</style>