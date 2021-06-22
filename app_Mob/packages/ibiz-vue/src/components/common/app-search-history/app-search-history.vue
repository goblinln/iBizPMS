<template>
  <div class="app-seach-history">
        <ion-toolbar>
            <ion-searchbar style="height: 36px; padding-bottom: 0;" :placeholder="$t('app.fastsearch')" debounce="500" @ionChange="quickValueChange($event)"  @ionFocus="searchbarFocus" @ionBlur="searchbarBlur" @search="searchVal" ref="searchbar"></ion-searchbar>
            <ion-button v-if="showfilter" class="filter-btn" size="small" slot="end"  @click="openSearchform"><ion-icon  slot="end" name="filter-outline"></ion-icon>{{$t('app.commonWords.filter')}}</ion-button>
        </ion-toolbar>
        <div class="history" v-if="hasHistory && (searchbarIsFocus || isSpeaking ) ">
          <div class="hisText"><div>{{$t('app.commonWords.recentSearch')}}</div> <van-icon name="delete" @click="clearHistory"/></div>
          <div class="hisItems">
            <van-tag type="primary" v-for="item in historyList" :key="item.index" round size="medium" color="#ededed" text-color="#333" @click="searchbarValueChange(item)">{{item}}</van-tag>
          </div>
        </div>
        <div class="record" v-show="thirdPartyService.isInit && (searchbarIsFocus || isSpeaking)" >
          <!-- <div class="record_icon" :class="{'speaking':isSpeaking}" >
            <ion-icon name="mic-outline"  @touchstart="gotouchstart"  @touchend="gotouchend" @gesturestart="gotouchstart" @gestureend="gotouchend"></ion-icon>
          </div>
          <div class="record_text" v-show="!isSpeaking"><div>按住说话</div></div>
          <div class="record_text" v-show="isSpeaking"><div>请说话</div></div> -->
        </div>
  </div>
</template>
<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from "vue-property-decorator";
import { ThirdPartyService } from "ibiz-core";
@Component({
    components: {},
})
export default class AppRoundList extends Vue {
  
    /**
     * 第三方服务
     *
     * @type {boolean}
     * @memberof AppSearchHistory
     */
    public thirdPartyService: ThirdPartyService = ThirdPartyService.getInstance();

    /**
     * 视图模型数据
     *
     * @type {any}
     * @memberof AppSearchHistory
     */
    @Prop() public viewmodel: any;

    /**
     * 是否展示过滤按钮
     *
     * @type {boolean}
     * @memberof AppSearchHistory
     */
    @Prop() public showfilter!: boolean;

    /**
     * 长按定时器
     *
     * @type {boolean}
     * @memberof AppSearchHistory
     */
    public timeOutEvent: any;

    /**
     * 搜索框是否聚焦
     *
     * @type {boolean}
     * @memberof AppSearchHistory
     */
    public searchbarIsFocus: boolean = false;

    /**
     * 是否正在说话
     *
     * @type {boolean}
     * @memberof AppSearchHistory
     */
    public isSpeaking: boolean = false;


    /**
     * 搜索历史数组
     *
     * @type {Array}
     * @memberof AppSearchHistory
     */
    public historyList: Array<any> = [];
    @Watch("historyList")
    onHistoryListChange(newVal: any) {
        if (newVal.length != 0) {
            this.hasHistory = true;
        }
    }

    /**
     * 每一类搜索历史
     *
     * @type {Object}
     * @memberof AppSearchHistory
     */
    public historyLists: any = {};

    /**
     * 分类缓存搜索历史
     *
     * @type {Array}
     * @memberof AppSearchHistory
     */
    public historyStorage: Array<any> = [];

    /**
     * 是否展开搜索历史
     *
     * @type {boolean}
     * @memberof AppSearchHistory
     */
    public hasHistory: boolean = false;

    /**
     * 是否有搜索历史功能
     *
     * @type {boolean}
     * @memberof AppSearchHistory
     */
    public enableHistory: boolean = true;

    /**
     * touchstart 开始长按
     *
     * @type {boolean}
     * @memberof AppSearchHistory
     */
    public gotouchstart() {
        this.isSpeaking = true;
        let that = this;
        clearTimeout(this.timeOutEvent); //清除定时器
        this.timeOutEvent = 0;
        this.timeOutEvent = setTimeout(() => {
            this.startRecord();
        }, 700); //这里设置定时
    }

    /**
     * 开始录音
     *
     * @type {boolean}
     * @memberof AppSearchHistory
     */
    public startRecord() {
        this.thirdPartyService.thirdPartyEvent("startRecord");
    }

    /**
     * touchend 结束长按
     *
     * @type {any}
     * @memberof AppSearchHistory
     */
    public gotouchend() {
        clearTimeout(this.timeOutEvent);
        if (this.timeOutEvent != 0) {
            this.stopRecord();
            this.isSpeaking = false;
            //这里写要执行的内容（尤如onclick事件）
        }
    }

    /**
     * touchmove
     *  如果手指有移动，则取消所有事件，此时说明用户只是要移动而不是长按
     * @type {any}
     * @memberof AppSearchHistory
     */
    public gotouchmove() {
        clearTimeout(this.timeOutEvent); //清除定时器
        this.timeOutEvent = 0;
        this.isSpeaking = false;
    }

    /**
     * 释放长按语音
     *
     * @type {any}
     * @memberof AppSearchHistory
     */
    public async stopRecord() {
        let res = await this.thirdPartyService.thirdPartyEvent(
            "translateVoice",
            await this.thirdPartyService.thirdPartyEvent("stopRecord")
        );
        this.searchbarValueChange(res.content.replace("。", ""));
    }

    /**
     * 快速搜索值变化
     *
     * @param {*} event
     * @returns
     * @memberof AppSearchHistory
     */
    public quickValueChange($event: any) {
        this.$emit("quickValueChange", $event);
        this.searchVal($event);
    }

    /**
     * 打开搜索表单
     *
     * @memberof AppSearchHistory
     */
    public openSearchform() {
        this.$store.commit('setSearchformStatus',true); 
        this.$emit("openSearchform");
    }

    /**
     * 聚焦搜索框时
     *
     * @memberof AppSearchHistory
     */
    public searchbarFocus() {
        this.searchbarIsFocus = true;
        // 显示历史记录
        if (this.enableHistory) {
            if (
                Array.isArray(this.historyLists.historyList) &&
                this.historyLists.historyList.length <= 0
            ) {
                this.hasHistory = false;
            } else {
                this.hasHistory = true;
            }
        }
    }

    /**
     * 失焦搜索框时
     * @memberof AppSearchHistory
     */
    public searchbarBlur() {
        setTimeout(() => {
            this.searchbarIsFocus = false;
        }, );
    }

    /**
     * 触发搜索
     * @memberof AppSearchHistory
     */
    public searchVal($event: any) {
        if ($event.target) {
            let val: any = $event.target.value;
            val = val.trim();
            if (val === "") {
                return;
            }
            if (this.historyList.length > 0) {
                if (this.historyList.indexOf(val) !== -1) {
                    this.historyList.splice(this.historyList.indexOf(val), 1);
                    this.historyList.unshift(val);
                } else {
                    this.historyList.unshift(val);
                }
            } else {
                this.historyList.unshift(val);
            }
            if (this.historyList.length > 10) {
                this.historyList.pop();
            }
            if (this.enableHistory) {
                this.historyLists = this.historyStorage.find((historyLists: any) => {
                    return historyLists.viewname == this.viewmodel.viewname;
                });
                this.historyLists.historyList = this.historyList;
                localStorage.setItem(
                    "historyStorage",
                    JSON.stringify(this.historyStorage)
                );
            }
        }
    }

    /**
     * 清除搜索历史
     *
     * @type {function}
     * @memberof AppSearchHistory
     */
    public async clearHistory() {
        const result = await this.$Notice.confirm.call(this,this.$t('app.components.app_search_history.remind'), this.$t('app.components.app_search_history.clear'));
        if (result) {
            this.historyList = [];
            this.historyLists.historyList = [];
            localStorage.setItem(
                "historyStorage",
                JSON.stringify(this.historyStorage)
            );
            return true;
        } else {
            return false;
        }
    }

    /**
     * 点击tag
     *
     * @memberof AppSearchHistory
     */
    public searchbarValueChange(value: string) {
        let searchbar: any = this.$refs.searchbar;
        if (searchbar) {
            searchbar.value = value;
            this.quickValueChange({ detail: { value: value } });
        }
    }

    /**
     * 执行mounted后的逻辑
     *
     * @memberof AppSearchHistory
     */
    public afterMounted() {
        setTimeout(() => {
            this.hasHistory = false;
        }, 1);
        let storage: any = localStorage.getItem("historyStorage");
        if (storage) {
            // 后续拿
            let historystorage: any = JSON.parse(storage);
            this.historyLists = historystorage.find((historyLists: any) => {
                return historyLists.viewname == this.viewmodel.viewname;
            });
            if (this.historyLists) {
                // 如果是缓存过的模型
                this.historyList = this.historyLists.historyList;
                this.historyStorage = historystorage;
            } else {
                // 如果是未缓存过的模型 = 第一次拿
                this.historyLists = { viewname: this.viewmodel.viewname, historyList: [] };
                this.historyList = this.historyLists.historyList;
                historystorage.push(this.historyLists);
                this.historyStorage = historystorage;
            }
        } else {
            // 第一次拿
            this.historyLists = { viewname: this.viewmodel.viewname, historyList: [] };
            this.historyList = this.historyLists.historyList;
            this.historyStorage.push(this.historyLists);
        }
    }

    /**
     * 生命周期
     *
     * @memberof AppSearchHistory
     */
    public mounted() {
        this.afterMounted();
    }

}
</script>
<style lang = "less">
@import "./app-search-history.less";
</style>