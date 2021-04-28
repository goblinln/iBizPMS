<template>
  <div class="app-wf-approval">
    <van-steps active-icon="success" active-color="#38f" direction="vertical" :active="0">
      <van-step>
        <div class="step-item">
          <div class="step-start-user">
            <div class="step-text">{{$t('start')}}</div>
            <div class="step-start-user-name">{{data.startUserName}}</div>
          </div>
          <div class="step-start-time">{{data.startTime}}</div>
        </div>
      </van-step>
      <template v-for="(usertask,index) in data.usertasks" >
      <van-step v-if="usertask.identitylinks.length >0"  :key="index+'identitylinks'">
        <div class="step-start-user" >
            <div class="step-text">{{$t('wait')}}</div>
            <div  class="step-start-user-name">
              <span v-for="(identitylink,inx) in usertask.identitylinks" :key="inx">{{identitylink.displayname}}<span v-if="inx >0">、</span></span>
            </div>
          </div>
      </van-step>
      <van-step v-if="usertask.comments.length >0" :key="index">
          <div class="userTaskName">{{usertask.userTaskName}}</div>
            <div class="approval-content-item-info" >
                <div class="step-name" v-for="(comment,commentInx) in usertask.comments" :key="commentInx">
                  <div
                    class="approval-content-item-info-item approval-content-item-info-top"
                  >{{`【${comment.type}】${comment.fullMessage}`}}
                  <div class="info-bottom-name">{{comment.authorName}}</div>
                  </div>
                  <div class="approval-content-item-info-item approval-content-item-info-bottom">
                    <span class="step-start-time">{{comment.time}}</span>
                  </div>
                </div>
            </div>
        </van-step>
       </template>
    </van-steps>
  </div>
</template>
<script lang = 'ts'>
import { Vue, Component, Prop, Model } from "vue-property-decorator";

@Component({
    components: {},
    i18n: {
        messages: {
            'ZH-CN': {
                start: '发起申请',
                wait: '等待处理',
            },
            'EN-US': {
                start: 'Initiate an application',
                wait: 'Waiting to be processed',
            }
        }
    }
})
export default class AppWFApproval extends Vue {

      /**
       * 激活步骤
       *
       *  @memberof AppWFApproval
       */
      public activeNames = [];

      /**
       * 双向绑定值
       *
       *  @memberof AppWFApproval
       */
      @Model("change") value!: string;

      /**
       * 数据
       *
       *  @memberof AppWFApproval
       */
      public data: any = {};

      /**
       *  初始化memo
       *
       *  @memberof AppWFApproval
       */
      public initmemo: string = "";

      /**
       * 传入数据服务
       *
       *  @memberof AppWFApproval
       */
      @Prop() public service: any;

      /**
       *  上下文
       *
       *  @memberof AppWFApproval
       */
      @Prop() public context: any;

      /**
       *  视图参数
       *
       *  @memberof AppWFApproval
       */
      @Prop() public viewparams: any;

      /**
       * 初始化数据
       *
       *  @memberof AppWFApproval
       */
      public created() {
        if (this.service) {
          this.service.GetWFHistory(this.context).then((res: any) => {
            if (res && res.status === 200) {
              this.data = res.data;
            }
          });
        }
      }

      /**
       * 抛出wfprocmemo
       *
       *  @memberof AppWFApproval
       */
      public handleBlur($event: any) {
        this.$emit("change", $event.target.value);
      }
}
</script>

<style lang="less">
@import "./app-wf-approval.less";
</style>