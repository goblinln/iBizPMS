<template>
    <el-breadcrumb class="app-breadcrumb" :separator="separator">
        <transition-group name="breadcrumb">
            <el-breadcrumb-item v-for="(item, index) in breadcrumbs" :key="item.tag">
                <span v-if="index === breadcrumbs.length - 1" class="no-redirect"
                    >{{ item.title }}
                    <span v-if="isShowSelected(item)">
                        <dropdown trigger="click" @on-click="selectNavData($event, item)">
                            <span class="app-breadcrumb-selected">
                                <i class="el-icon-caret-bottom"></i>
                            </span>
                            <dropdown-menu slot="list">
                                <dropdown-item
                                    v-for="dataitem in getPreNavData(item)"
                                    :name="dataitem.srfkey"
                                    :key="dataitem.srfkey"
                                >
                                    <span :class="{ curselected: isCurSelected(item, dataitem) }">{{
                                        dataitem.srfmajortext
                                    }}</span>
                                </dropdown-item>
                            </dropdown-menu>
                        </dropdown>
                    </span>
                </span>
                <a v-else @click.prevent="handleLink(item)">{{ item.title }}</a>
            </el-breadcrumb-item>
        </transition-group>
    </el-breadcrumb>
</template>

<script lang="ts">
import { Component, Vue, Watch, Prop } from 'vue-property-decorator';
import { Subscription } from 'rxjs';
import { LogUtil } from 'ibiz-core';
import { NavDataService } from 'ibiz-vue';
import { appConfig } from '@/config/appConfig';
import { Environment } from '@/environments/environment';

@Component({})
export default class Breadcrumb extends Vue {
    /**
     * 面包屑列表
     *
     * @memberof Breadcrumb
     */
    private breadcrumbs: Array<any> = [];

    /**
     * 面包屑分隔符
     *
     * @memberof Breadcrumb
     */
    private separator: string = appConfig.breadcrumbSeparator;

    /**
     * 导航服务
     *
     * @memberof Breadcrumb
     */
    private navDataService = NavDataService.getInstance(this.$store);

    /**
     * 首页tag
     *
     * @memberof Breadcrumb
     */
    @Prop() public indexViewTag?: string;

    /**
     * 监听路由
     *
     * @memberof Breadcrumb
     */
    @Watch('navDataService.navDataStack')
    private onNavDataStackChange(data: any) {
        this.getBreadcrumb();
    }

    /**
     * 导航服务事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof Dev
     */
    public serviceStateEvent: Subscription | undefined;

    /**
     * vue  生命周期
     *
     * @memberof Breadcrumb
     */
    created() {
        this.getBreadcrumb();
    }

    /**
     * 获取面包屑数据
     *
     * @memberof Breadcrumb
     */
    private getBreadcrumb() {
        const navData: Array<any> = this.navDataService.getNavData();
        if (navData && navData.length > 0) {
            this.breadcrumbs = navData.filter((item: any) => {
                return item.viewmode;
            });
        } else {
            this.breadcrumbs = [];
        }
        this.$forceUpdate();
    }

    /**
     * 获取面包屑指定元素前一条数据
     *
     * @memberof Breadcrumb
     */
    private getPreNavData(item: any) {
        let preNavData: any = this.navDataService.getPreNavData(item.tag);
        return preNavData?.data;
    }

    /**
     * 判断是否为当前选中项
     *
     * @memberof Breadcrumb
     */
    private isCurSelected(item: any, singleItem: any) {
        return item.key === singleItem.srfkey;
    }

    /**
     * 面包屑点击行为
     *
     * @memberof Breadcrumb
     */
    private handleLink(item: any) {
        if (item && Object.is(item.tag, this.indexViewTag)) {
            this.$router.push(window.sessionStorage.getItem(Environment.AppName) as string).catch(err => {
                LogUtil.warn(err);
            });
            this.navDataService.removeNavDataFrist();
        } else {
            this.$router.push(item.path).catch(err => {
                LogUtil.warn(err);
            });
            this.navDataService.removeNavData(item.tag);
        }
    }

    /**
     * 切换导航行为
     *
     * @memberof Breadcrumb
     */
    private selectNavData($event: any, item: any) {
        this.navDataService.serviceState.next({ action: 'viewrefresh', name: item.tag, data: $event });
        this.$forceUpdate();
    }

    /**
     * 是否显示下拉列表
     *
     * @memberof Breadcrumb
     */
    public isShowSelected(item: any) {
        if (!item || !item.tag) {
            return false;
        }
        const preElementData: any = this.getPreNavData(item);
        return preElementData && Array.isArray(preElementData) ? true : false;
    }

    /**
     * 组件销毁
     *
     * @memberof Breadcrumb
     */
    public destroyed() {
        if (this.serviceStateEvent) {
            this.serviceStateEvent.unsubscribe();
        }
    }
}
</script>

<style lang='less'>
@import './app-breadcrumb.less';
</style>