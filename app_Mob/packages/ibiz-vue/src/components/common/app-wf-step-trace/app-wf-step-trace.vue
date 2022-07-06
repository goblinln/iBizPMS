<template>
  <div class="app-wf-step-trace">
    <template v-for="(usertask, usertaskIndex) in data.usertasks">
      <div
        class="wf-action-item"
        v-if="usertask.comments.length > 0"
        :key="usertaskIndex"
      >
        <div class="action-line">
            <img src="/assets/images/right.svg" alt="">
        </div>
        <div class="action-content">
          <div class="time">
             {{
              usertask.comments[usertask.comments.length - 1] &&
              formatDate(
                usertask.comments[usertask.comments.length - 1].time,
                "MM月DD日 HH:mm:ss"
              )
            }}
          </div>
          <div class="content-info">
            <span class="name" v-if="usertask.comments && usertask.comments.length>0">{{usertask.comments[0].authorName}} </span>
            <span class="action">执行了 </span>
            <span class="action-name">{{ usertask.userTaskName }} </span>
            <span>操作</span>
          </div>
        </div>
      </div>
      <!-- {{data.usertasks.length}} -->
      <div
        :class="{'wf-action-item':true,'now-action':true}"
        v-if="usertask.identitylinks.length > 0"
        :key="usertaskIndex"
      >
        <div class="action-line-now">
           
        </div>
        <div class="action-top">
          <span class="name">
            当前
          </span>
          <div class="border">
          </div>
          <div class="content-info">
            <span class="action-name">{{ usertask.userTaskName }}</span>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>

<script lang="ts">
import { GlobalService } from "ibiz-service";
import moment from "moment";
import { Vue, Component, Prop } from "vue-property-decorator";

@Component({})
export default class AppWfStepTrace extends Vue {
  /**
   * 数据
   *
   *  @memberof ExtendActionTimeline
   */
  public data: any = {};

  /**
   *  初始化memo
   *
   *  @memberof ExtendActionTimeline
   */
  public initmemo: string = "";

  /**
   *  子项索引初始值
   *
   *  @memberof ExtendActionTimeline
   */
  public usertasksIndex: number = 1;

  /**
   *  子项长度初始值
   *
   *  @memberof ExtendActionTimeline
   */
  public usertasksLength: number = 1;

  /**
   * 应用实体名称
   *
   * @memberof ExtendActionTimeline
   */
  @Prop() public appEntityCodeName!: string;

  /**
   *  上下文
   *
   *  @memberof ExtendActionTimeline
   */
  @Prop() public context: any;

  /**
   *  视图参数
   *
   *  @memberof ExtendActionTimeline
   */
  @Prop() public viewparams: any;

  /**
   * 初始化数据
   *
   *  @memberof ExtendActionTimeline
   */
  public created() {
    if (this.appEntityCodeName) {
      new GlobalService()
        .getService(this.appEntityCodeName, this.context)
        .then((service: any) => {
          if (service) {
            service.GetWFHistory(this.context,this.viewparams).then((res: any) => {
              if (res && res.status === 200) {
                this.data = res.data;
                this.initUIStateData();
              }
            });
          }
        });
    }
  }

  /**
   * 初始化数据添加标记
   *
   *  @memberof ExtendActionTimeline
   */
  public initUIStateData() {
    if (this.data && this.data.usertasks) {
      this.usertasksIndex = 1;
      for (let i in this.data.usertasks) {
        this.data.usertasks[i].isShow = false;
        this.data.usertasks[i].index = this.usertasksIndex;
        this.usertasksIndex++;
      }
      this.usertasksLength = this.usertasksIndex - 1;
      this.$forceUpdate();
    }
    console.log(this.data);
  }

  /**
   * 时间转换
   *
   *  @memberof ExtendActionTimeline
   */
  public formatDate(date: string, format: string) {
    return moment(date).format(format);
  }

  /**
   * 点击事件
   *
   *  @memberof ExtendActionTimeline
   */
  public changeExpand(usertask: any) {
    usertask.isShow = !usertask.isShow;
    this.$forceUpdate();
  }

  /**
   * 办理人员名称显示去重
   *
   * @param tag 需要去重的名称标识
   * @param datas 需要去重数据集
   * @memberof ExtendActionTimeline
   */
  public acceptingOfficerNodup(tag: string, datas: any[]): any[] {
    let tempData: any[] = [];
    if (datas?.length > 0 && tag) {
      datas.forEach((data: any) => {
        tempData.push(data[tag]);
      });
    }
    const nodup = [...new Set(tempData)];
    return nodup;
  }
}
</script>

<style lang='less'>
@import "./app-wf-step-trace.less";
</style>