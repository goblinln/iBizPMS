<template>
    <div class="app-inpu-ip">
        <el-input  
            type="text" 
            size="small"
            :disabled="disabled"
            :readonly="readonly"
            v-model="firstIp"
            @focus="getFocus"
            maxlength="3" />.
        <el-input         
            type="text" 
            size="small"
            :disabled="disabled"
            :readonly="readonly"
            v-model="secIp"
            @focus="getFocus"
            maxlength="3" />.
        <el-input 
            type="text"
            size="small"
            maxlength="3" 
            :disabled="disabled"
            :readonly="readonly"
            @focus="getFocus"
            v-model="thirdIp" />.
        <el-input 
            type="text" 
            size="small"
            maxlength="3"
            :disabled="disabled"
            :readonly="readonly"
            @focus="getFocus"
            v-model="forIp" />
    </div>     
</template>

<script lang='ts'>
import { Component, Vue, Prop, Model, Watch } from 'vue-property-decorator';
import { Subject } from 'rxjs';

@Component({
})
export default class AppInputIp extends Vue {  

    /**
     * 应用上下文
     * 
     * @type {any}
     * @memberof AppInputIp
     */
    @Prop() context: any;

    /**
     * 视图参数
     * 
     * @type {any}
     * @memberof AppInputIp
     */
    @Prop() viewparam: any;

    /**
     * 是否启用禁用
     * 
     * @type {any}
     * @memberof AppInputIp
     */
    @Prop({default: false}) disabled: any;

	/**
	 * 只读模式
	 * 
	 * @type {boolean}
	 */
	@Prop({default: false}) public readonly?: boolean;

    /**
     * 表单状态对象
     *
     * @type {Subject<any>}
     * @memberof AppInputIp
     */
    @Prop() public formState!: Subject<any>;

    /**表单数据绑定
     * 
     * @type {string}
     * @memberof AppInputIp
     */
    @Model('change') public ipdata!: string;

    /**
     * 当前组件是否已获取到焦点
     * 
     * @type {boolean}
     * @memberof AppInputIp
     */
    public activeElement: boolean = false;

    /**
     * 获取当前值
     *
     * @type {string}
     * @memberof AppInputIp
     */
    public CurValue: any[] = [];

    /**
     * 第一段ip
     * 
     * @type {any}
     * @memberof AppInputIp
     */
    public firstIp: any = '';

    /**
     * 第二段ip
     * 
     * @type {any}
     * @memberof AppInputIp
     */
    public secIp: any = '';

    /**
     * 第三段ip
     * 
     * @type {any}
     */
    public thirdIp: any = '';

    /**
     * 第四段ip
     * 
     * @type {any}
     * @memberof AppInputIp
     */
    public forIp: any = '';
   
    /**
     * Vue声明周期(处理组件的输入属性)
     *
     * @memberof AppInputIp
     */
    public created(){
        if(this.formState){
             this.formState.subscribe(({type,data})=>{  
                if(Object.is('load',type)){
                    this.loadData();
                }
            })
        }
        this.loadData();
    } 

    /**
     * 加载数据
     *
     * @memberof AppInputIp
     */
    public loadData(){
        if(this.ipdata){
            let iparr:Array<any> = this.ipdata.split('.');
            this.CurValue = iparr;
            this.firstIp = this.CurValue[0];
            this.secIp = this.CurValue[1];
            this.thirdIp = this.CurValue[2];
            this.forIp = this.CurValue[3];
        }
    }

    /**
     * 监听每段ip变化
     * 
     * @memberof AppInputIp
     */
    @Watch('firstIp')
    public FirstIpChange(newVal:any,oldVal:any){
        this.checkIpVal(newVal,oldVal,'firstIp',0);
    }

    @Watch('secIp')
    public SecIpChange(newVal:any,oldVal:any){
        this.checkIpVal(newVal,oldVal,'secIp',1);
    }

    @Watch('thirdIp')
    public ThirdIpChange(newVal:any,oldVal:any){
        this.checkIpVal(newVal,oldVal,'thirdIp',2);
    }

    @Watch('forIp')
    public ForIpChange(newVal:any,oldVal:any){
        this.checkIpVal(newVal,oldVal,'forIp',3);
    }

    /**
     * 输入框获取到焦点时设置该组件已获取到焦点
     * 
     * @memberof AppInputIp
     */
    public getFocus(){
        this.activeElement = true;
    }

    /**
     * 验证格式
     * 
     * @memberof AppInputIp
     */
    public checkIpVal(newVal:any,oldVal:any,flag:any,index:number){
        if(newVal === '') return
        let val = newVal;
        let reg = /^(([0-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5]))))$/g;
        if(reg.test(val)){
            this.CurValue[index] = val;
            if(val.length == 3 && this.activeElement && index >=0 && index <= 2){
                const inputElement: any = this.$children;
                inputElement[index+1]?.focus();
            } 
        }else{
            if(flag){
                let that:any = this;
                that[flag] = oldVal;
                this.CurValue[index] = oldVal;
            }
        }
        if(this.firstIp && this.secIp && this.thirdIp && this.forIp){
            this.$emit('change',this.firstIp+'.'+this.secIp+'.'+this.thirdIp+'.'+this.forIp);
        }
    }
}
</script>

<style lang='less'>
@import './app-input-ip.less';
</style>
