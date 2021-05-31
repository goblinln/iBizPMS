<template>
  <div class="list-cardlist-card">
    <div class="list-line">
      <div class="timebox" v-if="item.time">{{item.time.substr(0,10)}}</div>
      <div class="infobox">
        <div class="infoline">
          <div class="infoleft">
            <van-icon class="list-card-icon" name="bookmark-o" />
            <label class="bookname" v-if="item.srfmajortext">{{item.srfmajortext}}</label>
          </div>
          <div class="inforight">
            <van-icon icon-rigth name="arrow" />
          </div>
        </div>
        <div class="list-hr"></div>
        <div class="infoline">
          <div class="booktypebox">
            <div >
              <p class="type-p">{{$t('subtitle')}}</p>
            </div>
            <p class="typeinfo" v-if="item.subtitle">{{item.subtitle}}</p>
          </div>
          <div>
            <p class="type-p">{{$t('number')}}</p>
            <p class="typeinfo" v-if="item.num">{{item.num}}</p>
          </div>
          <div>
            <p class="type-p">{{$t('content')}}</p>
            <p class="typeinfo" v-if="item.content">{{item.content}}</p>
          </div>
        </div>

      </div>
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
              subtitle: '副标题',
              number: '数量',
              content: '内容',
          },
          'EN-US': {
              subtitle: 'Subtitle',
              number: 'Number',
              content: 'Content',
          }
      }
  }
})
export default class AppCardList extends Vue{
  
  /**
   * 传入item
   *
   * @type {any}
   * @memberof AppCardList
   */
  @Prop() public item?: any;

};
</script>
<style lang = "less">
@import "./app-card-list.less";
</style>