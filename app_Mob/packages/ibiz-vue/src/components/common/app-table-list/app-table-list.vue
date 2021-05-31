<template>
        <div class="booklist-item-ulbox">
          <ul class="booklist-ul">
            <li class="booklist-li" v-if="item.srfmajortext"><div class="text-box">{{item.srfmajortext}}</div></li>
            <li class="booklist-li" ><div class="text-box">{{"45.5"}}</div></li>
            <li class="booklist-li" v-if="item.subtitle"><div class="text-box">{{item.subtitle}}</div></li>
            <li class="booklist-li" v-if="item.content"><div class="text-box">{{item.content}}</div></li>
          </ul>
    </div>
</template>
<script lang="ts">
import {Vue, Component, Prop, Provide, Emit, Watch} from "vue-property-decorator";

@Component({
  components: {}
})
export default class AppTableList extends Vue{

  /**
   * 传入item
   * @type {any}
   * @memberof AppStatusList
   */
  @Prop() public item?:any;

}
</script>
<style lang = "less">
@import "./app-table-list.less";
</style>