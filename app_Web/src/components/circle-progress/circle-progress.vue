<template>
<div class='circle-progress'>
    <i-circle
        :size='size'
        :trail-width="width"
        :stroke-width="width"
        :stroke-color="strokeColor"
        :trail-color="color"
        :percent="percent">
        <div>
            <div class='caption'>
                <strong>{{ caption }}</strong>
            </div>
            <div class='progress'>
                <strong>{{ total }}</strong>
            </div>
        </div>
    </i-circle>
</div>
</template>

<script lang = 'ts'>
import { Vue, Component, Prop, Model, Watch } from 'vue-property-decorator';
@Component({})
export default class CircleProgress extends Vue {

    /**
     * 进度环尺寸
     * 
     */
    @Prop({default: 192}) size!: number;
    /**
     * 进度环颜色
     */
    @Prop() strokeColor: any;
    /**
     * 进度环宽度
     */
    @Prop() strokeWidth!: number;
    /**
     * 进度环具体宽度
     */
    public width = this.strokeWidth / 2;
    /**
     * 进度环内文字标题
     */
    @Prop() caption: any;
    /**
     * 进度环内总数
     */
    @Prop() total:any;
    /**
     * 当前进度
     */
    @Prop() progress:any;

    /**
     * 监听总数
     */
    @Watch('total')
    public change() {
        this.initData();
    }

    /**
     * 监听进度
     */
    @Watch('progress')
    public changeProgress() {
        this.initData();
    }
    /**
     * 进度百分比
     */
    public percent: number = 0;
    /**
     * 进度环背景颜色
     */
    public color: any = 'rgba(var(--form-editor-active-color-rgb), 0.36)';

    /**
     * vue生命周期
     */
    public created() {
        this.initData();
    }

    /**
     * 数据初始化
     */
    public initData() {
        if(this.total) {
            this.percent = (this.progress / this.total) * 100;
        }
        if(this.total === 0) {
            this.percent = 0;
        }
        if(this.strokeColor) {
            this.color = "rgba(" + this.strokeColor.toString().substr(0, this.strokeColor.length-1) + '-rgb)' + ", 0.36)";
        }
    }

}
</script>
<style lang="less">
.circle-progress {
    display: flex;
    height: 100%;
    position: relative;
    justify-content: center;
    align-items: center;
    
    .caption {
        font-size: 28px;   
        margin-bottom: 8px;
    }

    .progress {
        font-size: 28px;
    }
}
</style>