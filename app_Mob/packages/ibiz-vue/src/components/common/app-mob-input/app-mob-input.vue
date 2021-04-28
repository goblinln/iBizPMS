<template>
    <div class="editor-input">
        <ion-input class="app-mob-input" debounce="300" :type="type" :disabled="disabled" :value="value" :placeholder="placeholder" @ionChange="change" @ionBlur="()=>{this.$emit('blur')}"></ion-input>
        <div class="app-mob-unit" v-if="unit">{{unit}}</div>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop} from 'vue-property-decorator';

@Component({
    components: {
    }
})
export default class AppInput extends Vue {       
    /**
     * 值
     *
     * @type {string}
     * @memberof AppInput
     */
    @Prop() public value?: string;

    /**
     * 禁用
     *
     * @type {string}
     * @memberof AppInput
     */
    @Prop({default:false}) public disabled?: boolean;
    
    /**
     * 类型
     *
     * @type {string}
     * @memberof AppInput
     */
    @Prop() public type?: string;


    /**
     * 占位提示文字
     *
     * @type {string}
     * @memberof AppInput
     */
    @Prop() public placeholder?:string;  
    
    /**
     * 单位
     *
     * @type {string}
     * @memberof AppInput
     */
    @Prop() public unit?: string;

    /**
     * change事件
     *
     * @memberof AppInput
     */
    public change(value: any) {
        if(this.type == "number"){
            this.$emit("change",parseInt(value.detail.value));
        }else{
            this.$emit("change", value.detail.value);
        }
    }
}
</script>
<style lang="less">
  @import './app-mob-input.less';
</style>