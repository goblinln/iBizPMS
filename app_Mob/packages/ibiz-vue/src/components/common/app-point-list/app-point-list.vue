<template>
  <van-index-bar :index-list="marklist" class="point">
    <div v-for="item in marklist" :key="item">
      <van-index-anchor v-if="marklist" :index="item" />
      <div v-for="(i,index) in items" :key="index">
        <van-cell v-if="i.group == item" :title="i.srfmajortext" @click="test" />
      </div>
    </div>
    <div v-if="marklist.length==0">{{$t('without_group')}}</div>
  </van-index-bar>
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
  components: {},
    i18n: {
        messages: {
            'ZH-CN': {
                without_group: '没有group属性无法分组',
            },
            'EN-US': {
                without_group: 'Cannot group without group attribute',     
            }
        }
    }
})
export default class AppPointList extends Vue {
  /**
   * 传入item
   *
   * @type {any}
   * @memberof AppIconList
   */
  @Prop() public items?: Array<any>;

  /**
   * 数组
   *
   * @type {Array<any>}
   * @memberof AppPointList
   */
  @Provide() public marklist: Array<any> = [];

  /**
   * 将集合中的数据按照mark标识进行分组  将组别塞入marklist中
   * @memberof AppPointList
   */
  created() {
    let nowmark = "";
    let isother = false;
    if (this.items) {
      for (var i = 0; i < this.items.length; i++) {
        nowmark = this.items[i].group;
        if (this.marklist.indexOf(nowmark) === -1) {
          if (nowmark == "null" || nowmark.trim().length === 0) {
            this.items[i].group = "#";
            isother = true;
          } else {
            this.marklist.push(nowmark);
          }
        }
      }
    }
    this.marklist.sort();
    if (isother) {
      this.marklist.push("#");
    }
  }
}
</script>
<style lang="less">
@import "./app-point-list.less";
</style>