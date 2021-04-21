<template>
    <div class="app-actionbar">
        <div class="app-actionbar-item" v-for="(item,index) in Object.values(items)" :key="index">
            <Badge v-if="item.counterService&&item.counterService.counterData" v-show="item.visabled"
                :count="item.counterService.counterData[item.counterId]" type="primary">
                <i-button :disabled="item.disabled" @click="handleClick(item, $event)">
                    <i v-if="item.icon" style="margin-right: 5px;" :class="item.icon"></i>
                    {{item.actionName}}
                </i-button>
            </Badge>
            <i-button v-show="item.visabled" :disabled="item.disabled" v-else @click="handleClick(item, $event)">
                <i v-if="item.icon" style="margin-right: 5px;" :class="item.icon"></i>
                {{item.actionName}}
            </i-button>
        </div>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from "vue-property-decorator";
import { Subject, Subscription } from "rxjs";
import { ViewState, ViewTool } from 'ibiz-core';

@Component({})
export default class AppActionBar extends Vue {

    /**
     * 传入操作栏模型数据
     * 
     * @type {any}
     * @memberof AppActionBar
     */
    @Prop() public items!: any;

    /**
     * 注入的UI服务
     *
     * @type {*}
     * @memberof AppActionBar
     */
    @Prop() public uiService!: any;

    /**
     * 视图通讯对象
     *
     * @type {Subject<ViewState>}
     * @memberof AppActionBar
     */
    @Prop() public viewState!: Subject<ViewState>;

    /**
     * 视图状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof ActionlinetestBase
     */
    public viewStateEvent: Subscription | undefined;

    /**
     * 部件数据
     *
     * @type {*}
     * @memberof AppActionBar
     */
    public data: any;

    /**
     * 组件初始化
     *
     * @memberof AppActionBar
     */
    public created() {
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }) => {
                if (!Object.is(tag, "all-portlet")) {
                    return;
                }
                if (Object.is(action, 'loadmodel')) {
                    this.data = data;
                    ViewTool.calcActionItemAuthState(data, this.items, this.uiService);
                    this.$forceUpdate();
                }
            });
        }
    }

    /**
     * 触发界面行为
     * 
     * @memberof AppActionBar
     */
    public handleClick(item: any, $event: any) {
        let _data = {
            tag: item.viewlogicname,
            params: this.data,
            event: $event
        };
        this.$emit('itemClick', _data);
    }

    /**
     * 组件销毁
     * 
     * @memberof AppActionBar
     */
    public destory() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }

}
</script>

<style lang='less'>
@import './app-actionbar.less';
</style>