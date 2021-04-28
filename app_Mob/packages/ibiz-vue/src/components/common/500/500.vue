<template>
  <div class="app-500">
    <img src="assets/images/500.jpg" alt />
    <div class="btnbox">
      <ion-button class="backbtn" @click="back">{{$t('previous')}}</ion-button>
      <ion-button class="backbtn" @click="backindex">{{$t('homepage')}}</ion-button>
    </div>
  </div>
</template>
<script lang="ts">
import {
  Vue,
  Component,
  Prop,
  Provide,
  Emit,
  Watch
} from "vue-property-decorator";

@Component({
  components: {},
  i18n: {
      messages: {
          'ZH-CN': {
              previous: '上一步',
              homepage: '返回首页',
          },
          'EN-US': {
              previous: 'Previous',
              homepage: 'Homepage',
          }
      }
  }
})
export default class App404 extends Vue {
  /**
   * 返回上一步
   */
  public back() {
    if (window.history.length <= 1) {
      this.$router.push({ path: "/" });
      return false;
    } else {
      this.$router.go(-1);
    }
  }
  /**
   * 返回首页
   */
  public backindex() {
    this.$router.push({ path: "/" });
    let ionModal:any = document.querySelector(".show-modal");
    if(ionModal){ ionModal.dismiss(); }
  }
}
</script>
<style lang = "less">
@import "./500";
</style>