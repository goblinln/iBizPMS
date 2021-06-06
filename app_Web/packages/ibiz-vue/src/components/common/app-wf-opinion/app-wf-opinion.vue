<template>
    <div :class="['app-wf-opinion', isShow ? 'is-show' : '']" ref='wf-opinion'>
        <el-button v-if="!isShow" type="primary" size="small" @click="click">意见</el-button>
        <template>
            <div v-show="isShow" ref="wf-opinion-container" class="wf-opinion-container" :style="{ 'height': containerHeight + 'px' }">
                <div class="header">
                    <span class="title">意见</span>
                    <div class="icon-container">
                        <i :class="['icon', enableClick ? 'el-icon-lock' : 'el-icon-unlock' ]" @click="enableClick = !enableClick;"/>
                        <i :class="['icon', 'el-icon-minus', enableClick ? 'enable' : '']" @click="minusClick"/>
                    </div>
                </div>
                <div class="content">
                    <div class="textarea">
                        <input-box v-model="value" @change="valueChange" :placeholder="placeholder || '请输入...'" type="textarea"/>
                    </div>
                    <div class="navbar">
                        <div class="navbar-header">
                            <i-input search placeholder="内容搜索…" @on-search="onSearch" />
                        </div>
                        <div class="navbar-items">
                            <template v-for="(filter, index) in filterItems">
                                <span :class="getClass(filter)" :key="index" @click="filterItemClick(filter, $event)">{{ filter }}</span>
                            </template>
                        </div>
                    </div>
                </div>
            </div>
        </template>
    </div>
</template>


<script lang="ts">
import { Vue, Prop, Component } from 'vue-property-decorator';

@Component({})
export default class AppWFOpinion extends Vue {
    
    /**
     * 项名称
     * 
     * @type {string}
     * @memberof AppWFOpinion
     */
    @Prop() public name!: string;

    /**
     * 值
     * 
     * @type {*}
     * @memberof AppWFOpinion
     */
    @Prop() public value!: any;

    /**
     * 是否禁用
     * 
     * @type {boolean}
     * @memberof AppWFOpinion
     */
    @Prop({ default: false }) public disabled?: boolean;

    /**
     * 空白提示内容
     * 
     * @type {string}
     * @memberof AppWFOpinion
     */
    @Prop() public placeholder: any;

    /**
     * 应用上下文
     * 
     * @type {*}
     * @memberof AppWFOpinion
     */
    @Prop() public context: any;

    /**
     * 视图参数
     * 
     * @type {*}
     * @memberof AppWFOpinion
     */
    @Prop() public viewparams: any;

    /**
     * 输入框id
     * 
     * @type {*}
     * @memberof AppWFOpinion
     */
    @Prop() public textareaId: any;

    /**
     * 父容器id
     * 
     * @type {*}
     * @memberof AppWFOpinion
     */
    @Prop() public parentContainerId: any;

    /**
     * 容器高度
     * 
     * @type {number}
     * @memberof AppWFOpinion
     */
    @Prop({ default: 220 }) public containerHeight?: number;

    /**
     * 是否显示详情框
     * 
     * @type {boolean}
     * @memberof AppWFOpinion
     */
    public isShow: boolean = true;

    /**
     * 过滤选中项
     * 
     * @type {*}
     * @memberof AppWFOpinion
     */
    public selectItem: any = null;

    /**
     * 过滤项
     * 
     * @type {Array<any>}
     * @memberof AppWFOpinion
     */
    public filterItems: Array<any> = [];

    /**
     * 是否禁用缩小点击
     * 
     * @type {boolean}
     * @memberof AppWFOpinion
     */
    public enableClick: boolean = false;

    /**
     * Vue生命周期 -- created
     * 
     * @memberof AppWFOpinion
     */
    public created() {
        this.handleFilterItems();
    }

    /**
     * Vue生命周期 -- mounted
     * 
     * @memberof AppWFOpinion
     */
    public mounted() {
        this.$nextTick(() => {
            const dom: any = this.$refs['wf-opinion'];
            if (dom && dom.parentNode) {
                dom.parentNode.style.position = 'sticky';
                dom.parentNode.style.bottom = '0px';
            }
        })
    }

    /**
     * 处理过滤项
     * 
     * @memberof AppWFOpinion
     */
    public handleFilterItems() {
        this.filterItems = ['同意', '不同意', '已阅', '待阅', '未查看', '已查看'];
    }

    /**
     * 过滤项点击
     * 
     * @param item 过滤项
     * @param event 点击事件
     * @memberof AppWFOpinion
     */
    public filterItemClick(item: any, event: any) {
        if (!item) {
            return;
        }
        this.selectItem = item;
    }

    /**
     * 获取过滤项样式
     * 
     * @param item 过滤项
     * @memberof AppWFOpinion
     */
    public getClass(item: any) {
        if (this.selectItem && Object.is(this.selectItem, item)) {
            return ['filter-item', 'select-item'];
        }
        return ["filter-item"];
    }

    /**
     * 搜索
     * 
     * @param item 搜索值
     * @memberof AppWFOpinion
     */
    public onSearch(value: any) {
        //TODO
    }

    /**
     * 缩小按钮点击
     * 
     * @memberof AppWFOpinion
     */
    public minusClick() {
        if (!this.enableClick) {
            this.isShow = false;
        }
    }

    /**
     * 按钮点击事件
     * 
     * @memberof AppWFOpinion
     */
    public click() {
        this.isShow = true;
        this.scrollToContainer();
    }

    /**
     * 滚动到容器位置
     * 
     * @memberof AppWFOpinion
     */
    public scrollToContainer() {
        const cubic = (value: any) => Math.pow(value, 3);
        const easeInOutCubic = (value: any) => value > 0.5
            ? 1 - cubic((1 - value) * 2) / 2
            : cubic(value * 2) / 2;
        const el: any = document.querySelector(`#${this.parentContainerId} .ivu-row .ivu-tabs-content`);
        if (!el) {
            return;
        }
        const beginTime = Date.now();
        const beginValue = el.scrollTop;
        const endValue = el.scrollHeight + this.containerHeight;
        const rAF = window.requestAnimationFrame || (func => setTimeout(func, 16));
        const frameFunc = () => {
            const progress = (Date.now() - beginTime) / 500;
            if (progress < 1) {
                el.scrollTop = (endValue - beginValue) * (easeInOutCubic(progress)) + beginValue;
                rAF(frameFunc);
            } else {
                el.scrollTop = endValue;
            }
        };
        rAF(frameFunc);
    }

    /**
     * 值变更
     * 
     * @memberof AppWFOpinion
     */
    public valueChange(value: any) {
        this.$emit('change', value);
    }

}    
</script>

<style lang="less">
@import './app-wf-opinion.less'; 
</style>