<template>
  <div class="anchor">
    <div v-for="(item,showItemIndex) in showItems" :key="showItemIndex+'a'" class="main-list">
      <div v-if="item.isAnchor" class="anchor-point">
        <span v-if="item.group" :id="item.group">{{item.group}}</span>
      </div>
      <div class="detail" @click="termClick(item)">
        <span>{{item.mobordername}}</span>
      </div>
    </div>
    <div v-for="(item,unSetIndex) in unSetGroupList" :key="unSetIndex+'b'" class="main-list">
      <div v-if="unSetIndex === 0" class="anchor-point" id="notGrouped">#</div>
      <div class="detail" @click="termClick(item)">
        <span>{{item.mobordername}}</span>
      </div>
    </div>
    <div class="anchor-list">
      <div v-for="(anchor,anchorIndex) in anchorList" :key="anchorIndex+'c'" class="anchor-item" @click="jump(anchor)">
        <span>{{anchor}}</span>
      </div>
      <div class="anchor-item" @click="jump('notGrouped')">#</div>
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
  Watch,
} from "vue-property-decorator";
@Component({
  components: {},
})
export default class AppQuickGroupTab extends Vue {
  /**
   * 数据项集合
   *
   * @type {string}
   * @memberof Mob
   */
  @Prop() protected items?: any;

  /**
   * UI数据项集合
   *
   * @type {string}
   * @memberof Mob
   */
  public showItems: Array<any> = [];

  /**
   * 分组项集合
   *
   * @type {string}
   * @memberof Mob
   */
  public anchorList: Array<any> = [];

  /**
   * 分组项集合
   *
   * @type {string}
   * @memberof Mob
   */
  public unSetGroupList: Array<any> = [];

  /**
   * 监控items变化
   *
   * @memberof AppQuickGroup
   */
  @Watch("items", { immediate: true })
  public itemsWatch(newVal: any, oldVal: any): void {
    if (newVal) {
      // 清空当前ui数组
      this.showItems.length = 0;
      this.unSetGroupList.length = 0;
      this.$forceUpdate();
      
      // 根据分组属性进行排序
      this.showItems.push(...newVal);
      this.showItems.sort((prev: any, next: any) => {
        let x:any = prev.group;
        let y:any = next.group;
        if (x < y) {return -1}
        if (x > y) {return 1}
        return 0;
      });

      // 没有分组的单独处理
      this.unSetGroupHandled();

      // 处理锚点列表数据
      this.anchorListHandled(newVal);

      // 设置锚点
      this.setAnchor();
    }
  }

  /**
   * 锚点列表处理
   *
   * @memberof AppQuickGroup
   */
  public anchorListHandled(array:any) {
    // 赋值锚点列表
    array.forEach((item: any) => {
      if (item.group) {
        this.anchorList.push(item.group);
      }
    });
    // 锚点列表去重
    this.anchorList = this.anchorList.filter((anchor: any, index: any) => {
      return this.anchorList.indexOf(anchor, 0) === index;
    });
    // 锚点列表排序
      this.anchorList.sort((prev: any, next: any) => {
        let x:any = prev;
        let y:any = next;
        if (x < y) {return -1}
        if (x > y) {return 1}
        return 0;
      });
  }

  /**
   * 列表项点击事件
   *
   * @memberof AppQuickGroup
   */
  public termClick(item:any) {
    this.$emit("termClick",item)
  }

  /**
   * 未分组项处理
   *
   * @memberof AppQuickGroup
   */
  public unSetGroupHandled() {
    this.showItems.forEach((item:any,index:any) => {
      if (!item.group) {
        this.unSetGroupList.push(item)
        this.showItems.splice(index,1)
      }
    })
  }

  /**
   * 设置锚点
   *
   * @memberof AppQuickGroup
   */
  public setAnchor() {
    if (this.anchorList.length > 0) {
      this.anchorList.forEach((anchor: any) => {
        let index = this.showItems.findIndex((item: any) => {
          if (item.group) {
            return item.group === anchor;
          }
        });
        if (index !== -1) {
          this.showItems[index].isAnchor = true;
        }
      });
    }
  }

  /**
   * 锚点跳转
   *
   * @memberof AppQuickGroup
   */
  public jump(id:string) {
    let target:any = document.querySelector(`#${id}`);
    if (target) {
      target.scrollIntoView(true);
      this.$toast({
        duration:750,
        message:`${id}`,
      })
    }
  }
}
</script>

<style lang="less">
@import "./app-anchor-list.less";
</style>