import TaskStats_zh_CN_Base from './task-stats_zh_CN_base';

function getLocaleResource(){
    const TaskStats_zh_CN_OwnData = {};
    const targetData = Object.assign(TaskStats_zh_CN_Base(), TaskStats_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;