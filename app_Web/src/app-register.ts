import Vue from 'vue';
import { ViewTool, UIActionTool, Verify, Util, Http } from 'ibiz-core';
import { exportExcel } from './utils/export/export';
import { AppPopover, AppModal, AppDrawer } from 'ibiz-vue';
import { codeListRegister, messageServiceRegister } from 'ibiz-service';
import TabPageExp from '@components/tab-page-exp/tab-page-exp.vue';
import AppDataUpload from '@components/app-data-upload/app-data-upload.vue';
import Breadcrumb from '@components/app-breadcrumb/app-breadcrumb.vue';
import { ComponentsRegister, FooterItemsService, TopItemsService, UIStateService } from 'ibiz-vue';
import { PluginRegister } from './plugin/app-plugin-register';
import { install } from '@ibiz/model-location';
import { applyPolyfills, defineCustomElements } from '@ibiz/model-location/loader';
import { Environment } from './environments/environment';
import IBzPlugin from 'ibiz-plugin';

Vue.use(IBzPlugin);
Vue.use(ComponentsRegister);
Vue.use(PluginRegister);

// 全局挂载代码表服务注册中心
window['codeListRegister'] = codeListRegister;
// 全局挂载视图消息服务注册中心
window['messageServiceRegister'] = messageServiceRegister;

export const AppComponents = {
  install(v: any, opt: any) {
    applyPolyfills().then(() => defineCustomElements(window));
    install({
      debugAppUrl: './assets/www/index.html?origin=' + encodeURIComponent(location.origin),
      dynamicConfigToolUrl: Environment.dynamicConfigToolUrl + encodeURIComponent(location.origin),
    });
    v.prototype.$appdrawer = AppDrawer.getInstance();
    v.prototype.$appmodal = AppModal.getInstance();
    v.prototype.$apppopover = AppPopover.getInstance();
    v.prototype.$http = Http.getInstance();
    v.prototype.$export = exportExcel.getInstance();
    v.prototype.$util = Util;
    v.prototype.$verify = Verify;
    v.prototype.$viewTool = ViewTool;
    v.prototype.$uiActionTool = UIActionTool;
    v.prototype.$footerRenderService = new FooterItemsService();
    v.prototype.$topRenderService = new TopItemsService();
    v.prototype.$uiState = new UIStateService();
    v.component('tab-page-exp', TabPageExp);
    v.component('app-data-upload', AppDataUpload);
    v.component('app-breadcrumb', Breadcrumb);
  },
};
