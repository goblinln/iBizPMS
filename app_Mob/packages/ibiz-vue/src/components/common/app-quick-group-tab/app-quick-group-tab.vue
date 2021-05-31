<template>
<div>
  <div class="app-quick-group">
    <div :class="{'quick-group-tab':true,'app-seleted-item':isSelectedItem(item) || item.childSelected}" v-for="(item,index) in showItems" :key="index" @click="handleClick(item)">
      <div :style="{color:item.color}">
        <ion-icon v-if=" item.iconcls && !Object.is(item.iconcls, '')" :name="ViewTool.setIcon(item.iconcls)"></ion-icon>
        <img v-else-if="item.icon && !Object.is(item.icon, '')" :src="item.icon" />
        <span v-if="item.selectChildLabel" class="app-quick-item-label">{{item.selectChildLabel}}</span>
        <span v-else class="app-quick-item-label">{{item.label}}</span>
        <ion-icon v-if="item.children" name="caret-down-outline" style="margin-left:4px"></ion-icon>
      </div>
      <ion-badge class="badge" v-if="isSelectedItem(item) && pageTotal !== 0 && !item.children">{{pageTotal}}</ion-badge>
      <ion-badge class="badge" v-if="item.childSelected && pageTotal">{{pageTotal}}</ion-badge>
    </div>
  </div>
  <div ref="child-list" :class="{'child-list':true,'open':subItems.length > 0}">
    <div :class="{'child':true,'selected':item.selected}" v-for="(item,index) in subItems" :key="index" @click="handleClick(item)">
      <span>
        <ion-icon v-if=" item.iconcls && !Object.is(item.iconcls, '')" :name="ViewTool.setIcon(item.iconcls)"></ion-icon>
        <img v-else-if="item.icon && !Object.is(item.icon, '')" :src="item.icon" />
        <span>{{item.label}}</span>
      </span>
      <ion-badge class="badge" v-if="pageTotal !== 0 && item.selected">{{pageTotal}}</ion-badge>
      <ion-icon size="small" v-if="item.selected" style="margin-left:auto;color:green" name="checkmark-outline"></ion-icon>
    </div>
  </div>
  <ion-backdrop style="height:100vh;z-index:-1" v-show="subItems.length > 0" visible="true" tappable="true" @ionBackdropTap="closeBackdrop"></ion-backdrop>
</div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch, } from "vue-property-decorator";
import { ViewTool } from "ibiz-core";
@Component({
    components: {},
})
export default class AppQuickGroupTab extends Vue {
    /**
     * 快速分组代码表
     *
     * @type {any[]}
     * @memberof ViewQuickGroupTab
     */
    @Prop({ default: () => [] }) public items!: Array<any>;

    /**
   * 快速分组代码表
   *
   * @type {any[]}
   * @memberof ViewQuickGroupTab
   */
    @Prop({ default: 0 }) public pageTotal!: number;

    /**
     * 渲染列表
     *
     * @type {any[]}
     * @memberof AppQuickGroup
     */
    public showItems: any[] = [];

    /**
     * 子项列表
     *
     * @type {any[]}
     * @memberof AppQuickGroup
     */
    public subItems: any[] = [];

    /**
     * 监控代码表变化
     *
     * @memberof AppQuickGroup
     */
    @Watch("items", { immediate: true })
    public itemsWatch(): void {
        if (this.items.length > 0) {
            this.items.every((item: any) => {
                if (item.default) {
                    this.$nextTick(() => {
                        this.handleClick(item);
                    })
                }
                return !item.default;
            });
            const select = this.items.find((item: any)=> { return item.default });
            this.$nextTick(() => {
                this.handleClick(select ? select : this.items[0], true);
            })
            this.showItems = this.handleDataSet(this.items);
        }
    }

    /**
     * UI选中项
     *
     * @type {*}
     * @memberof AppQuickGroup
     */
    public selectedUiItem: any = {};

    /**
     * 是否选中当前项
     *
     * @param item 传入当前项
     * @memberof AppQuickGroup
     */
    public isSelectedItem(item: any): boolean {
        if (this.selectedUiItem && this.selectedUiItem.id === item.id) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 处理代码表返回数据(树状结构)
     *
     * @param result 返回数组
     * @memberof AppQuickGroup
     */
    public handleDataSet(result: Array<any>): any[] {
        let list: Array<any> = [];
        if (result.length === 0) {
            return list;
        }
        result.forEach((codeItem: any) => {
            if (!codeItem.pvalue) {
                let valueField: string = codeItem.value;
                this.setChildCodeItems(valueField, result, codeItem);
                list.push(codeItem);
            }
        });
        return list;
    }

    /**
     * 处理非根节点数据
     *
     * @param pValue 父值
     * @param result 返回数组
     * @param codeItem 代码项
     * @memberof AppQuickGroup
     */
    public setChildCodeItems(pValue: string, result: Array<any>, codeItem: any): void {
        result.forEach((item: any) => {
            if (item.pvalue == pValue) {
                let valueField: string = item.value;
                this.setChildCodeItems(valueField, result, item);
                if (!codeItem.children) {
                    codeItem.children = [];
                }
                codeItem.children.push(item);
            }
        });
    }

    /**
     * 处理点击事件
     *
     * @param $event 值
     * @param isswitch 是否切换UI选中项
     * @memberof AppQuickGroup
     */
    public handleClick($event: any, isfirst:boolean = false): void {
        this.selectedUiItem = $event;
        if ($event.children) {
            if (this.subItems.length > 0) {
                this.subItems.length = 0;
            } else {
                if (!isfirst) {
                  this.subItems.push(...$event.children);
                }
            }
        } else {
            this.subItems.length = 0;
            this.items.forEach((item: any) => {
                item.selected = false;
                item.childSelected = false;
                item.selectChildLabel = "";
            })
            $event.selected = true;
            if ($event.pvalue) {
                this.items.forEach((item: any) => {
                    if (item.value === $event.pvalue) {
                        item.childSelected = true;
                        item.selectChildLabel = $event.label;
                    }
                })
            }
            this.$emit("valuechange", $event);

        }
        this.$forceUpdate();
    }

    /**
     * 关闭遮罩层
     *
     * @type {any[]}
     * @memberof AppQuickGroup
     */
    public closeBackdrop() {
        this.subItems.length = 0;
        this.$forceUpdate();
    }
}
</script>

<style lang="less">
@import "./app-quick-group-tab.less";
</style>