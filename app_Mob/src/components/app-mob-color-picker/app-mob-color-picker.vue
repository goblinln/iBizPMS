<template>
    <div class="app-mob-color-picker">
        <div class="text">
            <ion-input :disabled="disabled" :value="curVal" ref="colorPicker" @ionChange="change" :placeholder="placeholder"></ion-input>
        </div>
        <div class="picker">
            <ion-icon name="color-palette-outline" @click="openPicker"></ion-icon>
            <input type="color" ref="picker" v-model="colorValue" name="color" id="color" class="color" @change="changeColor">
        </div>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Watch, Prop, Model } from 'vue-property-decorator';
import { CodeListService } from "ibiz-service";
import { Subject, Subscription } from 'rxjs';
@Component({
})
export default class AppMobColorPicker extends Vue {
    /**
     * 双向绑定表单项数据
     * 
     * @type {*}
     * @memberof AppMobColorPicker
     */
    @Prop() public value: any;

    /**
     * 表单数据
     * 
     * @type {*}
     * @memberof AppMobColorPicker
     */
    @Prop() public data: any;

    /**
     * 表单通讯对象
     * 
     * @type {*}
     * @memberof AppMobColorPicker
     */
    @Prop() public formState?: Subject<any>;

    /**
     * 禁用状态
     * 
     * @type {*}
     * @memberof AppMobColorPicker
     */
    @Prop({default: false}) public disabled?: boolean;

    /**
     * 占位提示
     * 
     * @type {*}
     * @memberof AppMobColorPicker
     */
    @Prop() public placeholder?: string;

    /**
     * 上下文
     * 
     * @type {*}
     * @memberof AppMobColorPicker
     */
    @Prop() public context: any;

    /**
     * 视图参数
     * 
     * @type {*}
     * @memberof AppMobColorPicker
     */
    @Prop() public viewparam: any;

    /**
     * 颜色对应字段值
     * 
     * @type {*}
     * @memberof AppMobColorPicker
     */
    @Prop() public color: any;

    /**
     * 双向绑定颜色
     * 
     * @type {*}
     * @memberof AppMobColorPicker
     */
    public colorValue: any = null;

    /**
     * 获取输入框值
     * 
     * @type {*}
     * @memberof AppMobColorPicker
     */
    get curVal() {
        return this.value;
    }


    public change(val: any){
        const {detail} = val;
        if(!detail){
            return 
        }
        const {value} = detail;
        this.$emit('change', value);
    }
    /**
     * Vue生命周期
     * 
     * @memberof AppMobColorPicker
     */
    public created() {
        this.handleData();
    }

    /**
     * 数据处理
     * 
     * @memberof AppMobColorPicker
     */
    @Watch('value')
    public handleData() {
        if(!this.value && !this.color) {
            return;
        }
        this.colorValue = this.data[this.color];
        this.handleInputColor();
    }

    /**
     * 设置输入框字体颜色
     * 
     * @memberof AppMobColorPicker
     */
    public handleInputColor() {
        let textDom: any = document.getElementsByClassName('text')[0];
        if(textDom){
            textDom.style.color = this.colorValue;
        }
    }

    /**
     * 颜色变化
     * 
     * @memberof AppMobColorPicker
     */
    public changeColor($event: any){
        this.handleInputColor();
        this.$emit('colorChange', { name: this.color, value: this.colorValue });
    }

    /**
     * 打开颜色选择
     * 
     * @memberof AppMobColorPicker
     */
    public openPicker(){
        let e: any = document.createEvent('MouseEvent');
        e.initEvent('click', true, true);
        let doc:any = document;
        let picker: any = doc.getElementById("color");
        picker.dispatchEvent(e);
    }
}
</script>

<style lang='less'>
@import './app-mob-color-picker.less';
</style>