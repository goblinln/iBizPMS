<template>
    <div class="app-mob-menu-list-view">
        <ion-card-content class="app-mob-menu-list-view__content">
            <ion-list>
                <template v-for="item in items">
                    <ion-item :key="item.id" @click="selectItem(item.name)" :class="item.textcls?item.textcls:''">
                        <span slot="start">
                            <!-- 图片：BEGIN -->
                            <template v-if="item.icon != ''">
                                <ion-avatar>
                                    <img :src="item.icon"/>
                                </ion-avatar>
                            </template>
                            <!-- 图片：END -->
                            <!-- fontawesome：END -->
                            <!-- http://www.fontawesome.com.cn/ -->
                            <template v-else-if="item.iconcls != '' && item.iconcls.indexOf('fa fa') === 0">
                                <i :class="item.iconcls"></i>
                            </template>
                            <!-- fontawesome：END -->
                            <!-- ionic 图标：BEGIN -->
                            <template v-else-if="item.iconcls != ''">
                                <app-mob-icon  :name="item.iconcls"></app-mob-icon>
                            </template>
                            <!-- ionic 图标：BEGIN -->
                            <template v-else>
                                <app-mob-icon name="home-outline"></app-mob-icon>
                            </template>
                        </span>
                        <ion-label>
                            {{$t(`app.menus.${menuName}.${item.name}`)}}
                        </ion-label>
                        <template v-if="counterdata[item.counterid]">
                            <ion-badge color="danger" slot="end">{{counterdata[item.counterid]}}</ion-badge>
                        </template>
                    </ion-item>
                </template>
            </ion-list>
        </ion-card-content>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Emit } from 'vue-property-decorator';
@Component({
    components: {
    }
})
export default class AppMobMenuListView extends Vue {

    /**
     * 菜单名称
     *
     * @type {string}
     * @memberof AppMobMenuListView
     */
    @Prop() public menuName!: string;

    /**
     * 菜单数据项
     *
     * @type {Array<any>}
     * @memberof AppMobMenuListView
     */
    @Prop() public items!: Array<any>;

    /**
     * 计数器名称
     *
     * @type {string}
     * @memberof AppMobMenuListView
     */
    @Prop() public counterName!: string;

    /**
     * 菜单选中事件
     *
     * @param {*} val
     * @returns
     * @memberof AppMobMenuListView
     */
    @Emit()
    select(val: any) {
        return val;
    }

    /**
     * 选中菜单项
     *
     * @param {string} name
     * @memberof AppMobMenuListView
     */
    public selectItem(name: string): void {
        this.select(name);
    }

    /**
     * 计数器数据
     *
     * @type {*}
     * @memberof AppMobMenuListView
     */
    public counterdata: any = {};

    /**
     * vue 生命周期
     *
     * @memberof AppMobMenuListView
     */
    public created() {
        this.loadCounterData();
    }

    /**
     * vue 生命周期
     *
     * @memberof AppMobMenuListView
     */
    public destroyed() {
        this.counterdata = null;
    }

    /**
     * 加载计数器数据
     *
     * @returns {Promise<any>}
     * @memberof AppMobMenuListView
     */
    public async loadCounterData(): Promise<any> {
        // todo 计数器
        // const counterServiceConstructor = window.counterServiceConstructor;
        // const counterServide = await counterServiceConstructor.getService(this.counterName);
        // if (counterServide) {
        //     this.counterdata = counterServide.counterData;
        // }
    }
}
</script>

<style lang="less">
@import "./app-mob-menu-list-view.less";
</style>