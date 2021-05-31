<template>  
<ion-page :className="{ 'view-container': true,'app-mob-select-changeStyle': true}">
      <ion-header v-if="titleStatus">
        <ion-toolbar class="ionoc-view-header">
          <ion-buttons slot="start">
          <ion-button @click="closeView">
            <ion-icon name="chevron-back"></ion-icon>
            {{$t('app.button.back')}}
          </ion-button>
        </ion-buttons>
          <ion-title>
            <label class="title-label">{{$t('app.title.styleSetting')}}</label>
          </ion-title>
        </ion-toolbar>
      </ion-header>

    <div class="content">
      <!-- <ion-list>
        <ion-radio-group >
          <ion-list-header>
            <ion-label>文本对齐方式</ion-label>
          </ion-list-header>
          <div class="align-boxs">
            <div :class="{alignbox:true,active:activeAlign == item.value}" v-for="item in alignItems" :key="item.index" @click="changeTextAlign(item)">
              <ion-label>{{item.text}}</ion-label>
              <div class="info">{{item.info}}</div>
              <ion-icon name="checkmark-circle-outline"></ion-icon>
            </div>
          </div>
        </ion-radio-group>
      </ion-list> -->

      <ion-list>
        <ion-radio-group :value="activeNavi">
          <ion-list-header>
            <ion-label>导航栏风格切换</ion-label>
          </ion-list-header>
          <ion-item  v-for="item in naviItems" :key="item.index" @click="changeNaviStyle">
            <ion-label>{{item.text}}</ion-label>
            <ion-radio slot="end" :value="item.value"></ion-radio>
          </ion-item>
        </ion-radio-group>
      </ion-list>

    </div>
</ion-page>
   
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch, } from "vue-property-decorator";

@Component({
    components: {},
})
export default class AppSelect extends Vue {

    /**
     * 当前文本对齐方式选中值
     * @memberof AppMobSelectChangeStyle
     */
    // public activeAlign:any = '';

    /**
     * 当前导航栏选中值
     * @memberof AppMobSelectChangeStyle
     */
    public activeNavi:any = '';

    /**
     * 文本对齐方式
     * @memberof AppMobSelectChangeStyle
     */
    // public alignItems:Array<any> = [
    //     {text:'左对齐',value:'app-text-align-left',info:'设置对齐方式为左对齐'},
    //     {text:'右对齐',value:'app-text-align-right',info:'设置对齐方式为右对齐'},
    //   ]

    /**
     * 导航栏样式
     * @memberof AppMobSelectChangeStyle
     */
    public naviItems:Array<any> = [
        {text:'经典',value:'app-navi-class'},
        {text:'新颖',value:'app-navi-new'}
      ]

    /**
     * 生命周期
     */
    public created() {
      this.thirdPartyInit();
      this.setViewTitleStatus();
    }

    /**
     * 标题状态
     *
     * @memberof ProductCloseMobEditViewBase
     */
    public titleStatus :boolean = true;

    /**
     * 设置标题状态
     *
     * @memberof ProductCloseMobEditViewBase
     */
    public setViewTitleStatus(){
        const thirdPartyName = this.$store.getters.getThirdPartyName();
        if(thirdPartyName){
            this.titleStatus = false;
        }
    }

    /**
     * 生命周期mounted
     * @memberof AppMobSelectChangeStyle
     */
    public mounted(){
        // this.activeAlign = this.getActiveAlign();
        this.activeNavi = this.getActiveNavi();
    }

    /**
     * 获取文本对齐方式
     * @memberof AppMobSelectChangeStyle
     */
    // public getActiveAlign(){
    //     if (this.$store.state.selectTextAlign) {
    //         return this.$store.state.selectTextAlign;
    //     } else if (localStorage.getItem('align-class')) {
    //         return localStorage.getItem('align-class');
    //     } else {
    //         return 'app-text-align-right';
    //     }
    // }

    /**
     * 获取导航栏样式
     * @memberof AppMobSelectChangeStyle
     */
    public getActiveNavi(){
        if (this.$store.state.selectNaviStyle) {
            return this.$store.state.selectNaviStyle;
        } else if (localStorage.getItem('navigation-class')) {
            return localStorage.getItem('navigation-class');
        } else {
            return 'app-navi-new';
        }
    }

    /**
     * 点击改变文本对齐方式
     * @memberof AppMobSelectChangeStyle
     */
    // public changeTextAlign(item:any){
    //   this.textAlignChange(item.value);
    // }

    /**
     * 文本对齐方式变化
     * @memberof AppMobSelectChangeStyle
     */
    // public textAlignChange(val:any){
    //     if (!Object.is(this.activeAlign, val)) {
    //         this.activeAlign = val;
    //         localStorage.setItem('align-class', val);
    //         this.$store.commit('setTextAlignStyle', val);
    //     }
    // }

    /**
     * 点击改变导航栏样式
     * @memberof AppMobSelectChangeStyle
     */
    public changeNaviStyle($event:any){
      this.naviStyleChange($event.target.value)
    }

    /**
     * 导航栏样式变化
     * @memberof AppMobSelectChangeStyle
     */
    public naviStyleChange(val:any){
        if (!Object.is(this.activeNavi, val)) {
            this.activeNavi = val;
            localStorage.setItem('navigation-class', val);
            this.$store.commit('setNavigationStyle', val);
        }
    }

    /**
     * 退出模态框
     * @memberof AppMobSelectChangeStyle
     */
    public save(){
      this.$emit('close');
    }

     /**
     * 关闭视图
     * 
     * @memberof AppMobSelectChangeStyle
     */
    public closeView() {
        this.$emit("close");
    }

  /**
   *  第三方容器初始化
   *
   * @type {function}
   * @memberof AppMobSelectChangeStyle
   */
  protected  thirdPartyInit(){
        this.$viewTool.setViewTitleOfThirdParty("风格设置");
        this.$viewTool.setThirdPartyEvent(this.closeView);
        const thirdPartyName = this.$store.getters.getThirdPartyName();
        if(thirdPartyName){
            this.titleStatus = false;
        }
  }
}
</script>

<style lang="less">
@import './app-mob-select-changeStyle.less';
</style>