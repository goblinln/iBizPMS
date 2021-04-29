<template>
    <ion-page :className="{ 'app-login': true }">
        <ion-content fullscreen>
            <div class="app-login-contant">
                <form class="app-login-form" :class="isloginPage?'activeFrom':''">
                    <span class="title">登录</span>
                    <ion-item lines="none">
                        <ion-label position="stacked">{{$t('username')}}：</ion-label>
                        <ion-input clear-input required type="text" debounce="100" :value="username" @ionChange="($event) => username = $event.detail.value"></ion-input>
                    </ion-item>
                    <ion-item lines="none">
                        <ion-label position="stacked">{{$t('password')}}：</ion-label>
                        <ion-input clear-input required type="password" debounce="100" :value="password" @ionChange="($event) => password = $event.detail.value"></ion-input>
                    </ion-item>
                    <div class="ion-padding button">
                        <ion-button expand="block" :disabled="isLoadding" class="ion-no-margin" @click="login('login')">{{$t('submit')}}</ion-button>
                    </div>
                        <div class="forgetpassword" :style="!isloginPage?'display:none':''"><span @click='forgetpassword'>忘记密码?</span></div>
                        <div class="app-register-text-contet" :style="isloginPage?'display:none':''"><span class="app-register-text">已有账户？<span class="register-button" @click="activeChange">立刻登录!</span></span></div>
                    <div class="visitor" v-if="isVisitorsMode">
                        <ion-button expand="block" color="medium" size="small" fill="clear" class="ion-visitor" @click="login('visitors')">以访客身份登录</ion-button>
                    </div>
                </form>
                 <form class="app-register-form" :class="!isloginPage?'activeFrom':''">
                    <span class="title">注册</span>
                    <ion-item lines="none">
                        <ion-label position="stacked">{{$t('username')}}：</ion-label>
                        <ion-input clear-input required type="text" debounce="100" :value="username" @ionChange="($event) => username = $event.detail.value"></ion-input>
                    </ion-item>
                    <ion-item lines="none">
                        <ion-label position="stacked">{{$t('password')}}：</ion-label>
                        <ion-input clear-input required type="password" debounce="100" :value="password" @ionChange="($event) => password = $event.detail.value"></ion-input>
                    </ion-item>
                                        <div class="ion-padding button">
                        <ion-button expand="block" :disabled="isLoadding" class="ion-no-margin" @click="register('login')">注册</ion-button>
                    </div>
                    <div class="app-register-text-contet" :style="!isloginPage?'display:none':''"><span class="app-register-text">还没有账户？<span class="register-button" @click="activeChange">立刻注册!</span></span></div>
                </form>
                <div class="thirdParty">
                    <img @click="thirdLogin('钉钉')" src="assets/aliiconfont/dingding.svg">
                    <img @click="thirdLogin('微信')" src="assets/aliiconfont/weixin.svg">
                </div>
            </div>
        </ion-content>
    </ion-page>
</template>
<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

import { Environment } from '@/environments/environment';

import { ThirdPartyService } from "ibiz-core";
@Component({
    components: {},
    i18n: {
        messages: {
            'ZH-CN': {
                username: '用户名',
                password: '密码',
                submit: '提交',
                usernametipinfo: '用户名为空',
                passwordtipinfo: '密码为空',
                dingdingfailed: '钉钉认证失败，请联系管理员',
                badlogin: '登录异常',
            },
            'EN-US': {
                username: 'User name',
                password: 'Password',
                submit: 'Submit',
                usernametipinfo: 'User name is empty.',
                passwordtipinfo: 'Password id empty.',
                dingdingfailed: 'Dingding authentication failed, please contact the administrator',
                badlogin: 'Login exception',
            }
        }
    }
})
export default class Login extends Vue {

    /**
     * 第三方服务
     *
     * @type {string}
     * @memberof Login
     */
    public thirdPartyService:ThirdPartyService = ThirdPartyService.getInstance();

    /**
     * 是否为登录
     *
     * @type {boolean}
     * @memberof Login
     */
    public isloginPage:boolean =true;

    /**
     * 用户名
     *
     * @type {string}
     * @memberof Login
     */
    public username: string = "";

   /**
     * 密码
     *
     * @type {string}
     * @memberof Login
     */
    public password: string = "";

    /**
     * 是否是访客模式
     *
     * @type {string}
     * @memberof Login
     */
    public isVisitorsMode:boolean = Environment.VisitorsMode;

    /**
     * 是否加载中
     *
     * @type {string}
     * @memberof Login
     */
    public isLoadding:boolean = false;

    /**
     * 第三方登录
     *
     * @type {string}
     * @memberof Login
     */
    public async thirdLogin(name:string){
        const info: string = window.navigator.userAgent.toUpperCase();
        if (info.indexOf('DINGTALK') == -1 && info.indexOf('WXWORK') == -1) {
            this.$Notice.error(`不在${name}容器`);
        }
        let loginStatus :any = await this.thirdPartyService.login();
        if(!loginStatus.issuccess){
            this.$Notice.error(loginStatus.message?loginStatus.message:`${this.$t('dingdingfailed')}` );
            setTimeout(()=>{
                this.thirdPartyService.close();
            },1500);
        }else if(loginStatus.issuccess){
            const url: any = this.$route.query.redirect? this.$route.query.redirect: "*";
            this.$router.replace({ path: url });
        }
    }

    /**
     * 密码
     *
     * @memberof Login
     */
    public activeChange(){
       this.isloginPage = !this.isloginPage;
    }

     /**
     * 登录
     *
     * @memberof Login
     */
    public login(tag:any) {
        let url = "";
        let token = localStorage.getItem('token');
        let user = localStorage.getItem('user');
        if(token){
            localStorage.removeItem("token");
        }
        if(user){
            localStorage.removeItem("user");
        }
        if(tag === 'login'){
            if (Object.is(this.username, '')) {
                this.$Notice.error(`${this.$t('usernametipinfo')}`);
                return;
            }
            if (Object.is(this.password, '')) {
                this.$Notice.error(`${this.$t('passwordtipinfo')}`);
                return;
            }
            url = Environment.RemoteLogin;
        }else{
            url = Environment.VisitorsUrl;
        }
        const post: Promise<any> = this.$http.post(url, { loginname: this.username, password: this.password });
        this.isLoadding = true;
        post.then((response: any) => {
            if (response && response.status === 200) {
                this.isLoadding = false;
                const data = response.data;
                localStorage.setItem("token", data.token);
                localStorage.setItem("user", JSON.stringify(data.user));
                const url: any = this.$route.query.redirect
                    ? this.$route.query.redirect
                    : "*";
                this.$router.replace({ path: url });
            }
        }).catch((error: any) => {
            this.isLoadding = false;
            this.$Notice.error(error?error.data.message:`${this.$t('badlogin')}`);
        });
    }

     /**
     * 注册
     *
     * @memberof register
     */
    public register(){
        this.$Notice.error(`注册暂未支持,请联系管理员`);
    }
     /**
     * 找回密码
     *
     * @memberof register
     */
    public forgetpassword() {
        this.$Notice.error(`暂未支持找回密码,请联系管理员`);
    }

}
</script>

<style lang='less'>
@import "./login.less";
</style>