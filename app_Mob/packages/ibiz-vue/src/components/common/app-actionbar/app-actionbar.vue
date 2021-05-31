<template>
  <div class="app-actionbar">
    <div class="app-actionbar-item" v-for="(item,index) in items" :key="index">
      <template v-if="item.counterService&&item.counterService.counterData">
          <ion-badge color="success">{{ item.counterService.counterData[item.counterId] }}</ion-badge>
          <ion-button color="light" @click="handleClick(item.viewlogicname)">{{item.name}}</ion-button>
      </template>
      <ion-button v-else color="light" @click="handleClick(item.viewlogicname)">{{item.name}}</ion-button>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Model, Emit } from "vue-property-decorator";
import { Subject } from "rxjs";

@Component({})
export default class AppActionBar extends Vue {

  /**
   * 传入操作栏模型数据
   * 
   * @type {any}
   * @memberof AppActionBar
   */
  @Prop() public items!:any;

  /**
   * 触发界面行为
   * 
   * @memberof AppActionBar
   */
  public handleClick($event:any){
    this.$emit('itemClick',$event);
  }

}
</script>

<style lang='less'>
@import "./app-actionbar.less";
</style>