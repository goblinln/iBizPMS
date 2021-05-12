<template>
  <ion-page :className="{ 'view-container': true,'app-setting': true}">
    <div class="content">
      <ion-list class="content-list content-list-top">
        <template v-for="item in data.top">
            <template v-if="!item.component">
                <ion-item :key="item.name" v-if="item.isEnable" @click="onItemClick(item)">
                    <div class="content-list-item-content">
                    <div class="content-list-item-content-text">{{item.showtext}}</div>
                    <app-mob-icon
                        v-if="item.name !== 'theme' &&  item.name !== 'accountInformation'"
                        name="chevron-forward-outline"
                    ></app-mob-icon>
                    <app-mob-select-theme v-if="item.name == 'theme'" ref="changeTheme"></app-mob-select-theme>
                    <div
                        v-if="item.name == 'accountInformation'"
                        class="content-list-item-content-text"
                    >{{srfloginname}}</div>
                    </div>
                </ion-item>
            </template>
            <template v-else>
                <component :key="item.name" :is="item.component"></component>
            </template>
        </template>
      </ion-list>
      <ion-list class="content-list content-list-center">
        <template v-for="item in data.center">
            <template v-if="!item.component">
                <ion-item :key="item.name" v-if="item.isEnable" @click="onItemClick(item)">
                    <div class="content-list-item-content">
                    <div class="content-list-item-content-text">{{item.showtext}}</div>
                    <app-mob-icon
                        v-if="item.name !== 'theme' &&  item.name !== 'accountInformation'"
                        name="chevron-forward-outline"
                    ></app-mob-icon>
                    <app-mob-select-theme v-if="item.name == 'theme'" ref="changeTheme"></app-mob-select-theme>
                    <div
                        v-if="item.name == 'accountInformation'"
                        class="content-list-item-content-text"
                    >{{srfloginname}}</div>
                    </div>
                </ion-item>
            </template>
            <template v-else>
                <component :key="item.name" :is="item.component"></component>
            </template>
        </template>
      </ion-list>
      <ion-list class="content-list content-list-bottom">
        <template v-for="item in data.bottom">
            <template v-if="!item.component">
                <ion-item :key="item.name" v-if="item.isEnable" @click="onItemClick(item)">
                    <div class="content-list-item-content">
                    <div class="content-list-item-content-text">{{item.showtext}}</div>
                    <app-mob-icon
                        v-if="item.name !== 'theme' &&  item.name !== 'accountInformation'"
                        name="chevron-forward-outline"
                    ></app-mob-icon>
                    <app-mob-select-theme v-if="item.name == 'theme'" ref="changeTheme"></app-mob-select-theme>
                    <div
                        v-if="item.name == 'accountInformation'"
                        class="content-list-item-content-text"
                    >{{srfloginname}}</div>
                    </div>
                </ion-item>
            </template>
            <template v-else>
                <component :key="item.name" :is="item.component"></component>
            </template>
        </template>
      </ion-list>
    </div>
  </ion-page>
</template>
<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { settingConfig } from "./config";
import { Util, ViewTool, ThirdPartyService, removeSessionStorage, AppCapacitorService } from 'ibiz-core';
import i18n from '@/locale'
import { Environment } from "@/environments/environment";
@Component({
    components: {},
})
export default class AppSetting extends Vue {

    /**
     * 用户名称
     *
     * @type {string}
     * @memberof AppSetting
     */
    public srfloginname = "";

    /**
     * 第三方服务
     *
     * @type {string}
     * @memberof AppSetting
     */
    public thirdPartyName = ThirdPartyService.getInstance().platform;

    /**
     * 视图数据
     * 
     * @memberof AppSetting
     */
    public data: any = {
        top: [],
        center: [],
        bottom: [],
    };

    /**
     * 初始化数据
     * 
     * @memberof AppSetting
     */
    public initializeData() {
        let data = [];
        data.push(...settingConfig.default, ...settingConfig.userCustomize);
        // 分类合并
        data.forEach((item: any) => {
            if (item.position == "top") {
                this.data.top.push(item);
            }
            if (item.position == "center") {
                this.data.center.push(item);
            }
            if (item.position == "bottom") {
                this.data.bottom.push(item);
            }
            // 多语言处理
            if (item.entext) {
                if (i18n.locale == "ZH-CN") {
                    item.showtext = item.text;
                } else if (i18n.locale == "EN-US") {
                    item.showtext = item.entext;
                }
            } else {
                item.showtext = item.text;
            }
        });
        // 排序规则
        let compare = (property: any) => {
            return (a: any, b: any) => {
                var value1 = a[property];
                var value2 = b[property];
                return value1 - value2;
            };
        };
        // 排序
        Object.keys(this.data).forEach((item: any) => {
            this.data[item].sort(compare("sort"));
        });
    }

    /**
     * 标题状态
     *
     * @memberof AppSetting
     */
    public titleStatus: boolean = true;

    /**
     * 设置标题状态
     *
     * @memberof AppSetting
     */
    public setViewTitleStatus() {
        const thirdPartyName = this.$store.getters.getThirdPartyName();
        if (thirdPartyName) {
            this.titleStatus = false;
        }
    }

    /**
     * 退出登录
     *
     * @memberof AppSetting
     */
    public async logout() {
        const title: any = this.$t("app.tabpage.sureclosetip.title");
        const result = await this.$Notice.confirm(title, "确认退出当前账号？");
        if (result) {
            AppCapacitorService.getInstance().exitApp();
            if (this.thirdPartyName) {
                ThirdPartyService.getInstance().close();
                return;
            }
            const get: Promise<any> = this.$http.get("v7/logout");
            get.then((response: any) => {
                if (response && response.status === 200) {
                    this.$store.commit('addAppData', null);
                    this.doLogin();
                }
            })
                .catch((error: any) => {
                    console.error(error);
                });
        }
    }

    /**
     * 去登录
     *
     * @memberof AppSetting
     */
    public doLogin(data?: any): void {
        // 清除user、token
        this.clearAppData()
        if (Environment.loginUrl) {
            window.location.href = `${Environment.loginUrl}?redirect=${window.location.href}`;
        } else {
            this.$router.push({ name: 'login' });
        }
    }

     /**
     * 清除数据
     *
     * @memberof register
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
     * 清除缓存
     *
     * @memberof AppSetting
     */
    public async clear() {
        const result = await this.$Notice.confirm("清除缓存", "清除缓存可能会导致当前登录信息失效！！！");
        if (result) {
            localStorage.clear();
        }
    }

    /**
     * 主题切换
     *
     * @memberof AppSetting
     */
    public changeTheme() {
        let changeTheme: any = this.$refs.changeTheme;
        if (changeTheme && changeTheme[0]) {
            changeTheme[0].open();
        }
    }

    /**
     * 个人中心
     *
     * @memberof AppSetting
     */
    public userCenter() {
        const { context } = this.$store.getters.getAppData();
        let user: any = {};
        if (context && context.srfuserid) {
            user[settingConfig.userEntityName] = context.srfuserid;
        }
        if (settingConfig.userCenterViewName) {
            this.$appmodal.openModal(
                { viewname: settingConfig.userCenterViewName, title: "" },
                user
            );
        } else {
            this.$Notice.warning("用户自定义视图未配置");
        }
    }

    /**
     * 模态打开
     *
     * @memberof AppSetting
     */
    private async openPopupModal(view: any, context: any, param: any): Promise<any> {
        const result: any = await this.$appmodal.openModal(view, context, param);
        if (result || Object.is(result.ret, "OK")) {
            this.$emit("change", result.datas[0].backEnd);
        }
    }

    /**
     * 语言列表
     *
     * @memberof AppSetting
     */
    public lanArray: any;

    /**
     * 切换语言
     *
     * @memberof AppSetting
     */
    public changeLanguage() {
        let lanarr: any = localStorage.getItem('lanArray');
        this.lanArray = JSON.parse(lanarr);
        this.lanArray = this.lanArray.map((language: any) => language.indexOf("ZH-CN") > -1 ? language.replace("ZH-CN", "EN-US") : language.replace("EN-US", "ZH-CN"));
        i18n.locale = this.lanArray[0];
        localStorage.setItem('local', this.lanArray[0]);
        localStorage.setItem('lanArray', JSON.stringify(this.lanArray));
        // 提示框
        if (this.lanArray[0] === "ZH-CN") {
            this.$Notice.success('已切换为中文！');
        } else if (this.lanArray[0] === "EN-US") {
            this.$Notice.success('已切换为英文！');
        }
        Object.keys(this.data).forEach((items: any) => {
            // 多语言处理
            this.data[items].forEach((item: any) => {
                if (item.entext) {
                    if (i18n.locale == "ZH-CN") {
                        item.showtext = item.text;
                    } else if (i18n.locale == "EN-US") {
                        item.showtext = item.entext;
                    }
                }
            })
        });
        this.$forceUpdate();
    }

    /**
     * item点击事件
     *
     * @memberof AppSetting
     */
    public onItemClick(item: any) {
        // 内置功能
        if (item.name == "accountInformation") {
            this.userCenter();
        } else if (item.name == "theme") {
            this.changeTheme();
        } else if (item.name == "layoutStyle") {
            this.openPopupModal({ viewname: item.viewName }, {}, {});
        } else if (item.name == "logout") {
            this.logout();
        } else if (item.name == "clear") {
            this.clear();
        } else if (item.name == "language") {
            this.changeLanguage();
        } else {
            // 自定义功能
            if (item.path) {
                this.$router.push(`/viewshell/${item.path}`);
            } else if (item.viewName) {
                this.openPopupModal({ viewname: item.viewName }, {}, {});
            }
        }
    }

    /**
     * 生命周期
     *
     * @memberof AppSetting
     */
    public created() {
        let appdata = this.$store.state.appdata;
        this.srfloginname = appdata && appdata.context && appdata.context.srfloginname ? appdata.context.srfloginname : "";
        // this.$viewTool.setViewTitleOfThirdParty("设置");
        this.setViewTitleStatus();
        this.initializeData();
    }
}
</script>
<style lang="less">
@import "./app-setting.less";
</style>