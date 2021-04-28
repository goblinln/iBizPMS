<template>
  <div class="list-status">
    <div class="list03 booklist-item-box" >
      <div class="list03-box">
        <div class="list03-box-line">
          <label v-if="item.srfmajortext">
            <i class="el-icon-s-promotion lsit-status-icon"></i>
            {{item.srfmajortext}}
          </label>
          <div class="status_s">
            {{$t('status')}}
          </div>
        </div>
        <div class="list-hr"></div>
        <div class="list03-box-line">
          <div>
            <p class="typeinfo" v-if="item.subtitle">{{item.subtitle}}</p>
            <p class="type-p">{{$t('field_one')}}</p>
          </div>
          <div>
            <p class="typeinfo" v-if="item.content">{{item.content}}</p>
            <p class="type-p">{{$t('field_two')}}</p>
          </div>
          <div>
            <p class="typeinfo" v-if="item.time" >{{item.time.substr(0,10)}}</p>
            <p class="type-p">{{$t('field_three')}}</p>
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
                status: '状态',
                field_one: '字段一',
                field_two: '字段二',
                field_three: '字段三',
            },
            'EN-US': {
                status: 'Status',
                field_one: 'Field one',
                field_two: 'Field two',
                field_three: 'Field three',
            }
        }
    }
})
export default class AppStatusList extends Vue{

  /**
   * 传入item
   * @type {any}
   * @memberof AppStatusList
   */
  @Prop() public item?:any;

};
</script>
<style lang="less">
@import "./app-status-list.less";
</style>