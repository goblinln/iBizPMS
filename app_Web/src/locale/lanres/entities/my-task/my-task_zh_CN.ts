import MyTask_zh_CN_Base from './my-task_zh_CN_base';

function getLocaleResource(){
    const MyTask_zh_CN_OwnData = {};
    const targetData = Object.assign(MyTask_zh_CN_Base(), MyTask_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;