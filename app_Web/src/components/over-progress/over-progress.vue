<template>
<div class='progress'>
    <span class='progress-text'>{{ value }}</span>
    <el-progress :percentage="percentage" :show-text='false' ></el-progress>
</div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from "vue-property-decorator";

@Component({})
export default class OverProgress extends Vue {
    
    /**
     * 传入数据
     *
     * @type {string}
     * @memberof OverProgress
     */
    @Prop() data!: any;

    /**
     * 进度条百分比
     *
     * @type {number}
     * @memberof OverProgress
     */
    public percentage: number = 0;

    /**
     * 进度条百分比字符
     *
     * @type {string}
     * @memberof OverProgress
     */
    public value?: string = "0%";

    /**
     * 生命周期
     *
     * @memberof OverProgress
     */
    public created() {
        this.handleData();
    }

    /**
     * 数据处理
     *
     * @memberof OverProgress
     */
    public handleData() {
        if(this.data) {
            const jd = parseFloat(this.data) * 100;
            this.value = jd.toFixed(2) + "%";
            this.percentage = jd;
        }
    }
}
</script>
<style lang="less">
.el-table {
    .progress {
        .progress-text {
            position: absolute;
            left: 2px;
            bottom: 4.5px;
            margin-left: 7px;  
        }
        .el-progress.el-progress--line {
            margin-left: 46px;
        }
    }
}
</style>