import { IBizSearchBarModel } from 'ibiz-core';
import moment from 'moment';
import { AppSearchBarService } from '../ctrl-service/app-searchbar-service';
import { MDControlBase } from './MDControlBase';

export class SearchBarControlBase extends MDControlBase {


    /**
     * 搜索栏部件实例对象
     * 
     * @type {IBizSearchBarModel}
     * @memberof SearchBarControlBase
     */
    public controlInstance!: IBizSearchBarModel;

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
     * 过滤属性集合
     * 
     * @memberof SearchBarControlBase
     */
    get filterFields() {
        return Object.values(this.detailsModel);
    }

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
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof SearchBarControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        this.isExpandSearchForm = newVal.isExpandSearchForm;
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
        this.appdeName = this.controlInstance.appDataEntity ? this.controlInstance.appDataEntity.codeName : "";
        this.modelId = `searchbar_${this.appdeName ? this.appdeName.toLowerCase() : 'app'}_${this.controlInstance.codeName.toLowerCase()}`;
    }

    /**
     * 部件初始化
     * 
     * @memberof SearchBarControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        this.initDetailsModel();
        if(this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (Object.is('load', action)) {
                    this.load(data);
                }
            });
        }
    }

    /**
     * 初始化过滤属性模型集合
     * 
     * @memberof SearchBarControlBase
     */
    public initDetailsModel() {
        const barFilters = this.controlInstance.searchBarFilters;
        if(!barFilters || barFilters.length == 0) {
            return;
        }
        barFilters.forEach((filter: any) => {
            let tempModel: any = {
                label: filter.getPSAppDEField ? this.controlInstance.appDataEntity.getFieldByCodeName(filter.getPSAppDEField.codeName)?.logicName : "",
                localtag: "",
                name: filter?.name,
                prop: filter.getPSAppDEField ? filter.getPSAppDEField.codeName?.toLowerCase() : filter?.name,
                disabled: false
            };
            if(filter.getPSDEFSearchMode && filter.getPSDEFSearchMode.valueOP) {
                Object.assign(tempModel, { mode: filter.getPSDEFSearchMode.valueOP });
            }
            Object.assign(this.detailsModel, { [filter.name]: tempModel });
        })
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
     * @return {*}
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
     * @return {*}
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

    /**
     * 删除过滤项
     *
     * @return {*}
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
     * @return {*}
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
            appdeName: this.appdeName,
            modelid: this.modelId,
            utilServiceName: this.utilServiceName,
			...this.viewparams
		});
		let post = this.service.saveModel(this.utilServiceName, this.context, param);
		post.then((response: any) => {
            this.ctrlEvent({ controlname: this.controlInstance.name, action: "save", data: response.data });
		}).catch((response: any) => {
			console.log(response);
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
    public load(data: any) {
        let param: any = {};
        Object.assign(param, {
            appdeName: this.appdeName,
            modelid: this.modelId,
            utilServiceName: this.utilServiceName,
            ...this.viewparams
        });
        let post = this.service.loadModel(this.utilServiceName, this.context, param);
		post.then((response: any) => {
			if(response.status == 200) {
                this.historyItems = response.data;
			}
		}).catch((response: any) => {
			console.log(response);
		});
    }

    /**
     * 改变过滤条件
     *
     * @return {*}
     * @memberof SearchBarControlBase
     */
    public onFilterChange(evt: any) {
        let item: any = this.historyItems.find((item: any) => Object.is(evt, item.value));
        if(item) {
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
        let propip: any = this.$refs.propip;
        propip.handleMouseleave();
        this.onSave();
    }

}