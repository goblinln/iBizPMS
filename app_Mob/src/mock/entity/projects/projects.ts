import qs from 'qs';
import { MockAdapter } from '@/mock/mock-adapter';
const mock = MockAdapter.getInstance();

// 模拟数据
const mockDatas: Array<any> = [
];


//getwflink
mock.onGet(new RegExp(/^\/wfcore\/pms-app-mob\/projects\/[a-zA-Z0-9\-\;]+\/usertasks\/[a-zA-Z0-9\-\;]+\/ways$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: getwflink");
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
mock.onGet(new RegExp(/^\/wfcore\/pms-app-mob\/projects\/process-definitions-nodes$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: getwfstep");
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
mock.onPost(new RegExp(/^\/projects\/batch$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: createBatch");
    console.table({url:config.url, method: config.method, data:config.data});
    console.groupEnd();
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, {}];
    }
    return [status, {}];
});

// updateBatch
mock.onPut(new RegExp(/^\/projects\/batch$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: updateBatch");
    console.table({url:config.url, method: config.method, data:config.data});
    console.groupEnd();
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, {}];
    }
    return [status, {}];
});

// removeBatch
mock.onDelete(new RegExp(/^\/projects\/batch$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: removeBatch");
    console.table({url:config.url, method: config.method, data:config.data});
    console.groupEnd();
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, {}];
    }
    return [status, {}];
});


// Select
mock.onGet(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/select$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: Select");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/select$/).exec(config.url);
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
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: Create");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
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
mock.onPut(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: Update");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
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
mock.onGet(new RegExp(/^\/projects\/getdraft$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: GetDraft");
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
        
// Activate
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/activate$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: Activate");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/activate$/).exec(config.url);
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
        
// BatchUnlinkStory
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/batchunlinkstory$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: BatchUnlinkStory");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/batchunlinkstory$/).exec(config.url);
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
        
// CancelProjectTop
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/cancelprojecttop$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: CancelProjectTop");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/cancelprojecttop$/).exec(config.url);
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
        
// CheckKey
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/checkkey$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: CheckKey");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/checkkey$/).exec(config.url);
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
        
// Close
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/close$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: Close");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/close$/).exec(config.url);
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
        
// LinkStory
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/linkstory$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: LinkStory");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/linkstory$/).exec(config.url);
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
        
// ManageMembers
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/managemembers$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: ManageMembers");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/managemembers$/).exec(config.url);
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
        
// MobProjectCount
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/mobprojectcount$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: MobProjectCount");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/mobprojectcount$/).exec(config.url);
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
        
// PmsEeProjectAllTaskCount
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/pmseeprojectalltaskcount$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: PmsEeProjectAllTaskCount");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/pmseeprojectalltaskcount$/).exec(config.url);
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
        
// PmsEeProjectTodoTaskCount
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/pmseeprojecttodotaskcount$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: PmsEeProjectTodoTaskCount");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/pmseeprojecttodotaskcount$/).exec(config.url);
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
        
// ProjectTaskQCnt
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/projecttaskqcnt$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: ProjectTaskQCnt");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projecttaskqcnt$/).exec(config.url);
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
        
// ProjectTop
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/projecttop$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: ProjectTop");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/projecttop$/).exec(config.url);
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
        
// Putoff
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/putoff$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: Putoff");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/putoff$/).exec(config.url);
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
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/save$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: Save");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/save$/).exec(config.url);
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
        
// Start
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/start$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: Start");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/start$/).exec(config.url);
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
        
// Suspend
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/suspend$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: Suspend");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/suspend$/).exec(config.url);
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
        
// UnlinkMember
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/unlinkmember$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: UnlinkMember");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/unlinkmember$/).exec(config.url);
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
        
// UnlinkStory
mock.onPost(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/unlinkstory$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: UnlinkStory");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/unlinkstory$/).exec(config.url);
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
        
// UpdateOrder
mock.onPut(new RegExp(/^\/projects\/?([a-zA-Z0-9\-\;]{0,35})\/updateorder$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: UpdateOrder");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})\/updateorder$/).exec(config.url);
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
    
// FetchBugProject
mock.onGet(new RegExp(/^\/projects\/fetchbugproject$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchBugProject");
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

// FetchBugProject
mock.onGet(new RegExp(/^\/projects\/fetchbugproject(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchBugProject");
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
    
// FetchCurPlanProject
mock.onGet(new RegExp(/^\/projects\/fetchcurplanproject$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchCurPlanProject");
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

// FetchCurPlanProject
mock.onGet(new RegExp(/^\/projects\/fetchcurplanproject(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchCurPlanProject");
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
    
// FetchCurProduct
mock.onGet(new RegExp(/^\/projects\/fetchcurproduct$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchCurProduct");
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

// FetchCurProduct
mock.onGet(new RegExp(/^\/projects\/fetchcurproduct(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchCurProduct");
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
    
// FetchCurUser
mock.onGet(new RegExp(/^\/projects\/fetchcuruser$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchCurUser");
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

// FetchCurUser
mock.onGet(new RegExp(/^\/projects\/fetchcuruser(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchCurUser");
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
    
// FetchCurUserSa
mock.onGet(new RegExp(/^\/projects\/fetchcurusersa$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchCurUserSa");
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

// FetchCurUserSa
mock.onGet(new RegExp(/^\/projects\/fetchcurusersa(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchCurUserSa");
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
    
// FetchCurUserTodo
mock.onGet(new RegExp(/^\/projects\/fetchcurusertodo$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchCurUserTodo");
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

// FetchCurUserTodo
mock.onGet(new RegExp(/^\/projects\/fetchcurusertodo(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchCurUserTodo");
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
mock.onGet(new RegExp(/^\/projects\/fetchdefault$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchDefault");
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
mock.onGet(new RegExp(/^\/projects\/fetchdefault(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchDefault");
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
    
// FetchESBulk
mock.onGet(new RegExp(/^\/projects\/fetchesbulk$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchESBulk");
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

// FetchESBulk
mock.onGet(new RegExp(/^\/projects\/fetchesbulk(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchESBulk");
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
    
// FetchInvolvedProject
mock.onGet(new RegExp(/^\/projects\/fetchinvolvedproject$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchInvolvedProject");
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

// FetchInvolvedProject
mock.onGet(new RegExp(/^\/projects\/fetchinvolvedproject(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchInvolvedProject");
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
    
// FetchInvolvedProject_StoryTaskBug
mock.onGet(new RegExp(/^\/projects\/fetchinvolvedproject_storytaskbug$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchInvolvedProject_StoryTaskBug");
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

// FetchInvolvedProject_StoryTaskBug
mock.onGet(new RegExp(/^\/projects\/fetchinvolvedproject_storytaskbug(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchInvolvedProject_StoryTaskBug");
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
    
// FetchMyProject
mock.onGet(new RegExp(/^\/projects\/fetchmyproject$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchMyProject");
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

// FetchMyProject
mock.onGet(new RegExp(/^\/projects\/fetchmyproject(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchMyProject");
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
    
// FetchProjectTeam
mock.onGet(new RegExp(/^\/projects\/fetchprojectteam$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchProjectTeam");
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

// FetchProjectTeam
mock.onGet(new RegExp(/^\/projects\/fetchprojectteam(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchProjectTeam");
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
    
// FetchStoryProject
mock.onGet(new RegExp(/^\/projects\/fetchstoryproject$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchStoryProject");
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

// FetchStoryProject
mock.onGet(new RegExp(/^\/projects\/fetchstoryproject(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchStoryProject");
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
    
// FetchUnDoneProject
mock.onGet(new RegExp(/^\/projects\/fetchundoneproject$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchUnDoneProject");
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

// FetchUnDoneProject
mock.onGet(new RegExp(/^\/projects\/fetchundoneproject(\?[\w-./?%&=,]*)*$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: FetchUnDoneProject");
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
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现
// URI参数传递情况未实现


// Remove
mock.onDelete(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: Remove");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
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
mock.onGet(new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})$/)).reply((config: any) => {
    console.groupCollapsed("实体:project 方法: Get");
    console.table({url:config.url, method: config.method, data:config.data});
    let status = MockAdapter.mockStatus(config);
    if (status !== 200) {
        return [status, null];
    }    
    const paramArray:Array<any> = ['id'];
    const matchArray:any = new RegExp(/^\/projects\/([a-zA-Z0-9\-\;]{1,35})$/).exec(config.url);
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
