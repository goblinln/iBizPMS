import Task_zh_CN_Base from './task_zh_CN_base';

function getLocaleResource(){
    const Task_zh_CN_OwnData = {};
    const targetData = Object.assign(Task_zh_CN_Base(), Task_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;