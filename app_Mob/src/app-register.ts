import Vue from 'vue';
import { Http, Util, Verify, ViewTool } from 'ibiz-core';
import { AppModal, AppDrawer } from 'ibiz-vue';
import { UtilServiceRegister, codeListRegister } from 'ibiz-service';
import { ComponentsRegister } from 'ibiz-vue';
Vue.use(ComponentsRegister);
import { PluginRegister } from './plugin/app-plugin-register';

Vue.use(PluginRegister);
// 全局挂载功能服务注册中心
window['utilServiceRegister'] = UtilServiceRegister.getInstance();
// 全局挂载代码表服务注册中心
window['codeListRegister'] = codeListRegister;

/**
 * Vue插件
 */
export const AppComponents = {
    install(v: any, opt: any) {
        v.prototype.$http = Http.getInstance();
        v.prototype.$util = Util;
        v.prototype.$verify = Verify;
        v.prototype.$appdrawer = AppDrawer.getInstance();
        v.prototype.$appmodal = AppModal.getInstance();
        v.prototype.$viewTool = ViewTool;
    },
};