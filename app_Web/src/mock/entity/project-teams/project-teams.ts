import qs from 'qs';
import { MockAdapter } from '@/mock/mock-adapter';
const mock = MockAdapter.getInstance();

// 模拟数据
const mockDatas: Array<any> = [
];


//getwflink
mock.onGet(new RegExp(/^\/wfcore\/ibizpms-app-web\/projectteams\/[a-zA-Z0-9\-\;]+\/usertasks\/[a-zA-Z0-9\-\;]+\/ways$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: getwflink");
    console.table({url:config.url, method: config.method, data:config.data});
    console.groupEnd();
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, {}];
    }
    return [status,[
        {"sequenceFlowId":"dfdsfdsfdsfdsfds","sequenceFlowName":"同意",
         "taskId":"aaaaddddccccddddd","processDefinitionKey":"support-workorders-approve-v1",
         "processInstanceId":"ddlfldldfldsfds","refViewKey":""},
        {"sequenceFlowId":"ddssdfdfdfdfsfdf","sequenceFlowName":"不同意",
         "taskId":"aaaaddddccccddddd","processDefinitionKey":"support-workorders-approve-v1",
         "processInstanceId":"ddfdsldlfdlldsf","refViewKey":"workorder_ltform_editview"}
        ]];
});

// getwfstep
mock.onGet(new RegExp(/^\/wfcore\/ibizpms-app-web\/projectteams\/process-definitions-nodes$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: getwfstep");
    console.table({url:config.url, method: config.method, data:config.data});
    console.groupEnd();
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, {}];
    }
    return [status, [
        {"userTaskId":"sddfddfd-dfdf-fdfd-fdf-dfdfd",
        "userTaskName":"待审",
        "cnt":0,
        "processDefinitionKey":"support-workorders-approve-v1",
        "processDefinitionName":"工单审批流程v1"
        },
        {"userTaskId":"sddfddfd-dfdf-fdfd-fdf-87927",
        "userTaskName":"待分配",
        "cnt":3,
        "processDefinitionKey":"support-workorders-approve-v1",
        "processDefinitionName":"工单审批流程v1"}
        ]];
});

// createBatch
mock.onPost(new RegExp(/^\/projectteams\/batch$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: createBatch");
    console.table({url:config.url, method: config.method, data:config.data});
    console.groupEnd();
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, {}];
    }
    return [status, {}];
});

// updateBatch
mock.onPut(new RegExp(/^\/projectteams\/batch$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: updateBatch");
    console.table({url:config.url, method: config.method, data:config.data});
    console.groupEnd();
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, {}];
    }
    return [status, {}];
});

// removeBatch
mock.onDelete(new RegExp(/^\/projectteams\/batch$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: removeBatch");
    console.table({url:config.url, method: config.method, data:config.data});
    console.groupEnd();
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, {}];
    }
    return [status, {}];
});


    
// getuserrole
mock.onPut(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/getuserrole$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: getuserrole");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/getuserrole$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// getuserrole
mock.onPut(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/getuserrole$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: getuserrole");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/getuserrole$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});


// Select
mock.onGet(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/select$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Select");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/select$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    let items = mockDatas ? mockDatas : [];
    let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(_items);
    console.groupEnd();
    console.groupEnd();
    return [status, _items];
});

// Select
mock.onGet(new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/select$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Select");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/select$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    let items = mockDatas ? mockDatas : [];
    let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(_items);
    console.groupEnd();
    console.groupEnd();
    return [status, _items];
});

    
// Create
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Create");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// Create
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Create");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(mockDatas[0]);
    console.groupEnd();
    console.groupEnd();
    return [status, mockDatas[0]];
});

    
// Update
mock.onPut(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Update");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// Update
mock.onPut(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Update");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});




// GetDraft
mock.onGet(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/getdraft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: GetDraft");
    console.table({url:config.url, method: config.method, data:config.data});
    // GetDraft
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});

// GetDraft
mock.onGet(new RegExp(/^\/projectteams\/getdraft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: GetDraft");
    console.table({url:config.url, method: config.method, data:config.data});
    // GetDraft
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});

    
// CheckKey
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/checkkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: CheckKey");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/checkkey$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// CheckKey
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/checkkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: CheckKey");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/checkkey$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// Save
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/save$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Save");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/save$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// Save
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/save$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Save");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/save$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});


// FetchDefault
mock.onGet(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/fetchdefault$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchDefault");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id'];
    let tempValue: any = {};
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/fetchdefault$/).exec(config.url);
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    let items = mockDatas ? mockDatas : [];
    if (items.length > 0 && paramArray.length > 0) {
        paramArray.forEach((paramkey: any) => {
            if (tempValue[paramkey] && tempValue[paramkey].indexOf(";") > 0) {
                let keysGrounp: Array<any> = tempValue[paramkey].split(new RegExp(/[\;]/));
                let tempArray: Array<any> = [];
                keysGrounp.forEach((singlekey: any) => {
                    let _items =  items.filter((item: any) => { return item[paramkey] == singlekey });
                   if(_items.length >0){
                    tempArray.push(..._items);
                   }
                })
                items = tempArray;
            } else {
                items = items.filter((item: any) => { return item[paramkey] == tempValue[paramkey] });
            }
        })
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(items);
    console.groupEnd();
    console.groupEnd();
    return [status, items];
});
    
// FetchDefault
mock.onGet(new RegExp(/^\/projectteams\/fetchdefault$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchDefault");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(mockDatas);
    console.groupEnd();
    console.groupEnd();
    return [status, mockDatas ? mockDatas : []];
});

// FetchDefault
mock.onGet(new RegExp(/^\/projectteams\/fetchdefault(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchDefault");
    console.table({url:config.url, method: config.method, data:config.data});
    if(config.url.includes('page')){
        let url = config.url.split('?')[1];
        let params  =  qs.parse(url);
        Object.assign(config, params);
    }
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    let total = mockDatas.length;
    let records: Array<any> = [];
    if(!config.page || !config.size){
        records = mockDatas;
    }else{
        if((config.page-1)*config.size < total){
          records = mockDatas.slice(config.page,config.size);
        }
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(records ?  records : []);
    console.groupEnd();
    console.groupEnd();
    return [status, records ?  records : []];
});


// FetchProjectTeamPm
mock.onGet(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/fetchprojectteampm$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchProjectTeamPm");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id'];
    let tempValue: any = {};
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/fetchprojectteampm$/).exec(config.url);
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    let items = mockDatas ? mockDatas : [];
    if (items.length > 0 && paramArray.length > 0) {
        paramArray.forEach((paramkey: any) => {
            if (tempValue[paramkey] && tempValue[paramkey].indexOf(";") > 0) {
                let keysGrounp: Array<any> = tempValue[paramkey].split(new RegExp(/[\;]/));
                let tempArray: Array<any> = [];
                keysGrounp.forEach((singlekey: any) => {
                    let _items =  items.filter((item: any) => { return item[paramkey] == singlekey });
                   if(_items.length >0){
                    tempArray.push(..._items);
                   }
                })
                items = tempArray;
            } else {
                items = items.filter((item: any) => { return item[paramkey] == tempValue[paramkey] });
            }
        })
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(items);
    console.groupEnd();
    console.groupEnd();
    return [status, items];
});
    
// FetchProjectTeamPm
mock.onGet(new RegExp(/^\/projectteams\/fetchprojectteampm$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchProjectTeamPm");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(mockDatas);
    console.groupEnd();
    console.groupEnd();
    return [status, mockDatas ? mockDatas : []];
});

// FetchProjectTeamPm
mock.onGet(new RegExp(/^\/projectteams\/fetchprojectteampm(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchProjectTeamPm");
    console.table({url:config.url, method: config.method, data:config.data});
    if(config.url.includes('page')){
        let url = config.url.split('?')[1];
        let params  =  qs.parse(url);
        Object.assign(config, params);
    }
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    let total = mockDatas.length;
    let records: Array<any> = [];
    if(!config.page || !config.size){
        records = mockDatas;
    }else{
        if((config.page-1)*config.size < total){
          records = mockDatas.slice(config.page,config.size);
        }
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(records ?  records : []);
    console.groupEnd();
    console.groupEnd();
    return [status, records ?  records : []];
});


// FetchRowEditDefault
mock.onGet(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/fetchroweditdefault$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchRowEditDefault");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id'];
    let tempValue: any = {};
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/fetchroweditdefault$/).exec(config.url);
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    let items = mockDatas ? mockDatas : [];
    if (items.length > 0 && paramArray.length > 0) {
        paramArray.forEach((paramkey: any) => {
            if (tempValue[paramkey] && tempValue[paramkey].indexOf(";") > 0) {
                let keysGrounp: Array<any> = tempValue[paramkey].split(new RegExp(/[\;]/));
                let tempArray: Array<any> = [];
                keysGrounp.forEach((singlekey: any) => {
                    let _items =  items.filter((item: any) => { return item[paramkey] == singlekey });
                   if(_items.length >0){
                    tempArray.push(..._items);
                   }
                })
                items = tempArray;
            } else {
                items = items.filter((item: any) => { return item[paramkey] == tempValue[paramkey] });
            }
        })
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(items);
    console.groupEnd();
    console.groupEnd();
    return [status, items];
});
    
// FetchRowEditDefault
mock.onGet(new RegExp(/^\/projectteams\/fetchroweditdefault$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchRowEditDefault");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(mockDatas);
    console.groupEnd();
    console.groupEnd();
    return [status, mockDatas ? mockDatas : []];
});

// FetchRowEditDefault
mock.onGet(new RegExp(/^\/projectteams\/fetchroweditdefault(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchRowEditDefault");
    console.table({url:config.url, method: config.method, data:config.data});
    if(config.url.includes('page')){
        let url = config.url.split('?')[1];
        let params  =  qs.parse(url);
        Object.assign(config, params);
    }
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    let total = mockDatas.length;
    let records: Array<any> = [];
    if(!config.page || !config.size){
        records = mockDatas;
    }else{
        if((config.page-1)*config.size < total){
          records = mockDatas.slice(config.page,config.size);
        }
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(records ?  records : []);
    console.groupEnd();
    console.groupEnd();
    return [status, records ?  records : []];
});


// FetchTaskCntEstimateConsumedLeft
mock.onGet(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchTaskCntEstimateConsumedLeft");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id'];
    let tempValue: any = {};
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    let items = mockDatas ? mockDatas : [];
    if (items.length > 0 && paramArray.length > 0) {
        paramArray.forEach((paramkey: any) => {
            if (tempValue[paramkey] && tempValue[paramkey].indexOf(";") > 0) {
                let keysGrounp: Array<any> = tempValue[paramkey].split(new RegExp(/[\;]/));
                let tempArray: Array<any> = [];
                keysGrounp.forEach((singlekey: any) => {
                    let _items =  items.filter((item: any) => { return item[paramkey] == singlekey });
                   if(_items.length >0){
                    tempArray.push(..._items);
                   }
                })
                items = tempArray;
            } else {
                items = items.filter((item: any) => { return item[paramkey] == tempValue[paramkey] });
            }
        })
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(items);
    console.groupEnd();
    console.groupEnd();
    return [status, items];
});
    
// FetchTaskCntEstimateConsumedLeft
mock.onGet(new RegExp(/^\/projectteams\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchTaskCntEstimateConsumedLeft");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(mockDatas);
    console.groupEnd();
    console.groupEnd();
    return [status, mockDatas ? mockDatas : []];
});

// FetchTaskCntEstimateConsumedLeft
mock.onGet(new RegExp(/^\/projectteams\/fetchtaskcntestimateconsumedleft(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchTaskCntEstimateConsumedLeft");
    console.table({url:config.url, method: config.method, data:config.data});
    if(config.url.includes('page')){
        let url = config.url.split('?')[1];
        let params  =  qs.parse(url);
        Object.assign(config, params);
    }
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    let total = mockDatas.length;
    let records: Array<any> = [];
    if(!config.page || !config.size){
        records = mockDatas;
    }else{
        if((config.page-1)*config.size < total){
          records = mockDatas.slice(config.page,config.size);
        }
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(records ?  records : []);
    console.groupEnd();
    console.groupEnd();
    return [status, records ?  records : []];
});

    
// CreateTemp
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: CreateTemp");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// CreateTemp
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: CreateTemp");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// CreateTempMajor
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: CreateTempMajor");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// CreateTempMajor
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: CreateTempMajor");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// UpdateTemp
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: UpdateTemp");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// UpdateTemp
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: UpdateTemp");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// UpdateTempMajor
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: UpdateTempMajor");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// UpdateTempMajor
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: UpdateTempMajor");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// RemoveTemp
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: RemoveTemp");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// RemoveTemp
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: RemoveTemp");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// RemoveTempMajor
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: RemoveTempMajor");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// RemoveTempMajor
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: RemoveTempMajor");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// GetTemp
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: GetTemp");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// GetTemp
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: GetTemp");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// GetTempMajor
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: GetTempMajor");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// GetTempMajor
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: GetTempMajor");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// GetDraftTemp
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: GetDraftTemp");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// GetDraftTemp
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: GetDraftTemp");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// GetDraftTempMajor
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: GetDraftTempMajor");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// GetDraftTempMajor
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: GetDraftTempMajor");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// FetchTempDefault
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchTempDefault");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// FetchTempDefault
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchTempDefault");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// FetchTempProjectTeamPm
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchTempProjectTeamPm");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// FetchTempProjectTeamPm
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchTempProjectTeamPm");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// FetchTempRowEditDefault
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchTempRowEditDefault");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// FetchTempRowEditDefault
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchTempRowEditDefault");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// FetchTempTaskCntEstimateConsumedLeft
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchTempTaskCntEstimateConsumedLeft");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// FetchTempTaskCntEstimateConsumedLeft
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FetchTempTaskCntEstimateConsumedLeft");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// FilterUpdate
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterUpdate");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// FilterUpdate
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterUpdate");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// FilterSearch
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterSearch");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// FilterSearch
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterSearch");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// FilterGet
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterGet");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// FilterGet
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterGet");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// FilterCreate
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterCreate");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// FilterCreate
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterCreate");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// FilterGetDraft
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterGetDraft");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// FilterGetDraft
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterGetDraft");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// FilterRemove
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterRemove");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// FilterRemove
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterRemove");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});

    
// FilterFetch
mock.onPost(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterFetch");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table({});
    console.groupEnd();
    console.groupEnd();
    return [status, {}];
});
        
// FilterFetch
mock.onPost(new RegExp(/^\/projectteams\/?([a-zA-Z0-9\-\;]{0,35})\/fetchtaskcntestimateconsumedleft$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: FilterFetch");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})\/fetchtaskcntestimateconsumedleft$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    //let items = mockDatas ? mockDatas : [];
    //let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
      let data = JSON.parse(config.data);
    mockDatas.forEach((item)=>{
        if(item['id'] == tempValue['id'] ){
            for(let value in data){
              if(item.hasOwnProperty(value)){
                  item[value] = data[value];
              }
            }
        }
    })
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(data);
    console.groupEnd();
    console.groupEnd();
    return [status, data];
});


// Remove
mock.onDelete(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Remove");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    let items = mockDatas ? mockDatas : [];
    let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(_items?_items:{});
    console.groupEnd();
    console.groupEnd();
    return [status, _items?_items:{}];
});

// Remove
mock.onDelete(new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Remove");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    let items = mockDatas ? mockDatas : [];
    let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(_items?_items:{});
    console.groupEnd();
    console.groupEnd();
    return [status, _items?_items:{}];
});

// Get
mock.onGet(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Get");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id','id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    let items = mockDatas ? mockDatas : [];
    let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(_items?_items:{});
    console.groupEnd();
    console.groupEnd();
    return [status, _items?_items:{}];
});

// Get
mock.onGet(new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:projectteam 方法: Get");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projectteams\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
    let tempValue: any = {};
    if(matchArray && matchArray.length >1 && paramArray && paramArray.length >0){
        paramArray.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
    }
    let items = mockDatas ? mockDatas : [];
    let _items = items.find((item: any) => Object.is(item.id, tempValue.id));
    console.groupCollapsed("response数据  status: "+status+" data: ");
    console.table(_items?_items:{});
    console.groupEnd();
    console.groupEnd();
    return [status, _items?_items:{}];
});
