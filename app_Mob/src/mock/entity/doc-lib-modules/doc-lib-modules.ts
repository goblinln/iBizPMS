import qs from 'qs';
import { MockAdapter } from '@/mock/mock-adapter';
const mock = MockAdapter.getInstance();

// 模拟数据
const mockDatas: Array<any> = [
];


//getwflink
mock.onGet(new RegExp(/^\/wfcore\/ibizpms-app-mob\/doclibmodules\/[a-zA-Z0-9\-\;]+\/usertasks\/[a-zA-Z0-9\-\;]+\/ways$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: getwflink");
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
mock.onGet(new RegExp(/^\/wfcore\/ibizpms-app-mob\/doclibmodules\/process-definitions-nodes$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: getwfstep");
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
mock.onPost(new RegExp(/^\/doclibmodules\/batch$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: createBatch");
    console.table({url:config.url, method: config.method, data:config.data});
    console.groupEnd();
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, {}];
    }
    return [status, {}];
});

// updateBatch
mock.onPut(new RegExp(/^\/doclibmodules\/batch$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: updateBatch");
    console.table({url:config.url, method: config.method, data:config.data});
    console.groupEnd();
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, {}];
    }
    return [status, {}];
});

// removeBatch
mock.onDelete(new RegExp(/^\/doclibmodules\/batch$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: removeBatch");
    console.table({url:config.url, method: config.method, data:config.data});
    console.groupEnd();
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, {}];
    }
    return [status, {}];
});


// Select
mock.onGet(new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/select$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: Select");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/select$/).exec(config.url);
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
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: Create");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
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
mock.onPut(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: Update");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
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
mock.onGet(new RegExp(/^\/doclibmodules\/getdraft$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: GetDraft");
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
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/checkkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: CheckKey");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/checkkey$/).exec(config.url);
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
        
// Collect
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/collect$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: Collect");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/collect$/).exec(config.url);
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
        
// DocLibModuleNFavorite
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/doclibmodulenfavorite$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: DocLibModuleNFavorite");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/doclibmodulenfavorite$/).exec(config.url);
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
        
// DoclibModuleFavorite
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/doclibmodulefavorite$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: DoclibModuleFavorite");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/doclibmodulefavorite$/).exec(config.url);
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
        
// Fix
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/fix$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: Fix");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/fix$/).exec(config.url);
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
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/save$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: Save");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/save$/).exec(config.url);
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
        
// UnCollect
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/uncollect$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: UnCollect");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/uncollect$/).exec(config.url);
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
    
// FetchAllDocLibModule_Custom
mock.onGet(new RegExp(/^\/doclibmodules\/fetchalldoclibmodule_custom$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchAllDocLibModule_Custom");
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

// FetchAllDocLibModule_Custom
mock.onGet(new RegExp(/^\/doclibmodules\/fetchalldoclibmodule_custom(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchAllDocLibModule_Custom");
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
    
// FetchAllDoclibModule
mock.onGet(new RegExp(/^\/doclibmodules\/fetchalldoclibmodule$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchAllDoclibModule");
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

// FetchAllDoclibModule
mock.onGet(new RegExp(/^\/doclibmodules\/fetchalldoclibmodule(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchAllDoclibModule");
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
    
// FetchChildModuleByParent
mock.onGet(new RegExp(/^\/doclibmodules\/fetchchildmodulebyparent$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchChildModuleByParent");
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

// FetchChildModuleByParent
mock.onGet(new RegExp(/^\/doclibmodules\/fetchchildmodulebyparent(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchChildModuleByParent");
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
    
// FetchChildModuleByRealParent
mock.onGet(new RegExp(/^\/doclibmodules\/fetchchildmodulebyrealparent$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchChildModuleByRealParent");
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

// FetchChildModuleByRealParent
mock.onGet(new RegExp(/^\/doclibmodules\/fetchchildmodulebyrealparent(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchChildModuleByRealParent");
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
    
// FetchDefault
mock.onGet(new RegExp(/^\/doclibmodules\/fetchdefault$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchDefault");
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
mock.onGet(new RegExp(/^\/doclibmodules\/fetchdefault(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchDefault");
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
    
// FetchMyFavourites
mock.onGet(new RegExp(/^\/doclibmodules\/fetchmyfavourites$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchMyFavourites");
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

// FetchMyFavourites
mock.onGet(new RegExp(/^\/doclibmodules\/fetchmyfavourites(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchMyFavourites");
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
    
// FetchParentModule
mock.onGet(new RegExp(/^\/doclibmodules\/fetchparentmodule$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchParentModule");
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

// FetchParentModule
mock.onGet(new RegExp(/^\/doclibmodules\/fetchparentmodule(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchParentModule");
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
    
// FetchRootModuleMuLu
mock.onGet(new RegExp(/^\/doclibmodules\/fetchrootmodulemulu$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchRootModuleMuLu");
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

// FetchRootModuleMuLu
mock.onGet(new RegExp(/^\/doclibmodules\/fetchrootmodulemulu(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchRootModuleMuLu");
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
    
// FetchRootModuleMuLuByRoot
mock.onGet(new RegExp(/^\/doclibmodules\/fetchrootmodulemulubyroot$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchRootModuleMuLuByRoot");
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

// FetchRootModuleMuLuByRoot
mock.onGet(new RegExp(/^\/doclibmodules\/fetchrootmodulemulubyroot(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchRootModuleMuLuByRoot");
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
    
// FetchRootModuleMuLuBysrfparentkey
mock.onGet(new RegExp(/^\/doclibmodules\/fetchrootmodulemulubysrfparentkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchRootModuleMuLuBysrfparentkey");
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

// FetchRootModuleMuLuBysrfparentkey
mock.onGet(new RegExp(/^\/doclibmodules\/fetchrootmodulemulubysrfparentkey(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FetchRootModuleMuLuBysrfparentkey");
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
        
// FilterUpdate
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/fetchrootmodulemulubysrfparentkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FilterUpdate");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/fetchrootmodulemulubysrfparentkey$/).exec(config.url);
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
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/fetchrootmodulemulubysrfparentkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FilterSearch");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/fetchrootmodulemulubysrfparentkey$/).exec(config.url);
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
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/fetchrootmodulemulubysrfparentkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FilterGet");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/fetchrootmodulemulubysrfparentkey$/).exec(config.url);
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
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/fetchrootmodulemulubysrfparentkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FilterCreate");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/fetchrootmodulemulubysrfparentkey$/).exec(config.url);
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
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/fetchrootmodulemulubysrfparentkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FilterGetDraft");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/fetchrootmodulemulubysrfparentkey$/).exec(config.url);
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
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/fetchrootmodulemulubysrfparentkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FilterRemove");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/fetchrootmodulemulubysrfparentkey$/).exec(config.url);
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
mock.onPost(new RegExp(/^\/doclibmodules\/?([a-zA-Z0-9\-\;]{0,35})\/fetchrootmodulemulubysrfparentkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: FilterFetch");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})\/fetchrootmodulemulubysrfparentkey$/).exec(config.url);
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
mock.onDelete(new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: Remove");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
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
mock.onGet(new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:doclibmodule 方法: Get");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/doclibmodules\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
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
