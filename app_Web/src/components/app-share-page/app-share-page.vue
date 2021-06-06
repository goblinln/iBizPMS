<template>
    <div class="app-share-page">
        <div class="content-container">
            <img src="@/assets/img/logo.png"/>
            <span class="apply">{{ userName }} {{$t('components.appsharepage.invite')}}</span>
            <button class="apply-button" @click='applyShareOptions'>{{ $t('components.appsharepage.apply') }}</button>
            <button class="cancel-button" @click='cancel'>{{ $t('components.appsharepage.cancel') }}</button>
        </div>
    </div>
</template>

<script lang="ts">
import qs from 'qs';
import { Vue, Component } from 'vue-property-decorator';

@Component({})
export default class AppSharePage extends Vue{
    
    /**
     * 链接参数
     *
     * @type {*}
     * @memberof AppShareTheme
     */
    public urlParams: any = {};

    /**
     * 分享用户
     *
     * @type {*}
     * @memberof AppShareTheme
     */
    public userName: any;

    /**
     * vue生命周期 -- created
     *
     * @memberof AppShareTheme
     */
    public created() {
        this.urlParams = this.parseViewParam(window.location.href);
        this.userName = decodeURIComponent(this.urlParams['shareUserName']);
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