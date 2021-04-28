<template>
    <ion-page :className="{ 'app-login': true }">
        <ion-content fullscreen v-if="!platform">
            <div class="app-login-contant">
                <img src="assets/images/logo.png" class="ibizLogo"/>
                <form class="app-login-form">
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
                    <div class="visitor" v-if="isVisitorsMode">
                        <ion-button expand="block" color="medium" size="small" fill="clear" class="ion-visitor" @click="login('visitors')">以访客身份登录</ion-button>
                    </div>
                </form>
                <!-- <div class="thirdParty">
                    <img src="assets/aliiconfont/dingding.svg">
                    <img src="assets/aliiconfont/QQ.svg">
                    <img src="assets/aliiconfont/weixin.svg">
                </div> -->
            </div>
        </ion-content>
        <ion-content v-else>
            <div class="app-loading-contant">
                <section class="loadingCircle"></section>
            </div>
        </ion-content>
    </ion-page>
</template>
<script lang="ts">
import { Vue, Component } from "vue-property-decorator";

import { Environment } from '@/environments/environment';

import { ThirdPartyService , DingTalkService } from "ibiz-core";
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


    public platform:any = "";

    /**
     * 用户名
     *
     * @type {string}
     * @memberof Login
     */
    public username: string = "";

    /**
     * 是否是访客模式
     *
     * @type {string}
     * @memberof Login
     */
    public isVisitorsMode:boolean = Environment.VisitorsMode;

    /**
     * 是否开启第三方免登
     *
     * @type {string}
     * @memberof Login
     */
    public isEnableThirdPartyLogin:boolean = Environment.enableThirdPartyLogin;

    /**
     * 生命周期
     * 
     * @memberof Login
     */
    public created(){
        this.platform = this.isEnableThirdPartyLogin?this.thirdPartyService.platform:"";
        if(this.platform){
            this.thirdLogin();
        }
    }

    /**
     * 第三方登录
     *
     * @type {string}
     * @memberof Login
     */
    public async thirdLogin(){
        let loginStatus :any = await this.thirdPartyService.login();
        if(!loginStatus.issuccess){
            this.$Notice.error(loginStatus.message?loginStatus.message:`${this.$t('dingdingfailed')}` );
            setTimeout(()=>{
                this.thirdPartyService.close();
            },1500);
        }else if(loginStatus.issuccess){
            const url: any = this.$route.query.redirect? this.$route.query.redirect: "*";
            this.$router.replace({ path: url });
            this.$router.go(-1);
        }
    }

    /**
     * 密码
     *
     * @type {string}
     * @memberof Login
     */
    public password: string = "";

       /**
     * 是否加载中
     *
     * @type {string}
     * @memberof Login
     */
    public isLoadding:boolean = false;

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
                this.$router.go(-1)
            }
        }).catch((error: any) => {
            this.isLoadding = false;
            this.$Notice.error(error?error.data.message:`${this.$t('badlogin')}`);
        });
    }

}
</script>

<style lang='less'>
@import "./login.less";
</style>