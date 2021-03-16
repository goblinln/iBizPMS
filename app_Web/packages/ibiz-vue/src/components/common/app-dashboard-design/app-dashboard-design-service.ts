import { AppServiceBase, Http, IBizEntityModel, IBizPortletModel } from 'ibiz-core';
import {UtilServiceBase} from 'ibiz-core';

export default class AppDashboardDesignService {

    /**
     * 工具服务对象
     *
     * @protected
     * @type {UtilService}
     * @memberof AppDashboardDesignService
     */
    protected utilService: UtilServiceBase = new UtilServiceBase();

    /**
     * 加载数据模型
     *
     * @param {string} serviceName
     * @param {*} context
     * @param {*} viewparams
     * @memberof AppDashboardDesignService
     */
    public loadModel(serviceName: string, context: any, viewparams: any) {
        return new Promise((resolve: any, reject: any) => {
            this.utilService.getService(serviceName).then((service: any) => {
                service.loadModelData(JSON.stringify(context), viewparams).then((response: any) => {
                    resolve(response);
                }).catch((response: any) => {
                    reject(response);
                });
            }).catch((response: any) => {
                reject(response);
            });
        });
    }

    /**
     * 保存模型
     *
     * @param {string} serviceName
     * @param {*} context
     * @param {*} viewparams
     * @returns
     * @memberof AppDashboardDesignService
     */
    public saveModel(serviceName: string, context: any, viewparams: any) {
        return new Promise((resolve: any, reject: any) => {
            this.utilService.getService(serviceName).then((service: any) => {
                let saveModel: any = []
                let returnModel: any = []
                for(const model of viewparams.model){
                    // 保存的数据去除modelData
                    let temp: any = {...model};
                    delete temp.modelData;
                    saveModel.push(temp)
                    // 返回给数据看板的modelData换成JSON数据
                    let temp2: any = {...model};
                    temp2.modelData = model.modelData.controlModelData;
                    returnModel.push(temp2)
                }
                viewparams.model = saveModel;
                service.saveModelData(JSON.stringify(context), '', viewparams).then((response: any) => {
                    resolve(returnModel);
                }).catch((response: any) => {
                    reject(response);
                });
            }).catch((response: any) => {
                reject(response);
            });
        });
    }

    

    /**
     * 加载门户部件集合
     *
     * @memberof AppDashboardDesignService
     */
    public async loadPortletList(context: any, viewparams: any): Promise<any> {
        const app = AppServiceBase.getInstance().getAppModelDataObject();
        let list: any = [];
        const portletCats = app.getAllPSAppPortletCats;
        // 实体缓存集合
        const appDeMap = new Map<string, IBizEntityModel>();
        if(app.getAllPSAppPortlets?.length > 0){
            for(const portlet of app.getAllPSAppPortlets){
                // 门户部件实例
                let modelData = portlet.getPSControl;
                modelData.getPSAppPortletCat = portlet.getPSAppPortletCat;
                const portletInstance = new IBizPortletModel(modelData,null,null,{context: context})
                // 实体实例
                let appDe = null;
                if(portlet?.getPSAppDataEntity?.modelref && portlet.getPSAppDataEntity?.path){
                    appDe = appDeMap.get(portlet.getPSAppDataEntity)
                }
                // 门户部件分类集合
                let portletCat = portletCats.find((item:any) => item.codeName == portletInstance.appPortletCat.id )
                let temp: any = {
                    type: 'app',
                    portletCodeName: portlet.codeName,
                    portletName: portlet.name,
                    groupCodeName: portletCat.codeName,
                    groupName: portletCat.name,
                    modelData: portletInstance,
                }
                if(appDe){
                    temp.appCodeName = appDe.path;
                    temp.appName = appDe.path;
                }else{
                    temp.appCodeName = app.pKGCodeName;
                    temp.appName = app.name;
                }
                list.push(temp);
            }
        }
        const datas: any[] = this.filterData(list, viewparams.appdeNamePath);
        const result = this.prepareList(datas);
        const groups = this.prepareGroup(datas);
        return {data: datas, result: result.reverse(), groups: groups};
    }
    

    /**
     * 加载门户部件集合
     *
     * @memberof AppDashboardDesignService
     */
    public loadPortletList2(context: any, viewparams: any): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            Http.getInstance().get('./assets/json/portlet-data.json').then((response: any) => {
                if (response && response.status === 200 && response.data) {
                    let result:Array<any> = [];
                    if(typeof(response.data)=='string'){
                        const index:number = response.data.lastIndexOf(",");
                        result = JSON.parse((response.data).slice(0,index)+']');
                    }else{
                        result = response.data;
                    }
                    const datas: any[] = this.filterData(result, viewparams.appdeName);
                    const list = this.prepareList(datas);
                    const groups = this.prepareGroup(datas);
                    resolve({data: datas, result: list.reverse(), groups: groups});
                }
            }).catch((response: any) => {
                console.log(response);
            });
        });
    }

    /**
     * 过滤数据
     *
     * @param {any[]} datas
     * @memberof AppDashboardDesignService
     */
    public filterData(datas: any[] = [], dataType: string): any[] {
        let items: any[] = [];
        datas.forEach((data: any) => {
            if(Object.is(data.type, 'app')) {
                items.push(data);
            }
            if(Object.is(data.appCodeName, dataType)) {
                items.push(data);
            }
        });
        return items;
    }

    /**
     * 分组集合
     *
     * @param {any[]} [datas=[]]
     * @returns {any[]}
     * @memberof AppDashboardDesignService
     */
    public prepareGroup(datas: any[] = []): any[] {
        let items: any[] = [];
        datas.forEach((data: any) => {
            let item = items.find((item: any) => Object.is(item.value, data.groupCodeName));
            if(item) {
                let _item = item.children.find((a: any) => Object.is(a.portletCodeName, data.portletCodeName));
                if(!_item) {
                    item.children.push(data);
                }
            } else {
                items.push({name: data.groupName, value: data.groupCodeName, children: [data]});
            }
        });
        return items;
    }

    /**
     * 准备list集合
     *
     * @memberof AppDashboardDesignService
     */
    public prepareList(datas: any[] = []): any[] {
        let list: any[] = [];
		datas.forEach((data: any) => {
			let item = list.find((item: any) => Object.is(data.type, item.type));
			if(!item) {
				item = {};
				Object.assign(item, { 
					type: data.type,
					name: Object.is(data.type, 'app') ? "全局" : data.appName,
					children: []
				});
				list.push(item);
			}
			this.prepareList2(item.children, data);
        })
        return list
	}

    /**
     * 准备list项集合
     *
     * @param {any[]} [children=[]]
     * @param {*} [data={}]
     * @memberof AppDashboardDesignService
     */
    public prepareList2(children: any[] = [], data: any = {}) {
		let item = children.find((item: any) => Object.is(data.groupCodeName, item.type));
		if(!item) {
			item = {};
			Object.assign(item, { 
				type: data.groupCodeName,
				name: data.groupName,
				children: []
			});
			children.push(item);
		}
		let _item = item.children.find((a: any) => Object.is(a.portletCodeName, data.portletCodeName));
        if(!_item) {
            item.children.push(data);
        }
    }
}