
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppDefaultChart } from 'ibiz-vue/src/components/control/app-default-chart/app-default-chart';


/**
 * 燃尽图插件类
 *
 * @export
 * @class BurnoutFigure
 * @class BurnoutFigure
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class BurnoutFigure extends AppDefaultChart {


    /**
     * 获取图表数据
     * 
     * @returns {*} 
     * @memberof BurnoutFigure
     */
    public load(opt?:any) {
        let _this = this;
        const arg: any = { ...opt };
        const parentdata: any = {};
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "beforeload", data: parentdata });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg, { viewparams: tempViewParams });
        Object.assign(arg, { page: 0, size: 1000 });
        let tempContext:any = JSON.parse(JSON.stringify(this.context));
        this.onControlRequset('load', tempContext, arg);
        this.service.search(this.fetchAction,tempContext,arg,this.showBusyIndicator).then((res: any) => {
            this.onControlResponse('load', res);
            if (res) {
                if(parentdata && parentdata.isweekend){
                    this.chartOption.xAxis.forEach((xAix: any)=>{
                        xAix.axisLabel = {interval: parseInt(parentdata.isweekend)}
                    })
                }
                this.transformToBasicChartSetData(res.data,(codelist:any) =>{_this.drawCharts()});
            }
        }).catch((error: any) => {
            this.onControlResponse('load', error);
            console.error(error);
        });
    }

}

