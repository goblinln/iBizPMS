<template>
    <div class="login">
        <img src="/assets/img/background.png" />
        <div class="login-con" v-if="!isEmbedThridPlatForm">
            <card :bordered="false">
                <p slot="title" style="text-align: center">&nbsp;&nbsp;{{ appTitle }}</p>
                <div class="form-con">
                    <i-form ref="loginForm" :rules="rules" :model="form">
                        <form-item prop="loginname">
                            <i-input
                                size="large"
                                prefix="ios-contact"
                                v-model.trim="form.loginname"
                                :placeholder="$t('components.login.placeholder1')"
                                @keyup.enter.native="handleSubmit"
                            >
                            </i-input>
                        </form-item>
                        <form-item prop="password">
                            <i-input
                                size="large"
                                prefix="ios-key"
                                v-model.trim="form.password"
                                type="password"
                                :placeholder="$t('components.login.placeholder2')"
                                @keyup.enter.native="handleSubmit"
                            >
                            </i-input>
                        </form-item>
                        <form-item>
                            <i-button @click="handleSubmit" type="primary" class="login_btn"
                                >{{ $t('components.login.name') }}
                            </i-button>
                            <i-button @click="goReset" type="success" class="login_reset"
                                >{{ $t('components.login.reset') }}
                            </i-button>
                        </form-item>

                        <form-item>
                            <div style="text-align: center">
                                <span class="form_tipinfo">{{ $t('components.login.other') }}</span>
                            </div>
                            <div style="text-align: center">
                                <div class="sign-btn" @click="handleThridLogin('DINGDING')">
                                    <img src="/assets/img/dingding.svg" class="third-svg-container" draggable="false" />
                                </div>
                                <div class="sign-btn" @click="handleThridLogin('WXWORK')">
                                    <img
                                        src="/assets/img/qiyeweixin.svg"
                                        class="third-svg-container"
                                        draggable="false"
                                    />
                                </div>
                            </div>
                        </form-item>
                    </i-form>
                    <p class="login-tip">
                        {{ this.loginTip }}
                    </p>
                </div>
            </card>
            <div class="log_footer">
                <div class="copyright">
                    <a href="https://www.ibizlab.cn/" target="_blank">{{ appTitle }} is based on ibizlab .</a>
                </div>
            </div>
        </div>
        <div class="login-loadding-container" v-if="isEmbedThridPlatForm">
            <div class="content-loadding">
                <span>第三方登录跳转中</span>
                <div class="loading">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Watch } from 'vue-property-decorator';
import { Environment } from '@/environments/environment';
import { removeSessionStorage, Util } from 'ibiz-core';
import { AppThirdService } from 'ibiz-vue';

@Component({
    components: {},
})
export default class Login extends Vue {
    /**
     * 表单对象
     *
     * @type {*}
     * @memberof Login
     */
    public form: any = { loginname: '', password: '' };

    /**
     * 第三方登录服务
     *
     * @type {AppThirdService}
     * @memberof Login
     */
    public appThirdService: AppThirdService = AppThirdService.getInstance();

    /**
     *　登录提示语
     *
     * @type {string}
     * @memberof Login
     */
    public loginTip: string = '';

    /**
     * 运行平台
     *
     * @type {*}
     * @memberof Login
     */
    public platform: any;

    /**
     * 是否嵌入第三方平台
     *
     * @type {boolean}
     * @memberof Login
     */
    public isEmbedThridPlatForm: boolean = false;

    /**
     *　按钮可点击
     *
     * @type {boolean}
     * @memberof Login
     */
    public canClick: boolean = true;

    /**
     * 应用名称
     *
     * @type {string}
     * @memberof Login
     */
    public appTitle: string = Environment.AppTitle;

    /**
     * 值规则
     *
     * @type {*}
     * @memberof Login
     */
    public rules = {};

    /**
     * 生命周期
     *
     * @memberof Login
     */
    public created() {
        this.setRules();
        this.platform = window.navigator.userAgent.toUpperCase();
        if (this.platform.indexOf('DINGTALK') !== -1) {
            this.DingDingLogin();
            this.isEmbedThridPlatForm = true;
        } else if (this.platform.indexOf('WXWORK') !== -1) {
            this.WXWorkLogin();
            this.isEmbedThridPlatForm = true;
        }
    }

    /**
     * 监听语言变化
     *
     * @memberof Login
     */
    @Watch('$i18n.locale')
    onLocaleChange(newval: any, val: any) {
        this.setRules();
    }

    /**
     * 设置值规则
     *
     * @memberof Login
     */
    public setRules() {
        this.rules = {
            loginname: [{ required: true, message: this.$t('components.login.loginname.message'), trigger: 'change' }],
            password: [{ required: true, message: this.$t('components.login.password.message'), trigger: 'change' }],
        };
    }

    /**
     * 重置页面
     *
     * @memberof Login
     */
    public goReset(): void {
        const _this = this;
        _this.form = { loginname: '', password: '' };
    }

    /**
     * 登陆处理
     *
     * @memberof Login
     */
    public handleSubmit(): void {
        this.clearAppData();
        const form: any = this.$refs.loginForm;
        let validatestate: boolean = true;
        form.validate((valid: boolean) => {
            validatestate = valid ? true : false;
        });
        if (!validatestate) {
            return;
        }
        const loginname: any = this.form.loginname;
        const post: Promise<any> = this.$http.post('/v7/login', this.form, true);
        post.then((response: any) => {
            if (response && response.status === 200) {
                const data = response.data;
                if (data && data.token) {
                    Util.setCookie('ibzuaa-token', data.token, 7);
                }
                if (data && data.user) {
                    Util.setCookie('ibzuaa-user', JSON.stringify(data.user), 7);
                }
                // 设置cookie,保存账号密码7天
                Util.setCookie('loginname', loginname, 7);
                // 跳转首页
                const url: any = this.$route.query.redirect ? this.$route.query.redirect : '*';
                this.$router.push({ path: url });
            }
        }).catch((error: any) => {
            // 登录提示
            const data = error.data;
            if (data && data.message) {
                this.loginTip = data.message;
                this.$throw((this.$t('components.login.loginfailed') as string) + ' ' + data.message);
            } else {
                this.$throw(this.$t('components.login.loginfailed') as string);
            }
        });
    }

    /**
     * 清除应用数据
     *
     * @private
     * @memberof Login
     */
    private clearAppData() {
        // 清除user、token
        let leftTime = new Date();
        leftTime.setTime(leftTime.getSeconds() - 1);
        document.cookie = "ibzuaa-token=;expires=" + leftTime.toUTCString();
        document.cookie = "ibzuaa-user=;expires=" + leftTime.toUTCString();
        // 清除应用级数据
        localStorage.removeItem('localdata')
        this.$store.commit('addAppData', {});
        this.$store.dispatch('authresource/commitAuthData', {});
        // 清除租户相关信息
        removeSessionStorage("activeOrgData");
        removeSessionStorage("tempOrgId");
        removeSessionStorage("dcsystem");
        removeSessionStorage("orgsData");
    }

    /**
     * 第三方登录(网页扫码方式)
     *
     * @memberof Login
     */
    public handleThridLogin(type: string) {
        if (!type) return;
        switch (type) {
            case 'DINGDING':
                this.dingtalkHandleClick();
                break;
            case 'WXWORK':
                this.wxWorkHandleClick();
                break;
            default:
                console.log(`暂不支持${type}登录`);
                break;
        }
    }

    /**
     * 钉钉授权登录
     *
     * @memberof Login
     */
    public async dingtalkHandleClick() {
        let result: any = await this.appThirdService.dingtalkLogin(Environment);
        if (result?.state && Object.is(result?.state, 'SUCCESS')) {
            const data = result.data;
            // 截取地址，拼接需要部分组成新地址
            const baseUrl = this.getNeedLocation();
            // 1.钉钉开放平台提供的appId
            const appId = data.appid;
            // 2.钉钉扫码后回调地址,需要UrlEncode转码
            const redirect_uri = baseUrl + 'assets/third/dingdingRedirect.html?id=' + data.appid;
            const redirect_uri_encode = encodeURIComponent(redirect_uri);
            // 3.钉钉扫码url
            const url =
                'https://oapi.dingtalk.com/connect/qrconnect?response_type=code' +
                '&appid=' +
                appId +
                '&redirect_uri=' +
                redirect_uri_encode +
                '&scope=snsapi_login' +
                '&state=STATE';

            // 4.跳转钉钉扫码
            window.location.href = url;
        } else {
            this.$throw(result?.message);
        }
    }

    /**
     * 企业微信授权登录
     *
     * @memberof Login
     */
    public async wxWorkHandleClick() {
        let result: any = await this.appThirdService.wxWorkLogin(Environment);
        if (result?.state && Object.is(result?.state, 'SUCCESS')) {
            const data = result.data;
            // 截取地址，拼接需要部分组成新地址
            const baseUrl = this.getNeedLocation();
            // 1.钉钉开放平台提供的appId
            const appId = data.corp_id;
            const agentId = data.agentid;
            // 2.钉钉扫码后回调地址,需要UrlEncode转码
            const redirect_uri = baseUrl + 'assets/third/wxWorkRedirect.html?id=' + data.appid;
            const redirect_uri_encode = encodeURIComponent(redirect_uri);
            // 3.钉钉扫码url
            const url =
                'https://open.work.weixin.qq.com/wwopen/sso/qrConnect?state=STATE' +
                '&appid=' +
                appId +
                '&agentid=' +
                agentId +
                '&redirect_uri=' +
                redirect_uri_encode;
            // 4.跳转钉钉扫码
            window.location.href = url;
        } else {
            this.$throw(result?.message);
        }
    }

    /**
     * 钉钉免登
     *
     * @memberof Login
     */
    public async DingDingLogin() {
        let result: any = await this.appThirdService.embedDingTalkLogin(Environment);
        if (result?.state && Object.is(result?.state, 'SUCCESS')) {
            if (result.data.token && result.data.user) {
                Util.setCookie('ibzuaa-token', result.data.token, 7);
                if (this.$route.query.redirect) {
                    window.location.href = decodeURIComponent(this.$route.query.redirect as any);
                    location.reload();
                } else {
                    this.$router.push({ path: '/' });
                }
            }
        } else {
            this.$throw(result?.message);
        }
    }

    /**
     * 企业微信免登
     *
     * @memberof Login
     */
    public async WXWorkLogin() {
        let result: any = await this.appThirdService.embedwxWorkLogin(Environment);
        if (result?.state && Object.is(result?.state, 'SUCCESS')) {
            // 截取地址，拼接需要部分组成新地址
            const baseUrl = this.getNeedLocation();
            // 1.企业微信提供的corp_id
            const appId = result.data.corp_id;
            // 2.认证成功后回调地址,需要UrlEncode转码
            const redirect_uri = baseUrl + 'assets/third/wxWorkRedirect.html?id=' + result.data.appid;
            const redirect_uri_encode = encodeURIComponent(redirect_uri);
            // 3.微信认证url
            const url =
                'https://open.weixin.qq.com/connect/oauth2/authorize?response_type=code&scope=snsapi_base&#wechat_redirect' +
                '&appid=' +
                appId +
                '&redirect_uri=' +
                redirect_uri_encode +
                '&scope=snsapi_base' +
                '&state=STATE';
            // 4.跳转到微信认证地址
            window.location.href = url;
        } else {
            this.$throw(result?.message);
        }
    }

    /**
     * 获取需要的location部分
     *
     * @memberof Login
     */
    private getNeedLocation() {
        // 截取地址，拼接需要部分组成新地址
        const scheme = window.location.protocol;
        const host = window.location.host;
        let baseUrl: any = scheme + '//' + host;
        const port = window.location.port;
        if (port) {
            if (port == '80' || port == '443') {
                baseUrl += '/';
            } else {
                baseUrl += ':' + port + '/';
            }
        } else {
            baseUrl += '/';
        }
        return baseUrl;
    }
}
</script>

<style lang='less'>
@import './login.less';
</style>