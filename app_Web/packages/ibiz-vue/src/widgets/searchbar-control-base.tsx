import { IPSAppDEField, IPSApplication, IPSAppUtil, IPSDEFSearchMode, IPSSearchBar, IPSSearchBarFilter } from '@ibiz/dynamic-model-api';
import { GetModelService, LogUtil, SearchBarControlInterface } from 'ibiz-core';
import moment from 'moment';
import { AppSearchBarService } from '../ctrl-service/app-searchbar-service';
import { MDControlBase } from './md-control-base';

export class SearchBarControlBase extends MDControlBase implements SearchBarControlInterface{

    /**
     * 搜索栏部件实例对象
     * 
     * @type {IBizSearchBarModel}
     * @memberof SearchBarControlBase
     */
    public controlInstance!: IPSSearchBar;

    /**
     * 是否展开搜索栏部件
     * 
     * @type {boolean}
     * @memberof SearchBarControlBase
     */
    public isExpandSearchForm: boolean = false;

    /**
     * 过滤属性模型集合
     * 
     * @type {any}
     * @memberof SearchBarControlBase
     */
    public detailsModel: any = {};

    /**
     * 过滤项集合
     * 
     * @type {any[]}
     * @memberof SearchBarControlBase
     */
    public filterItems: any[] = [];

    /**
     * 应用实体名称
     * 
     * @type {string}
     * @memberof SearchBarControlBase
     */
    public appdeName: string = "";

    /**
     * modelid
     * 
     * @type {string}
     * @memberof SearchBarControlBase
     */
    public modelId: string = "";

    /**
     * 功能服务名称
     * 
     * @type {string}
     * @memberof SearchBarControlBase
     */
    public utilServiceName: string = "";

    /**
     * 历史记录
     * 
     * @type {any[]}
     * @memberof SearchBarControlBase
     */
    protected historyItems: any[] = [];

    /**
     * 选中记录
     * 
     * @type {any}
     * @memberof SearchBarControlBase
     */
    protected selectItem: any = null;

    /**
     * 存储项名称
     * 
     * @type {string}
     * @memberof SearchBarControlBase
     */
    protected saveItemName: string = "";

    /**
     * 过滤属性集合
     * 
     * @memberof SearchBarControlBase
     */
    get filterFields() {
        return Object.values(this.detailsModel);
    }

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof SearchBarControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        this.isExpandSearchForm = newVal.isExpandSearchForm;
        //搜索栏绘制之后关闭清空数据
        if (!this.isExpandSearchForm && this.controlIsLoaded) {
            this.filterItems = [];
        }
        super.onDynamicPropsChange(newVal, oldVal);
    }

    /**
     * 部件模型数据初始化
     *
     * @memberof SearchBarControlBase
     */
    public async ctrlModelInit() {
        await super.ctrlModelInit();
        this.service = new AppSearchBarService();
        this.modelId = `searchbar_${this.appDeCodeName ? this.appDeCodeName.toLowerCase() : 'app'}_${this.controlInstance.codeName.toLowerCase()}`;
        await this.initUtilService();
    }

    /**
     * 初始化功能服务名称
     *
     * @memberof SearchBarControlBase
     */
    public async initUtilService() {
        const appUtil: IPSAppUtil = ((await (await GetModelService(this.context))?.app as IPSApplication).getAllPSAppUtils() || []).find((util: any) => {
            return util.utilType == 'FILTERSTORAGE';
        }) as IPSAppUtil;
        if (appUtil) {
            this.utilServiceName = appUtil.codeName?.toLowerCase();
        }
    }

    /**
     * 部件初始化
     * 
     * @memberof SearchBarControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        this.initDetailsModel();
        this.load();
    }

    /**
     * 初始化过滤属性模型集合
     * 
     * @memberof SearchBarControlBase
     */
    public initDetailsModel() {
        const barFilters: Array<IPSSearchBarFilter> = this.controlInstance.getPSSearchBarFilters() || [];
        if(barFilters.length === 0) {
            return;
        }
        barFilters.forEach((filter: IPSSearchBarFilter) => {
            const appDeField = filter.getPSAppDEField() as IPSAppDEField;
            let tempModel: any = {
                label: appDeField?.logicName,
                localtag: "",
                name: filter.name,
                prop: appDeField?.codeName?.toLowerCase() || filter.name,
                disabled: false
            };
            const searchMode: IPSDEFSearchMode | null = filter.getPSDEFSearchMode();
            if(searchMode && searchMode.valueOP) {
                Object.assign(tempModel, { mode: searchMode.valueOP });
            }
            Object.assign(this.detailsModel, { [filter.name]: tempModel });
        })
    }

    /**
     * 删除过滤项
     *
     * @param {number} index 索引
     * @memberof SearchBarControlBase
     */
    public onRemove(index: number) {
        this.filterItems.splice(index, 1);
    } 

    /**
     * 搜索
     *
     * @return {*}
     * @memberof SearchBarControlBase
     */
    public onSearch() {
        this.ctrlEvent({ 
            controlname: this.controlInstance.name, 
            action: "search",
            data: this.getData()
        });
    }

    /**
     * 保存
     *
     * @param {string} [name] 名称
     * @memberof SearchBarControlBase
     */
    public onSave(name?: string) {
        let time = moment();
        this.historyItems.push({
            name: (name ? name : time.format('YYYY-MM-DD HH:mm:ss')),
            value: time.unix().toString(),
            data: JSON.parse(JSON.stringify(this.filterItems))
        })
        this.selectItem = time.unix().toString();
        let param: any = {};
		Object.assign(param, {
            model: JSON.parse(JSON.stringify(this.historyItems)),
            appdeName: this.appDeCodeName,
            modelid: this.modelId,
            utilServiceName: this.utilServiceName,
			...this.viewparams
		});
		let post = this.service.saveModel(this.utilServiceName, this.context, param);
		post.then((response: any) => {
            this.ctrlEvent({ controlname: this.controlInstance.name, action: "save", data: response.data });
		}).catch((response: any) => {
			LogUtil.log(response);
		});
    }

    /**
     * 重置
     *
     * @return {*}
     * @memberof SearchBarControlBase
     */
    public onReset() {
        this.filterItems = [];
    }

    /**
     * 加载
     *
     * @return {*}
     * @memberof SearchBarControlBase
     */
    public load() {
        let param: any = {};
        Object.assign(param, {
            appdeName: this.appDeCodeName,
            modelid: this.modelId,
            utilServiceName: this.utilServiceName,
            ...this.viewparams
        });
        let tempContext:any = JSON.parse(JSON.stringify(this.context));
        this.onControlRequset('load', tempContext, param);
        let post = this.service.loadModel(this.utilServiceName, tempContext, param);
        post.then((response: any) => {
            this.onControlResponse('load', response);
            if(response.status == 200) {
                this.historyItems = response.data;
            }
            this.isControlLoaded = true;
        }).catch((response: any) => {
            this.onControlResponse('load', response);
            LogUtil.log(response);
        });
    }

    /**
     * 改变过滤条件
     *
     * @param {*} evt
     * @memberof SearchBarControlBase
     */
    public onFilterChange(evt: any) {
        let item: any = this.historyItems.find((item: any) => Object.is(evt, item.value));
        if(item) {
            this.selectItem = item.value;
            this.filterItems = JSON.parse(JSON.stringify(item.data));
        }
    }

    /**
     * 打开弹框
     *
     * @return {*}
     * @memberof SearchBarControlBase
     */
    public openPoper() {
        this.saveItemName = '';
    }

    /**
     * 确定
     *
     * @return {*}
     * @memberof SearchBarControlBase
     */
    public onOk() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let propip: any = this.$refs.propip;
        propip.handleMouseleave();
        this.onSave(this.saveItemName);
    }

    /**
     * 取消设置
     *
     * @return {*}
     * @memberof SearchBarControlBase
     */
    public onCancel() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let propip: any = this.$refs.propip;
        propip.handleMouseleave();
        // this.onSave();
    }

    /**
     * 获取数据集
     * 
     * @memberof SearchBarControlBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项数据
     * 
     * @memberof SearchBarControlBase
     */
    public getData() {
        let data: any = {};
        if(this.filterFields.length > 0) {
            let filter: any = this.getFilter();
            Object.assign(data, { filter: filter ? filter : null })
        }
        return data;
    }

    /**
     * 获取过滤树
     * 
     * @memberof SearchBarControlBase
     */
    public getFilter() {
        if(this.filterItems.length === 0) {
            return null;
        }
        let ands: any[] = this.transformAnd(this.filterItems);
        this.transformResult(ands, '$and');
        if(ands.length === 0) {
            return null;
        }
        return { '$and': ands };
    }

    /**
     * 处理结果集
     *
     * @param {any[]} datas 数据
     * @param {string} pName
     * @memberof SearchBarControlBase
     */
    public transformResult(datas: any[], pName: string) {
        let items: any[] = [];
        for(let i = datas.length - 1; i >= 0; i--) {
            let data: any = datas[i];
            let field: string = Object.is(pName, '$and') ? '$or' : '$and';
            if(data.hasOwnProperty(field)) {
                items.push(data);
                datas.splice(i, 1);
                this.transformResult(data[field], field);
            }

        }
        if(items.length > 0) {
            let item: any = {};
            item[pName] = items;
            datas.push(item);
        }
    }

    /**
     * 处理并且逻辑
     *
     * @param {any[]} datas 数据
     * @return {*}  {*}
     * @memberof SearchBarControlBase
     */
    public transformAnd(datas: any[]): any {
        let result: any[] = [];
        datas.forEach((data: any) => {
            let item: any = {};
            if(data.field && data.mode) {
                item[data.field] = {};
                let valField: string = data.editor ? data.editor : data.field;
                item[data.field][data.mode] = (data[valField] == null  ? '' : data[valField]);
                result.push(item)
            } else if(Object.is(data.label, '$and')) {
                let items: any[] = this.transformAnd(data.children);
                result = [...result, ...items];
            } else if(Object.is(data.label, '$or')) {
                item[data.label] = this.transformOr(data.children);
                result.push(item)
            }
        })
        return result;
    }

    /**
     * 处理或逻辑
     *
     * @param {any[]} datas 数据
     * @return {*} 
     * @memberof SearchBarControlBase
     */
    public transformOr(datas: any[]) {
        let result: any[] = [];
        datas.forEach((data: any) => {
            let item: any = {};
            if(data.field && data.mode) {
                item[data.field] = {};
                let valField: string = data.editor ? data.editor : data.field;
                item[data.field][data.mode] = (data[valField] == null ? '' : data[valField]);
                result.push(item);
            } else if(Object.is(data.label, '$and')) {
                item[data.label] = this.transformAnd(data.children);
                result.push(item)
            } else if(Object.is(data.label, '$or')) {
                item[data.label] = this.transformOr(data.children);
                result.push(item);
            }
        })
        return result;
    }

}