
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { Product } from 'ibiz-service';
import { AppDefaultList } from 'ibiz-vue/src/components/control/app-default-list/app-default-list';
import { AppViewLogicService } from 'ibiz-vue';
import { IPSDEListDataItem } from '@ibiz/dynamic-model-api';


/**
 * 测试列表(左侧导航区)插件类
 *
 * @export
 * @class TESTLEFTNAVLIST
 * @class TESTLEFTNAVLIST
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class TESTLEFTNAVLIST extends AppDefaultList {

   
    /**
     * 选中项
     *
     * @memberof LEFTNAVLIST
     */
    public selectItem = '';    

	/**
     * 项单机
     *
     * @protected
     * @param {MouseEvent} e
     * @param {Product} item
     * @memberof TestList
     */
    protected click(e: MouseEvent, item: Product): void {
        e.stopPropagation();
			this.selectItem = item.id;
        this.handleDblClick(item);
    }

    /**
     * 打开行为视图
     *
     * @protected
     * @param {MouseEvent} e
     * @param {Product} item
     * @param {string} srftabactivate
     * @memberof TestList
     */
    protected openActionView(e: MouseEvent, item: any, srftabactivate: string): void {
        e.stopPropagation();
        item.srftabactivate = srftabactivate;
        this.handleDblClick(item);
    }

    /**
      * 处理界面行为
      * 
      * @memberof GridControlBase
      */
    public handleActionClick(data: any, event: any, tag: any) {
        AppViewLogicService.getInstance().executeViewLogic(this.getViewLogicTag('list', 'quicktoolbar', tag), event, this, data, this.controlInstance.getPSAppViewLogics());
    }

    /**
     * 置顶
     *
     * @param {*} $event
     * @param {*} item
     * @memberof TestList
     */
    public setTop($event: any, item: any) {
        $event.stopPropagation();
        this.$nextTick(() => {
            this.handleActionClick(item, {}, 'deuiaction1');
        })
    }

    /**
     * 取消置顶
     *
     * @param {*} $event
     * @param {*} item
     * @memberof TestList
     */
    public canelTop($event: any, item: any) {
        $event.stopPropagation();
        this.handleActionClick(item, {}, 'deuiaction2');
    }

    /**
     * 绘制产品项
     *
     * @protected
     * @param {Product} p
     * @returns {*}
     * @memberof TestList
     */
    protected renderItem(p: Product): any {
        let typeItem: IPSDEListDataItem | undefined =  this.controlInstance.getPSDEListDataItems()?.find((item:any)=>{
            return item.name == 'type'
        });
        let statusItem: IPSDEListDataItem | undefined =  this.controlInstance.getPSDEListDataItems()?.find((item:any)=>{
            return item.name == 'status'
        });
        return <listItem class={{ 'is-top': p.istop ,'is-select':p.id===this.selectItem}}>
            <div class="content-wrapper" on-click={(e: any) => this.click(e, p)}>
                <div class="title">
                    <tag type="border">{p.id}</tag>
                    <tag>{p.code}</tag>
                    <div class="name-content">
                        <div class="name" title={p.name}>{p.name}</div>
                        <span class="open-action" on-click={(e: any) => this.click(e, p)}>
                            <icon type="md-open" />
                        </span>
                    </div>
                    <div class="item-action">
                        {p.istop ? <i-button on-click={($event: any) => this.canelTop($event, p)}>取消置顶</i-button> : <i-button on-click={($event: any) => this.setTop($event, p)}>置顶</i-button>}
                    </div>
                </div>
                <div class="content">
                    <tag color="cyan">类型：
                       <codelist value={p.type} codeList={typeItem?.getFrontPSCodeList()} context={this.context} viewparams={this.viewparams} textOnly={true}></codelist>
                    </tag>
                    <tag color="geekblue">状态：
                        <codelist value={p.status} codeList={statusItem?.getFrontPSCodeList()} context={this.context} viewparams={this.viewparams} textOnly={true}></codelist>
                    </tag>
                </div>
            </div>
            <template slot="action">
                <li on-click={(e: any) => this.openActionView(e, p, 'tabviewpanel2')}>
                    未解决：{p.activebugcnt}
                </li>
                <li on-click={(e: any) => this.openActionView(e, p, 'tabviewpanel2')}>
                    未确认：{p.unconfirmbugcnt}
                </li>
                <li on-click={(e: any) => this.openActionView(e, p, 'tabviewpanel2')}>
                    未关闭：{p.notclosedbugcnt}
                </li>
            </template>
        </listItem>;
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof TestList
     */
    public render(): any {
        if (!this.controlIsLoaded) {
            return null;
        }
        return <div class="product-list">
            {this.items.map((item: Product) => {
                return <list item-layout="vertical">{this.renderItem(item)}</list>;
            })}
        </div>;
    }


}

