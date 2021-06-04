<template>
    <div class="app-share-page">
        <div class="content-container">
            <span>是否应用主题</span>
        </div>
        <div class="button-container">
            <button @click='applyShareOptions'>应用</button>
            <button @click='cancel'>取消</button>
        </div>
            
    </div>
</template>

<script lang="ts">
import qs from 'qs';
import { Vue, Component } from 'vue-property-decorator';

@Component({})
export default class AppSharePage extends Vue{
    
    /**
     * vue生命周期 -- created
     *
     * @type {*}
     * @memberof AppShareTheme
     */
    public urlParams: any = {};

    /**
     * vue生命周期 -- created
     *
     * @memberof AppShareTheme
     */
    public created() {
        this.urlParams = this.parseViewParam(window.location.href);
    }

    /**
     * vue生命周期 -- mounted
     *
     * @memberof AppShareTheme
     */
    public mounted() {
        setTimeout(() => {
            const el = document.getElementById('app-loading-x');
            if (el) {
                el.style.display = 'none';
            }
        }, 300);
    }

    /**
     * 处理路径数据
     *
     * @param {*} [urlStr] 路径
     * @memberof AppShareTheme
     */
    public parseViewParam(urlStr: string): any {
        let tempViewParam: any = {};
        const tempViewparam: any = urlStr.slice(urlStr.lastIndexOf('?') + 1);
        const viewparamArray: Array<string> = decodeURIComponent(tempViewparam).split(';');
        if (viewparamArray.length > 0) {
            viewparamArray.forEach((item: any) => {
                Object.assign(tempViewParam, qs.parse(item));
            });
        }
        return tempViewParam;
    }

    /**
     * 应用分享配置
     *
     * @memberof AppShareTheme
     */
    public applyShareOptions() {
        let url: string = '/share?';
        Object.keys(this.urlParams).forEach((param: any, index: number) => {
            url += `${param}=${this.urlParams[param]}${index == Object.keys(this.urlParams).length - 1 ? '' : '&'}`;
        })
        this.$router.push(url);
    }

    /**
     * 取消应用
     *
     * @memberof AppShareTheme
     */
    public cancel() {
        this.$router.push('/appindexview');
    }
}
</script>

<style lang="less">
@import './app-share-page.less';
</style>