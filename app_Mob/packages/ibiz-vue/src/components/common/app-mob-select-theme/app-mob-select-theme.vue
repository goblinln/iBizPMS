<template>
    <div  class="app-mobile-theme">
        <div class="activeoption" v-if="activeoption" >
          <div :style="{'background':activeoption.background,'width':'8px','height':'8px'}"></div>
          <span class="activeoption-text">{{activeoption.text}}</span>
          <app-mob-icon name="chevron-forward-outline" ></app-mob-icon>
        </div>
        <van-action-sheet  get-container="#app" :title="$t('themeSelection')" :closeable="false"   v-model="show" :cancel-text="$t('app.button.cancel')"  close-on-click-action>
            <div class = "app-mob-select-theme_theme">
                <div @click="themeChange(item.value)" v-for="item in options" :key="item.value" class="app-mob-select-theme_theme_item" :style="{background:item.background,color:item.color}">{{item.text}}</div>
            </div>
        </van-action-sheet>
    </div>   
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch, } from "vue-property-decorator";
import { appConfig } from '@/config/appConfig'
@Component({
    i18n: {
        messages: {
            'ZH-CN': {
                themeSelection: '主题选择',
            },
            'EN-US': {
                themeSelection: 'Theme selection',
            }
        }
    },
    components: {},
})
export default class AppSelectTheme extends Vue {

    /**
     * 显示状态
     *
     * @memberof AppSelectTheme
     */
    public show = false;

    /**
     * 当前激活主题
     *
     * @memberof AppSelectTheme
     */
    public activeoption: any = {};

    /**
     * 获取当前主题
     *
     * @memberof AppSelectTheme
     */
    public getTheme() {
        if (this.$router.app.$store.state.selectTheme) {
            return this.$router.app.$store.state.selectTheme;
        } else if (localStorage.getItem('theme-class')) {
            return localStorage.getItem('theme-class');
        } else {
            return appConfig.defaultTheme;
        }
    }

    /**
     * 下拉数据数组
     *
     * @type {any[]}
     * @memberof AppSelectTheme
     */
    public options: any[] = appConfig.themes;

    /**
     * 激活主题
     *
     * @memberof AppSelectTheme
     */
    public activeTheme = "";

    /**
     * 设置
     *
     * @memberof AppSelectTheme
     */
    public setActiveoption() {
        let index = this.options.findIndex((item: any) => {
            return this.activeTheme == item.value;
        })
        this.activeoption = index > -1 ? this.options[index] : null;
        this.show = false;
    }

    /**
     * 主题变化
     *
     * @param {*} val
     * @memberof AppTheme
     */
    public themeChange(val: any) {
        if (!Object.is(this.activeTheme, val)) {
            this.activeTheme = val;
            localStorage.setItem('theme-class', val);
            this.$router.app.$store.commit('setCurrentSelectTheme', val);
        }
        const dom = document.body.parentElement;
        if (dom) {
            dom.classList.forEach((val: string) => {
                if (val.indexOf('app-') === 0) {
                    dom.classList.remove(val);
                }
            });
            dom.classList.add(val);
        }
        this.setActiveoption();
    }

    /**
     * 打开
     *
     * @param {*} val
     * @memberof AppTheme
     */
    public open() {
        this.show = true;
    }

    /**
     * 声明周期钩子
     * 
     * @memberof AppSelectTheme
     */
    public mounted() {
        this.activeTheme = this.getTheme();
        this.setActiveoption();
    }
}
</script>

<style lang="less">
@import './app-mob-select-theme.less';
</style>