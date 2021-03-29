
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppListBase } from 'ibiz-vue/src/components/control/app-common-control/app-list-base';

import '../plugin-style.less';

/**
 * 文件列表下载绘制插件插件类
 *
 * @export
 * @class ListDownload
 * @class ListDownload
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class ListDownload extends AppListBase {

/**
     * 绘制
     * 
     * @memberof ListDownload
     */
    public render(): any{
        if(!this.controlIsLoaded){
            return null;
        }
        let listClass: any = 'app-list';
        if(this.items.length <= 0){
            listClass = 'app-list app-list-empty';
        }
        return <div class={listClass}>
            {this.items.map((item: any)=>{
                let itemClass: any = 'app-list-item list-download';
                if(item.isselected){
                    itemClass = 'app-list-item isSelect';
                }
                return <div class={itemClass} on-click={()=>{this.handleClick(item)}} on-dblclick={()=>{this.handleDblClick(item)}}>
                    <a href={'../ibizutilpms/ztdownload/' + item.srfkey} target="_blank">{item.srfmajortext}</a>&nbsp;<icon type="md-close" on-click={()=>{this.remove([item])}} />
                </div>
            })}
        </div>
    }

}

