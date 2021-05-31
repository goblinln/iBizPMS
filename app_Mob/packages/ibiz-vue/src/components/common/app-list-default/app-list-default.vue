<template>
    <div class="app-list-default">
        <div class="icon_box" :style="getstyle()"><app-mob-icon :name="geticon()" /></div>
        <div class="info_box" v-if=" dataItemNames.length > 0">
            <template v-for="(i,index) in dataItemNames" >
                <div v-if="index == 0" :key="index" class="title">{{item[i]}}</div>
                <div  v-else :key="index" class="info">{{item[i]}}</div>
            </template>
        </div>
        <div v-else class="info_box">
            <div class="title">{{item[major]}}</div>
            <div class="info">{{$t('messages')}}</div>
        </div>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator';

@Component({
    i18n: {
        messages: {
            'ZH-CN': {
                messages: '[暂无内容]'
            },
            'EN-US': {
                messages: '[No data]'
            }
        }
    },
    components: {}
})
export default class AppDefaultList extends Vue {

    /**
     * 传入item
     *
     * @type {*}
     * @memberof AppDefaultList
     */
    @Prop() public item?: any;

    /**
     * 主属性
     *
     * @type {string}
     * @memberof AppDefaultList
     */
    @Prop({ default: 'srfmajortext' }) public major?: string;

    /**
     * 属性参数数组
     *
     * @type {Array}
     * @memberof AppDefaultList
     */
    @Prop({default:()=>{ return []}}) public dataItemNames?: Array<any>;

    /**
     * 图标数组
     *
     * @memberof AppDefaultList
     */
    public icon = ['airplane-outline', 'alarm-outline', 'albums-outline', 'alert-outline', 'american-football-outline', 'basket-outline', 'boat-outline', 'bug-outline', 'construct-outline', 'gift-outline'];

    /**
     * 颜色数组
     *
     * @memberof AppDefaultList
     */
    public color = ['#ba6fcc', '#46b4d9', '#cb99c5', 'pink', '#f59f00', '#9cd672', '#fa5a5a', '#f0d264', '#42bb85', '#7fccde'];

    /**
     * 获取icon
     *
     * @returns {string}
     * @memberof AppDefaultList
     */
    public geticon(): string {
        let num = Math.floor(Math.random() * 10);
        return this.icon[num];
    }

    /**
     * 获取样式
     *
     * @returns {string}
     * @memberof AppDefaultList
     */
    public getstyle(): string {
        let num = Math.floor(Math.random() * 10);
        return 'background: ' + this.color[num];
    }

}
</script>

<style lang="less">
@import "./app-list-default.less";
</style>