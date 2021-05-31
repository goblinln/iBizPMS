<template>
    <van-rate class="app-mobile-rate" :disabled="disabled" v-model="curValue"/>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';
import { Rate } from 'vant';

Vue.use(Rate);
@Component({
    components: {
    }
})
export default class AppRate extends Vue {       
    /**
     * 评分值
     *
     * @type {string}
     * @memberof AppRate
     */
    @Prop() public value?: string;

    /**
     * 名称
     *
     * @type {string}
     * @memberof AppRate
     */
    @Prop() public name?: string;

    /**
     * 禁用
     *
     * @type {string}
     * @memberof AppRate
     */
    @Prop({default:false}) public disabled?: boolean;
    
    /**
     * 获取输入的Value值
     *
     * @type {string}
     * @memberof AppRate
     */
    get curValue(){
        return Number(this.value);
    }

    /**
     * 根据curValue变化抛出事件change
     *
     * @type {string}
     * @memberof AppRate
     */
    set curValue(val:any){
        this.$emit('change',val.toString());
    }

}
</script>
<style lang="less">
  .app-mobile-rate{
    width: 100%;
    display: flex;
    justify-content: flex-end;
   }
</style>