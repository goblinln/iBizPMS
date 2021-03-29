<template>
    <div class="app-user-span">
            <codelist 
                v-if="flag"
                style="color:red;"
                :value="value" 
                :tag="tag" 
                :codelistType="codelistType" 
                :renderMode="renderMode" 
                :valueSeparator="valueSeparator" 
                :textSeparator="textSeparator"
            ></codelist>
            <codelist 
                v-else
                :value="value"  
                :tag="tag" 
                :codelistType="codelistType" 
                :renderMode="renderMode" 
                :valueSeparator="valueSeparator" 
                :textSeparator="textSeparator" 
            ></codelist>
    </div>
</template>

<script lang="ts">
import { argThresholdOpts } from 'moment';
import { Vue, Component, Prop, Provide, Emit, Watch, Model,Inject } from 'vue-property-decorator';
@Component({})
export default class AppUserSpan extends Vue{

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppUserSpan
     */
    @Prop() public viewparams: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppUserSpan
     */
    @Prop() public context: any;

    /**
     * 传递当前值
     * 
     * @type {*}
     * @memberof AppUserSpan
     */
    @Prop() public value: any;

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppUserSpan
     */
    @Prop() public tag!: string;

    /**
     * 代码表类型
     *
     * @type {string}
     * @memberof AppUserSpan
     */
    @Prop() public codelistType?: string;

    /**
     * 获取或模式
     * @type {boolean}
     * @memberof AppUserSpan
     */
    @Prop({ default: 'STR' }) public renderMode?: string;

    /**
     * 文本分隔符
     * @type {boolean}
     * @memberof AppUserSpan
     */
    @Prop({ default: ',' }) public textSeparator?: string;

    /**
     * 值分隔符
     * @type {boolean}
     * @memberof AppUserSpan
     */
    @Prop({ default: ',' }) public valueSeparator?: string;

    /**
     * 显示值
     * @type {*}
     * @memberof AppUserSpan
     */
    public text:any = '';

    /**
     * 当前登录用户
     * 
     * @type {*}
     * @memberof AppUserSpan
     */
    public flag:boolean = false;

    /**
     * VUE 生命周期
     * 
     * @memberof AppUserSpan
     */
    public created(){
        if(this.context && this.value){
            if(Object.is(this.value,this.context.srfloginname)){
                this.flag = true;
            }else{
                this.flag = false;
            }
        }else{
            this.flag = false;
        }
    }

    /**
     * VUE 生命周期
     * 
     * @memberof AppUserSpan
     */
    public updated(){
        if(this.context && this.value){
            if(Object.is(this.value,this.context.srfloginname)){
                this.flag = true;
            }else{
                this.flag = false;
            }
        }else{
            this.flag = false;
        }
    }

}
</script>

