import Vue from 'vue';
import Vuex from 'vuex';
import VueRouter from 'vue-router';
import App from '@/App.vue';
import ElementUi from 'element-ui';
import ViewUI from 'view-design';
import ibizLab from 'ibiz-vue-lib';
import axios from "axios";
import { initNoticeHandler, Interceptors } from '@/utils';
import  {Print} from '@/utils/print';
import i18n from '@/locale';
import { install } from 'ibiz-service';
import { installPlugin } from '@/plugin/app-plugin-service';
import { Environment } from '@/environments/environment';

import 'element-ui/lib/theme-chalk/index.css';
import 'view-design/dist/styles/iview.css';
import 'ibiz-vue-lib/lib/ibiz-vue-lib.css';
import '@/styles/default.less';
import { AppComponentService,AppLayoutService, NoticeHandler } from 'ibiz-vue';

import VueAMap from 'vue-amap';
Vue.use(VueAMap);
AppComponentService.registerAppComponents();
AppLayoutService.registerLayoutComponent();

VueAMap.initAMapApiLoader({
  key: '6ab2751103aea67e817c90a5528181b5',
  plugin: ["AMap.Geolocation","AMap.PlaceSearch","AMap.Geocoder", "AMap.Autocomplete"],
  uiVersion: '1.1'
});

const pathToRegExp = require('path-to-regexp');
import { AppComponents } from '@/app-register';
import { UserComponent } from '@/user-register';
import store from '@/store';
import router from './router';

const win: any = window;
win.axios = axios;
install({baseUrl:Environment.BaseUrl});
installPlugin();
initNoticeHandler();

// 异常处理
Vue.config.errorHandler = function (err: any, vm: any, info: any) {
    NoticeHandler.errorHandler(err,info);
}
Vue.prototype.$throw = function (err:any, param?: any, fnName?: string){
    NoticeHandler.errorHandler(err, param, this, fnName);
}
Vue.prototype.$success = function (err:any, param?: any, fnName?: string){
    NoticeHandler.successHandler(err, param, this, fnName);
}
Vue.prototype.$warning = function (err:any, param?: any, fnName?: string){
    NoticeHandler.warningHandler(err, param, this, fnName);
}
Vue.prototype.$info = function (err:any, param?: any, fnName?: string){
    NoticeHandler.infoHandler(err, param, this, fnName);
}

Vue.config.productionTip = false;
Vue.use(Print);
Vue.use(ibizLab);
Vue.use(Vuex);
Vue.use(win.AVUE);
Vue.use(VueRouter);;
Vue.use(ElementUi, {
  i18n: (key: any, value: any) => i18n.t(key, value)
});
Vue.use(ViewUI, {
  i18n: (key: any, value: any) => i18n.t(key, value)
});

Vue.prototype.$pathToRegExp = pathToRegExp;
Vue.use(AppComponents);
Vue.use(UserComponent);

router.beforeEach((to: any, from: any, next: any) => {
  if(sessionStorage.getItem('lockState') && to.path != '/lock'){
    next({ path: '/lock'});
  }else{
    if (to.meta && !to.meta.ignoreAddPage) {
      router.app.$store.commit('addPage', to);
    }
    next();
  }
});

Interceptors.getInstance(router,store);

const init = async () => {
  new Vue({
      i18n,
      store,
      router,
      render: (h: any) => h(App),
  }).$mount('#app');
};
init();