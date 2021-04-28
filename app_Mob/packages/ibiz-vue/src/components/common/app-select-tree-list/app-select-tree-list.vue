<template>
  <div class="app-select-tree-list">
    <div v-for="item in items" :key="item.srfkey" class="app-select-tree-list-item" >
      <div class="app-select-tree-list-item-header">
        <div class="index_text">{{getindexText(item.srfmajortext)}}</div>
        <div class="text">{{item.srfmajortext}}</div>
      </div>
      <div class="delete" @click="item_delete(item)">移除</div>
      </div>
  </div>
</template>
<script lang="ts">
import {  Vue,  Component,  Prop,  Provide,  Emit,  Watch} from "vue-property-decorator";

@Component({})
export default class AppSelectTreeList extends Vue{

  /**
   * 传入item
   * @type {any}
   * @memberof AppSelectTreeList
   */
  @Prop() public items?:any;

  /**
   * 渲染数据
   * @type {any}
   * @memberof AppSelectTreeList
   */
  public data :any = [];


  /**
   * getindexText
   */
  public getindexText(item:string) {
    return item.trim().substring(0,1);
  }

  @Watch('items')
  onItemsChange(){

  }


  /**
   * item_delete
   */
  public item_delete(item:any) {
    this.$emit('item_delete',item);
    this.data
  }

};
</script>
<style lang="less">
@import "./app-select-tree-list.less";
</style>