<template>
  <div class="action-timeline">
    <div class="extend-action-timeline-table">
      <div class="action-timeline-thead"></div>
      <div class="action-timeline-body" v-if="data && data.usertasks">
        <div class="timeline-draw timeline timeline-head">
          <div class="timeline-wrapper">
            <div class="timeline-index">{{$t('app.components.timeline.index')}}</div>
            <div class="usertaskname">{{$t('app.components.timeline.node')}}</div>
            <div class="authorname">{{$t('app.components.timeline.author')}}</div>
            <div class="type">{{$t('app.components.timeline.type')}}</div>
            <div class="last-time">{{$t('app.components.timeline.lastTime')}}</div>
            <div class="fullmessage">{{$t('app.components.timeline.opinion')}}</div>
          </div>
          <div class="arrow"></div>
        </div>
        <template v-for="(usertask, usertaskIndex) in data.usertasks">
          <div v-if="usertask.comments.length > 0" class="timeline-content" :key="usertaskIndex">
            <div class="timeline">
              <div class="timeline-wrapper">
                <div class="timeline-index">
                  <span>{{ usertask.index }}</span>
                  <div class="icon-bottom" v-if="usertask.index != 1">
                    <i class="el-icon-bottom"></i>
                  </div>
                  <div
                    class="icon-top"
                    v-if="usertask.index < usertasksLength"
                  ></div>
                </div>
                <div class="usertaskname">{{ usertask.userTaskName }}</div>
                <div class="authorname">
                  <Tooltip
                    placement="bottom"
                    theme="light"
                    :disabled="usertask.comments.length > 1 ? false : true"
                  >
                    {{
                      usertask.comments
                        .map((item) => item.authorName)
                        .toString()
                    }}
                    <div slot="content">
                      <div class="tooltips">
                        <div
                          class="tooltips-content"
                          v-for="(item, toolindex) in usertask.comments"
                          :key="toolindex"
                        >
                          {{ item.authorName }}
                        </div>
                      </div>
                    </div>
                  </Tooltip>
                </div>
                <div class="type">
                  <div
                    v-if="
                      usertask.comments[usertask.comments.length - 1] &&
                      usertask.comments[usertask.comments.length - 1].type
                    "
                    class="dot"
                  ></div>
                  <span>{{
                    usertask.comments[usertask.comments.length - 1] &&
                    usertask.comments[usertask.comments.length - 1].type
                  }}</span>
                </div>
                <div class="last-time">
                  {{
                    usertask.comments[usertask.comments.length - 1] &&
                    formatDate(
                      usertask.comments[usertask.comments.length - 1].time,
                      "MM月DD日 HH:mm:ss"
                    )
                  }}
                </div>
                <div class="fullmessage">
                  {{
                    usertask.comments[usertask.comments.length - 1] &&
                    usertask.comments[usertask.comments.length - 1].fullMessage
                  }}
                </div>
              </div>
              <div
                v-if="usertask.comments.length > 1"
                class="arrow"
                @click="changeExpand(usertask)"
              >
                <i
                  :class="usertask.isShow ? 'el-icon-minus' : 'el-icon-plus'"
                />
              </div>
            </div>
            <div v-if="usertask.isShow">
              <template v-for="(comment, index) in usertask.comments">
                <div class="timeline-draw timeline" :key="index">
                  <div class="timeline-wrapper">
                    <div class="timeline-index">
                      <div
                        v-if="usertask.index < usertasksLength"
                        class="icon-line"
                      ></div>
                    </div>
                    <div class="usertaskname"></div>
                    <div class="authorname">
                      {{ comment.authorName }}
                    </div>
                    <div class="type">
                      <div class="dot"></div>
                      <span>{{ comment.type }}</span>
                    </div>
                    <div class="last-time">
                      {{ formatDate(comment.time, "MM月DD日 HH:mm:ss") }}
                    </div>
                    <div class="fullmessage">{{ comment.fullMessage }}</div>
                  </div>
                  <div class="arrow"></div>
                </div>
              </template>
            </div>
          </div>
          <div v-else class="timeline-content" :key="usertaskIndex">
            <div class="timeline">
              <div class="timeline-wrapper">
                <div class="timeline-index">
                  <span>{{ usertask.index }}</span>
                  <div class="icon-bottom" v-if="usertask.index != 1">
                    <i class="el-icon-bottom"></i>
                  </div>
                  <div
                    class="icon-top"
                    v-if="usertask.index < usertasksLength"
                  ></div>
                </div>
                <div class="usertaskname">{{ usertask.userTaskName }}</div>
                <div class="authorname">
                  <Tooltip
                    placement="bottom"
                    theme="light"
                    :disabled="usertask.identitylinks.length > 1 ? false : true"
                  >
                    {{
                      usertask.identitylinks
                        .map((item) => item.displayname)
                        .toString()
                    }}
                    <div slot="content">
                      <div class="tooltips">
                        <div
                          class="tooltips-content"
                          v-for="(item, toolindex) in usertask.identitylinks"
                          :key="toolindex"
                        >
                          {{ item.displayname }}
                        </div>
                      </div>
                    </div>
                  </Tooltip>
                </div>
              </div>
              <div
                v-if="usertask.identitylinks.length > 1"
                class="arrow"
                @click="changeExpand(usertask)"
              >
                <i
                  :class="usertask.isShow ? 'el-icon-minus' : 'el-icon-plus'"
                />
              </div>
            </div>
            <div v-if="usertask.isShow">
              <template v-for="(identitylink, index) in usertask.identitylinks">
                <div class="timeline-draw timeline" :key="index">
                  <div class="timeline-wrapper">
                    <div class="timeline-index">
                      <div
                        v-if="usertask.index < usertasksLength"
                        class="icon-line"
                      ></div>
                    </div>
                    <div class="usertaskname"></div>
                    <div class="authorname">
                      {{ identitylink.displayname }}
                    </div>
                  </div>
                  <div class="arrow"></div>
                </div>
              </template>
            </div>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from "vue-property-decorator";
import moment from "moment";
import { GlobalService } from "ibiz-service";

@Component({})
export default class ExtendActionTimeline extends Vue {
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
        .getService(this.appEntityCodeName)
        .then((service: any) => {
          if (service) {
            service.GetWFHistory(this.context).then((res: any) => {
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
}
</script>

<style lang="less">
@import "./extend-action-timeline.less";
</style>
