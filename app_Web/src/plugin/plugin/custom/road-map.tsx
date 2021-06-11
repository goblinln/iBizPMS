
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppListBase } from 'ibiz-vue/src/components/control/app-common-control/app-list-base';
import '../plugin-style.less';

/**
 * 产品路线图插件类
 *
 * @export
 * @class RoadMap
 * @class RoadMap
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class RoadMap extends AppListBase {

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof RoadMap
     */    
    public sortDir2:string = 'desc';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof RoadMap
     */    
    public sortField2: string = 'begin';

    /**
     * 绘制模式
     *
     * @type {('group' | 'default')}
     * @memberof RoadMap
     */
    public mode: any = 'default';

    /**
     * 展示的数据
     *
     * @protected
     * @type {RoadMapItem[]}
     * @memberof RoadMap
     */
    protected showItems: any[] = [];

    /**
     * 部件挂载
     *
     * @param {*} [args]
     * @memberof RoadMap
     */
    public ctrlMounted(args?: any) {
        this.ctrlEvent({
            controlname: 'list2',
            action: 'controlIsMounted',
            data: true
        })
        super.ctrlMounted();
    }
	
    /**
     * 格式化展示数据
     *
     * @protected
     * @memberof RoadMap
     */
    protected formatData(): void {
        if (Object.is(this.mode, 'default')) {
            const items: any[] = [];
            this.items.forEach((item: any) => {
                if (item && item.items) {
                    items.push(...item.items);
                }
            });
            this.showItems = [];
            if (items.length <= 7) {
                this.showItems = items;
            } else {
                this.showItems.push(...items.slice(0, 6));
                this.showItems.push(...items.slice(items.length - 1));
            }
        }
    }

    /**
     * 绘制分组模式
     *
     * @protected
     * @returns {*}
     * @memberof RoadMap
     */
    protected renderGroup(): any {
        return <div class="road-map-group-wrapper">
            {this.items.map((item: any) => {
                if (!item.items) {
                    return;
                }
                return <div class="road-map-group-item">
                    <div class="title-wrapper">
                        <div class="title">
                            <div class="year">{item.year}年</div>
                            <div class="iteration">迭代{item.items.length}次</div>
                        </div>
                    </div>
                    <div class="content">
                        {this.renderItems(item.items)}
                    </div>
                </div>;
            })}
        </div>;
    }

    /**
     *
     *
     * @protected
     * @param {any[]} items
     * @returns {*}
     * @memberof RoadMap
     */
    protected renderItems(items: any[]): any {
        return <div class="road-map-wrapper">
            {
                items.map((item, i) => {  
                    return <div class="road-map-item">
                        <a href="javascript:void(0);">
                            <div class="content">
                                <div class="title" title={item.productlifename}>{item.productlifename}</div>
                                {
                                    Object.is(item.type, 'productplan')
                                        ?
                                        <div class="date" title={item.begin}>{item.begin}{Object.is(this.mode, 'group') ? '~' + item.end : ''}</div>
                                        :
                                        <div class="date" title={item.end}>{item.end}</div>
                                }
                                {
                                    item.marker === 1 && (i % 2 === 0 ? <i class="fa fa-flag fa-2x odd" aria-hidden="true"></i> : <i class="fa fa-flag fa-2x even" aria-hidden="true"></i>)
                                }  
                            </div>
                        </a>
                    </div>;
                })
            }
        </div>;
    }

    /**
     * 加载数据
     * 
     * @param opt 
     */
     public async load(opt: any = {}) {
        if(!this.fetchAction){
            this.$Notice.error({ title: '错误', desc: 'ProductLifeRoadMapSListView9视图列表fetchAction参数未配置' });
            return;
        }        
        const arg: any = {...opt};
        const page: any = {};
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage-1, size: this.limit });
        }
        // 设置排序
        if (!Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF + ',' + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.ctrlEvent({
            controlname: this.controlInstance.name,
            action: 'beforeload',
            data: parentdata,
        });
        Object.assign(arg, parentdata);
        let tempViewParams:any = parentdata.viewparams?parentdata.viewparams:{};
        Object.assign(tempViewParams,JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg,{viewparams:tempViewParams});
		   let tempContext:any = JSON.parse(JSON.stringify(this.context));
        this.onControlRequset('load', tempContext, arg);
        const post: Promise<any> = this.service.search(this.fetchAction, tempContext, arg, this.showBusyIndicator);
        post.then(async (response: any) => {
			    this.onControlResponse('load', response);
            if (!response || response.status !== 200) {
                if (response.errorMessage) {
                    this.$Notice.error({ title: '错误', desc: response.errorMessage });
                }
                return;
            }
            const items: any = response.data;
            this.items = [];
            if (items&& items.length > 0) {
                for (let index = 0; index < items.length; index++) {
                    const item = items[index];
                    Object.assign(item, { isselected: false });
                    await this.load2(item);
                }
                this.totalRecord = response.total;
                this.items.push(...items);
            }
            this.ctrlEvent({
                controlname: this.controlInstance.name,
                action: 'load',
                data: this.items,
            });
            if(this.isSelectFirstDefault){
                this.handleClick(this.items[0]);
            }
            if(this.items.length > 0){
                if(this.controlInstance?.getParentPSModelObject?.()?.codeName == 'ProductLifeRoadMapListView'){
                    this.mode = 'group';
                }
                this.formatData();
            }
        }, (response: any) => {
			    this.onControlResponse('load', response);
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: '错误', desc: response.errorMessage });
        });
    }

    /**
     * 根据年份加载子数据
     *
     * @public
     * @param {*} [arg={}]
     * @renturn Promise<any>
     * @memberof RoadMap
     */
    public async load2(opt: any = {}): Promise<any> {
        const arg: any = {};
        if (this.isEnablePagingBar) {
            Object.assign(arg, { page: this.curPage - 1, size: this.limit });
        }
        // 设置排序
        if (!Object.is(this.sortDir2, '') && !Object.is(this.sortField2, '')) {
          const sort: string = this.sortField2+","+this.sortDir2;
            Object.assign(arg, { sort: sort });
        }
        arg.viewparams = this.viewparams || {};
        arg.viewparams.year = opt.year;
        const context = { ...(this.context || {}) };
        try {
			this.onControlRequset('FetchRoadmap', context, arg);
            const res = await this.service.search('FetchRoadmap', context, arg, this.showBusyIndicator);
			this.onControlResponse('FetchRoadmap', res);
            if (res && res.status === 200) {
                opt.items = res.data;
            }
        } catch (error) {
			this.onControlResponse('FetchRoadmap', error);
            console.log(error)
         }
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof RoadMap
     */
    public render(): any {
        if(!this.controlIsLoaded){
            return null;
        }
        if(this.items.length > 0){
            return <div class='app-list'>
                <div class="road-map">
                    {Object.is(this.mode, 'default') ? this.renderItems(this.showItems) : this.renderGroup()}
                </div>
            </div>
        } else {
            return <div class='app-list app-list-empty'>
                <div>暂无数据</div>
            </div>
        }
    }

}

