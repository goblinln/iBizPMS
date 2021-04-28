<template>
    <van-radio-group class="app-mobile-radio-list" :disabled="disabled" v-model="curValue" direction="horizontal">
        <van-radio v-for="(item,index) in options" :key="index" :name="item.value">{{item.text}}</van-radio>
    </van-radio-group>
</template>

<script lang="ts">
import { Vue, Component, Prop } from "vue-property-decorator";
import { CodeListServiceBase } from "ibiz-core";

@Component({
    components: {}
})
export default class AppMobRadio extends Vue {

    /**
     * 禁用
     *
     * @type {string}
     * @memberof AppStepper
     */
    @Prop({default:false}) public disabled?: boolean;

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppMobRadio
     */
    public codeListService: CodeListServiceBase = new CodeListServiceBase();

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppMobRadio
     */
    @Prop() public tag!: string;

    /**
     * 代码表类型
     *
     * @type {string}
     * @memberof AppMobRadio
     */
    @Prop() public codeListType!: string;

    /**
     * 代码表列表项
     *
     * @type {Array<any>}
     * @memberof AppMobRadio
     */
    public options?: Array<any> = [];

    /**
     * 输入值
     *
     * @type {any}
     * @memberof AppMobRadio
     */
    @Prop() public value?: any;

    /**
     * 输入值变化后的值
     *
     * @type {any}
     * @memberof AppMobRadio
     */
    get curValue() {
        return this.value;
    }

    set curValue(item:any){
      this.$emit("change", item);
    }

    /**
     *  vue 生命周期
     *
     * @returns
     * @memberof AppMobRadio
     */
    public created() {
        if (!this.tag || !this.codeListType) {
            return;
        }
        this.loadItems();
    }

    /**
     * 加载 数据
     *
     * @private
     * @returns {Promise<any>}
     * @memberof AppMobRadio
     */
    private async loadItems(): Promise<any> {
        if (Object.is(this.codeListType, 'dynamic')) {
            const response: any = await this.codeListService.getItems(this.tag);
            if (response) {
                this.options = response;
            } else {
                this.options = [];
            }
        } else {
            this.options = this.$store.getters.getCodeListItems(this.tag);
        }
    }
}
</script>

<style lang="less">
    .van-radio-group--horizontal{
        justify-content: flex-end;
    }
    .van-radio{
        margin:5px;
    }
</style>