
import { ViewBase, VueLifeCycleProcessing } from 'ibiz-vue';
import { Component } from 'vue-property-decorator';



/**
 * 首页更新日志插件类
 *
 * @export
 * @class UPDATELOGINDEXVIEW
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class UPDATELOGINDEXVIEW extends ViewBase {

    /**
     * 绘制视图内容
     * 
     * 
     * @memberof UPDATELOGINDEXVIEW
     */
    public render(){
        return <div>首页更新日志插件</div>;
    }

}
