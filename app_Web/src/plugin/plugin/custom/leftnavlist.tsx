
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppViewLogicService } from 'ibiz-vue';
import { Product } from 'ibiz-service';
import { AppDefaultList } from 'ibiz-vue/src/components/control/app-default-list/app-default-list';
import { IPSDEListDataItem } from '@ibiz/dynamic-model-api';
import '../plugin-style.less';

/**
 * 产品列表(左侧导航区)插件类
 *
 * @export
 * @class LEFTNAVLIST
 * @class LEFTNAVLIST
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class LEFTNAVLIST extends AppDefaultList {

/**
     * 项单机
     *
     * @protected
     * @param {MouseEvent} e
     * @param {Product} item
     * @memberof ProductList
     */
    protected click(e: MouseEvent, item: Product): void {
        e.stopPropagation();
        this.handleDblClick(item);
    }

    /**
     * 打开行为视图
     *
     * @protected
     * @param {MouseEvent} e
     * @param {Product} item
     * @param {string} srftabactivate
     * @memberof ProductList
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
     * 打开行为视图
     *
     * @protected
     * @param {MouseEvent} e
     * @param {Product} item
     * @param {string} srftabactivate
     * @memberof ProductList
     */
    protected openActionView2(e: MouseEvent, item: any, srftabactivate: string): void {
        e.stopPropagation();
        item.srftabactivate = srftabactivate;
        this.handleDblClick({
            ...item, parameters: [
                { pathName: 'products', parameterName: 'product' },
                { pathName: 'testtabexpview', parameterName: 'testtabexpview' },
            ]
        });
    }

    /**
     * 置顶
     *
     * @param {*} $event
     * @param {*} item
     * @memberof ProductList
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
     * @memberof ProductList
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
     * @memberof ProductList
     */
    protected renderItem(p: Product): any {
        let typeItem: IPSDEListDataItem | undefined =  this.controlInstance.getPSDEListDataItems()?.find((item:any)=>{
            return item.name == 'type'
        });
        let statusItem: IPSDEListDataItem | undefined =  this.controlInstance.getPSDEListDataItems()?.find((item:any)=>{
            return item.name == 'status'
        });
        return <listItem class={{ 'is-top': p.istop }}>
            <div class="content-wrapper" on-click={(e: any) => this.click(e, p)}>
                <div class="title">
                    <tag type="border">{p.productsn}</tag>
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
                        <codelist value={p.type} codeList={typeItem?.getFrontPSCodeList()} context={this.context} viewparams={this.viewparams} textOnly={true} ></codelist>
                    </tag>
                    <tag color="geekblue">状态：
                        <codelist value={p.status} codeList={statusItem?.getFrontPSCodeList()} context={this.context} viewparams={this.viewparams} textOnly={true} ></codelist>
                    </tag>
                </div>
            </div>
            <template slot="action">
                <li on-click={(e: any) => this.openActionView(e, p, 'tabviewpanel3')}>
                    计划数：{p.productplancnt}
                </li>
                <li on-click={(e: any) => this.openActionView(e, p, 'tabviewpanel4')}>
                    发布数：{p.releasecnt}
                </li>
                <li on-click={(e: any) => this.openActionView(e, p, 'tabviewpanel2')}>
                    激活需求：{p.activestorycnt}
                </li>
                <li on-click={(e: any) => this.openActionView2(e, p, 'tabviewpanel2')}>
                    未解决Bug：{p.activebugcnt}
                </li>
            </template>
        </listItem>;
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof ProductList
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

