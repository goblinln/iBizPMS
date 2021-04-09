import { Util } from 'ibiz-core';
import { Prop, Watch } from 'vue-property-decorator';
import { DataViewControlBase } from '../../../widgets';

/**
 * 数据视图部件基类
 *
 * @export
 * @class AppDataViewBase
 * @extends {DataViewControlBase}
 */
export class AppDataViewBase extends DataViewControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppDataViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppDataViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDataViewBase
     */
    @Watch('dynamicProps',{
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
           super.onDynamicPropsChange(newVal,oldVal);
        }
    }

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDataViewBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
            super.onStaticPropsChange(newVal,oldVal);
        }
    }

    /**
     * 渲染完成
     * 
     * @memberof AppDataViewBase
     */
    public mounted() {
        this.$nextTick(()=>{
            this.mouseEvent();
        })
        this.$el.addEventListener('scroll', ()=> {
            if(this.controlInstance.getBatchPSDEToolbar){
                let el: any = this.$el.getElementsByClassName('dataview-pagination')[0];
                el.style.top = 40 + this.$el.scrollTop + 'px';
            }
            if(this.controlInstance.enablePagingBar){
                if( this.$el.scrollTop +  this.$el.clientHeight  >=  this.$el.scrollHeight ) {
                    this.loadMore();
                }
            }
        })
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppDataViewBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     * 
     * @param 抛出参数 
     * @memberof AppDataViewBase
     */
    public ctrlEvent({ controlname, action, data }: { controlname: string, action: string, data: any }): void { }

    /**
     * 绘制
     * 
     * @memberof AppDataViewBase
     */
    public render(h: any) {
        const { controlClassNames } = this.renderOptions;
        return (
            <div class={{...controlClassNames, "app-data-view":true }}>
                {this.renderSortBar(h)}
                <row v-show={this.items.length > 0}class="data-view-container" gutter={20} type="flex" justify="start" style="margin:0px;">
                    {this.renderDataViewContent(h)}
                    {this.renderBatchToolbar(h)}
                </row>
                {this.renderQuickToolbar(h)}
                <el-backtop target=".content-container .app-data-view"></el-backtop> 
            </div>
        );
    }
}
