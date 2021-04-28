<template>
    <!-- <transition name="fade"> -->
        <app-keep-alive :routerList="getRouterList">
            <router-view :key="this.$route.fullPath"></router-view>
        </app-keep-alive>
    <!-- </transition> -->

</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component({})
export default class ViewShell extends Vue {

    /**
     * 获取路由列表
     *
     * @readonly
     * @type {any[]}
     * @memberof AppIndexViewBase
     */
    get getRouterList(): any[] {
        return this.$store.state.historyPathList;
    }

    /**
     * 首页上下文
     *
     * @type {*}
     * @memberof ViewShell
     */
    public context: any = {};

    /**
     * 首页参数
     *
     * @type {*}
     * @memberof ViewShell
     */
    public viewparams: any = {};

    /**
     * vue 生命周期
     *
     * @memberof ViewShell
     */
    public created() {
        this.parseViewParam();
        this.$viewTool.setIndexViewParam(this.context);
    }

    /**
     * 解析参数
     *
     * @private
     * @memberof ViewShell
     */
    private parseViewParam(): void {
        const path = (this.$route.matched[this.$route.matched.length - 1]).path;
        const keys: Array<any> = [];
        const curReg = this.$pathToRegExp.pathToRegexp(path, keys);
        const matchArray = curReg.exec(this.$route.path);
        let tempValue: Object = {};
        keys.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item.name, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
        this.$viewTool.formatRouteParams(tempValue, this.$route, this.context, this.viewparams);
    }
}
</script>

<style scoped>
    .fade-enter-active, .fade-leave-avtive {
        transition: opacity 1s
    }
    .fade-enter, .fade-leave-to {
        opacity: 0
    }
</style>