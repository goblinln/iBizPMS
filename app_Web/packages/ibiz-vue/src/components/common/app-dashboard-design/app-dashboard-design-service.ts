import { IPSApplication, IPSAppPortlet } from '@ibiz/dynamic-model-api';
import { IPSAppDataEntity } from '@ibiz/dynamic-model-api';
import { AppServiceBase, Http } from 'ibiz-core';
import { UtilServiceBase } from 'ibiz-core';
import { UtilServiceRegister } from 'ibiz-service';
export default class AppDashboardDesignService {

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
            UtilServiceRegister.getInstance().getService(context, serviceName).then((utilService: any) => {
                utilService.getService(serviceName).then((service: any) => {
                    service.loadModelData(JSON.stringify(context), viewparams).then((response: any) => {
                        resolve(response);
                    }).catch((response: any) => {
                        reject(response);
                    });
                }).catch((response: any) => {
                    reject(response);
                });
            })
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
            UtilServiceRegister.getInstance().getService(context, serviceName).then((utilService: any) => {
                let saveModel: any = []
                for (const model of viewparams.model) {
                    // 保存的数据去除modelData
                    let temp: any = { ...model };
                    delete temp.modelData;
                    saveModel.push(temp)
                }
                viewparams.model = saveModel;
                utilService.saveModelData(JSON.stringify(context), '', viewparams).then((response: any) => {
                    resolve(saveModel);
                }).catch((response: any) => {
                    reject(response);
                });
            })
        });
    }



    /**
     * 加载门户部件集合
     *
     * @memberof AppDashboardDesignService
     */
    public async loadPortletList(context: any, viewparams: any): Promise<any> {
        const app:IPSApplication = AppServiceBase.getInstance().getAppModelDataObject();
        let list: any = [];
        const portletCats = app.getAllPSAppPortletCats();
        // 实体缓存集合
        if (app.getAllPSAppPortlets?.()?.length) {
            for (const portlet of app.getAllPSAppPortlets() as IPSAppPortlet[]) {
                // 门户部件实例
                // 实体实例
                let appDe = null;
                if (portlet?.getPSAppDataEntity?.()) {
                    appDe = portlet.getPSAppDataEntity()
                }
                // 门户部件分类集合
                let portletCat = portletCats?.find((item: any) => item.codeName == portlet.getPSAppPortletCat?.()?.id)
                let temp: any = {
                    type: 'app',
                    portletCodeName: portlet.codeName,
                    portletName: portlet.name,
                    groupCodeName: portletCat?.codeName || "",
                    groupName: portletCat?.name || "",
                    modelData: portlet?.getPSControl?.(),
                }
                if (appDe) {
                    temp.appCodeName = appDe.codeName;
                    temp.appName = appDe.name;
                } else {
                    temp.appCodeName = app.pKGCodeName;
                    temp.appName = app.name;
                }
                list.push(temp);
            }
        }
        const datas: any[] = this.filterData(list, viewparams.appdeNamePath);
        const result = this.prepareList(datas);
        const groups = this.prepareGroup(datas);
        return { data: datas, result: result.reverse(), groups: groups };
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
            if (Object.is(data.type, 'app')) {
                items.push(data);
            }
            if (Object.is(data.appCodeName, dataType)) {
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
            if (item) {
                let _item = item.children.find((a: any) => Object.is(a.portletCodeName, data.portletCodeName));
                if (!_item) {
                    item.children.push(data);
                }
            } else {
                items.push({ name: data.groupName, value: data.groupCodeName, children: [data] });
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
            if (!item) {
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
        if (!item) {
            item = {};
            Object.assign(item, {
                type: data.groupCodeName,
                name: data.groupName,
                children: []
            });
            children.push(item);
        }
        let _item = item.children.find((a: any) => Object.is(a.portletCodeName, data.portletCodeName));
        if (!_item) {
            item.children.push(data);
        }
    }
}