import TaskEstimate_zh_CN_Base from './task-estimate_zh_CN_base';

function getLocaleResource(){
    const TaskEstimate_zh_CN_OwnData = {};
    const targetData = Object.assign(TaskEstimate_zh_CN_Base(), TaskEstimate_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;