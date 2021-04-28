import Vue from 'vue';
import Vuex from 'vuex';
import VueRouter from 'vue-router';
import App from '@/App.vue';
import i18n from '@/locale'
import Vant from 'vant';
import 'vant/lib/index.css';
import '@/styles/default.less';
import { AppComponentService, AppLayoutService, ViewOpenService } from 'ibiz-vue';
import { install } from 'ibiz-service';
import { Environment } from '@/environments/environment';
import { ionicInitialize } from './ionic-initialize';
import { ibizMobileComponentsInitialize } from './ibiz-mobile-components-initialize';
import { Lazyload } from 'vant';
import { installPlugin } from '@/plugin/app-plugin-service';
import VueAMap from "vue-amap";
import { AppComponents } from '@/app-register';
import { UserComponent } from '@/user-register';
import store from '@/store';
import router from '@/router/router';
import { Interceptors } from '@/utils';
// 手势滑动
import VueTouch from 'vue-touch';
// 富文本
import 'quill/dist/quill.snow.css';
import VueQuillEditor from 'vue-quill-editor';
// 模拟数据
if (process.env.NODE_ENV === 'development') {
    require('@/mock');
}
ibizMobileComponentsInitialize();
const pathToRegExp = require('path-to-regexp');
Vue.use(VueAMap);
Vue.use(Lazyload,{
    preLoad: 1,
    attempt: 1,
    error: 'https://gitee.com/kk_ah/images/raw/master/images/20201218105218.png',
});
VueAMap.initAMapApiLoader({
    key: "6e350f60986cba316719fdc7bd55d8d3",
    plugin: [
      "AMap.Geocoder",
      "AMap.Geolocation" 
    ],
})
installPlugin();
install({baseUrl:Environment.BaseUrl});
AppComponentService.registerAppComponents();
AppLayoutService.registerLayoutComponent();
Vue.config.errorHandler = function (err: any, vm: any, info: any) {
    console.log(err);
}
ionicInitialize({ mode: 'ios' });
Vue.config.productionTip = false;
Vue.config.ignoredElements =[/^ion-/, /^ibiz-/];

Vue.use(Vuex);
Vue.use(VueRouter);;
Vue.use(Vant, i18n);
Vue.prototype.$pathToRegExp = pathToRegExp;
Vue.use(AppComponents);
Vue.use(UserComponent);
Vue.use(VueQuillEditor);
Vue.use(VueTouch, {name: 'v-touch'});
VueTouch.config.press = {
    time: 700
  }
router.beforeEach((to: any, from: any, next: any) => {
    if (to.meta && !to.meta.ignoreAddPage) {
        router.app.$store.commit('addPage', to);
    }
    next();
});
Interceptors.getInstance(router, store);
ViewOpenService.getInstance(router);
new Vue({
    i18n,
    store,
    router,
    render: (h: any) => h(App),
}).$mount('#app');