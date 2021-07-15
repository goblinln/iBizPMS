<template>
    <div class="app-anchor" ref="app-anchor">
        <div class="line"></div>
        <div
            :class="['anchor-points', item.active ? 'active' : 'inactive']"
            v-for="(item, index) in datas"
            :key="item.codeName"
            @click.stop="handleClick(item, index)"
            :ref="item.codeName"
        >
            <span class="dot" v-if="item.active"></span>{{ item.caption }}
        </div>
    </div>
</template>

<script lang="ts">
import { Util } from 'ibiz-core';
import { Vue, Component, Prop } from 'vue-property-decorator';

@Component({})
export default class AppAnchor extends Vue {
    /**
     * 锚点数据
     *
     * @type {any}
     * @memberof AppAnchor
     */
    @Prop() anchorDatas!: any;

    /**
     * 视图类型
     *
     * @type {any}
     * @memberof AppAnchor
     */
    @Prop() viewType!: any;

    /**
     * 绘制UI数据
     *
     * @type {any}
     * @memberof AppAnchor
     */
    public datas: any[] = [];

    /**
     * 滚动盒子
     *
     * @type {any}
     * @memberof AppAnchor
     */
    public container: any = null;

    /**
     * Vue生命周期
     *
     * @memberof AppAnchor
     */
    public created() {
        if (this.anchorDatas) {
            this.handleAnchorDatas();
        }
    }

    /**
     * Vue生命周期
     *
     * @memberof AppAnchor
     */
    public mounted() {
        // this.setFixed();
        // 需要异步获取，确保高度获取正确
        setTimeout(() => {
            this.setSrollHeight();
            // 初始化默认锚点激活状态
            this.initActiveState();
        }, 0);
        // 获取滚动区域
        this.getScrollContainer();
        // 滚动区域滚动时
        this.handleScroll();
    }

    /**
     * 设置本组件固定定位属性
     *
     * @memberof AppAnchor
     */
    public setFixed() {
        const zIndex = this.$store.getters.getZIndex();
        let dom: any = document.querySelector(`.ivu-card-body form`);
        if (dom) {
            let domTop: number = dom.getBoundingClientRect().top;
            let anchorDom: any = this.$refs['app-anchor'];
            anchorDom.style.top = domTop + 'px';
            anchorDom.style.zIndex = zIndex;
        }
    }

    /**
     * 获取锚点到滚动条盒子顶部的距离
     *
     * @memberof AppAnchor
     */
    public setSrollHeight() {
        if (this.container) {
            // 容器实际高度
            const actualHeight: number = this.container.scrollHeight;
            // 容器可视区域高度
            const visualHeight: number = this.container.offsetHeight;
            // 容器滑动条最大高度
            const scrollHeight: number = actualHeight - visualHeight;
            // 系数
            const coefficient = scrollHeight / actualHeight;
            this.datas.forEach((item: any) => {
                let dom: any = document.querySelector(`.ivu-card-body #${item.codeName}`);
                if (dom) {
                    // 获取锚点元素与滚动盒子元素相对于视口的距离
                    let domTop: number = dom.getBoundingClientRect().top;
                    let containerTop: number = this.container.getBoundingClientRect().top;
                    // 获取需要滚动的距离
                    let total = Math.trunc((domTop - containerTop) * coefficient);
                    this.$set(item, 'scrollTop', total);
                }
            });
        }
    }

    /**
     * 获取滚动区域
     *
     * @memberof AppAnchor
     */
    public getScrollContainer() {
        switch (this.viewType) {
            case 'DEEDITVIEW':
                this.container = document.querySelector(`.deeditview .ivu-card-body .content-container`);
                break;
            case 'DEEDITVIEW3':
                this.container = document.querySelector(`.ivu-card-body .content-container .main-data`);
                break;
            default:
                this.container = document.querySelector(`.ivu-card-body .content-container`);
                break;
        }
    }

    /**
     * 滚动区域滚动
     *
     * @memberof AppAnchor
     */
    public handleScroll() {
        if (this.container) {
            this.container.addEventListener('scroll', (e: any) => {
                let scrollTop = e.target.scrollTop;
                this.datas.forEach(item => {
                    item.active = false;
                });
                let i = -1;
                while (++i < this.datas.length) {
                    let currentEle = this.datas[i];
                    let nextEle = this.datas[i + 1];
                    if (scrollTop >= currentEle.scrollTop && scrollTop < ((nextEle && nextEle.scrollTop) || Infinity)) {
                        this.datas[i].active = true;
                        break;
                    }
                }
            });
        }
    }

    /**
     * 处理数据
     *
     * @memberof AppAnchor
     */
    public handleAnchorDatas() {
        this.datas = Util.deepCopy(this.anchorDatas);
        this.datas.forEach((item: any) => {
            this.$set(item, 'active', false);
        });
    }

    /**
     * 设置锚点激活状态
     *
     * @memberof AppAnchor
     */
    public initActiveState() {
        if (this.datas?.length > 0) {
            this.handleClick(this.datas[0]);
        }
    }

    /**
     * 点击锚点
     *
     * @memberof AppAnchor
     */
    public handleClick(item: any, index?: any) {
        // 设置滚动
        if (this.container) {
            this.container.scrollTop = item.scrollTop;
        }
    }
}
</script>
<style lang="less">
@import './app-anchor.less';
</style>
