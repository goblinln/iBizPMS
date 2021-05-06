<template>  
  <ion-list class="app-update-log-list">
    <div class="list-item-background" v-for="item in items" :key="item.sysupdatelogid" @click="item_click(item)">
      <div class="list-item" >
      <div class="list-text">
        <div class="text">{{item.srfmajortext}}</div>
        <div class="date">{{item.update}}</div>
      </div>
      <div class="list-icon">
        <ion-icon name="chevron-forward-outline"></ion-icon>
      </div>
    </div>
    </div>
  </ion-list>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch, } from "vue-property-decorator";

@Component({
    components: {},
})
export default class AppUpdateLogList extends Vue {

    /**
     * 视图参数
     *
     * @memberof AppPmsUploadList
     */
    @Prop() protected items!: any;

    /**
     * item_click
     */
    public item_click(value:any) {
      this.$emit("item_click",value);
    }

}
</script>

<style lang="less">
@import './app-update-log-list.less';
</style>