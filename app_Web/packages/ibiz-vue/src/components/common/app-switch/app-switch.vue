<template>
    <el-switch v-model="curValue" :disabled="disabled || readonly"></el-switch>
</template>

<script lang="ts">
import { Vue, Component, Prop, Model } from 'vue-property-decorator';

@Component({})
export default class AppSwitch extends Vue {
    /**
     * checked选中状态
     *
     * @type {boolean}
     * @memberof Appswitch
     */
    @Prop() public value?: any;

    get curValue() {
        return this.value == 1 ? true : false;
    }

    set curValue(value: any) {
        let emitValue = value == true ? 1 : 0;
        this.$emit('change', emitValue);
    }

    /**
     * 禁用
     *
     * @type {boolean}
     * @memberof Appswitch
     */
    @Prop() public disabled?: boolean;

    /**
	 * 只读模式
	 * 
	 * @type {boolean}
	 */
	@Prop({default: false}) public readonly?: boolean;
}
</script>

<style lang="less">
@import './app-switch.less';
</style>
