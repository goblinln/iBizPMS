<template>
    <div class="app-quick-group">
        <span class="app-quick-item" v-for="item in showItems" :key="item.id" @click="handleClick(item)">
            <span
                v-if="!item.children"
                :style="{ color: item.color }"
                :class="{ 'app-seleted-item': isSelectedItem(item) }"
            >
                <i v-if="item.iconcls && !Object.is(item.iconcls, '')" :class="item.iconcls"></i>
                <img v-else-if="item.icon && !Object.is(item.icon, '')" :src="item.icon" />
                <span class="app-quick-item-label">{{ item.label }}</span>
                <span
                    v-show="
                        isSelectedItem(item) &&
                            counterService &&
                            counterService.counterData &&
                            counterService.counterData[item.codename.toLowerCase()]
                    "
                    class="app-quick-item-counter"
                    >{{ itemTag(item) }}</span
                >
            </span>
            <el-dropdown
                v-if="item.children"
                style="outline: none !important;"
                trigger="hover"
                @command="handleCommand($event, item)"
            >
                <span :style="{ color: item.color }" :class="{ 'app-seleted-item': isSelectedItem(item) }">
                    <i v-if="item.iconcls && !Object.is(item.iconcls, '')" :class="item.iconcls"></i>
                    <img v-else-if="item.icon && !Object.is(item.icon, '')" :src="item.icon" />
                    <span class="app-quick-item-label">{{ item.label }}</span>
                    <span
                        v-show="
                            isSelectedItem(item) &&
                                counterService &&
                                counterService.counterData &&
                                counterService.counterData[item.codename.toLowerCase()]
                        "
                        class="app-quick-item-counter"
                        >{{ itemTag(item) }}</span
                    >
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item v-for="childitem in item.children" :command="childitem" :key="childitem.id">
                        <span :style="{ color: childitem.color }">
                            <i
                                v-if="childitem.iconcls && !Object.is(childitem.iconcls, '')"
                                :class="childitem.iconcls"
                            ></i>
                            <img v-else-if="childitem.icon && !Object.is(childitem.icon, '')" :src="childitem.icon" />
                            <span :style="{ color: childitem.label == item.label ? '#0c64eb' : '' }">{{
                                childitem.label
                            }}</span>
                        </span>
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </span>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';

@Component({})
export default class AppQuickGroup extends Vue {
    /**
     * 传入渲染项
     *
     * @type {Array<any>}
     * @memberof AppQuickGroup
     */
    @Prop() public items!: Array<any>;

    /**
     * 计数器服务名
     *
     * @type {string}
     * @memberof AppQuickGroup
     */
    @Prop() public counterService?: any;

    /**
     * UI选中项
     *
     * @type {*}
     * @memberof AppQuickGroup
     */
    public selectedUiItem: any;

    /**
     * 渲染列表
     *
     * @type {any[]}
     * @memberof AppQuickGroup
     */
    public showItems: any[] = [];

    /**
     * 翻译代码表后的初始列表
     *
     * @type {any[]}
     * @memberof AppQuickGroup
     */
    public initItems: any[] = [];

    /**
     * 监控代码表变化
     *
     * @memberof AppQuickGroup
     */
    @Watch('items', {
        immediate: true
    })
    public itemsWatch(): void {
        if (this.items && this.items.length > 0) {
            const select = this.items.find((item: any) => { return item.default; })
            this.handleClick(select ? select : this.items[0]);
            this.initItems = this.handleDataSet(this.items);
            this.showItems = Util.deepCopy(this.initItems);
        }
    }

    public itemTag(item: any) {
        if (this.counterService && this.counterService.counterData && item.codename) {
            return this.counterService.counterData[item.codename.toLowerCase()];
        } else {
            return '';
        }
    }

    /**
     * 是否选中当前项
     *
     * @param item 传入当前项
     * @memberof AppQuickGroup
     */
    public isSelectedItem(item: any) {
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
    public handleDataSet(result: Array<any>) {
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
    public setChildCodeItems(pValue: string, result: Array<any>, codeItem: any) {
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
    public handleClick($event: any, isswitch: boolean = true) {
        if ((this.selectedUiItem && this.selectedUiItem.id === $event.id) || $event.children) {
            return;
        }
        if (isswitch) {
            this.selectedUiItem = $event;
        }
        if (!$event.pvalue && !$event.children) {
            for (let i = 0; i < this.showItems.length; i++) {
                if (this.showItems[i].children) {
                    this.showItems[i].label = this.initItems[i].label;
                }
            }
        }
        this.$emit('valuechange', $event);
        this.$forceUpdate();
    }

    /**
     * 处理子项点击事件
     *
     * @param $event 值
     * @param item 父值
     * @memberof AppQuickGroup
     */
    public handleCommand($event: any, item: any) {
        this.selectedUiItem = item;
        item.label = $event.label;
        item.codename = $event.codename;
        this.handleClick($event, false);
    }
}
</script>

<style lang="less">
@import './app-quick-group.less';
</style>
