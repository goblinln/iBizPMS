import Vue from 'vue'
import VueI18n from 'vue-i18n'

const vueApp: any = Vue;

vueApp.use(VueI18n);

import zhCn from './lang/zh-CN';
import vantZhCnLocale from 'vant/lib/locale/lang/zh-CN';

const messages = {
    'ZH-CN': Object.assign(vantZhCnLocale),
};


// 自动根据浏览器系统语言设置语言
const navLang = localStorage.getItem('local') || navigator.language.toUpperCase();
const localLang = (navLang.indexOf('ZH') !== -1 || (navLang.indexOf('EN') !== -1 && messages.hasOwnProperty('EN-US'))) ? navLang : false;
let lang: string = localLang || 'ZH-CN';

vueApp.config.lang = lang

// // vue-i18n 6.x+写法
vueApp.locale = () => { };

const i18n = new VueI18n({
    locale: lang,
    messages,
    silentTranslationWarn: true,
});

export default i18n;
